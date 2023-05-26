
public class Main {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<Integer, String>();
        bst.put(5, "dormitory");
        bst.put(2, "kabanbai batyr");
        bst.put(7, "maingilik");
        bst.put(1, "el");
        bst.put(3, "alau");
        System.out.println("Size of the tree: " + bst.size());
        System.out.println("Value associated with key 2: " + bst.get(2));
        System.out.println("Value associated with key 4: " + bst.get(4));
        bst.delete(1);
        bst.delete(7);
        System.out.println("Size of the tree after deletion: " + bst.size());
        bst.print();
    }

}
