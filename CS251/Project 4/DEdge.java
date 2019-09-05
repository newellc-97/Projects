public class DEdge
{
    private int source;
    private int target;
    private double weight;

    public DEdge(){}

    public DEdge(int source, int target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public int getSource() { return this.source; }
    public int getTarget() { return this.target; }
    public double getWeight() { return this.weight; }
}
