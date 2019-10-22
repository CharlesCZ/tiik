import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static HashMap<Character,Integer> charsMap;
public static List<Sign> signs=new ArrayList<>();

    public static void main(String[] args) throws IOException {

        //initiate
        String projectPath = System.getProperty("user.dir");
        charsMap = new HashMap<> ();
        // pass the path to the file as a parameter
        FileReader fr =
                new FileReader(projectPath + "\\src\\main\\resources\\test.txt");

        int i,quantity=0;
        while ((i=fr.read()) != -1){
            countChars((char)i);
            ++quantity;
        }
        System.out.println(charsMap);

        //quantity of input chracters
        final int sizeOfChars=quantity;

      charsMap.forEach((character, integer) -> {
          Sign sign=new Sign();
          sign.setCharacter(character);
          sign.setOccurences(integer);
          sign.setProbability((double)integer/sizeOfChars);
          sign.setUnitOfInformation(unitOfInformation(sign.getProbability()));
          signs.add(sign);
      });


      signs.forEach(System.out::println);
}

    public static void countChars(char inputChar){
        if (charsMap.containsKey(inputChar)) {
            charsMap.replace(inputChar, charsMap.get(inputChar)+1);
        }else
            charsMap.putIfAbsent(inputChar,1);
    }

    public static double unitOfInformation(Double probabilityOfUnitOfInformation){

        return Math.log(1/probabilityOfUnitOfInformation)/ Math.log(2);


    }
}