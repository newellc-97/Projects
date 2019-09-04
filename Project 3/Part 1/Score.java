import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Score {
    private String uid;
    private int score;

    public Score(String uid, int score) {
        this.uid = uid;
        this.score = score;
    }

    public String getUid() { return this.uid; }
    public int getScore() { return this.score; }
    public String toString() {
        return uid + " " + score;
    }
}