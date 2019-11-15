import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Huffman {

   private List<Node> queue=new ArrayList<>();
   private List<String> codes=new ArrayList<>();
   private String path="";

    public List<String> getCodes() {
        return codes;
    }

    public List<Node> getQueue() {
        return queue;
    }





public Node getHuffmanTree(){
    //queue=  queue.stream().sorted((x,y)->x.freq-y.freq).collect(Collectors.toList());
    System.out.println(queue);
        while (queue.size()>1){
            Node z=new Node();
            z.right=findMinQueue();
            z.left=findMinQueue();
            z.freq=z.left.freq+z.right.freq;
            queue.add(0,z);

        }
            return queue.get(0);


}


private Node findMinQueue(){

        Node min=queue.get(0);


        for(int i=1;i<queue.size();++i)
        {
            if(min.freq<queue.get(i).freq)
                min=queue.get(i);

        }
        queue.remove(min);
        return min;

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
