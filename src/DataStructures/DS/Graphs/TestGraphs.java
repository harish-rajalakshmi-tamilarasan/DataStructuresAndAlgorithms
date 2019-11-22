package DataStructures.DS.Graphs;

/**
 * @author Harish T
 */
public class TestGraphs {
    public static void main(String[] args)throws Exception {
        GraphMatrix graphsList=new GraphMatrix(5);
        int[] arr={2,5,6,7,9};
        graphsList.populateVertices(arr);
        graphsList.addEdge(2,5);
        graphsList.addEdge(2,9);
        graphsList.addEdge(5,7);
        graphsList.addEdge(5,6);
        graphsList.addEdge(6,7);
        graphsList.printEdges();
    }
}
