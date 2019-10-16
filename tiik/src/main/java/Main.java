import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String projectPath = System.getProperty("user.dir");
        // pass the path to the file as a parameter
        FileReader fr =
                new FileReader(projectPath + "\\src\\main\\resources\\test.txt");

        int i;
        while ((i=fr.read()) != -1)
            System.out.print((char) i);
    }

    }