public class Main {
    public static void main(String[] args) {
        Tree<Integer> splayTree = new SplayTree<>();

        splayTree.insert(10);
        splayTree.insert(5);
        splayTree.insert(-3);
        splayTree.insert(15);
        splayTree.insert(30);
        splayTree.insert(25);
        splayTree.insert(-23);
        splayTree.insert(125);
        splayTree.insert(58);
        System.out.println("The root of the tree is: "  + ((SplayTree) splayTree).printRoot());
        splayTree.delete(125);
        splayTree.traverse();

    }
}
