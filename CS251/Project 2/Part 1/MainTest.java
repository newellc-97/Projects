import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private final String inputFolderPath = "InputFiles/";
    private final String outputFolderPath = "OutputFiles/";
    private final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private final ByteArrayOutputStream newErr = new ByteArrayOutputStream();
    private final PrintStream oldOut = System.out;
    private final PrintStream oldErr = System.err;

    @BeforeEach
    public void redirectStreams() {
        System.setOut(new PrintStream(newOut));
        System.setErr(new PrintStream(newErr));
    }

    @Test
    public void test1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(outputFolderPath + "path1.txt"));
        StringBuilder sb = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(ls);
        }
        br.close();
        String outputString = sb.toString();
        Main.main(new String[]{inputFolderPath + "maze1.txt"});
        assertEquals(outputString, newOut.toString());
    }

    @Test
    public void test2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(outputFolderPath + "path2.txt"));
        StringBuilder sb = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(ls);
        }
        br.close();
        String outputString = sb.toString();
        Main.main(new String[]{inputFolderPath + "maze2.txt"});
        assertEquals(outputString, newOut.toString());
    }

    @Test
    public void test3() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(outputFolderPath + "path3.txt"));
        StringBuilder sb = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(ls);
        }
        br.close();
        String outputString = sb.toString();
        Main.main(new String[]{inputFolderPath + "maze3.txt"});
        assertEquals(outputString, newOut.toString());
    }

    @AfterEach
    public void reconvergeStreams(){
        System.setOut(oldOut);
        System.setErr(oldErr);
    }
}