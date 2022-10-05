// ES6 red-black tree


// reference:
// http://www.codebytes.in/2014/10/red-black-tree-java-implementation.html?m=1


/**
 * TODO:
 * - check if tests are correct --> https://www.youtube.com/watch?v=PhY56LpCtP4
 * - do a console.log for the different cases: case #1, case #2, ...
 * - create a CLI for user input
 * - make sure `nil` is FINAL
*/
class Node {
    constructor(){
        this.key = null;
        this.color = null;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    toString(){
        return `Node { key: ${this.key}, color: ${this.getColor()}, parent: ${this.hasParent()}, left: ${this.hasLeft()}, right: ${this.hasRight()} }`;
    }

    getColor(){
        return this.color == 0 ? 'RED' : 'BLACK';
    }

    hasParent(){
        if(this.parent){
            if(this.parent.key){
              return this.parent.key
            }
            return '*NO_KEY*';
        }

        return null;
    }

    hasLeft(){
        if(this.left){
            if(this.left.key){
              return this.left.key
            }
            return '*NO_KEY*';
        }

        return null;
    }

    hasRight(){
        if(this.right){
            if(this.right.key){
              return this.right.key
            }
            return '*NO_KEY*';
        }

        return null;
    }
}

class RBT {
    constructor(){
        this.RED = 0;
        this.BLACK = 1;
        this.nil = this.getEmptyNode();
        this.root = this.nil; // starts out as sentinel
    }

    /**
     * sentinel nodes are BLACK
     */
    getEmptyNode(){
        let node = new Node();
        node.key = -1;
        node.color = this.BLACK;
        return node;
    }

    /**
     * new nodes are RED
     */
    createNewNode(key){
        let node = this.getEmptyNode();
        node.key = key;
        node.color = this.RED;
        this.parent = this.nil;
        this.left = this.nil;
        this.right = this.nil;
        return node;
    }

    insert(key) {
        let node = this.createNewNode(key);
        let temp = this.root; // root will go down
        if (this.root == this.nil) {
            this.root = node;
            node.color = this.BLACK;
            node.parent = this.nil;
            node.left = this.nil;
            node.right = this.nil;
        } else {
            node.parent = this.nil;
            node.left = this.nil;
            node.right = this.nil;
            node.color = this.RED;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == this.nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.right == this.nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            this.fixTree(node);
        }
    }

    printTree(node) {
        if (node == this.nil) {
            return;
        }
        this.printTree(node.left);
        // console.log(((node.color==this.RED)?"Color: Red ":"Color: Black ")+"Key: "+node.key+" Parent: "+node.parent.key+"\n");
        console.log(node.toString());
        this.printTree(node.right);
    }

