#include "MaximumIndependentSet.h"

#include <algorithm>
#include <cstdint>
#include <functional>
#include <numeric>
#include <stdexcept>

namespace {
using Mask = std::uint64_t;

std::vector<Graph::Vertex> maskToVertices(Mask mask, std::size_t vertexCount) {
    std::vector<Graph::Vertex> vertices;
    for (Graph::Vertex vertex = 0; vertex < vertexCount; ++vertex) {
        if ((mask & (Mask{1} << vertex)) != 0) {
            vertices.push_back(vertex);
        }
    }
    return vertices;
}

int greedyCliqueCoverBound(const std::vector<Graph::Vertex>& candidates, const std::vector<Mask>& neighbourMasks) {
    std::vector<Mask> cliqueClasses;
    for (Graph::Vertex vertex : candidates) {
        bool placed = false;
        for (Mask& cliqueClass : cliqueClasses) {
            // A maximum independent set can take at most one vertex from each
            // clique, so any clique cover gives a valid upper bound.
            if ((neighbourMasks[vertex] & cliqueClass) == cliqueClass) {
                cliqueClass |= Mask{1} << vertex;
                placed = true;
                break;
            }
        }
        if (!placed) {
            cliqueClasses.push_back(Mask{1} << vertex);
        }
    }
    return static_cast<int>(cliqueClasses.size());
}
}

std::vector<Graph::Vertex> maximumIndependentSetExact(const Graph& graph) {
    const std::size_t vertexCount = graph.order();
    if (vertexCount == 0) {
        return {};
    }

    // The bit-mask implementation is deliberately limited to small/medium graphs,
    // which matches an exact branch-and-bound enumeration assignment.
    if (vertexCount > 63) {
        throw std::invalid_argument("Exact implementation supports at most 63 vertices");
    }

    std::vector<Mask> neighbourMasks(vertexCount, 0);
    for (Graph::Vertex vertex = 0; vertex < vertexCount; ++vertex) {
        for (Graph::Vertex neighbour : graph.neighbours(vertex)) {
            neighbourMasks[vertex] |= Mask{1} << neighbour;
        }
    }

    std::vector<Graph::Vertex> order(vertexCount);
    std::iota(order.begin(), order.end(), 0);
    std::sort(order.begin(), order.end(), [&graph](Graph::Vertex left, Graph::Vertex right) {
        return graph.degree(left) < graph.degree(right);
    });

    Mask bestMask = 0;
    int bestSize = 0;

    std::function<void(std::vector<Graph::Vertex>, Mask, int)> search =
        [&](std::vector<Graph::Vertex> candidates, Mask currentMask, int currentSize) {
            if (candidates.empty()) {
                if (currentSize > bestSize) {
                    bestSize = currentSize;
                    bestMask = currentMask;
                }
                return;
            }

            if (currentSize + static_cast<int>(candidates.size()) <= bestSize) {
                return;
            }
            if (currentSize + greedyCliqueCoverBound(candidates, neighbourMasks) <= bestSize) {
                return;
            }

            auto chosenIt = std::min_element(candidates.begin(), candidates.end(), [&graph](Graph::Vertex left, Graph::Vertex right) {
                return graph.degree(left) < graph.degree(right);
            });
            const Graph::Vertex chosen = *chosenIt;

            std::vector<Graph::Vertex> includeCandidates;
            includeCandidates.reserve(candidates.size());
            for (Graph::Vertex candidate : candidates) {
                if (candidate != chosen && !graph.hasEdge(chosen, candidate)) {
                    includeCandidates.push_back(candidate);
                }
            }
            search(includeCandidates, currentMask | (Mask{1} << chosen), currentSize + 1);

            candidates.erase(std::remove(candidates.begin(), candidates.end(), chosen), candidates.end());
            search(candidates, currentMask, currentSize);
        };

    search(order, 0, 0);
    return maskToVertices(bestMask, vertexCount);
}

std::vector<Graph::Vertex> findMaximumIndependentSet(const Graph& graph) {
    return maximumIndependentSetExact(graph);
}
