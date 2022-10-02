# this was not tested

def NodeBH(node: Node) -> int: # type hinting, in python 3.5
    # Base case
    if node.left == None and node.right == None: # leaf 
        return 2 if node.color == BLACK
        return 1 if node.color == RED

    leftBH = NodeBH(node.left)
    rightBH = NodeBH(node.right)

    # Invalid tree 
    if leftBH != rightBH: 
        raise Exception("Invalid tree")
        return 

    return leftBH + 1 if node.color == BLACK
    return leftBH + 0 if node.color == RED
    

        
bh = NodeBH(node) # raises exception if invalid!
