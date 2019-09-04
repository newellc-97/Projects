import com.sun.source.util.SourcePositions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1)
            return;
        String filename = args[0];
		Maze maze = new Maze();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
	    int r = 0;
	    int c = 0;
	    if(line != null) {
		r = Integer.parseInt(line);
		line = reader.readLine();
	    }
	    if(line != null) {
		c = Integer.parseInt(line);
		line = reader.readLine();
	    }

	    maze = new Maze(r, c);
	    int i = 0;
	    int j = 0;
	    
            while(line != null) {
		StepQueue q = new StepQueue();
                for (int k = 0; k < line.length(); k++) {
		    char ch = line.charAt(k);
		    if(ch == 'U')
			q.enqueue(Step.UP);
		    else if(ch == 'D')
			q.enqueue(Step.DOWN);
		    else if(ch == 'R')
			q.enqueue(Step.RIGHT);
		    else if(ch == 'L')
			q.enqueue(Step.LEFT);
		}
		maze.addQueue(q);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	//System.out.println(maze);

	MazeSolver ms = new MazeSolver(maze);
	StepStack path = new StepStack();
	try {
	    path = ms.findPath();
	} catch (MazeHasNoSolutionException e) {
	    System.out.println("No Solution");
	} catch (EmptyStackException e) {
		System.out.println("Empty Stack Exception");
	} catch (EmptyQueueException e) {
		System.out.println("Empty Queue Exception");
	}
	
	String s = "";
	while(true) {
	    try {
		s = path.pop().getLetter() + s;
	    } catch (EmptyStackException e) {
		break;
	    }
	}

	System.out.println(s);
    }
}