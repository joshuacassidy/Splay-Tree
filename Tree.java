public interface Tree<T extends Comparable<T>>{
    public void insert(T data);
    public Node<T> find(T data);
    public T getMin();
    public T getMax();
    public void inOrderTraversal(Node<T> node);
    public void delete(Comparable key);
    public void traverse();
}
