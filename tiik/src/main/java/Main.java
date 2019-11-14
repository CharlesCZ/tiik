import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {


private static final SignService signService=new SignService();

    public static void main(String[] args) throws IOException {

        //initiate
        String projectPath = System.getProperty("user.dir");

        // pass the path to the file as a parameter
        FileReader fr =
                new FileReader(projectPath + "\\src\\main\\resources\\test.txt");

     int quantity= signService.countChars(fr);

signService.setCharStatistics(quantity);
        //quantity of input chracters



}


}