public class MinHeap {
    private Score[] lb;
    private int limit;
    private int currSize;

    public MinHeap(int m) {
        lb = new Score[m+1];
        limit = m;
        currSize = 0;
    }

    private int parent(int childIndex) { return childIndex/2; }
    private int leftChild(int parentIndex) { return 2*parentIndex; }
    private int rightChild(int parentIndex) { return 2*parentIndex + 1; }

    // Return True when the key of the first node is smaller than the key for the second node
    private boolean less(int i, int j) {
        return lb[i].compareTo(lb[j]) < 0;
    }

    private void swap(int i, int j) {
        Score temp = lb[i];
        lb[i] = lb[j];
        lb[j] = temp;
    }

    /**
     * Bottom-up re-heapify: swim
     * If the heap order is violated because a node's key becomes smaller than that node's parent's key,
     * then we will swap the node with its parent.
     * Keep fixing the heap until we reach a node with a smaller key or the root.
     * @param: k is the index of the node to swim in the array
     */
    public void swim(int k) {
        if((lb[k] == null)) {
            return;
        }

        if(less(k, parent(k))) {
            swap(k, parent(k));
            swim(parent(k));
        }
    }

    /**
     * Top-down reheapify: sink
     * If the heap order is violated because a node's key becomes larger than one or both of its children's keys,
     * then we can swap the node with the smaller one of its children.
     * Keep sinking until we reach a node with both children larger
     * @param: k is the index of the node to sink in the array
     */
    public void sink(int k) {
        if(lb[k] == null || leftChild(k) >= currSize) {
            return;
        }

        boolean left;
        if(leftChild(k) > limit) {
            left = false;
        }
        else {
            left = less(leftChild(k), k);
        }

        boolean right;
        if(rightChild(k) > limit) {
            right = false;
        }
        else {
            right = less(rightChild(k), k);
        }

        if(left && right) {
            if(lb[leftChild(k)].getScore() < lb[rightChild(k)].getScore()) {
                swap(leftChild(k), k);
                sink(leftChild(k));
            }
            else {
                swap(rightChild(k), k);
                sink(rightChild(k));
            }
        }
        else if(left) {
            swap(leftChild(k), k);
            sink(leftChild(k));
        }
        else if(right)
        {
            swap(rightChild(k), k);
            sink(rightChild(k));
        }
    }


    /**
     * Insert:
     * The function adds the new key at the end of the array,
     * increases the size of the heap,
     * if the heap size reaches the limit and the new key is larger than the minimum of the current heap,
     * remove the minimum of the heap and insert the new key at the end of the heap.
     * Finally, swim up through the heap with the inserted key to restore the heap
     * @param: s is the new key (Score) added
     */
    public void insert(Score s) {
        if(currSize == 0) {
            lb[1] = s;
            currSize++;
            return;
        }

        if(currSize == limit) {
            if(s.getScore() > lb[1].getScore()) {
                delMin();
                currSize++;
                lb[currSize] = s;
            }
        }
        else {
            currSize++;
            lb[currSize] = s;
        }

        swim(currSize);
    }


    /**
     * delMin:
     * The function removes the minimum item from the root of the heap,
     * put the item from the end of the heap on top,
     * decrease the size of the heap,
     * and sink down through the heap with the key to restore the heap condition
     * @return: The minimum item (Score) of the heap
     */
    public Score delMin() {
        if(lb[1] == null) {
            return null;
        }

        Score min = lb[1];
        swap(1, currSize);
        currSize--;
        sink(1);

        return(min);
    }

    public void sort() {
        Score[] temp = new Score[lb.length];
        int size = currSize;

        for (int i = lb.length-1; i > 0; i--) {
            temp[i] = delMin();
        }

        lb = temp;
        currSize = size;
        Score a = lb[1];
        lb[1] = lb[2];
        lb[2] = a;
    }

    public String toString() {
        sort();
        String s = "";
        for(int i = 0; i < lb.length; i++) {
            if(lb[i] != null)
                s += lb[i].toString() + "\r\n";
        }
        return s;
    }

}
