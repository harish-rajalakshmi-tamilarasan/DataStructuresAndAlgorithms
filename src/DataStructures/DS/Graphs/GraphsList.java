package DataStructures.DS.Graphs;

import java.util.*;

/**
 * @author Harish T
 */
public class GraphsList {
    private int vertices = 0;
    private ArrayList<Integer> list = new ArrayList<>();
    private LinkedList<Integer> edgesList[];
    private HashMap<Integer, Integer> verticesMap = new HashMap<>();

    GraphsList(int verticesCount) {
        this.vertices = verticesCount;
        this.edgesList = new LinkedList[vertices];
    }

    public void populateVertices(int[] array) {
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
            verticesMap.put(array[i], i);
            edgesList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int des) throws Exception {
        if (verticesMap.containsKey(src) && verticesMap.containsKey(des)) {
            int srcIndex = verticesMap.get(src);
            int desIndex = verticesMap.get(des);
            edgesList[srcIndex].add(des);
            edgesList[desIndex].add(src);
        }
    }

    public void printEdges() {
        for (LinkedList<Integer> temp : edgesList) {
            System.out.println("List now is ");
            for (int list : temp) {
                System.out.println(list);
            }

        }
    }}

 /**   public ArrayList<Integer> BFS(int start) {

        if (!verticesMap.containsKey(start)) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            boolean[] isVisited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();
            int val=verticesMap.get(start);
            queue.offer(val);
            arrayList.add(start);
            isVisited[val] = true;
            while (!queue.isEmpty()) {
                int src = queue.poll();
                LinkedList<Integer> list = edgesList[verticesMap.get(src)];
                Iterator<Integer> iter = list.listIterator();
                while (iter.hasNext()) {
                    int n = iter.next();
                    int temp = verticesMap.get(n);
                    if (!isVisited[temp]) {
                        isVisited[temp] = true;
                        arrayList.add(n);
                        queue.offer(temp);
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        GraphsList graphsList = new GraphsList(5);
        System.out.println(graphsList.BFS(7).length);
    }
}
**/