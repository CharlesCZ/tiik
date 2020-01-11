package src;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;

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
     * Implementation of src.Huffman tree alg from Thomas H. Cormen Introduction to algorithms
     * @return src.Huffman tree
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
     * method creates codes based on src.Huffman tree
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
     * Way of decoding text with src.Huffman algorithm static version
     * @param encodedTree  encoded Tree like 01[A]01[G]01[T]01[C]1[X]
     * @param encodedText encoded text like 1111110111010100000
     * @return decoded text
     */
    public String decode(String encodedTree, String encodedText){
        Node root=getHuffmanTree(encodedTree);
        traversePreOrder2(root);
        return decodeHuffman(encodedText,root,root);



    }

    /**
     * Implementation of decoded src.Huffman tree
     * @return Decoded from file src.Huffman tree
     */
    Node getHuffmanTree(String treeCode) {
        return generateHuffmanSubTree(new StringCharacterIterator(treeCode));
    }



    Node generateHuffmanSubTree(CharacterIterator iterChar) {
        char ch = iterChar.current();
        iterChar.next();
        Node node = new Node();
        if (ch == '1') { // a leaf
            Sign sign = new Sign();
            sign.setCharacter(iterChar.next()); // skip "[" and get char
            iterChar.next();
            iterChar.next(); // skip "]"
            node.setSign(sign);
        } else { // can only be "0", so no IF is needed here

            node.setLeft(generateHuffmanSubTree(iterChar));
            node.setRight(generateHuffmanSubTree(iterChar));
        }
        return node;
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




    /**
     * @param encodedText encoded text
     * @param node
     * @param root needed to recure
     * @return decoded text
     */
private String decodeHuffman(String encodedText,Node node,Node root){

    if(!encodedText.isEmpty()) {
        if (node.getLeft() == null && node.getRight() == null) {
            return node.getSign().getCharacter().toString() + decodeHuffman(encodedText, root, root);
        }

        if (encodedText.charAt(0) == '0') {
            return "" + decodeHuffman(encodedText.substring(1), node.getLeft(), root);
        } else if (encodedText.charAt(0) == '1') {
            return "" + decodeHuffman(encodedText.substring(1), node.getRight(), root);
        }
    }else{
        if (node.getLeft() == null && node.getRight() == null) {
            return node.getSign().getCharacter().toString();
        }

    }
    return "";
}


}
