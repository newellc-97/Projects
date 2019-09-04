import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

//Make sure to replace inputFolderPath and outputFolderPath with the path to your input and output files appropriately.
class MainTest {
    private final String inputFolderPath = "Test/Input Files/";
    private final String outputFolderPath = "Test/Output Files/";
    private final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private final ByteArrayOutputStream newErr = new ByteArrayOutputStream();
    private final PrintStream oldOut = System.out;
    private final PrintStream oldErr = System.err;

    @BeforeEach
    public void redirectStreams(){
        System.setOut(new PrintStream(newOut));
        System.setErr(new PrintStream(newErr));
    }

    @Test
    public void test1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(outputFolderPath + "output1.txt"));
        StringBuilder sb = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = br.readLine()) != null){
            sb.append(line);
            sb.append(ls);
        }
        br.close();
        String outputString = sb.toString();
        Main.main(new String[] {inputFolderPath + "input1.txt"});
        assertEquals(outputString, newOut.toString());
    }

    @Test
    public void test2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(outputFolderPath + "output2.txt"));
        StringBuilder sb = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = br.readLine()) != null){
            sb.append(line);
            sb.append(ls);
        }
        br.close();
        String outputString = sb.toString();
        Main.main(new String[] {inputFolderPath + "input2.txt"});
        assertEquals(outputString, newOut.toString());
    }

    @Test
    public void test3() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(outputFolderPath + "output3.txt"));
        StringBuilder sb = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = br.readLine()) != null){
            sb.append(line);
            sb.append(ls);
        }
        br.close();
        String outputString = sb.toString();
        Main.main(new String[] {inputFolderPath + "input3.txt"});
        assertEquals(outputString, newOut.toString());
    }

    @Test
    public void test4() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(outputFolderPath + "output4.txt"));
        StringBuilder sb = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = br.readLine()) != null){
            sb.append(line);
            sb.append(ls);
        }
        br.close();
        String outputString = sb.toString();
        Main.main(new String[] {inputFolderPath + "input4.txt"});
        assertEquals(outputString, newOut.toString());
    }
    @AfterEach
    public void reconvergeStreams(){
        System.setOut(oldOut);
        System.setErr(oldErr);
    }
}
