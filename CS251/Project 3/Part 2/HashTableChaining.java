import java.util.ArrayList;
import java.util.LinkedList;

class Pair<K, V>{ //The pair class is intended to store user id and associated score as a <key, value> pair
    public K key;
    public V value;
    public Pair<K, V> next;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
    }
}

public class HashTableChaining<K, V> {
    private int size;
    private int capacity;
    private ArrayList<LinkedList<Pair>> hashTable;

    public HashTableChaining(int capacity) {
        /* initialize hashtable and other variables */
        this.size = 0;
        this.capacity = capacity;
        this.hashTable = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            hashTable.add(null);
        }
    }

    public ArrayList<LinkedList<Pair>> getHashTable(){
        return hashTable;
    }

    public void insert(String key, int value) {
        /* use hash() to determine the index of the useid, add to the linkedlist at that position of the hashtable */
        int hashVal = hash(key);

        if(hashTable.get(hashVal) != null) {
            hashTable.get(hashVal).add(new Pair(key, value));
            this.size++;
        }
        else {
            LinkedList<Pair> newLL = new LinkedList<>();
            newLL.addFirst(new Pair(key, value));
            hashTable.set(hashVal, newLL);
            this.size++;
        }
    }

    public void remove(String key) {
        int hashVal = hash(key);

        if(hashTable.get(hashVal) != null) {
            Pair head = hashTable.get(hashVal).getFirst();
            Pair prev = null;

            while(head != null) {
                if(head.key.equals(key)) {
                    break;
                }
                prev = head;
                head = head.next;
            }

            if(head == null) {
                return;
            }
            else {
                if(prev != null) {
                    prev.next = head.next;
                }
                else {
                    LinkedList<Pair> newLL = new LinkedList<>();
                    newLL.add(head.next);
                    hashTable.set(hashVal, newLL);
                }
            }
            this.size--;
        }
    }

    public boolean contains(String key) {
        int hashVal = hash(key);

        for (int i = 0; i < hashTable.get(hashVal).size(); i++)
        {
            if(hashTable.get(hashVal).get(i) == null) {
                break;
            }
            if(hashTable.get(hashVal).get(i).key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public int getSize() { //return the total number of keys in the hashtable
        return this.size;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public int hash(String key){
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public int getValue(String key){
        return -1;
    }

}
