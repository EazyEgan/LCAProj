/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Liam Egan
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Theta(N)
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */

    public int height() {

        if(isEmpty()){
            return -1;

        }
        else if(size()==1){
            return 0;
        }
        else{
            return height(root);
        }

    }
    private int height(Node n0de){



        if (n0de == null) {
            return 0;
        }
        else {
            int currentRightDepth = height(n0de.right);//takes right/left node so as to act on the subtree recursively
            int currentLeftDepth = height(n0de.left);

            if (currentRightDepth<currentLeftDepth ) {

                return (currentLeftDepth+1);
            }

            else {
                return (currentRightDepth+1);
            }
        }



        /*if(r00t.left==null&&r00t.right==null){
            currTotal++;
            return 0;
        }
        else if(r00t.left!=null){
            r00t=r00t.left;
            return 1;
        }
        else if(r00t.right!=null){
            r00t=r00t.right;
            return 1;
        }
        boolean maxHeightFound = false;
        int currentHigh=0;
        Node currNode = r00t;
        int sizeTrack=0;

        while(!maxHeightFound){
            if(currNode.left!=null){
                currNode = currNode.left;
                currentHigh++;
            }
            else{
                currentHigh++;
            }


            sizeTrack++;

        }
        return currentHigh;*/
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
        if (isEmpty()) return null;
            //TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree.
        else {
            return median(root, (size() + 1) / 2);
        }
    }
    private Key median(Node n0de, int mid) {
        //boolean medianFound = false;
        Key retKey = n0de.key;//placeholder/allow compilation
        if (n0de == null) {
            return null;
        }
        int sizeLeftTree = size(n0de.left);

        if (sizeLeftTree > mid) {
            return median(n0de.left, mid);
        } else if (sizeLeftTree < mid){
            return median(n0de.right, mid - sizeLeftTree - 1);
        }

        else {
            retKey = n0de.key; //no other case but when size of the left tree is the same as the provided median index
            return retKey;
        }

    }


    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
        if (isEmpty()) return "()";
        else{
            return printKeysInOrder(root);
        }
        //return null;
    }
    private String printKeysInOrder(Node n0de){
        String retString = "";
        if(n0de==null){
            retString+=("()");

        }
        else if(n0de!=null){
            retString += ("(");
            retString += printKeysInOrder(n0de.left);
            retString += (n0de.key);
            retString += printKeysInOrder(n0de.right);
            retString += (")");
            //return retString;

        }
        return retString;
        /*else{
            if(n0de.left!=null){
                System.out.print("("+printKeysInOrder(n0de.left));
            }
            if(n0de.left==null){
                System.out.print(n0de.key);
                printKeysInOrder(n0de.right);
            }
            if(n0de.right!=null){

                printKeysInOrder(n0de.right);
                System.out.print(")");
            }
            if(n0de.right==null){
                return null;
            }
            else return null;

        }*/

    }

    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
        return (prettyPrint(root,""));
        //return null;
    }
    private String prettyPrint(Node n0de, String prefix){
        /*void printGivenLevel (Node root ,int level)
        {
            if (root == null)
                return;
            if (level == 1)
                System.out.print(root.data + " ");
            else if (level > 1)
            {
                printGivenLevel(root.left, level-1);
                printGivenLevel(root.right, level-1);
            }
        } */
        String returnString ="";
        if(n0de==null){
            return (prefix+"-null\n");

        }
        else{
            returnString += prettyPrint(n0de.left, prefix);
            //System.out.print(n0de.key);
            if (prefix != null && prefix.length() > 0 && prefix.charAt(prefix.length() - 1) == '|') {
                prefix = prefix.substring(0, prefix.length() - 1);
                prefix+="  |";
            }
            returnString += prettyPrint(n0de.right, prefix);


        }
        return(prefix + "-" + n0de.key+"\n");//works from bottom up, changing layout as it goes down initially
    }

    /**
     * Deteles a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * //@param key the key to delete
     */

    public Node deleteMax()//was void
    {
        return deleteMax(root);
        // root = deleteMax(root);
    }

    private Node deleteMax(Node x)
    {
        if (x.left == null) return x.right;
        x.left = deleteMax(x.left);

        return x;
    }

    public void delete(Key key) {
        delete(root, key);
    }
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            Node inNode = t;
            while(inNode.right!=null){//find max
                inNode = inNode.right;
            }
            x.right = deleteMax(t.left);
            x.right = t.right;
        }

        return x;
    }
    public Key lowestCommonAncestor(Key i, Key j) {
        if (root == null){
            return null;			//If tree is empty return null.
        }
        if(!contains(i) || !contains(j)){
            return null;
        }
        return lowestCommonAncestor(root, i, j);
    }

    private Key lowestCommonAncestor(Node x, Key i, Key j) {

        int cmpI = x.key.compareTo(i);
        int cmpJ = x.key.compareTo(j);

        if(cmpI > 0 && cmpJ > 0){
            return lowestCommonAncestor(x.left, i, j);
        }
        else if(cmpI < 0 && cmpJ < 0){
            return lowestCommonAncestor(x.right, i, j);
        }
        else{
            return x.key;
        }
    }
}



/*while(inNode.right!=null){//find max
                inNode = inNode.right;
            }
            x = inNode;
            Node delMax = t.left;

            private Node deleteMin(Node x)
            {
                if (x.right == null) return x.left
                x.right = deleteMin(x.left);
                x.count = 1 + size(x.left) + size(x.right);
                return x;
            }
            x.right = delMax;*/