public class StepQueue {
    private Node front;
    private Node back;
    private int n;

    public StepQueue() {
        this.front = null;
        this.back = null;
        this.n = 0;
    }

    public boolean isEmpty() { //Return true if the queue is empty, false otherwise
        if(this.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int size() { return this.n; } //Return size of queue

    public void enqueue(Step step) { //Add a new Step (as a node) to the queue
        if(this.isEmpty()) {
            this.front = new Node(step);
            this.back = new Node(step);
        }
        else if(this.size() == 1) {
            this.back.next = new Node(step);
            this.back = this.back.getNext();
            this.front.next = this.back;
        }
        else {
            this.back.next = new Node(step);
            this.back = this.back.getNext();
        }

        this.n++;
    }

    public Step dequeue () throws EmptyQueueException { //remove an item from the queue and return the Step (not the Node)
        if(this.isEmpty()) {
            throw new EmptyQueueException();
        }

        if(this.size() == 1) {
            Step top = this.front.getStep();
            this.front = null;
            this.n--;
            return top;
        }
        else {
            Step top = this.front.getStep();
            this.front = this.front.next;
            this.n--;
            return top;
        }
    }

    public String toString() {
        String s = "";
        Node curr = this.front;

        while (curr != null) {
            Step step = curr.getStep();
            if (step != null)
                s += step.getLetter();
            curr = curr.getNext();
        }
        return s;
    }

    class Node {
        private Step step;
        private Node next;

        public Node() {
        }
        public Node(Step step) {
            this.step = step;
        }
        public Node getNext() {
            return this.next;
        }
        public Step getStep() {
            return this.step;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

}