import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
	/*read arguments from the command line into variables filename and m*/
	String filename = args[0];
	int m = Integer.parseInt(args[1]);
	Score testscore = new Score("john",5);


	LBSorted lbs = new LBSorted(m);
	LBUnsorted lbu = new LBUnsorted(m);

	BufferedReader reader;
	try {
	    reader = new BufferedReader(new FileReader(filename));
	    String line = reader.readLine();
            /*read file line by line and insert the scores to lbs and lbu*/

		while (line != null) {
			String[] split = line.split(" ");
			if(split.length == 2) {
				Score s = new Score(split[0], Integer.parseInt(split[1]));
				String id = s.getUid();
				int score = s.getScore();
				System.out.println(id + " " + score);
				System.out.println(s.compareTo(testscore));
				System.out.println(s.equals(testscore));
			}
			line = reader.readLine();
		}
	    
	    reader.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Sorted:");
	System.out.println(lbs);
	System.out.println("Unsorted:");
	System.out.println(lbu);
    }
}
