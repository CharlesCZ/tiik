
    class Node{
        char character;
        int freq;
        Node left;
        Node right;

        public Node() {
            left=null;
            right=null;
        }

        public Node(char character, int freq) {
            this.character = character;
            this.freq = freq;
            left=null;
            right=null;
        }

        @Override
        public String toString() {
            return " Node{" +
                    "character=" + character +
                    ", freq=" + freq +
                    ", left=" + left +
                    ", right=" + right +
                    "}  ";
        }
    }




