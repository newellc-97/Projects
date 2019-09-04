public class LBUnsorted {
    //instance variables
    private Score[] lb;
    int nextOpen;

    //constructor
    public LBUnsorted(int m) {
	lb = new Score[m];
	this.nextOpen = 0;
    }

    //methods
    public int getMinIndex() {
	/* return the index of the min element in the array; don't forget to check for null values */
	    int minDex = 0;

        for (int i = 1; i < this.lb.length - 1; i++)
        {
            if(this.lb[i] == null){
                this.nextOpen = i;
                break;
            }

            if(this.lb[i].getScore() < this.lb[i-1].getScore()) {
                minDex = i;
            }
        }

        return minDex;
    }

    public void insert(Score s) {
       /*insert a new score s into the array */
    }

    public Score delMin() {
	/*delete the minimum Score in the array by setting that element equal to null; return the minimum Score
         */
    }

    private void sort() {
	/*helper method*/
	    Score[] temp = new Score[lb.length];

	    for(int i = temp.length - 1; i <= 0; i--) {
	        temp[i] = delMin();
        }

        lb = temp;
    }

    public String toString() {
	sort();
	String s = "";
	for(int i = 0; i < lb.length; i++) {
	    if(lb[i] != null)
		s += lb[i].toString() + "\n";
	}
	return s;
    }
}

	
