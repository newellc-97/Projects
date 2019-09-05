public class Merge {
    private int[] aux;
    private int count;

    public void Merge(){
        aux = null;
        count = 0;
    }

    public void sort(int[] array) { //Sort, return number of array accesses
        sort(array, 0, array.length-1);
    }

    private void sort(int[] array, int low, int high){
        //Recursively sort array
        int center = (low + high) / 2;

        if(low < high) {
            sort(array, low, center);
            sort(array, center+1, high);
        }

        merge(array, low, center, high);
    }

    public void merge(int[] array, int low, int mid, int high){
        //Merge sorted subarrays
        int front = low;
        int back = mid+1;
        int index = 0;
        aux = new int[high - low + 1];

        while(front <= mid && back <= high) {
            if(array[front] < array[back]) {
                aux[index++] = array[front++];
                count += 2;
            }
            else {
                aux[index++] = array[back++];
                count += 2;
            }
        }
        while(front <= mid) {
            aux[index++] = array[front++];
            count += 2;
        }
        while(back <= high) {
            aux[index++] = array[back++];
            count += 2;
        }
        index = 0;
        while(low <= high) {
            array[low++] = aux[index++];
            count += 2;
        }
    }
}
