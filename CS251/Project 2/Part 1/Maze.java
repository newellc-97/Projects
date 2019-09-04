public class Maze {
    private StepQueue[][] maze;
    private int i; //index used when building the Maze
    private int j; //index used when building the Maze

    public Maze() {}

    public Maze(int h, int w) {
    	this.maze = new StepQueue[h][w];
    	this.i = 0;
    	this.j = 0;
    }

	public void addQueue(StepQueue newQueue) {
		if (!isFull()) {
			maze[i][j] = newQueue;
			j++;
			if (j == maze[0].length) {
				i++;
				j = 0;
			}
		}
	}

    public StepQueue getPaths(int row, int col) {
	return maze[row][col];
    }

    public boolean isFinish(int row, int col) {
	return row == maze.length - 1 && col == maze[0].length - 1;
    }

	public String toString() {
		String s = "";
		for (int r = 0; r < maze.length; r++) {
			for (int c = 0; c < maze[r].length; c++) {
				System.out.println(maze[r][c]);
			}
		}
		return s;
	}

    private boolean isFull() {
	return i == maze.length;
    }
}

