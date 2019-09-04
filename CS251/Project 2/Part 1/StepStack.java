public class StepStack {
    private int n;
    private Step[] stack;

    public StepStack() {
        this.n = 0;
        stack = new Step[2];
    }

    public int size() { return this.n; } //Return size of stack

    public boolean isEmpty() { //Return true if the stack is empty, false otherwise
        if(n == 0){
            return true;
        }
        else {
            return false;
        }
    }

    public Step peek() throws EmptyStackException { //Return the top item in the Stack without removing it
        if(this.isEmpty()){
            throw new EmptyStackException();
        }

        return this.stack[this.n-1];
    }

    public Step pop() throws EmptyStackException {
	//Remove the top item from the stack and return its value
	//If the result is that the Stack is less than 1/4 full, re-size the Stack to be 1/2 its size
        if(this.isEmpty()) {
            throw new EmptyStackException();
        }

        Step top = this.stack[this.n-1];
        this.stack[this.n-1] = null;
        this.n--;

        if(this.n < (this.stack.length/4)) {
            Step newStack[] = new Step[this.stack.length/2];

            for (int i = 0; i < this.stack.length; i++) {
                if(this.stack[i] == null) {
                    break;
                }
                newStack[i] = this.stack[i];
            }
            this.stack = newStack;
        }
        return top;
    }

    public void push(Step step) {
	//Push a new Step onto the Stack
	//If the Stack is full, re-size the Stack to be twice as large
        if(this.n == (this.stack.length-1)) {
            Step newStack[] = new Step[this.stack.length*2];

            for (int i = 0; i < this.stack.length; i++) {
                newStack[i] = this.stack[i];
            }

            this.stack = newStack;
        }

        this.stack[this.n] = step;
        this.n++;
    }

    public String toString() {
        String s = "";
        for (int i = n; i > 0; i--) {
            s += stack[n - 1].getLetter();
        }
        return s;
    }
}
