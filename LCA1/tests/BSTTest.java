import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class BSTTest
{

    @Test
    public void testInsert() {
        BST<Character, Character> bst = new BST<Character, Character>();


        //Testing node creation.
        bst.insert('B', 'B');
        assertEquals("Testing insert on empty tree",
                "(()B())", bst.printTree());

        //Testing nodes get sorted correctly when added.
        bst.insert('A', 'A');
        bst.insert('C', 'C');
        bst.insert('D', 'D');


        /*
         * 			B
         * 		   / \
         * 		  A   C
         * 			   \
         * 				D
         */

        assertEquals("Testing insert of multiple nodes into tree",
                "((()A())B(()C(()D())))", bst.printTree());

        //Testing update of value in tree.
        bst.insert('C', 'X');
        assertEquals("Testing insert of multiple nodes into tree",
                'X', (char)bst.get('C'));

        //Testing insert of null value. (Should delete node)
        bst.insert('C', null);
        assertEquals("Testing insert of null value into tree",
                "((()A())B(()D()))", bst.printTree());

        //Testing insert of null key. (Should have no effect)
        bst.insert(null, 'Q');
        assertEquals("Testing insert of null key into tree",
                "((()A())B(()D()))", bst.printTree());

    }

    @Test
    public void testGet(){
        BST<Character, Character> bst = new BST<Character, Character>();

        assertNull("Testing get on empty tree",
                bst.get('A'));

        bst.insert('B', 'B');
        bst.insert('C', 'C');
        bst.insert('D', 'D');
        bst.insert('E', 'E');

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
        bst.insert(1,1);

        //If given non-present keys - should return null.

        //One key present.
        assertNull("Testing one node tree given non-present keys", bst.lowestCommonAncestor(2,1));

        //Both non-present keys.
        assertNull("Testing one node tree given non-present keys", bst.lowestCommonAncestor(2,3));

        //Testing multi-node tree
        BST<Integer, Integer> bst2 = new BST<Integer, Integer>();

        bst2.insert(7, 7);   //        _7_
        bst2.insert(8, 8);   //      /     \
        bst2.insert(3, 3);   //    _3_      8
        bst2.insert(1, 1);   //  /     \
        bst2.insert(2, 2);   // 1       6
        bst2.insert(6, 6);   //  \     /
        bst2.insert(4, 4);   //   2   4
        bst2.insert(5, 5);   //        \
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

        bst.insert(1, 1);
        assertEquals("Testing non-empty tree", false, bst.isEmpty());
    }

    @Test
    public void testSize(){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing empty tree", 0, bst.size());

        bst.insert(1, 1);
        assertEquals("Testing single node tree", 1, bst.size());

        bst.insert(2, 2);
        bst.insert(7, 7);
        bst.insert(3, 3);
        bst.insert(99, 99);

        assertEquals("Testing multi node tree", 5, bst.size());
    }

    @Test
    public void testContains(){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing empty tree", false, bst.contains(4));

        bst.insert(1, 1);
        assertEquals("Testing single node tree containing key", true, bst.contains(1));
        assertEquals("Testing single node tree not containing key", false, bst.contains(5));

        bst.insert(2, 2);
        bst.insert(7, 7);
        bst.insert(3, 3);
        bst.insert(99, 99);

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