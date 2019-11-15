import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.round;

public class SignService {

    private   HashMap<Character,Integer> charsMap = new HashMap<> ();
    private List<Sign> signs=new ArrayList<>();
    private double entropy;

    private   void countChar(char inputChar){
        if (charsMap.containsKey(inputChar)) {
            charsMap.replace(inputChar, charsMap.get(inputChar)+1);
        }else
            charsMap.putIfAbsent(inputChar,1);
    }
    public  int countChars( FileReader fr ) throws IOException {
        int i,quantity=0;
        while ((i=fr.read()) != -1){
            if(!Character.isWhitespace(i)) {
                countChar((char) i);
                ++quantity;
            }
        }

        return quantity;
    }
    public  double unitOfInformation(Double probabilityOfUnitOfInformation){

        return Math.log(1/probabilityOfUnitOfInformation)/ Math.log(2);


    }


    public void setCharStatistics( final int sizeOfChars){


        charsMap.forEach((character, integer) -> {
            Sign sign=new Sign();
            sign.setCharacter(character);
            sign.setOccurences(integer);
            sign.setProbability( (double)round((integer*100.0)/sizeOfChars)/100 );
            sign.setUnitOfInformation( (double)round( unitOfInformation(sign.getProbability())*100.0 )/100  );
            signs.add(sign);
        });


        signs.forEach(System.out::println);

    }

    public double getEntropy() {
        return entropy;
    }

    public void setEntropy() {

                if(signs.size()>0){
                    signs.forEach(sign -> {
                        if(sign.getProbability()>0) {
                            entropy += sign.getProbability() * unitOfInformation(sign.getProbability());
                        }
                    });


                }

          entropy= (double)round(entropy*100.0)/100;

    }

    public HashMap<Character, Integer> getCharsMap() {
        return charsMap;
    }

    public void setCharsMap(HashMap<Character, Integer> charsMap) {
        this.charsMap = charsMap;
    }

    public List<Sign> getSigns() {
        return signs;
    }

    public void setSigns(List<Sign> signs) {
        this.signs = signs;
    }
}
