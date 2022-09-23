# Bellman-Ford algorithm

- calculates shortest distance from all nodes to a single selected node (same as dijkstra's algorithm)
- not a greedy algorithm
- it supports negative weights

- initialize all nodes weight to infinity distance, the source node is set to ZERO distance
- loop through all nodes, and update the distances 
  (remember to add the distance of the current node to source, plus the distance from the current 
   node to the neighbor that we are trying to update the distance)
