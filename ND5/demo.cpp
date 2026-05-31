#include "exact/Graph.h"
#include "exact/MaximumIndependentSet.h"

#include <chrono>
#include <iomanip>
#include <iostream>
#include <string>
#include <vector>

std::vector<Graph::Vertex> maximumIndependentSetHeuristic(const Graph& graph);

namespace {
Graph cycle(std::size_t n) {
    Graph graph(n);
    for (std::size_t i = 0; i < n; ++i) {
        graph.addEdge(i, (i + 1) % n);
    }
    return graph;
}

Graph twoCliquesWithBridge() {
    Graph graph(10);
    for (std::size_t i = 0; i < 5; ++i) {
        for (std::size_t j = i + 1; j < 5; ++j) {
            graph.addEdge(i, j);
        }
    }
    for (std::size_t i = 5; i < 10; ++i) {
        for (std::size_t j = i + 1; j < 10; ++j) {
            graph.addEdge(i, j);
        }
    }
    graph.addEdge(4, 5);
    return graph;
}

Graph sparseSample() {
    Graph graph(12);
    const std::vector<std::pair<std::size_t, std::size_t>> edges = {
        {0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7},
        {7, 8}, {8, 9}, {9, 10}, {10, 11}, {1, 8}, {2, 9}, {4, 10}
    };
    for (const auto& [first, second] : edges) {
        graph.addEdge(first, second);
    }
    return graph;
}

template <typename Function>
auto measure(Function function) {
    const auto start = std::chrono::steady_clock::now();
    auto result = function();
    const auto end = std::chrono::steady_clock::now();
    const auto micros = std::chrono::duration_cast<std::chrono::microseconds>(end - start).count();
    return std::pair{result, micros};
}

void printRow(const std::string& name, const Graph& graph) {
    const auto [exact, exactMicros] = measure([&] { return maximumIndependentSetExact(graph); });
    const auto [heuristic, heuristicMicros] = measure([&] { return maximumIndependentSetHeuristic(graph); });
    const double precision = exact.empty() ? 100.0 : (100.0 * heuristic.size() / exact.size());

    std::cout << std::left << std::setw(24) << name
              << std::right << std::setw(8) << graph.order()
              << std::setw(8) << graph.size()
              << std::setw(12) << exact.size()
              << std::setw(14) << exactMicros
              << std::setw(14) << heuristic.size()
              << std::setw(16) << heuristicMicros
              << std::setw(12) << std::fixed << std::setprecision(1) << precision << "%\n";
}
}

int main() {
    std::cout << std::left << std::setw(24) << "Graph"
              << std::right << std::setw(8) << "|V|"
              << std::setw(8) << "|E|"
              << std::setw(12) << "Exact"
              << std::setw(14) << "Exact us"
              << std::setw(14) << "Heuristic"
              << std::setw(16) << "Heuristic us"
              << std::setw(12) << "Precision" << '\n';
    std::cout << std::string(108, '-') << '\n';

    printRow("Cycle C9", cycle(9));
    printRow("Two cliques", twoCliquesWithBridge());
    printRow("Sparse sample", sparseSample());
}
