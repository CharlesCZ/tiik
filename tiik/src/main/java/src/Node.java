package src;

class Node{
   private Sign sign;
    private   Node left;
    private   Node right;

    public Node() {
        sign=new Sign();
        left=null;
        right=null;
    }

    public Node(char character, double freq) {
        sign=new Sign();
        sign.setCharacter(character);
        sign.setProbability(freq);
        left=null;
        right=null;
    }

    public Node(Sign sign) {
        this.sign = sign;
        left=null;
        right=null;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return " src.Node{" +
                "character=" + sign.getCharacter() +
                ", freq=" + sign.getProbability() +
                ", left=" + left +
                ", right=" + right +
                "}  ";
    }
}

