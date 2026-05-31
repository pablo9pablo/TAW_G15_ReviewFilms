#include "Graph.h"

#include <algorithm>
#include <stdexcept>

Graph::Graph(std::size_t vertexCount)
    : adjacency_(vertexCount, std::vector<bool>(vertexCount, false)) {}

Graph::Vertex Graph::addVertex() {
    const Vertex newVertex = adjacency_.size();
    for (auto& row : adjacency_) {
        row.push_back(false);
    }
    adjacency_.push_back(std::vector<bool>(newVertex + 1, false));
    return newVertex;
}

bool Graph::removeVertex(Vertex vertex) {
    if (!isValid(vertex) || degree(vertex) != 0) {
        return false;
    }

    adjacency_.erase(adjacency_.begin() + static_cast<std::ptrdiff_t>(vertex));
    for (auto& row : adjacency_) {
        row.erase(row.begin() + static_cast<std::ptrdiff_t>(vertex));
    }
    return true;
}

bool Graph::addEdge(Vertex first, Vertex second) {
    if (!isValid(first) || !isValid(second) || first == second || adjacency_[first][second]) {
        return false;
    }

    adjacency_[first][second] = true;
    adjacency_[second][first] = true;
    ++edgeCount_;
    return true;
}

bool Graph::removeEdge(Vertex first, Vertex second) {
    if (!isValid(first) || !isValid(second) || !adjacency_[first][second]) {
        return false;
    }

    adjacency_[first][second] = false;
    adjacency_[second][first] = false;
    --edgeCount_;
    return true;
}

std::size_t Graph::order() const {
    return adjacency_.size();
}

std::size_t Graph::size() const {
    return edgeCount_;
}

std::size_t Graph::degree(Vertex vertex) const {
    if (!isValid(vertex)) {
        throw std::out_of_range("Invalid vertex");
    }

    return static_cast<std::size_t>(std::count(adjacency_[vertex].begin(), adjacency_[vertex].end(), true));
}

std::vector<Graph::Vertex> Graph::neighbours(Vertex vertex) const {
    if (!isValid(vertex)) {
        throw std::out_of_range("Invalid vertex");
    }

    std::vector<Vertex> result;
    result.reserve(degree(vertex));
    for (Vertex candidate = 0; candidate < adjacency_.size(); ++candidate) {
        if (adjacency_[vertex][candidate]) {
            result.push_back(candidate);
        }
    }
    return result;
}

bool Graph::hasEdge(Vertex first, Vertex second) const {
    if (!isValid(first) || !isValid(second)) {
        return false;
    }
    return adjacency_[first][second];
}

bool Graph::isValid(Vertex vertex) const {
    return vertex < adjacency_.size();
}
