import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    //@ScoringWeight(0.5)
    public void testOne() throws Exception
    {
        int fileNo=1;
        File f = new File("C:\\Users\\cnewe\\Documents\\School\\CS 251\\Projects\\Project 5 Distribution\\P5\\files\\out\\out1.txt");
        runCase(fileNo);

        assertTrue("Output file "+fileNo+" is not created yet", f.exists());
        assertTrue("test" + fileNo +" failed", matchFiles("C:\\Users\\cnewe\\Documents\\School\\CS 251\\Projects\\Project 5 Distribution\\P5\\files\\expected\\expected1.txt",
                "C:\\Users\\cnewe\\Documents\\School\\CS 251\\Projects\\Project 5 Distribution\\P5\\files\\out\\out1.txt"));
    }


    @Test
    //@ScoringWeight(0.5)
    public void testTwo() throws Exception
    {
        int fileNo=2;
        File f = new File("C:\\Users\\cnewe\\Documents\\School\\CS 251\\Projects\\Project 5 Distribution\\P5\\files\\out\\out2.txt");
        runCase(fileNo);

        assertTrue("Output file "+fileNo+" is not created yet", f.exists());
        assertTrue("test" + fileNo +" failed", matchFiles("C:\\Users\\cnewe\\Documents\\School\\CS 251\\Projects\\Project 5 Distribution\\P5\\files\\expected\\expected2.txt",
                "C:\\Users\\cnewe\\Documents\\School\\CS 251\\Projects\\Project 5 Distribution\\P5\\files\\out\\out2.txt"));
    }


    public void runCase(int fileNo){
        try {
            Trie trie = new Trie();
            trie.buildTrie("C:\\Users\\cnewe\\Documents\\School\\CS 251\\Projects\\Project 5 Distribution\\P5\\files\\input\\input" + fileNo + ".txt");
            trie.autocomplete("C:\\Users\\cnewe\\Documents\\School\\CS 251\\Projects\\Project 5 Distribution\\P5\\files\\out\\out" + fileNo + ".txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean matchFiles(String real, String out){
        try {
            Scanner inf1 = new Scanner(new File(real));
            Scanner inf2 = new Scanner(new File(out));

            boolean check =false;

            while(inf1.hasNextLine()){
                String actual=inf1.nextLine();
                actual=actual.trim();
                if(!inf2.hasNextLine())return false;

                String got=inf2.nextLine();
                got=got.trim();

                if(!actual.equals(got)){
                    return false;
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }
}