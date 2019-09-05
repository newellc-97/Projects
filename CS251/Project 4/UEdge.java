public class UEdge
{
    private int source;
    private int adjacent;
    private double weight;

    public UEdge(){}

    public UEdge(int source, int adjacent, double weight) {
        this.source = source;
        this.adjacent = adjacent;
        this.weight = weight;
    }

    public int getSource() { return this.source; }
    public int getAdjacent() { return this.adjacent; }
    public double getWeight() { return this.weight; }
}
