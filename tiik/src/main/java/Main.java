import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

private static final SignService signService=new SignService();

    public static void main(String[] args) throws IOException {

   /*     //initiate
        String projectPath = System.getProperty("user.dir");

        // pass the path to the file as a parameter
        FileReader fr =
                new FileReader(projectPath + "\\src\\main\\resources\\test.txt");

     int quantity= signService.countChars(fr);

signService.setCharStatistics(quantity);
        //quantity of input chracters
signService.setEntropy();
        System.out.println(signService.getEntropy());
*/

Huffman huffman=new Huffman();
huffman.setHuffmanQueue(4);
huffman.getQueue().add(new Node('A',7));
        huffman.getQueue().add(new Node('G',3));
        huffman.getQueue().add(new Node('T',1));
        huffman.getQueue().add(new Node('C',1));

     //   for(int i=0;i<4;++i)
     //       System.out.println(huffman.getQueue().poll());

        Node root=huffman.getHuffmanTree();
        System.out.println(root);
   //     huffman.printCode(root,"");
        huffman.preorder(root,"");
        huffman.getCodes().forEach(System.out::println);
}


}