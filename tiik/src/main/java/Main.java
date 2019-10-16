import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static HashMap<Character,Integer> charsMap;
    public static void main(String[] args) throws IOException {

        //initiate
        String projectPath = System.getProperty("user.dir");
        charsMap = new HashMap<Character,Integer> ();
        // pass the path to the file as a parameter
        FileReader fr =
                new FileReader(projectPath + "\\src\\main\\resources\\test.txt");

        int i;
        while ((i=fr.read()) != -1){
            countChars((char)i);
        System.out.print("\n");
        System.out.print(charsMap);
        }
}

    public static void countChars(char inputChar){
        if (charsMap.containsKey(inputChar)) {
            charsMap.replace(inputChar, charsMap.get(inputChar)+1);
        }else
            charsMap.putIfAbsent(inputChar,1);
    }
}