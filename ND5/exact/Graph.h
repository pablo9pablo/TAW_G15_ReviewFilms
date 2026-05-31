#ifndef HOMEWORK5_EXACT_GRAPH_H
#define HOMEWORK5_EXACT_GRAPH_H

#include <cstddef>
#include <vector>

class Graph {
public:
    using Vertex = std::size_t;

    Graph() = default;
    explicit Graph(std::size_t vertexCount);

    Vertex addVertex();
    bool removeVertex(Vertex vertex);

    bool addEdge(Vertex first, Vertex second);
    bool removeEdge(Vertex first, Vertex second);

    std::size_t order() const;
    std::size_t size() const;

    std::size_t degree(Vertex vertex) const;
    std::vector<Vertex> neighbours(Vertex vertex) const;
    bool hasEdge(Vertex first, Vertex second) const;

private:
    bool isValid(Vertex vertex) const;

    std::vector<std::vector<bool>> adjacency_;
    std::size_t edgeCount_ = 0;
};

#endif
