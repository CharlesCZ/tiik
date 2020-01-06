import java.io.FileReader;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.round;

public class SignService {

    private   HashMap<Character,Integer> charsMap = new HashMap<> ();
    private List<Sign> signs=new ArrayList<>();
    private double entropy;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private   void countChar(char inputChar){
        if (charsMap.containsKey(inputChar)) {
            charsMap.replace(inputChar, charsMap.get(inputChar)+1);
        }else
            charsMap.putIfAbsent(inputChar,1);
    }
    public  int countChars( FileReader fr ) throws IOException {
        int quantity=0;


        CharacterIterator iter=new StringCharacterIterator(getFileContent(fr));
        for(char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
           countChar(c);
           ++quantity;
        }

     return quantity;
    }

    public String getFileContent( FileReader fr ) throws IOException {
        int i;

        StringBuilder text= new StringBuilder();
        while ((i=fr.read()) != -1){
            text.append((char) i);
        }
        text = new StringBuilder(text.toString().replaceAll("\r\n", "\n"));

        return text.toString();
    }

    /**
     * @return bits quantity from normal text
     */
    public int getBitsQuantity(){
        return content.length()*8;
    }


    /**
     * @param tree generated huffman tree like 01[A]01[G]01[T]01[C]1[X]
     * @param code encoded text like 11111101110101000000
     * @return
     */
    public int getBitsQuantity(String tree,String code) {
int quntity=code.length();
tree=tree.replaceAll("[\\[\\]]","");
for(int i=0;i<tree.length();++i){
    if(tree.charAt(i)=='0' || tree.charAt(i)=='1'){
        ++quntity;
    }else if(tree.charAt(i)!='0' && tree.charAt(i)!='1') {
        quntity=quntity+8;
    }
}

    return  quntity;
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
