public class Node<T extends Comparable<T>> {
    public Node<T> left, right, parent;
    public T data;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getLeftNode() {
        return left;
    }

    public Node<T> getRightNode() {
        return right;
    }

    public Node<T> getParentNode() {
        return parent;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }

    public void setLeftNode(Node<T> left) {
        this.left = left;
    }

    public void setRightNode(Node<T> right) {
        this.right = right;
    }

    public void setParentNode(Node<T> parent) {
        this.parent = parent;
    }
}
