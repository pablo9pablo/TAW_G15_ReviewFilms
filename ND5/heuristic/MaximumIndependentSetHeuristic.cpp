#include "MaximumIndependentSetHeuristic.h"

#include <algorithm>
#include <numeric>

namespace {
bool isIndependentWith(const Graph& graph, const std::vector<Graph::Vertex>& current, Graph::Vertex candidate) {
    return std::none_of(current.begin(), current.end(), [&graph, candidate](Graph::Vertex selected) {
        return graph.hasEdge(selected, candidate);
    });
}

std::vector<Graph::Vertex> greedyFromOrder(const Graph& graph, std::vector<Graph::Vertex> order) {
    std::vector<Graph::Vertex> independentSet;
    independentSet.reserve(graph.order());

    for (Graph::Vertex vertex : order) {
        if (isIndependentWith(graph, independentSet, vertex)) {
            independentSet.push_back(vertex);
        }
    }
    std::sort(independentSet.begin(), independentSet.end());
    return independentSet;
}

void improveByTwoForOne(const Graph& graph, std::vector<Graph::Vertex>& solution) {
    bool changed = true;
    while (changed) {
        changed = false;
        std::vector<bool> selected(graph.order(), false);
        for (Graph::Vertex vertex : solution) {
            selected[vertex] = true;
        }

        for (auto removeIt = solution.begin(); removeIt != solution.end() && !changed; ++removeIt) {
            const Graph::Vertex removed = *removeIt;
            std::vector<Graph::Vertex> base = solution;
            base.erase(std::remove(base.begin(), base.end(), removed), base.end());

            std::vector<Graph::Vertex> addable;
            for (Graph::Vertex candidate = 0; candidate < graph.order(); ++candidate) {
                if (!selected[candidate] && isIndependentWith(graph, base, candidate)) {
                    addable.push_back(candidate);
                }
            }

            for (std::size_t i = 0; i < addable.size() && !changed; ++i) {
                for (std::size_t j = i + 1; j < addable.size(); ++j) {
                    if (!graph.hasEdge(addable[i], addable[j])) {
                        base.push_back(addable[i]);
                        base.push_back(addable[j]);
                        std::sort(base.begin(), base.end());
                        solution = base;
                        changed = true;
                        break;
                    }
                }
            }
        }
    }
}
}

std::vector<Graph::Vertex> maximumIndependentSetHeuristic(const Graph& graph) {
    std::vector<Graph::Vertex> ascendingDegree(graph.order());
    std::iota(ascendingDegree.begin(), ascendingDegree.end(), 0);
    std::sort(ascendingDegree.begin(), ascendingDegree.end(), [&graph](Graph::Vertex left, Graph::Vertex right) {
        if (graph.degree(left) == graph.degree(right)) {
            return left < right;
        }
        return graph.degree(left) < graph.degree(right);
    });

    auto best = greedyFromOrder(graph, ascendingDegree);

    std::vector<Graph::Vertex> reverseOrder = ascendingDegree;
    std::reverse(reverseOrder.begin(), reverseOrder.end());
    auto alternative = greedyFromOrder(graph, reverseOrder);
    if (alternative.size() > best.size()) {
        best = alternative;
    }

    improveByTwoForOne(graph, best);
    return best;
}

std::vector<Graph::Vertex> findApproximateIndependentSet(const Graph& graph) {
    return maximumIndependentSetHeuristic(graph);
}
