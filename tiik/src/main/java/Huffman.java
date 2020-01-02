import sun.misc.Queue;

import java.util.*;
import java.util.stream.Collectors;
import static java.lang.Math.round;
public class Huffman {

   private PriorityQueue<Node> queue;
   private List<String> codes;


   public PriorityQueue<Node> insertSignsToQueue(List<Sign> signs){

       signs.forEach(sign -> {
           queue.add(new Node(sign));

       });

       return queue;
   }



    public List<String> getCodes() {
        return codes;
    }

    public PriorityQueue<Node> getQueue() {
        return queue;
    }


    public void setHuffmanQueue(int size) {

        queue = new PriorityQueue<>(size,(x,y)->{
            return  Double.compare(x.getSign().getProbability(),y.getSign().getProbability());
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
            z.setLeft(queue.poll());
            System.out.println(z.getLeft().getSign().getCharacter());
            System.out.println(z.getLeft().getSign().getProbability());
            z.setRight(queue.poll());
            System.out.println("pomiedzy");
            System.out.println(z.getRight().getSign().getCharacter());
            System.out.println(z.getRight().getSign().getProbability());
            System.out.println("z.sign.probability");
            z.getSign().setProbability((double)round( 100*(z.getLeft().getSign().getProbability()+z.getRight().getSign().getProbability())  )/100);
            System.out.println( z.getSign().getProbability());
            System.out.println("koniec");
            queue.add(z);
        }
        return queue.peek();


    }



    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.getSign().getCharacter()+node.getSign().getProbability()+" ");
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }

    /**
     * method creates codes based on Huffman tree
     * @param node  Tree root
     * @param c empty string like ""
     */
    public void preorder(Node node,String c){


        if(node.getLeft()==null && node.getRight()==null){

            codes.add(String.valueOf(node.getSign().getCharacter()));
            codes.add(c);
            return;
        }


    preorder(node.getLeft(),c+"0");
        preorder(node.getRight(),c+"1");


        }

    /**
     * Implementation of decoded Huffman tree
     * @return Decoded from file Huffman tree
     */
    public Node getHuffmanTree(String treeCode){
        Node root=new Node();

        int i=0;
        while (!treeCode.isEmpty()){
            if(treeCode.charAt(i)=='1'){
                generateHuffmanSubTree(treeCode.substring(0,i+2),root);
                treeCode=treeCode.substring(i+2);
                i=0;
                break;

            }else ++i;
        }


        while (!treeCode.isEmpty()){
            if(treeCode.charAt(i)=='1'){
                Node subTree=new Node();
                generateHuffmanSubTree(treeCode.substring(0,i+2),subTree);
                treeCode=treeCode.substring(i+2);
                i=0;
                //tree merge
                huffmanTreeMerge(root,subTree);
            }else ++i;
        }
        traversePreOrder2(root);
        return root;


    }
    private void huffmanTreeMerge(Node root,Node node){
        if(root.getLeft()!=null && root.getRight()==null){
            root.setRight(node);
            return;
        }else if(root.getLeft()==null && root.getRight()==null){
            return;
        }

        huffmanTreeMerge(root.getLeft(),node);
        huffmanTreeMerge(root.getRight(),node);


    }

    private void generateHuffmanSubTree(String treeCode,Node node){

        if(!treeCode.isEmpty()) {
            if (node == null) {
                node = new Node();
            }

            if (treeCode.charAt(0) == '1') {

                Sign sign = new Sign();
                sign.setCharacter(treeCode.charAt(1));
                //    System.out.println(treeCode.charAt(1));
                node.setSign(sign);
                return;
            }else if(treeCode.charAt(0) == '0'){
                node.setLeft(new Node());
                generateHuffmanSubTree(treeCode.substring(1),node.getLeft());
            }
        }
    }

    private void traversePreOrder2(Node node) {
        if (node != null) {

            if(node.getLeft()==null && node.getRight()==null)
                System.out.print("    "+"1"+node.getSign().getCharacter()+"    ");
            else
                System.out.print("0");
            traversePreOrder2(node.getLeft());
            traversePreOrder2(node.getRight());
        }
    }

    /**
     * @param node Tree root
     * @return string with which can be used to recreate HuffmanTree
     */
    public String preorderPath(Node node){


        if(node.getLeft()==null && node.getRight()==null){

            return "1"+"["+node.getSign().getCharacter()+"]";

        }


      return "0"+  preorderPath(node.getLeft())+  preorderPath(node.getRight());


    }


    /**
     * @param content to encode
     * @return encoded text
     */
public String encode(String content){
String encodedString="";
        for(int i=0;i<content.length();++i){
            char letterToCode=content.charAt(i);

            for(int j=0;j<codes.size();j=j+2){
                if(codes.get(j).charAt(0)==letterToCode){
                    encodedString+=codes.get(j+1);
                    break;
                }
            }

        }

return encodedString;
}


}
