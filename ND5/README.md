# ND5 - Fifth Homework - Maximum Independent Set

This submission solves variation **D: Finding the maximum independent set** for a simple, unweighted, undirected and unlabelled graph.

## Structure

- `exact/`: exact branch-and-bound algorithm. It still enumerates possibilities, but prunes branches that cannot beat the best solution found so far.
- `heuristic/`: faster approximate algorithm. It greedily builds independent sets from degree-based orders and applies a small 2-for-1 local improvement.

Only these two subfolders are used, matching the required Drive format: `ND5/exact` and `ND5/heuristic`. The optional comparison program is kept as `ND5/demo.cpp` so it does not create an extra folder.

## Public functions

- Exact: `std::vector<Graph::Vertex> maximumIndependentSetExact(const Graph& graph)`
- Exact adapter alias: `std::vector<Graph::Vertex> findMaximumIndependentSet(const Graph& graph)`
- Heuristic: `std::vector<Graph::Vertex> maximumIndependentSetHeuristic(const Graph& graph)`
- Heuristic adapter alias: `std::vector<Graph::Vertex> findApproximateIndependentSet(const Graph& graph)`

Each folder contains a `Graph.h` and `Graph.cpp` ADT with the required operations:

- add/remove isolated vertices;
- add/remove edges;
- return graph order and size;
- return vertex degree and neighbours;
- test whether an edge exists.

## Build examples

```bash
# Exact object files
g++ -std=c++17 -O2 -Wall -Wextra -pedantic -c ND5/exact/Graph.cpp ND5/exact/MaximumIndependentSet.cpp

# Heuristic object files
g++ -std=c++17 -O2 -Wall -Wextra -pedantic -c ND5/heuristic/Graph.cpp ND5/heuristic/MaximumIndependentSetHeuristic.cpp

# Demo comparison
g++ -std=c++17 -O2 -Wall -Wextra -pedantic \
  ND5/demo.cpp \
  ND5/exact/Graph.cpp \
  ND5/exact/MaximumIndependentSet.cpp \
  ND5/heuristic/MaximumIndependentSetHeuristic.cpp \
  -o /tmp/nd5_demo
/tmp/nd5_demo
```
