import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Part of the task was to round up to 2 decimal places
 */
public class Main {

private static final SignService signService=new SignService();

    public static void main(String[] args) throws IOException {

        //initiate
        String projectPath = System.getProperty("user.dir");

        // pass the path to the file as a parameter
        FileReader fr =
                new FileReader(projectPath + "\\src\\main\\resources\\test.txt");

     int quantity= signService.countChars(fr);
            System.out.println(quantity);
            System.out.println(signService.getCharsMap());
signService.setCharStatistics(quantity);
        //quantity of input chracters
signService.setEntropy();
        System.out.println(signService.getEntropy());


Huffman huffman=new Huffman();
huffman.setHuffmanQueue(10);

         //  huffman.getQueue().add(new Node('X',1));
       //     huffman.getQueue().add(new Node('T',1));
        //    huffman.getQueue().add(new Node('C',1));
        //    huffman.getQueue().add(new Node('G',2));
//huffman.getQueue().add(new Node('A',5));
huffman.insertSignsToQueue(signService.getSigns());

       Node root=huffman.getHuffmanTree();
            huffman.traversePreOrder(root);
           huffman.preorder(root,"");
           huffman.getCodes().forEach(System.out::println);
            System.out.println(huffman.preorderPath(root));


}


}