import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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


           huffman.getQueue().add(new Node('X',1));
            huffman.getQueue().add(new Node('T',1));
            huffman.getQueue().add(new Node('C',1));
            huffman.getQueue().add(new Node('G',2));
huffman.getQueue().add(new Node('A',5));




            System.out.println( huffman.getQueue());
       //     ((ArrayList<Node>)huffman.getQueue()).stream().min()
          //  huffman.getQueue().add(new Node('T',1));
       //       huffman.getQueue().add(new Node('C',1));

         //   System.out.println(huffman.getQueue());

        Node root=huffman.getHuffmanTree();
     //   System.out.println(root);
    //        huffman.traversePreOrder(root);
   //     huffman.printCode(root,"");
        huffman.preorder(root,"");
        huffman.getCodes().forEach(System.out::println);


            System.out.println(huffman.preorderPath(root));

List<Node> list=new ArrayList<>();
list.add(new Node('G',3));
            list.add(new Node('X',1));
            list.add(new Node('C',2));

       list=  list.stream().sorted((x,y)->x.freq - y.freq).collect(Collectors.toList());

       list.add(0,new Node('Y',1));
            System.out.println(list);

            list=  list.stream().sorted((x,y)->x.freq - y.freq).collect(Collectors.toList());

            System.out.println(list);
}


}