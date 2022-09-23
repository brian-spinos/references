# Dijkstra's algorithm

- greedy algorithm
- calculates shortest path from all nodes to a source node

- 1. have a distance array, initialize the source's distance to ZERO and the others to infinity
    - 2. update the distances to the neighbors of the source node (make sure to add the distance to get to the current node)
    - 3. pick the node with the smallest distance from the source, and mark the source node as visited
    - 4. repeat step 2, but with the current node, instead of the source node.
