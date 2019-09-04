public class LBSorted {
    //instance variables
    private Score[] lb;
    int minIndex;

    //constructor
    public LBSorted(int m) {
	lb = new Score[m];
	this.minIndex = m;
    }

    //methods
    public Score getMin() {
	/*return the minimum Score in the array*/
	    return this.lb[this.minIndex];
    }

    public void insert(Score s) {
	/*insert a new score */
	    boolean isFull = true;

	    //Checks if array is full
        for (int i = 0; i < this.lb.length - 1; i++)
        {
          if(this.lb[i] == null) {
              isFull = false;
              break;
          }
        }

        if(isFull == false) {

        }
        else {
            if(s.getScore() > this.getMin().getScore()) {

            }
            else {
                return;
            }
        }
    }

    public String toString() {
	String s = "";
	for(int i = lb.length-1; i >= 0; i--) {
	    if(lb[i] != null)
		s += lb[i].toString() + "\n";
	}
	return s;
    }

    private void swap(int i, int j) {
	/* swaps the values of the array at index i and index j*/
	    int h = i;
	    i = j;
	    j = h;
    }
}

	
