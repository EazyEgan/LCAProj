import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;



@RunWith(JUnit4.class)
public class BSTTest
{



    /** <p>Test {@link BST#prettyPrintKeys()}.</p> */

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

    }


    @Test
    public void testDelete() {

    }

}