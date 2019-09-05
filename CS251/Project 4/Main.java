import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static String parseCommand(String line){
        if (line == null) return null;
        return line.split(" ")[0];
    }

    private static int parseOne(String line){
        if (line == null) return -1;
        return Integer.parseInt(line.split(" ")[1]);
    }

    private static int parseTwo(String line){
        if (line == null) return -1;
        return Integer.parseInt(line.split(" ")[2]);
    }

    private static double parseThree(String line){
        if (line == null) return -1;
        return Double.parseDouble(line.split(" ")[3]);
    }

    public static void main(String[] args) {
        String filename = "C:\\Users\\cnewe\\Documents\\School\\CS 251\\Projects\\Project 4 Distribution\\tests\\part1\\dgraph\\matrix\\matrix_input3.txt";
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            String command = parseCommand(line);

            if (command.equals("dgraph")) {
                int size = parseOne(line); //size = number of vertices
                DGraph dGraph = new DGraph(size);

                line = reader.readLine();
                while (line != null) {
                    command = parseCommand(line);
                    if (command.equals("add")){
                        int u = parseOne(line); //u is starting vertex
                        int v = parseTwo(line); //v is ending vertex
                        double weight = parseThree(line); //weight is weight
                        dGraph.add(u, v, weight);
                    }
                    else if (command.equals("v")){
                        dGraph.v();
                    }
                    else if (command.equals("e")){
                        dGraph.e();
                    }
                    else if (command.equals("adj")) {
                        int v = parseOne(line); //v is the vertex you want to get the adjacency list for
                        dGraph.adj(v);
                    }
                    else if (command.equals("weight")) {
                        int u = parseOne(line); //u is the starting vertex
                        int v = parseTwo(line); //v is the ending vertex
                        dGraph.weight(u, v);
                    }
                    else if (command.equals("matrix")) {
                        dGraph.matrix();
                    }
                    else if (command.equals("tclosure")) {
                        dGraph.tclosure();
                    }
                    else if (command.equals("spath")) {
                        int u = parseOne(line);
                        int v = parseTwo(line);
                        dGraph.spath(u, v);
                    }
                    else if (command.equals("tsort")) {
                        dGraph.tsort();
                    }
                    else if (command.equals("sconn")) {
                        dGraph.sconn();
                    }
                    else if (command.equals("simple")) {
                        dGraph.simple();
                    }
                    line = reader.readLine();
                }
            }
            else if (command.equals("ugraph")){
                int size = parseOne(line); //size is number of vertices
                UGraph uGraph = new UGraph(size);

                line = reader.readLine();
                while (line != null ) {
                    command = parseCommand(line);
                    if (command.equals("add")){
                        int u = parseOne(line); //vertex 1
                        int v = parseTwo(line); //vertex 2
                        double weight = parseThree(line); //weight of edge
                        uGraph.add(u, v, weight);
                    }
                    else if (command.equals("v")){
                        uGraph.v();
                    }
                    else if (command.equals("e")){
                        uGraph.e();
                    }
                    else if (command.equals("adj")) {
                        int v = parseOne(line); //the vertex
                        uGraph.adj(v);
                    }
                    else if (command.equals("weight")) {
                        int u = parseOne(line); //the first vertex
                        int v = parseTwo(line); //the second vertex
                        uGraph.weight(u, v);
                    }
                    else if (command.equals("matrix")) {
                        uGraph.matrix();
                    }
                    else if (command.equals("spath")) {
                        int u = parseOne(line); //starting vertex
                        int v = parseTwo(line); //ending vertex
                        uGraph.spath(u, v);
                    }
                    else if (command.equals("mst")) {
                        //uGraph.mst();
                    }
                    else if (command.equals("components")) {
                        uGraph.components();
                    }
                    else if (command.equals("simple")) {
                        uGraph.simple();
                    }
                    line = reader.readLine();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}