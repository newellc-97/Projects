import java.util.ArrayList;
import java.util.LinkedList;

public class DGraph {
    private int size;
    private int numEdges;
    private ArrayList<LinkedList<DEdge>> vertList;

    public DGraph(){}

    public DGraph(int n) {
        this.size = n;
        this.numEdges = 0;
        this.vertList = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            vertList.add(null);
        }
    }

    public void add(int u, int v, double w) {
        if(u > size || u < 0 || v > size || v < 0) {
            System.out.print("\nvertex does not exist");
        }

        DEdge newEdge = new DEdge(u, v, w);
        if(vertList.get(u) == null) {
            LinkedList newList = new LinkedList<DEdge>();
            vertList.set(u, newList);
        }
        vertList.get(u).add(newEdge);
        this.numEdges++;
    }

    public void v() {
        System.out.print("Number of vertices: " + vertList.size());
    }

    public void e() {
        System.out.print("\nNumber of edges " + numEdges);
    }

    public void adj(int v) { //Prints out all vertices adjacent to input v
        if(vertList.get(v) == null) {
            System.out.print("\nAdjacent to vertex " + v +": none");
            return;
        }

        int[] adjArray = new int[vertList.get(v).size()];
        for (int i = 0; i < vertList.get(v).size(); i++) { //fill adjArray with -1's for printing check
            adjArray[i] = -1;
        }

        for (int i = 0; i < vertList.get(v).size(); i++) { //add adjacent vertex index values to adjArray
            if(vertList.get(v).get(i) == null) {
                break;
            }

            adjArray[i] = vertList.get(v).get(i).getTarget();
        }

        if(adjArray[0] == -1) { //no adjacent vertices
            System.out.print("\nAdjacent to vertex " + v +": none");
            return;
        }

        sort(adjArray); //sort array for printing

        System.out.print("\nAdjacent to vertex " + v +":");
        for (int i = 0; i < vertList.get(v).size(); i++) { //print adjacent vertices
            if(adjArray[i] == -1) {
                break;
            }
            System.out.print(" " + adjArray[i]);
        }
    }

    public double weight(int u, int v) { //Returns the weight of an edge
        if(vertList.get(u) == null) {
            System.out.print("\nEdge does not exist");
            return 0.0;
        }

        for (int i = 0; i < vertList.get(u).size(); i++) {
            if(vertList.get(u).get(i).getTarget() == v) {
                if(vertList.get(u).get(i).getWeight() == Math.floor(vertList.get(u).get(i).getWeight())) {
                    System.out.print("\nWeight of (" + u + ", " + v + "): " + (int)vertList.get(u).get(i).getWeight());
                    return vertList.get(u).get(i).getWeight();
                }
                else {
                    System.out.printf("\nWeight of (" + u + ", " + v + "): %.3f", vertList.get(u).get(i).getWeight());
                    return vertList.get(u).get(i).getWeight();
                }
            }
        }

        System.out.print("\nEdge does not exist");
        return 0.0;
    }

    public double edgeWeight(int u, int v) { //Gets the weight of an edge given a source and a target
        for (int i = 0; i < vertList.get(u).size(); i++)
        {
            if(vertList.get(u).get(i) == null) {
                break;
            }

            if(vertList.get(u).get(i).getTarget() == v) {
                return vertList.get(u).get(i).getWeight();
            }
        }

        return 0.0;
    }

    public void matrix() {
        System.out.print("\nAdjacency Matrix:");
        System.out.print("\n ");

        int[] space = new int[this.size];
        double[][] weightrix = new double[this.size][this.size]; //matrix of weights
        for (int i = 0; i < this.size; i++) { //fill weightrix
            if(vertList.get(i) == null) { //Skip null indices
                for (int j = 0; j < this.size; j++) {
                    weightrix[i][j] = -1;
                }
            }
            else {
                for (int j = 0; j < this.size; j++) { //Run through arraylist
                    int LLindex = 0; //"Linked-List Index"
                    boolean weightFound = false;
                    while(LLindex < vertList.get(i).size()) {
                        if(vertList.get(i).get(LLindex).getSource() == i && vertList.get(i).get(LLindex).getTarget() == j) {
                            weightrix[i][j] = edgeWeight(i, j);
                            weightFound = true;
                            break;
                        }
                        LLindex++;
                    }

                    if(!weightFound) {
                        weightrix[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < this.size; i++) { //Print top line
            System.out.print(" " + i);

            for (int j = 0; j < this.size; j++) { //Determine additional space size
                if(weightrix[j][i] != Math.floor(weightrix[j][i])) {
                    space[i] = 4;
                    break;
                }
                else if(weightrix[j][i] >= 100) {
                    space[i] = 2;
                }
                else if(weightrix[j][i] >= 10 && space[i] != 2) {
                    space[i] = 1;
                }
                else if(weightrix[j][i] == -1 && space[i] != 1) {
                    space[i] = 0;
                }
            }

            for (int j = 0; j < space[i]; j++) { //Print required additional spaces
                System.out.print(" ");
            }
        }

        for (int i = 0; i < this.size; i++) { //print weightrix
            System.out.print("\n" + i);
            for (int j = 0; j < this.size; j++) { //Print nonexistant edges
                if(weightrix[i][j] == -1) {
                    System.out.print(" X");
                    for (int k = 0; k < space[j]; k++) {
                        System.out.print(" ");
                    }
                }
                else if(weightrix[i][j] != Math.floor(weightrix[i][j])){
                    System.out.print(" ");
                    System.out.printf("%.3f", weightrix[i][j]);
                }
                else {
                    int spaces = 0;
                    System.out.print(" " + (int)weightrix[i][j]);
                    if(weightrix[i][j] >= 100) {
                        spaces = 2;
                    }
                    else if(weightrix[i][j] >= 10) {
                        spaces = 1;
                    }

                    for (int k = 0; k < space[j] - spaces; k++) {
                        System.out.print(" ");
                    }
                }
            }
        }
    }

    public void tclosure() {
        System.out.print("\nTransitive Closure:");
        System.out.print("\n ");
        for (int i = 0; i < this.size; i++) {
            System.out.print(" " + i);
        }

        boolean[][] transMatrix = new boolean[this.size][this.size]; //matrix of weights
        for (int i = 0; i < this.size; i++) { //fill transMatrix
            if(vertList.get(i) == null) { //Skip null arraylist indices
                for (int j = 0; j < this.size; j++) {
                    transMatrix[i][j] = false;
                }
                transMatrix[i][i] = true;
            }
            else {
                for (int j = 0; j < this.size; j++) {
                    int LLindex = 0;
                    boolean weightFound = false;
                    while(LLindex < vertList.get(i).size()) { //Run through adjacent vertices
                        if(vertList.get(i).get(LLindex).getSource() == i && vertList.get(i).get(LLindex).getTarget() == j) {
                            transMatrix[i][j] = true;
                            weightFound = true;
                            break;
                        }
                        LLindex++;
                    }

                    if(!weightFound) {
                        transMatrix[i][j] = false;
                    }
                }
                transMatrix[i][i] = true; //Add in guaranteed self-loops
            }
        }

        for (int i = 0; i < this.size; i++) { //Warshall's Transitive Closure
            for (int j = 0; j < this.size; j++) {
                if(!transMatrix[j][i]) {
                    continue;
                }

                for (int k = 0; k < this.size; k++) {
                    if(transMatrix[j][i] && transMatrix[i][k]) {
                        transMatrix[j][k] = true;
                    }
                }
            }
        }

        for (int i = 0; i < this.size; i++) { //print transMatrix
            System.out.print("\n" + i);
            for (int j = 0; j < this.size; j++) {
                if(transMatrix[i][j]) {
                    System.out.print(" 1");
                }
                else {
                    System.out.print(" 0");
                }
            }
        }
    }

    public void spath(int u, int v) {
        if(u > this.size || v > this.size) {
            System.out.print("\npath does not exist");
            return;
        }

        double[][] weightrix = new double[this.size][this.size]; //matrix of weights
        for (int i = 0; i < this.size; i++) { //fill weightrix
            if(vertList.get(i) == null) {
                for (int j = 0; j < this.size; j++) {
                    weightrix[i][j] = -1;
                }
            }
            else {
                for (int j = 0; j < this.size; j++) {
                    int LLindex = 0;
                    boolean weightFound = false;
                    while(LLindex < vertList.get(i).size()) {
                        if(vertList.get(i).get(LLindex).getSource() == i && vertList.get(i).get(LLindex).getTarget() == j) {
                            weightrix[i][j] = edgeWeight(i, j);
                            weightFound = true;
                            break;
                        }
                        LLindex++;
                    }

                    if(!weightFound) {
                        weightrix[i][j] = 0;
                    }
                }
            }
        }

        int[] path = new int[this.size*2]; //Array of chosen shortestEdge values used for path finding
        int[] pathWeights = new int[this.size]; //Array of all shortest path weights
        boolean[] marked = new boolean[this.size];
        for (int i = 0; i < this.size; i++) { //initialize arrays
            path[i] = -1;
            pathWeights[i] = Integer.MAX_VALUE;
            marked[i] = false;
        }
        pathWeights[u] = 0; //distance from source to itself is always 0
        path[0] = 0;

        int cnt = 0;
        for (int i = 0; i < v; i++) { //Dijkstra's for all vertices
            int shortEdge = shortestEdge(pathWeights, marked); //compute shortest edge of current vertex
            marked[shortEdge] = true;

            for (int j = 0; j < v+1; j++) { //check if j-th node is current edge to add weight
                if(!marked[j] && weightrix[shortEdge][j] != 0 && pathWeights[shortEdge] != Integer.MAX_VALUE && pathWeights[shortEdge] + (int)weightrix[shortEdge][j] < pathWeights[j]) {
                    pathWeights[j] = pathWeights[shortEdge] + (int)weightrix[shortEdge][j];
                    path[cnt] = shortEdge;
                    cnt++;
                }
            }
        }

        if(pathWeights[v] == -1 || pathWeights[v] == Integer.MAX_VALUE) { //infinity path weight means no path has been found
            System.out.print("\nPath does not exist");
            return;
        }

        boolean vFind = false; //Check if path stored the value of the destination
        for (int i = 0; i < this.size; i++) {
            if(path[i] == v) {
                vFind = true;
            }
        }

        if(!vFind) {
            for (int i = 0; i < this.size*2; i++) {
                if(path[i] == -1 || (i != 0 && (path[i-1] != 0 && path[i] == 0))) {
                    path[i] = v;
                    break;
                }
            }
        }

        int[] printPath = new int[this.size*2];
        printPath[0] = u;
        int printWeight = 0;
        int[][] pathsChecked = new int[this.size*2][this.size*2];
        int checkedCount = 0;

        while(printWeight != pathWeights[v]) {
            vFind = false;
            printWeight = 0;
            int pathIndex = 0;
            boolean newPathVal = false;
            for (int i = 1; i < this.size; i++) {
                printPath[i] = -1;
            }

            for (int i = 0; i < this.size*2; i++) { //Run through shortEdge values in path[]
                if (path[i] != printPath[pathIndex]) {
                    for (int num = 0; num < this.size; num++) {
                        if(pathsChecked[num] == null) {
                            break;
                        }

                        if (path[i] == pathsChecked[num][pathIndex + 1]) { //Avoid repeat paths by checking all previous paths
                            for (int j = i + 1; j < this.size*2; j++) { //Check following values for new paths
                                if (path[j] != path[j - 1]) {
                                    for (int k = 0; k < vertList.get(printPath[pathIndex]).size(); k++) {
                                        boolean skip = false;
                                        if (vertList.get(printPath[pathIndex]).get(k).getTarget() == path[j]) {
                                            for (int l = 0; l < this.size; l++) {
                                                if(pathsChecked[l][pathIndex + 1] == vertList.get(printPath[pathIndex]).get(k).getTarget() && pathsChecked[l][pathIndex] == printPath[pathIndex]) {

                                                    skip = true;
                                                }
                                            }
                                            if(!skip) {
                                                newPathVal = true;
                                                i = j;
                                                break;
                                            }
                                        }
                                    }
                                }

                                if (newPathVal) {
                                    break;
                                }
                            }
                        }
                    }

                    if(vertList.get(printPath[pathIndex]) == null) {
                        break;
                    }

                    for (int j = 0; j < vertList.get(printPath[pathIndex]).size(); j++) {
                        if (vertList.get(printPath[pathIndex]).get(j).getTarget() == path[i]) {
                            printWeight += vertList.get(printPath[pathIndex]).get(j).getWeight();
                            printPath[++pathIndex] = path[i];
                            break;
                        }
                    }
                }
            }

            if(printWeight == pathWeights[v]) { //Check for correct weight but incomplete path
                for (int i = 0; i < this.size*2; i++) {
                    if(printPath[i] == v) {
                        vFind = true;
                    }
                }

                if(!vFind) {
                    printWeight = 0;
                }
            }

            for (int i = 0; i < this.size*2; i++) {
                if(pathsChecked[checkedCount] == null) {
                    break;
                }

                pathsChecked[checkedCount][i] = printPath[i];
            }

            boolean noPath = true;
            if(checkedCount > 0) {
                for (int i = 0; i < this.size*2; i++) {
                    if(pathsChecked[checkedCount][i] != pathsChecked[checkedCount-1][i]) {
                        noPath = false;
                    }
                }
                if(noPath) {
                    System.out.print("\nPath does not exist");
                    return;
                }
            }

            checkedCount++;
        }

        System.out.print("\nShortest path from " + u + " to " + v + ":");
        System.out.println();
        for (int i = 0; i < this.size; i++) {
            if(printPath[i] != -1) {
                System.out.print(printPath[i] + " ");
            }
        }

        System.out.print("\nWeight of path: " + pathWeights[v]);
    }

    public void tsort() {
        for (int i = 0; i < this.size; i++) { //If there are cycles, no tsort
            if(vertList.get(i) == null) { continue; }

            for (int j = 0; j < vertList.get(i).size(); j++) {
                if(vertList.get(vertList.get(i).get(j).getTarget())== null) { continue; }

                for (int k = 0; k < vertList.get(vertList.get(i).get(j).getTarget()).size(); k++) {
                    if(vertList.get(vertList.get(i).get(j).getTarget()).get(k).getSource() == i) {
                        System.out.print("\nNo topological ordering exists");
                        return;
                    }
                }
            }
        }
        boolean[] marked = new boolean[this.size];
        int[] indegrees = new int[this.size];

        for (int i = 0; i < this.size; i++) { //initialize indegree values
            if(vertList.get(i) == null) {
                continue;
            }

            for (int j = 0; j < vertList.get(i).size(); j++) {
                indegrees[vertList.get(i).get(j).getTarget()]++;
            }
        }

        ArrayList<Integer> stack = new ArrayList<>();

        if(tsortCheck(marked, indegrees, stack)) {
            System.out.print("\nTopological Orderings:");
            tsortRec(marked, indegrees, stack);
        }
        else {
            System.out.print("\nNo topological ordering exists");
        }
    }

    public void tsortRec(boolean[] marked, int[] indegrees, ArrayList<Integer> stack) {
        boolean done = false; //determine if all vertices have been hit

        for (int i = 0; i < this.size; i++) {
            if(vertList.get(i) == null) {
                continue;
            }

            if(!marked[i] && indegrees[i] == 0) {
                marked[i] = true;
                stack.add(i);
                for (int j = 0; j < vertList.get(i).size(); j++) {
                    indegrees[vertList.get(i).get(j).getTarget()]--;
                }

                tsortRec(marked, indegrees, stack);

                //Reset values in case of multiple orderings
                marked[i] = false;
                stack.remove(stack.size() - 1);
                for (int j = 0; j < vertList.get(i).size(); j++) {
                    indegrees[vertList.get(i).get(j).getTarget()]++;
                }

                //All vertices visited
                done = true;
            }
        }

        if(!done) { //print out each topological ordering
            System.out.println();
            stack.forEach(i -> System.out.print(i + " "));

            for (int i = 0; i < this.size; i++) {
                if (vertList.get(i) == null) { //Print values without any outdegrees at end of list
                    System.out.print(i);
                }
            }
        }
    }

    public boolean tsortCheck(boolean[] marked, int[] indegrees, ArrayList<Integer> stack) { //Determine if graph can be sorted - True if yes
        boolean done = false; //determine if all vertices have been hit

        for (int i = 0; i < this.size; i++) {
            if(vertList.get(i) == null) {
                continue;
            }

            if(!marked[i] && indegrees[i] == 0) {
                marked[i] = true;
                stack.add(i);
                for (int j = 0; j < vertList.get(i).size(); j++) {
                    indegrees[vertList.get(i).get(j).getTarget()]--;
                }

                tsortCheck(marked, indegrees, stack);

                marked[i] = false;
                stack.remove(stack.size() - 1);
                for (int j = 0; j < vertList.get(i).size(); j++) {
                    indegrees[vertList.get(i).get(j).getTarget()]++;
                }


                done = true;
            }
        }

        if(!done) { //Check if graph can be tsorted
            if (stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void sconn() {
        boolean[] marked = new boolean[this.size];

        for (int i = 0; i < this.size; i++) {
            if(vertList.get(i) == null) {
                continue;
            }
            for (int j = 0; j < vertList.get(i).size(); j++) {
                if(vertList.get(i).get(j) == null) {
                    break;
                }
                else {
                    if(!marked[vertList.get(i).get(j).getTarget()]) {
                        marked[vertList.get(i).get(j).getTarget()] = true;
                    }
                }
            }
        }

        for (int i = 0; i < this.size; i++) {
            if(!marked[i]) {
                System.out.print("\nGraph is not strongly connected");
                return;
            }
        }

        System.out.print("\nGraph is strongly connected");
    }

    public void simple() {
        for (int i = 0; i < this.size; i++) { //check for multiple edges
            boolean[] targets = new boolean[this.size];
            if(vertList.get(i) == null) {
                continue;
            }
            for (int j = 0; j < vertList.get(i).size(); j++) {
                targets[j] = false;
            }

            for (int j = 0; j < vertList.get(i).size(); j++) {
                if(!targets[vertList.get(i).get(j).getTarget()]) {
                    targets[vertList.get(i).get(j).getTarget()] = true;
                }
                else {
                    System.out.print("\nGraph is not simple");
                    return;
                }
            }
        }

        for (int i = 0; i < this.size; i++) { //check for self-loops
            if(vertList.get(i) == null) {
                continue;
            }
            for (int j = 0; j < vertList.get(i).size(); j++) {
                if(vertList.get(i).get(j).getSource() == vertList.get(i).get(j).getTarget()) {
                    System.out.print("\nGraph is not simple");
                    return;
                }
            }
        }

        System.out.print("\nGraph is simple");
    }

    public int shortestEdge(int[] path, boolean[] marked) {
        int min = Integer.MAX_VALUE;
        int mIndex = -1;

        for (int i = 0; i < this.size; i++) {
            if(!marked[i] && path[i] <= min) {
                min = path[i];
                mIndex = i;
            }
        }

        return mIndex;
    }

    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
