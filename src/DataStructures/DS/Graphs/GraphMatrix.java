package DataStructures.DS.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Harish T
 */
public class GraphMatrix {
    private int vertices = 0;
    private ArrayList<Integer> list = new ArrayList<>();
    private int[][] adjMatrix;
    private HashMap<Integer, Integer> verticesMap = new HashMap<>();

    GraphMatrix(int verticesCount) {
        this.vertices = verticesCount;
        this.adjMatrix = new int[vertices][vertices];
    }

    public void populateVertices(int[] array) {
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
            verticesMap.put(array[i], i);
        }
    }

    public void addEdge(int src, int des) {
        if (verticesMap.containsKey(src) && verticesMap.containsKey(des)) {
            int srcIndex = verticesMap.get(src);
            int desIndex = verticesMap.get(des);
            adjMatrix[srcIndex][desIndex] = 1;
            adjMatrix[desIndex][srcIndex] = 1;
        }
    }

    public void printEdges() {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] == 1) {
                    System.out.println("[" + i + "][" + j + "]=" + adjMatrix[i][j]);
                }
            }
        }
    }
}
