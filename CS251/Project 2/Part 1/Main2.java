import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class Main2 {
	public static void main(String[] args) throws IOException, EmptyQueueException, EmptyStackException {

		///////////////////////////// MODIFY THIS SECTION ////////////////////////////////////
		char testNum = '1';       //We have 3 test cases (1 ~ 3)
		boolean testAll = true;  //false: test one case, true: test all cases.
		String inputFolderPath = "c:/Users/cnewe/Documents/School/CS 251/Projects/Project 2 Distribution//CS251Project2_1Skeleton/InputFiles/"; //Example: "/homes/project2_1/input/"
		String outputFolderPath = "c:/Users/cnewe/Documents/School/CS 251/Projects/Project 2 Distribution//CS251Project2_1Skeleton/OutputFiles/"; //Example: "/homes/roject2_1/output/;
		//////////////////////////////////////////////////////////////////////////////////


		if(!testAll) {
			testPart(inputFolderPath, outputFolderPath, testNum);
		}
		else{
			testA(inputFolderPath, outputFolderPath);
		}
	}

	public static void testPart(String inputFolderPath, String outputFolderPath, char testNum) throws IOException, EmptyQueueException, EmptyStackException {
		String input_fileName = "/maze" + testNum + ".txt";
		String out_fileName = "/path" + testNum + ".txt";

		BufferedReader br = new BufferedReader(new FileReader(outputFolderPath + out_fileName));
		String expected = "";
		String line;

		while ((line = br.readLine()) != null){
			expected += line;
		}
		br.close();

		System.out.printf("------------ Test with %s ------------\n",input_fileName);

		run(inputFolderPath + input_fileName, expected);

		System.out.println("=============================================\n\n");
	}

	public static void testA(String inputFolderPath, String outputFolderPath) throws IOException, EmptyQueueException, EmptyStackException {
		char testNum = '1';

		for(int i = 0; i < 3; i++) {
			String input_fileName = "/maze" + testNum + ".txt";
			String out_fileName = "/path" + testNum + ".txt";

			BufferedReader br = new BufferedReader(new FileReader(outputFolderPath + out_fileName));
			String expected = "";
			String line;

			while ((line = br.readLine()) != null) {
				expected += line;
			}
			br.close();

			System.out.printf("------------ Test with %s ------------\n",input_fileName);

			run(inputFolderPath + input_fileName, expected);

			System.out.println("=============================================\n\n");

			testNum++;
		}
	}

	public static void run(String filename, String expected) throws EmptyQueueException, EmptyStackException{
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

			System.out.println("Expected output: " + expected);
			System.out.printf("  Your   output: ");

			String output = "";

			try {
				path = ms.findPath();

				while(true) {
					try {
						output = path.pop().getLetter() + output;
					} catch (EmptyStackException e) {
						break;
					}
				}

			} catch (MazeHasNoSolutionException e) {
				System.out.println("No Solution");
			}

			System.out.println(output);

			if(output.compareTo(expected) == 0){
				System.out.println("Result:  PASSED");
			}
			else{
				System.out.println("Result:  FAILED");
			}
		}
	}

