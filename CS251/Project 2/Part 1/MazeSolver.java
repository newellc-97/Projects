public class MazeSolver {
    private int row;
    private int col;
    private Maze maze;
    private StepStack path;

    public MazeSolver(Maze maze) {
        this.row = 0;
        this.col = 0;
        this.maze = maze;
        this.path = new StepStack();
    }

    public StepStack findPath() throws MazeHasNoSolutionException, EmptyQueueException, EmptyStackException {
	    //find a path through the maze
	    //throw MazeHasNoSolutionException if there is no possible path
        while(!this.maze.isFinish(this.row, this.col)) {
            if(this.maze.getPaths(this.row, this.col).isEmpty()) {
                Step step = this.path.pop();

                if(step.getLetter().equals("U")) {
                    this.row++;
                }
                else if(step.getLetter().equals("D")) {
                    this.row--;
                }
                else if(step.getLetter().equals("L")) {
                    this.col++;
                }
                else if(step.getLetter().equals("R")) {
                    this.col--;
                }
            }
            else {
                Step step = this.maze.getPaths(this.row, this.col).dequeue();
                this.path.push(step);

                if(step.getLetter().equals("U")) {
                    this.row--;
                }
                else if(step.getLetter().equals("D")) {
                    this.row++;
                }
                else if(step.getLetter().equals("L")) {
                    this.col--;
                }
                else if(step.getLetter().equals("R")) {
                    this.col++;
                }
            }
        }
        return(path);
    }
}