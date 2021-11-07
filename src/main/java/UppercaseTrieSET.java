class UppercaseTrieSET {
    private static final int R = 'Z' - 'A' + 1;
    private Node root;
    private int n;

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        } else {
            Node x = get(root, key, 0);
            return x != null && x.isString;
        }
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        } else if (d == key.length()) {
            return x;
        } else {
            char c = key.charAt(d);
            return get(x.next(c), key, d + 1);
        }
    }

    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to add() is null");
        } else {
            root = add(root, key, 0);
        }
    }

    private Node add(Node x, String key, int d) {
        if (x == null) {
            x = new Node();
        }

        if (d == key.length()) {
            if (!x.isString) {
                ++n;
            }

            x.isString = true;
        } else {
            char c = key.charAt(d);
            x.setNext(c, add(x.next(c), key, d + 1));
        }

        return x;
    }

    public boolean hasAnyKeyWithPrefix(String prefix) {
        Node x = get(root, prefix, 0);
        return x != null;
    }

     private static class Node {
        private final Node[] next;
        private boolean isString;

        private Node() {
            next = new Node[R];
        }

        Node next(char c) {
            return next[c - 'A'];
        }

         void setNext(char c, Node node) {
             next[c - 'A'] = node;
         }
     }
}

