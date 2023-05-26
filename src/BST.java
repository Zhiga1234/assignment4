import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;
    public int size() {
        return size;
    }
    private class Node {
        private K key;
        private V val;
        private Node left, right;
    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }

    }
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        return x;
    }
    public V get(K key){
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        size--;
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }
    public Iterator<Node> iterator() {
        return new InOrderIterator(root);
    }

    private class InOrderIterator implements Iterator<Node> {
        private Stack<Node> stack;

        public InOrderIterator(Node root) {
            stack = new Stack<Node>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public Node next() {
            Node node = stack.pop();
            Node result = node;
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }


        public Iterator<Node> iterator() {
            return null;
        }
    }

    public void print() {
        InOrderIterator iterator = new InOrderIterator(root);
        while (iterator.hasNext()) {
            Node node = iterator.next();
            System.out.println(node.key + ": " + node.val);
        }
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
