import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

@RunWith(JUnit4.class)
public class DAGTest
{
    @Test
    public void testAddEdge(){
        DAG DAG1 = new DAG(5);

        assertEquals("Testing adding a self-loop. Should return false.", false, DAG1.addEdge(0, 0));

        assertEquals("Testing adding a valid edge. Should return true.", true, DAG1.addEdge(0, 1));
        assertEquals("Testing adding a valid edge. Should return true.", true, DAG1.addEdge(1, 2));

        assertEquals("Testing adding an edge that would result in a cylce. Should return false.", false, DAG1.addEdge(2, 0));

        assertEquals("Testing adding an edge from non-existing vertices. Should return false.", false, DAG1.addEdge(5, 4));
        assertEquals("Testing adding an edge from non-existing vertices. Should return false.", false, DAG1.addEdge(89, 53));
        assertEquals("Testing adding an edge from negative vertices. Should return false.", false, DAG1.addEdge(-2, -4));

    }

    @Test
    public void testV(){
        DAG DAG1 = new DAG(5);
        assertEquals("Testing V()", 5, DAG1.V());
    }

    @Test
    public void testAdj(){
        DAG DAG1 = new DAG(5);

        assertTrue("Testing empty adj list", DAG1.adjacent(0).isEmpty());

        ArrayList<Integer> expectedResult = new ArrayList<Integer>();

        //Testing single edge adj list
        expectedResult.add(2);
        DAG1.addEdge(1, 2);

        assertTrue("Testing single edge adj list", DAG1.adjacent(1).size() == expectedResult.size());
        for(int i : expectedResult){
            assertTrue("Testing single edge adj list", DAG1.adjacent(1).contains(i));
        }

        expectedResult.clear();

        expectedResult.add(3);
        expectedResult.add(4);

        DAG1.addEdge(2, 3);
        DAG1.addEdge(2, 4);

        assertTrue("Testing multi-edge adj list", DAG1.adjacent(2).size() == expectedResult.size());
        for(int i : expectedResult){
            assertTrue("Testing multi-edge adj list", DAG1.adjacent(2).contains(i));
        }
    }

    @Test
    public void testDagLowestCommonAncestor(){
        DAG testDag1 = new DAG(5);

        testDag1.addEdge(0, 1);
        testDag1.addEdge(0, 2);
        testDag1.addEdge(2, 3);
        testDag1.addEdge(3, 4);

        ArrayList<Integer> expectedResult = new ArrayList<Integer>();
        expectedResult.add(0);

        assertTrue("Testing single lca return", testDag1.lowestCommonAncestor(4,1).size() == expectedResult.size());
        for(int i : expectedResult){
            assertTrue("Testing single lca return", testDag1.lowestCommonAncestor(4,1).contains(i));
        }





        DAG testDag2 = new DAG(7);

        testDag2.addEdge(0, 3);
        testDag2.addEdge(1, 3);
        testDag2.addEdge(1, 4);
        testDag2.addEdge(2, 5);
        testDag2.addEdge(2, 6);
        testDag2.addEdge(3, 5);
        testDag2.addEdge(3, 6);
        testDag2.addEdge(4, 6);

        expectedResult.clear();
        expectedResult.add(2);
        expectedResult.add(3);

        assertTrue("Testing mutliple lca return", testDag1.lowestCommonAncestor(5,6).size() == expectedResult.size());
        for(int i : expectedResult){
            assertTrue("Testing mutliple lca return", testDag1.lowestCommonAncestor(5,6).contains(i));
        }

        assertTrue("Testing negative input", testDag2.lowestCommonAncestor(-2, -1).isEmpty());

        assertTrue("Testing out of range input", testDag2.lowestCommonAncestor(2457, 987).isEmpty());

    }
}