package io.abhishek.designpattern.bst;

/**
 * Avl Tree impl and insert, search and delete Api
 * @param <Key>
 * @author abhivenkiabhi(Abhishek Kumar)
 */
public class AvlTree<Key extends Comparable<Key> > {
  private Node root;

  /**
   * Node class, it encapsulated the behaviour of Node
   */
  private class Node {
    Key key;
    private int height;
    private Node left, right;

    public Node(Key key) {
      this.key = key;
      height = 1;
      this.left = this.right = null;
    }
  }

  /**
   * constructor for the Avl tree
   */
  public AvlTree() {
    root = null;
  }

  /**
   * get the balance of the node
   * @param node
   * @return the balance of the node
   */
  private int getBalance(Node node) {
    if (node == null)
      return 0;
    return getHeight(node.left) - getHeight(node.right);
  }

  /**
   * get the height of the node
   * @param node
   * @return height of the node
   */
  public int getHeight(Node node) {
    if (node == null)
      return 0;
    return node.height;
  }

  /**
   * insert a key in to Tree
   * @param key the key
   */
  public void insert(Key key) {
    root = insertRec(root, key);
  }

  /**
   * delete key from tree
   * @param key the key
   */
  public void delete(Key key) {
    root = deleteNode(root, key);
  }

  /**
   * search the key in the tree
   * @param key the key
   * @return node for the key
   */
  public Node search(Key key) {
    return searchNode(root, key);
  }

  /**
   * helper function for searching the key
   * @param node the root node
   * @param key the key
   * @return node for the key
   */
  private Node searchNode(Node node, Key key) {
    if (node == null)
      return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0)
      return searchNode(node.left, key);
    else if (cmp > 0)
      return searchNode(node.right, key);
    else
      return node;
  }

  /**
   * helper function for deleting the Node having the key from the tree
   * @param node
   * @param key
   * @return root node
   */
  private Node deleteNode(Node node, Key key) {
    if (node == null)
      return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = deleteNode(node.left, key);
    } else if (cmp > 0) {
      node.right  = deleteNode(node.right, key);
    } else {
      if (node.left == null || node.right == null) {
        Node temp = null;
        if (node.left == null)
          temp = node.right;
        else
          temp = node.left;

        if (temp == null) {
          temp = node;
          node = null;
        }
        else
          node = temp;
      } else {
        Node temp = inorderSuccessor(node.right);
        node.key = temp.key;
        node.right = deleteNode(node.right, temp.key);
      }
    }

    if (node == null)
      return null;

    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    int balance = getBalance(node);
    //Left-Left case
    if (balance > 1 && getBalance(node.left) >= 0) {
      return rotateRight(node);
    }
    //Left-Right case
    if (balance > 1 && getBalance(node.left) < 0) {
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    }
    //Right-right case
    if (balance < 1 && getBalance(node.right) >= 0) {
      return rotateLeft(node);
    }
    //Right-Left case
    if (balance < 1 && getBalance(node.right) < 0) {
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    }
    return node;
  }

  /**
   * get the inorderSuccessor
   * @param node
   * @return the node which is inorder successor
   */
  private Node inorderSuccessor(Node node) {
    if (node == null)
      return null;
    while(node.left != null) {
      node = node.left;
    }
    return node;
  }

  /**
   * helper function for inserting the key in tree
   * @param node the root node
   * @param key
   * @return the root node
   */
  private Node insertRec(Node node, Key key) {
    if (node == null) {
      return new Node(key);
    }
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = insertRec(node.left, key);
    } else if (cmp > 0){
      node.right = insertRec(node.right, key);
    }

    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    int balance = getBalance(node);
    //left-left Case
    if (balance > 1 && key.compareTo(node.left.key) < 0) {
      return rotateRight(node);
    }
   //left-right case
    if (balance > 1 && key.compareTo(node.left.key) > 0) {
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    }
    //right-right case
    if (balance < -1 && key.compareTo(node.right.key) > 0) {
      return rotateLeft(node);
    }
    //right-left case
    if (balance < -1 && key.compareTo(node.right.key) < 0) {
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    }

    return node;
  }

  /**
   * rotate the tree to right around the node
   * @param node the node along which rotation will be performed
   * @return the root of the subtree
   */
  private Node rotateRight(Node node) {
    //copy left
    Node x = node.left;
    Node xRight = x.right;
    //rotate
    node.left = xRight;
    x.right = node;
    //update height
    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

    return x;
  }

  /**
   * rotate the tree to left around the node
   * @param node the node along which rotation will be performed
   * @return the new root of the subtree
   */
  private Node rotateLeft(Node node) {
    //copy right
    Node x = node.right;
    Node xLeft =  x.left;
    //rotate
    node.right = xLeft;
    x.left = node;
    //update Height
    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

    return x;
  }

  public static void main(String[] args) {
    AvlTree<Integer> avl = new AvlTree<>();
//    for (int i = 0;i < 50;i++) {
//      avl.insert(i);
//    }
    avl.insert(5);
    avl.insert(3);
    avl.insert(4);
    avl.delete(4);
    System.out.println(avl.root.key.toString());
    System.out.println(avl.getHeight(avl.root));
  }
}
