import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class BSTTest
{

    @Test
    public void testPut() {
        BST<Character, Character> bst = new BST<Character, Character>();


        //Testing node creation.
        bst.put('B', 'B');
        assertEquals("Testing put on empty tree",
                "(()B())", bst.printKeysInOrder());

        //Testing nodes get sorted correctly when added.
        bst.put('A', 'A');
        bst.put('C', 'C');
        bst.put('D', 'D');


        /*
         * 			B
         * 		   / \
         * 		  A   C
         * 			   \
         * 				D
         */

        assertEquals("Testing put of multiple nodes into tree",
                "((()A())B(()C(()D())))", bst.printKeysInOrder());

        //Testing update of value in tree.
        bst.put('C', 'X');
        assertEquals("Testing put of multiple nodes into tree",
                'X', (char)bst.get('C'));

        //Testing put of null value. (Should delete node)
        bst.put('C', null);
        assertEquals("Testing put of null value into tree",
                "((()A())B(()D()))", bst.printKeysInOrder());

        //Testing put of null key. (Should have no effect)
        bst.put(null, 'Q');
        assertEquals("Testing put of null key into tree",
                "((()A())B(()D()))", bst.printKeysInOrder());

    }

    @Test
    public void testGet(){
        BST<Character, Character> bst = new BST<Character, Character>();

        assertNull("Testing get on empty tree",
                bst.get('A'));

        bst.put('B', 'B');
        bst.put('C', 'C');
        bst.put('D', 'D');
        bst.put('E', 'E');

        assertNull("Testing get on multiple node tree that doesn't contain key",
                bst.get('A'));


        assertEquals("Testing get on multiple node tree that does contain key",
                'C', (char)bst.get('C'));   //Have to cast to char as will return type of 'Value'.

        assertNull("Testing null get", bst.get(null));

    }


    @Test
    public void testLowestCommonAncestor(){
        //Lowest Common Ancestor should require two keys and return key of LCA. We then can use


        BST<Integer, Integer> bst = new BST<Integer, Integer>();

        //Testing empty tree.
        assertNull("Testing LCA on empty tree", bst.lowestCommonAncestor(1, 2));

        //Testing one-node tree.
        bst.put(1,1);

        //If given non-present keys - should return null.

        //One key present.
        assertNull("Testing one node tree given non-present keys", bst.lowestCommonAncestor(2,1));

        //Both non-present keys.
        assertNull("Testing one node tree given non-present keys", bst.lowestCommonAncestor(2,3));

        //Testing multi-node tree
        BST<Integer, Integer> bst2 = new BST<Integer, Integer>();

        bst2.put(7, 7);   //        _7_
        bst2.put(8, 8);   //      /     \
        bst2.put(3, 3);   //    _3_      8
        bst2.put(1, 1);   //  /     \
        bst2.put(2, 2);   // 1       6
        bst2.put(6, 6);   //  \     /
        bst2.put(4, 4);   //   2   4
        bst2.put(5, 5);   //        \
        //         5

        //If either given key is the root, should return the root
        assertEquals("Testing one node tree given root key", 7, (int)bst2.lowestCommonAncestor(7,2));

        assertEquals("Testing multi-node tree", 7, (int)bst2.lowestCommonAncestor(3,8));
        assertEquals("Testing multi-node tree", 7, (int)bst2.lowestCommonAncestor(5,8));
        assertEquals("Testing multi-node tree", 3, (int)bst2.lowestCommonAncestor(3,6));
        assertEquals("Testing multi-node tree", 3, (int)bst2.lowestCommonAncestor(2,5));
    }

    @Test
    public void testIsEmpty(){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing empty tree", true, bst.isEmpty());

        bst.put(1, 1);
        assertEquals("Testing non-empty tree", false, bst.isEmpty());
    }

    @Test
    public void testSize(){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing empty tree", 0, bst.size());

        bst.put(1, 1);
        assertEquals("Testing single node tree", 1, bst.size());

        bst.put(2, 2);
        bst.put(7, 7);
        bst.put(3, 3);
        bst.put(99, 99);

        assertEquals("Testing multi node tree", 5, bst.size());
    }

    @Test
    public void testContains(){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing empty tree", false, bst.contains(4));

        bst.put(1, 1);
        assertEquals("Testing single node tree containing key", true, bst.contains(1));
        assertEquals("Testing single node tree not containing key", false, bst.contains(5));

        bst.put(2, 2);
        bst.put(7, 7);
        bst.put(3, 3);
        bst.put(99, 99);

        assertEquals("Testing multi node tree containing key", true, bst.contains(99));
        assertEquals("Testing multi node tree containing key", false, bst.contains(4));

        assertEquals("Testing contains null. Should return false", false,  bst.contains(null));
    }


    @Test
    public void testheight() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking height check of BST",
                -1, bst.height());

        //  -7
        //   |-3
        //   | |-1
        //   | | |-null
        bst.put(7, 7);       //   | |  -2
        bst.put(8, 8);       //   | |   |-null
        bst.put(3, 3);       //   | |    -null
        bst.put(1, 1);       //   |  -6
        bst.put(2, 2);       //   |   |-4
        bst.put(6, 6);       //   |   | |-null
        bst.put(4, 4);       //   |   |  -5
        bst.put(5, 5);       //   |   |   |-null
        //   |   |    -null
        //   |    -null
        //    -8
        //     |-null
        //      -null

        int result = 5;
        assertEquals("Checking height of non-empty tree", result, bst.height());
    }

