import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 

public class Score implements Comparable<Score> {
    //instance variables
    String uid;
    int score;

    //constructors
    public Score() {
	this.uid = null;
	this.score = -1;
    }

    public Score(String uid, int score) {
	this.uid = uid;
	this.score = score;
    }

    //methods
    public String getUid() {
		/* return the Uid*/
		return uid;
    }

    public int getScore() {
    	/*return the score*/
    	return score;
    }

    public int compareTo(Score o) {
	/*return difference between the scores of the two objects
          don't forget to check if the score is null*/
		if((o == null) || (this == null)){
			throw new IllegalArgumentException("Score is null");
		}

		return(this.score - o.score);
    }

    public boolean equals(Object anObject) {
	/*returns true if the scores are equal.
          don't forget to check if either of the scores is null*/
		if(anObject instanceof Score) {
			Score newObject = (Score)anObject;

			if((this == null) || (anObject == null)) {
				throw new IllegalArgumentException("Score is null");
			}

			if(this.score == newObject.score) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
    }

    public String toString() {
	return uid + " " + score;
    }
    public static void main(String[] args) {
	    String filename = args[0];
	    Score testscore = new Score("john",5);
	    BufferedReader reader;
	    try {
		    reader = new BufferedReader(new FileReader(filename));
		    String line = reader.readLine();
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
    }
}
