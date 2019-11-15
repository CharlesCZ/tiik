import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {

   private PriorityQueue<Node> queue;
   private ArrayList<String> codes;


    public ArrayList<String> getCodes() {
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
            z.right=queue.poll();
            z.left=queue.poll();
            z.freq=z.left.freq+z.right.freq;
                queue.add(z);
        }
            return queue.peek();


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








}
