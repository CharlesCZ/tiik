
class Node{
    Sign sign;
    Node left;
    Node right;

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

    @Override
    public String toString() {
        return " Node{" +
                "character=" + sign.getCharacter() +
                ", freq=" + sign.getProbability() +
                ", left=" + left +
                ", right=" + right +
                "}  ";
    }
}

