import sun.misc.Queue;

import java.util.*;
import java.util.stream.Collectors;

public class Huffman {

   private PriorityQueue<Node> queue;
   private List<String> codes=new ArrayList<>();
   private String path="";

    public List<String> getCodes() {
        return codes;
    }

    public PriorityQueue<Node> getQueue() {
        return queue;
    }


    public void setHuffmanQueue(int size) {

        queue = new PriorityQueue<>(size,(x,y)->x.freq - y.freq);
        codes=new ArrayList<>();
    }



    public Node getHuffmanTree(){

        while (queue.size()>1){
            Node z=new Node();
            z.left=queue.poll();
            System.out.println(z.left.character);
            System.out.println(z.left.freq);
            z.right=queue.poll();
            System.out.println("pomiedzy");
            System.out.println(z.right.character);
            System.out.println(z.right.freq);
            System.out.println("z.freq");
            z.freq=z.left.freq+z.right.freq;
            System.out.println( z.freq);
            System.out.println("koniec");
            queue.add(z);
        }
        return queue.peek();


    }



    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.character+node.freq+" ");
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void preorder(Node node,String c){


        if(node.left==null && node.right==null){

            codes.add(String.valueOf(node.character));
            codes.add(c);
            return;
        }


    preorder(node.left,c+"0");
        preorder(node.right,c+"1");


        }


    public String preorderPath(Node node){


        if(node.left==null && node.right==null){

            return "1"+"["+node.character+"]";

        }


      return "0"+  preorderPath(node.left)+  preorderPath(node.right);


    }





}