    /**
     * Takes as argument the newly inserted node
     */
    fixTree(node) {
        while (node.parent.color == this.RED) {           // while parent is RED
            let uncle = this.nil;
            if (node.parent == node.parent.parent.left) { // parent is left child
                uncle = node.parent.parent.right;

                if (uncle != this.nil && uncle.color == this.RED) { // uncle is red
                    node.parent.color = this.BLACK;
                    uncle.color = this.BLACK;
                    node.parent.parent.color = this.RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.right) {              // node is right child
                    //Double rotation needed
                    node = node.parent;
                    this.rotateLeft(node);
                }
                node.parent.color = this.BLACK;
                node.parent.parent.color = this.RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                this.rotateRight(node.parent.parent);
            } else {                                     // parent is right child
                uncle = node.parent.parent.left;
                 if (uncle != this.nil && uncle.color == this.RED) { // uncle is red
                    node.parent.color = this.BLACK;
                    uncle.color = this.BLACK;
                    node.parent.parent.color = this.RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {               // node is left child
                    //Double rotation needed
                    node = node.parent;
                    this.rotateRight(node);
                }
                node.parent.color = this.BLACK;
                node.parent.parent.color = this.RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                this.rotateLeft(node.parent.parent);
            }
        }
        this.root.color = this.BLACK;
    }

    rotateLeft(node) {
        if (node.parent != this.nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != this.nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {//Need to rotate this.root
            let right = this.root.right;
            this.root.right = right.left;
            right.left.parent = this.root;
            this.root.parent = right;
            right.left = this.root;
            right.parent = this.nil;
            this.root = right;
        }
    }

    rotateRight(node) {
        if (node.parent != this.nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != this.nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {//Need to rotate this.root
            let left = this.root.left;
            this.root.left = this.root.left.right;
            left.right.parent = this.root;
            this.root.parent = left;
            left.right = this.root;
            left.parent = this.nil;
            this.root = left;
        }
    }

    findNode(findNode, node) {
        if (this.root == this.nil) {
            return null;
        }

        if (findNode.key < node.key) {
            if (node.left != this.nil) {
                return this.findNode(findNode, node.left);
            }
        } else if (findNode.key > node.key) {
            if (node.right != this.nil) {
                return this.findNode(findNode, node.right);
            }
        } else if (findNode.key == node.key) {
            return node;
        }
        return null;
    }

    //Deletes whole tree
    deleteTree(){
        this.root = this.nil;
    }

    treeMinimum(subTreeRoot){
        while(subTreeRoot.left !== this.nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }

    //
    // Deletion code
    //

    /**
     * This operation doesn't care about the new Node's connections
     * with previous node's left and right. The caller has to take care
     * of that.
     */
    transplant(target, with2){
          if(target.parent == this.nil){
              this.root = with2;
          }else if(target == target.parent.left){
              target.parent.left = with2;
          }else
              target.parent.right = with2;
          with2.parent = target.parent;
    }

    delete(z){
        if((z = this.findNode(z, this.root))==null)return false;
        let x;
        let y = z; // temporary reference y
        let y_original_color = y.color;

        if(z.left == this.nil){
            x = z.right;
            this.transplant(z, z.right);
        }else if(z.right == this.nil){
            x = z.left;
            this.transplant(z, z.left);
        }else{
            y = this.treeMinimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                this.transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            this.transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if(y_original_color==this.BLACK)
            this.deleteFixup(x);
        return true;
    }

    deleteFixup(x){
        while(x!=this.root && x.color == this.BLACK){
            if(x == x.parent.left){
                let w = x.parent.right;
                if(w.color == this.RED){
                    w.color = this.BLACK;
                    x.parent.color = this.RED;
                    this.rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == this.BLACK && w.right.color == this.BLACK){
                    w.color = this.RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == this.BLACK){
                    w.left.color = this.BLACK;
                    w.color = this.RED;
                    this.rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == this.RED){
                    w.color = x.parent.color;
                    x.parent.color = this.BLACK;
                    w.right.color = this.BLACK;
                    this.rotateLeft(x.parent);
                    x = this.root;
                }
            }else{
                let w = x.parent.left;
                if(w.color == this.RED){
                    w.color = this.BLACK;
                    x.parent.color = this.RED;
                    this.rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == this.BLACK && w.left.color == this.BLACK){
                    w.color = this.RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == this.BLACK){
                    w.right.color = this.BLACK;
                    w.color = this.RED;
                    this.rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == this.RED){
                    w.color = x.parent.color;
                    x.parent.color = this.BLACK;
                    w.left.color = this.BLACK;
                    this.rotateRight(x.parent);
                    x = this.root;
                }
            }
        }
        x.color = this.BLACK;
    }


}


/**
 * Test helper
 */


assertTrue = (a, b, msg) => {
    let result = a == b;

    if(result){
        console.log("TEST[PASSED]:" + msg);
    } else {
        let err = `\n\n${msg}\n\nactual:   ${a}\nexpected: ${b}\n\n`
        throw new Error(err);
    }
}


/**
 * Tests
 */


let rbt = new RBT();

assertTrue(`
${rbt.root.toString()}
`, `
Node { key: -1, color: BLACK, parent: null, left: null, right: null }
`, "Empty tree")

rbt.insert(1)


/**
 *    B1
 *   /  \
 */


assertTrue(`
${rbt.root.parent.toString()}
${rbt.root.toString()}
${rbt.root.left.toString()}
${rbt.root.right.toString()}
`, `
Node { key: -1, color: BLACK, parent: null, left: null, right: null }
Node { key: 1, color: BLACK, parent: -1, left: -1, right: -1 }
Node { key: -1, color: BLACK, parent: null, left: null, right: null }
Node { key: -1, color: BLACK, parent: null, left: null, right: null }
`, "Inserting 1 (No RED nodes)")


rbt.insert(2)


/**
 *    B1
 *   /  \
 *      R2
 *     /  \
 */


// TODO: sentinel parent needs to point to root as child
assertTrue(`
${rbt.root.parent.toString()}
${rbt.root.toString()}
${rbt.root.left.toString()}
${rbt.root.right.toString()}
`, `
Node { key: -1, color: BLACK, parent: null, left: null, right: null }
Node { key: 1, color: BLACK, parent: -1, left: -1, right: 2 }
Node { key: -1, color: BLACK, parent: null, left: null, right: null }
Node { key: 2, color: RED, parent: 1, left: -1, right: -1 }
`, "Inserting 2 (only 2 is RED)")


rbt.insert(3)


/**
 *         B2
 *      /      \
 *     R1      R3
 *   /   \   /    \
 *
 */


// TODO: sentinel parent needs to point to root as child

// ISSUE: parent has another parent ???
assertTrue(`
${rbt.root.parent.toString()}
${rbt.root.toString()}
${rbt.root.left.toString()}
${rbt.root.right.toString()}
`, `
Node { key: -1, color: BLACK, parent: 1, left: null, right: null }
Node { key: 2, color: BLACK, parent: -1, left: 1, right: 3 }
Node { key: 1, color: RED, parent: 2, left: -1, right: -1 }
Node { key: 3, color: RED, parent: 2, left: -1, right: -1 }
`, "Inserting 3 (only 1 and 3 are RED)")


rbt.insert(4)


/**
 *        B2
 *     /      \
 *    B1      B3
 *  /   \   /    \
 *               R4
 *              /  \
 */


// TODO: sentinel parent needs to point to root as child
// ISSUE: parent has another parent ??? [CIRCULAR]
assertTrue(`
${rbt.root.parent.toString()}
${rbt.root.toString()}
${rbt.root.left.toString()}
${rbt.root.right.toString()}
${rbt.root.right.right.toString()}
${rbt.root.right.right.right.toString()}
${rbt.root.right.right.left.toString()}
`, `
Node { key: -1, color: BLACK, parent: 1, left: null, right: null }
Node { key: 2, color: BLACK, parent: -1, left: 1, right: 3 }
Node { key: 1, color: BLACK, parent: 2, left: -1, right: -1 }
Node { key: 3, color: BLACK, parent: 2, left: -1, right: 4 }
Node { key: 4, color: RED, parent: 3, left: -1, right: -1 }
Node { key: -1, color: BLACK, parent: 1, left: null, right: null }
Node { key: -1, color: BLACK, parent: 1, left: null, right: null }
`, "Inserting 4 (only 4 is red)")


rbt.insert(5)


/**
 *        B2
 *     /      \
 *    B1      B4
 *  /   \   /    \
 *         R3    R5
 *        /  \  /  \
 */


// TODO: sentinel parent needs to point to root as child
// ISSUE: parent has another parent ??? [CIRCULAR]
assertTrue(`
${rbt.root.parent.toString()}
${rbt.root.toString()}
${rbt.root.left.toString()}
${rbt.root.right.toString()}
${rbt.root.right.left.toString()}
${rbt.root.right.right.toString()}
`, `
Node { key: -1, color: BLACK, parent: 1, left: null, right: null }
Node { key: 2, color: BLACK, parent: -1, left: 1, right: 4 }
Node { key: 1, color: BLACK, parent: 2, left: -1, right: -1 }
Node { key: 4, color: BLACK, parent: 2, left: 3, right: 5 }
Node { key: 3, color: RED, parent: 4, left: -1, right: -1 }
Node { key: 5, color: RED, parent: 4, left: -1, right: -1 }
`, "Inserting 5 (only 3 and 5 are RED)")


rbt.insert(6)


/**
 *        B2
 *     /      \
 *    B1      R4
 *  /   \   /    \
 *         B3    B5
 *        /  \  /  \
 *                 R6
 *                /  \
 */


// TODO: sentinel parent needs to point to root as child
// ISSUE: parent has another parent ??? [CIRCULAR]
assertTrue(`
${rbt.root.parent.toString()}
${rbt.root.toString()}
${rbt.root.left.toString()}
${rbt.root.right.toString()}
${rbt.root.right.left.toString()}
${rbt.root.right.right.toString()}
${rbt.root.right.right.right.toString()}
`, `
Node { key: -1, color: BLACK, parent: 1, left: null, right: null }
Node { key: 2, color: BLACK, parent: -1, left: 1, right: 4 }
Node { key: 1, color: BLACK, parent: 2, left: -1, right: -1 }
Node { key: 4, color: RED, parent: 2, left: 3, right: 5 }
Node { key: 3, color: BLACK, parent: 4, left: -1, right: -1 }
Node { key: 5, color: BLACK, parent: 4, left: -1, right: 6 }
Node { key: 6, color: RED, parent: 5, left: -1, right: -1 }
`, "Inserting 6 (only 4 and 6 are RED)")


const doSuffleArray = (count) => {
    // shuffle array
    for (var a=[],i=0;i<count;++i) a[i]=i + 10;
    // http://stackoverflow.com/questions/962802#962890
    function shuffle(array) {
      var tmp, current, top = array.length;
      if(top) while(--top) {
        current = Math.floor(Math.random() * (top + 1));
        tmp = array[current];
        array[current] = array[top];
        array[top] = tmp;
      }
      return array;
    }

    return shuffle(a);
};


//
// Add shuffled inputs
//


shuffledArray = doSuffleArray(20);
console.log("\n\nINPUTS:");
console.log(shuffledArray);
console.log("\n\n");
shuffledArray.forEach(n => rbt.insert(n));
rbt.printTree(rbt.root)


//
// Find a node:
//
foundNode = rbt.findNode(rbt.createNewNode(4), rbt.root)
console.log("\n\nfoundNode: " + foundNode.toString());


//
// treeMinimum
//


treeMinimumNode = rbt.treeMinimum(rbt.root.right.right)
console.log("\n\ntreeMinimumNode: " + treeMinimumNode.toString());


//
// Delete node
//


console.log("Delete node 15:");
wasDeleted = rbt.delete(rbt.createNewNode(15))
rbt.printTree(rbt.root)


//
// delete tree
//


rbt.deleteTree();
rbt.printTree(rbt.root)


/* OUTPUT:


TEST[PASSED]:Empty tree
TEST[PASSED]:Inserting 1 (No RED nodes)
TEST[PASSED]:Inserting 2 (only 2 is RED)
TEST[PASSED]:Inserting 3 (only 1 and 3 are RED)
TEST[PASSED]:Inserting 4 (only 4 is red)
TEST[PASSED]:Inserting 5 (only 3 and 5 are RED)
TEST[PASSED]:Inserting 6 (only 4 and 6 are RED)


INPUTS:
[ 16, 13, 15, 22, 28, 20, 14, 18, 10, 23, 25, 24, 26, 12, 17, 27, 11, 21, 19, 29 ]



Node { key: 1, color: BLACK, parent: 2, left: -1, right: -1 }
Node { key: 2, color: BLACK, parent: 4, left: 1, right: 3 }
Node { key: 3, color: BLACK, parent: 2, left: -1, right: -1 }
Node { key: 4, color: RED, parent: 15, left: 2, right: 6 }
Node { key: 5, color: BLACK, parent: 6, left: -1, right: -1 }
Node { key: 6, color: BLACK, parent: 4, left: 5, right: 13 }
Node { key: 10, color: RED, parent: 11, left: -1, right: -1 }
Node { key: 11, color: BLACK, parent: 13, left: 10, right: 12 }
Node { key: 12, color: RED, parent: 11, left: -1, right: -1 }
Node { key: 13, color: RED, parent: 6, left: 11, right: 14 }
Node { key: 14, color: BLACK, parent: 13, left: -1, right: -1 }
Node { key: 15, color: BLACK, parent: -1, left: 4, right: 22 }
Node { key: 16, color: BLACK, parent: 18, left: -1, right: 17 }
Node { key: 17, color: RED, parent: 16, left: -1, right: -1 }
Node { key: 18, color: BLACK, parent: 22, left: 16, right: 20 }
Node { key: 19, color: RED, parent: 20, left: -1, right: -1 }
Node { key: 20, color: BLACK, parent: 18, left: 19, right: 21 }
Node { key: 21, color: RED, parent: 20, left: -1, right: -1 }
Node { key: 22, color: RED, parent: 15, left: 18, right: 25 }
Node { key: 23, color: BLACK, parent: 25, left: -1, right: 24 }
Node { key: 24, color: RED, parent: 23, left: -1, right: -1 }
Node { key: 25, color: BLACK, parent: 22, left: 23, right: 27 }
Node { key: 26, color: BLACK, parent: 27, left: -1, right: -1 }
Node { key: 27, color: RED, parent: 25, left: 26, right: 28 }
Node { key: 28, color: BLACK, parent: 27, left: -1, right: 29 }
Node { key: 29, color: RED, parent: 28, left: -1, right: -1 }


foundNode: Node { key: 4, color: RED, parent: 15, left: 2, right: 6 }


treeMinimumNode: Node { key: 23, color: BLACK, parent: 25, left: -1, right: 24 }
Delete node 15:
Node { key: 1, color: BLACK, parent: 2, left: -1, right: -1 }
Node { key: 2, color: BLACK, parent: 4, left: 1, right: 3 }
Node { key: 3, color: BLACK, parent: 2, left: -1, right: -1 }
Node { key: 4, color: RED, parent: 16, left: 2, right: 6 }
Node { key: 5, color: BLACK, parent: 6, left: -1, right: -1 }
Node { key: 6, color: BLACK, parent: 4, left: 5, right: 13 }
Node { key: 10, color: RED, parent: 11, left: -1, right: -1 }
Node { key: 11, color: BLACK, parent: 13, left: 10, right: 12 }
Node { key: 12, color: RED, parent: 11, left: -1, right: -1 }
Node { key: 13, color: RED, parent: 6, left: 11, right: 14 }
Node { key: 14, color: BLACK, parent: 13, left: -1, right: -1 }
Node { key: 16, color: BLACK, parent: -1, left: 4, right: 22 }
Node { key: 17, color: BLACK, parent: 18, left: -1, right: -1 }
Node { key: 18, color: BLACK, parent: 22, left: 17, right: 20 }
Node { key: 19, color: RED, parent: 20, left: -1, right: -1 }
Node { key: 20, color: BLACK, parent: 18, left: 19, right: 21 }
Node { key: 21, color: RED, parent: 20, left: -1, right: -1 }
Node { key: 22, color: RED, parent: 16, left: 18, right: 25 }
Node { key: 23, color: BLACK, parent: 25, left: -1, right: 24 }
Node { key: 24, color: RED, parent: 23, left: -1, right: -1 }
Node { key: 25, color: BLACK, parent: 22, left: 23, right: 27 }
Node { key: 26, color: BLACK, parent: 27, left: -1, right: -1 }
Node { key: 27, color: RED, parent: 25, left: 26, right: 28 }
Node { key: 28, color: BLACK, parent: 27, left: -1, right: 29 }
Node { key: 29, color: RED, parent: 28, left: -1, right: -1 }
*/
