public class SplayTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void insert(T data) {

        Node<T> tempNode = this.root;
        Node<T> parentNode = null;

        while (tempNode != null) {

            parentNode = tempNode;

            if (tempNode.getData().compareTo(data) < 0) {
                tempNode = tempNode.getRightNode();
            } else {
                tempNode = tempNode.getLeftNode();
            }
        }

        tempNode = new Node<T>(data);
        tempNode.setParentNode(parentNode);

        if (parentNode == null) {
            this.root = tempNode;
        } else if (parentNode.getData().compareTo(tempNode.getData()) < 0) {
            parentNode.setRightNode(tempNode);
        } else {
            parentNode.setLeftNode(tempNode);
        }

        splay(tempNode);
    }

    private void rotateLeft(Node<T> node) {
        Node<T> temp = node.right;
        if (temp != null) {
            node.right = temp.left;

            if (temp.left != null) {
                temp.left.parent = node;
            }

            temp.parent = node.parent;
        }

        if (node.parent == null) {
            this.root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }

        if (temp != null) {
            temp.left = node;
        }

        node.parent = temp;
    }

    private void rotateRight(Node<T> node) {
        Node<T> temp = node.left;
        if (temp != null) {
            node.left = temp.right;

            if (temp.right != null) {
                temp.right.parent = node;
            }
            temp.parent = node.parent;

        }

        if (node.parent == null) {
            this.root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }

        if (temp != null) {
            temp.right = node;
        }

        node.parent = temp;
    }

    @Override
    public Node<T> find(T data) {

        Node<T> tempNode = this.root;

        while (tempNode != null) {
            if (tempNode.getData().compareTo(data) < 0) {
                tempNode = tempNode.getRight();
            } else if (tempNode.getData().compareTo(data) > 0) {
                tempNode = tempNode.getLeft();
            } else {
                splay(tempNode);

                return tempNode;
            }
        }

        splay(tempNode);

        return null;
    }

    @Override
    public T getMin() {
        if(this.root != null) {
            return getMin(root);
        }
        return null;
    }

    public T getMin(Node<T> node) {
        if(node != null) {
            return getMin(node.left);
        } else {
            return node.data;
        }
    }

    @Override
    public T getMax() {
        if(this.root != null) {
            return getMin(root);
        }
        return null;
    }

    public T getMax(Node<T> node) {
        if(node != null) {
            return getMin(node.right);
        } else {
            return node.data;
        }
    }

    @Override
    public void inOrderTraversal(Node<T> node) {
        if (node.left != null) {
            inOrderTraversal(node.left);
        }

        System.out.print(node.data + " ");

        if (node.right != null) {
            inOrderTraversal(node.right);
        }
    }

    public void traverse() {
        if(this.root != null) {
            inOrderTraversal(root);
        }

    }

    private void splay(Node<T> node) {


        while (node.getParent() != null) {
            if (node.getParent().getParent() == null) {
                if (node.getParent().getLeft() == node) {
                    rotateRight(node.getParent());
                } else {
                    rotateLeft(node.getParent());
                }
            }

            else if (node.getParent().getLeft() == node
                    && node.getParent().getParent().getLeft() == node.getParent()) {
                rotateRight(node.getParent().getParent());
                rotateRight(node.getParent());
            }


            else if (node.getParent().getRight() == node
                    && node.getParent().getParent().getRight() == node.getParent()) {
                rotateLeft(node.getParent().getParent());
                rotateLeft(node.getParent());
            }

            else if (node.getParent().getLeft() == node
                    && node.getParent().getParent().getRight() == node.getParent()) {
                rotateRight(node.getParent());
                rotateLeft(node.getParent());

            }

            else {
                rotateLeft(node.getParent());
                rotateRight(node.getParent());

            }
        }


    }

    public T printRoot() {
        return root.data;
    }


    public void delete(Comparable key) {
        if (isEmpty()) {
            throw new BinarySearchTreeIsEmptyException("Cannot delete from a empty bst");
        } else {
            delete(root,key);
        }
    }

    public Node<T> delete(Node node, Comparable key) {
        if (node == null) {
            System.out.println(key + " can not be found in the Splay Tree");
            return node;
        } else {
            if (node.getData().compareTo(key) > 0) {
                return delete(node.getLeft(), key);
            } else if (node.getData().compareTo(key) < 0) {
                return delete(node.getRight(), key);
            } else {

                if (node.getRight() == null && node.getLeft() == null) {
                    if (node.parent.getLeft() == node) {
                        node.parent.left = null;
                    } else {
                        node.parent.right = null;

                    }
                    return null;
                } else if (node.getRight() == null) {

                    Node<T> temp = node.getLeft();
                    node.getLeft().parent = node.parent;
                    if (node.parent != null) {
                        if (node.parent.getLeft() == node) {
                            node.parent.left = node.getLeft();
                        } else {
                            node.parent.right = node.getLeft();

                        }
                    }
                    node =null;
                    return temp;
                } else if (node.getLeft() == null) {


                    Node<T> temp = node.getRight();
                    if (node.parent != null) {

                        if (node.parent.getLeft() == node) {
                            node.parent.left = node.getRight();
                        } else {
                            node.parent.right = node.getRight();

                        }
                    }
                    node.getRight().parent = node.parent;
                    node = null;
                    return temp;
                } else {

                    Node<T> temp = getPredessor(node.getLeft());
                    node.data = temp.data;

                    node.left = delete(node.getLeft(),temp.getData());

                }
            }
        }
        return node;
    }

    public Node getPredessor(Node node) {
        if(node.getRight() != null) {
            return getPredessor(node.getRight());
        }
        return node;
    }

    public boolean isEmpty() {
        return root==null;
    }
    
}
