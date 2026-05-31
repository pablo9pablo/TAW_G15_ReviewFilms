#ifndef HOMEWORK5_HEURISTIC_MAXIMUM_INDEPENDENT_SET_H
#define HOMEWORK5_HEURISTIC_MAXIMUM_INDEPENDENT_SET_H

#include "Graph.h"

#include <vector>

std::vector<Graph::Vertex> maximumIndependentSetHeuristic(const Graph& graph);
std::vector<Graph::Vertex> findApproximateIndependentSet(const Graph& graph);

#endif
