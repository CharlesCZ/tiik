import sun.misc.Queue;

import java.util.*;
import java.util.stream.Collectors;

public class Huffman {

   private PriorityQueue<Node> queue;
   private List<String> codes;
   private String path="";

    public List<String> getCodes() {
        return codes;
    }

    public PriorityQueue<Node> getQueue() {
        return queue;
    }


    public void setHuffmanQueue(int size) {

        queue = new PriorityQueue<>(size,(x,y)->{
            return  Double.compare(x.sign.probability,y.sign.probability);
  });
        codes=new ArrayList<>();
    }


    /**
     * Implementation of Huffman tree alg from Thomas H. Cormen Introduction to algorithms
     * @return Huffman tree
     */
    public Node getHuffmanTree(){

        while (queue.size()>1){
            Node z=new Node();
            z.left=queue.poll();
            System.out.println(z.left.sign.character);
            System.out.println(z.left.sign.probability);
            z.right=queue.poll();
            System.out.println("pomiedzy");
            System.out.println(z.right.sign.character);
            System.out.println(z.right.sign.probability);
            System.out.println("z.sign.probability");
            z.sign.probability=z.left.sign.probability+z.right.sign.probability;
            System.out.println( z.sign.probability);
            System.out.println("koniec");
            queue.add(z);
        }
        return queue.peek();


    }



    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.sign.character+node.sign.probability+" ");
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    /**
     * method creates codes based on Huffman tree
     * @param node  Tree root
     * @param c empty string like ""
     */
    public void preorder(Node node,String c){


        if(node.left==null && node.right==null){

            codes.add(String.valueOf(node.sign.character));
            codes.add(c);
            return;
        }


    preorder(node.left,c+"0");
        preorder(node.right,c+"1");


        }


    /**
     * @param node Tree root
     * @return string with which can be used to recreate HuffmanTree
     */
    public String preorderPath(Node node){


        if(node.left==null && node.right==null){

            return "1"+"["+node.sign.character+"]";

        }


      return "0"+  preorderPath(node.left)+  preorderPath(node.right);


    }





}
