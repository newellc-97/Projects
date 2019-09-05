import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class HashTableChainingTest {
    HashTableChaining<String, Integer> ht;
    ArrayList<Pair> input;
    @Before
    public void setUp() throws Exception{
        ht = new HashTableChaining<>(11);
        input = new ArrayList<>();
        Scanner sc = new Scanner(new File("Test/test.txt"));
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("\\s+");
            input.add(new Pair(line[0], Integer.parseInt(line[1])));
        }
        for(int i = 0; i < 1000; i++) {
            ht.insert(input.get(i).key.toString(), Integer.parseInt(input.get(i).value.toString()));
        }
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void hash(){
        /* check whether your hash() function is assigning the correct index to the userid */
        ht = new HashTableChaining<String, Integer>(11);
        int[] expectedHashes = {10, 9, 1, 1, 2};
        for(int i = 0; i < 5; i++){
            assertEquals(expectedHashes[i], ht.hash(input.get(i).key.toString()));
        }
    }

    @Test
    public void insert_contains() throws Exception{
        /* check whether insert is successful and whether contains() function is detecting inserted element */
        for(int i = 0; i < 1000; i++){
            assertTrue(ht.contains(input.get(i).key.toString()));
        }
    }

    @Test
    public void size() throws Exception{
        /* check whether elements are inserted properly and if the size() function is returning the correct size */
        assertEquals(1000, ht.getSize());
    }

    @Test
    public void remove() throws Exception{
        /* check whether remove an element is successful and if contains() is detecting the removal */
        for(int i = 0; i < 1000; i++){
            ht.remove(input.get(i).key.toString());
            assertEquals(false, ht.contains(input.get(i).key.toString()));
        }
        for(int i = 0; i < 1000; i++){
            assertEquals(false, ht.contains(input.get(i).key.toString()));
        }
    }
}