@Test
public void testMedian() {
    BST<Integer, Integer> bst = new BST<Integer, Integer>();
    assertEquals("Checking the median item",
            null, bst.median());

    //  -7
    //   |-3
    //   | |-1
    //   | | |-null
    bst.put(7, 7);       //   | |  -2
    bst.put(8, 8);       //   | |   |-null
    bst.put(3, 3);       //   | |    -null
    bst.put(1, 1);       //   |  -6
    bst.put(2, 2);       //   |   |-4
    bst.put(6, 6);       //   |   | |-null
    bst.put(4, 4);       //   |   |  -5
    bst.put(5, 5);       //   |   |   |-null
    //   |   |    -null
    //   |    -null
    //    -8
    //     |-null
    //      -null


    assertEquals("Checking median of non-empty tree", bst.get(5), bst.median());

}

@Test
public void testPrintKeysInOrder() {
    BST<Integer, Integer> bst = new BST<Integer, Integer>();
    assertEquals("Checking regular printing of empty tree",
            "()", bst.printKeysInOrder());

    //  -7
    //   |-3
    //   | |-1
    //   | | |-null
    bst.put(3, 3);       //   | |    -null
    bst.put(1, 1);       //   |  -6
    bst.put(2, 2);       //   |   |-4
    bst.put(4, 4);       //   |   |  -5

    //   |   |    -null
    //   |    -null
    //    -8
    //     |-null
    //      -null

    String result = "((()1())2(()3(()4())))" ;

    assertEquals("Checking pretty printing of non-empty tree", result, bst.printKeysInOrder());
}


    @Test
    public void testPrettyPrint() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking pretty printing of empty tree",
                "-null\n", bst.prettyPrintKeys());

        //  -7
        //   |-3
        //   | |-1
        //   | | |-null
        bst.put(7, 7);       //   | |  -2
        bst.put(8, 8);       //   | |   |-null
        bst.put(3, 3);       //   | |    -null
        bst.put(1, 1);       //   |  -6
        bst.put(2, 2);       //   |   |-4
        bst.put(6, 6);       //   |   | |-null
        bst.put(4, 4);       //   |   |  -5
        bst.put(5, 5);       //   |   |   |-null
        //   |   |    -null
        //   |    -null
        //    -8
        //     |-null
        //      -null

        String result =
                "-7\n" +
                        " |-3\n" +
                        " | |-1\n" +
                        " | | |-null\n" +
                        " | |  -2\n" +
                        " | |   |-null\n" +
                        " | |    -null\n" +
                        " |  -6\n" +
                        " |   |-4\n" +
                        " |   | |-null\n" +
                        " |   |  -5\n" +
                        " |   |   |-null\n" +
                        " |   |    -null\n" +
                        " |    -null\n" +
                        "  -8\n" +
                        "   |-null\n" +
                        "    -null\n";
        assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
    }



    @Test
    public void testDelete() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.delete(1);
        assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
        //                                   5

        assertEquals("Checking order of constructed tree",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(9);
        assertEquals("Deleting non-existent key",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(8);
        assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

        bst.delete(6);
        assertEquals("Deleting node with single child",
                "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

        bst.delete(3);
        assertEquals("Deleting node with two children",
                "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
    }

}