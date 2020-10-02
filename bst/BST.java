package bst;

/**
 * BST class having some generic Key
 * @param <Key>
 * @author abhivenkiabhi(Abhishek Kumar)
 */
public class BST<Key extends Comparable<Key> > {
  private Node root;
  /**
   * Node class which encapsulates properties of Node
   */
  class Node {
    private Key key;
    private Node left, right;

    public Node(Key key) {
      this.key = key;
      this.left = this.right = null;
    }

    public Node(Key key, Node left, Node right) {
      this.key = key;
      this.left = left;
      this.right = right;
    }
  }
  public BST() {
    root = null;
  }
  /**
   * insert a Node in BST
   * @param key
   */
  public void insert(Key key) {
    root = insertRec(root, key);
  }

  /**
   * helper function for inserting a node in BST.
   * @param node
   * @param key
   * @return root Node
   */
  private Node insertRec(Node node, Key key) {
    if (node == null || key.compareTo(node.key) == 0) {
      node = new Node(key);
      return node;
    }
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = insertRec(node.left, key);
    } else if(cmp > 0) {
      node.right = insertRec(node.right, key);
    }
    return node;
  }

  /**
   *
   * @param key the key
   * @return node if found otherwise null
   */
  public Node search(Key key) {
    return searchRec(root, key);
  }

  /**
   * helper function to search Node in BST
   * @param node
   * @param key
   * @return Node if found
   */
  private Node searchRec(Node node, Key key) {
    if (node == null || key.compareTo(node.key) == 0)
      return node;
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      return searchRec(node.left, key);
    } else {
      return searchRec(node.right, key);
    }
  }

  /**
   * delete a node with given key
   * @param key the key
   */
  public void delete(Key key) {
    root = deleteRec(root, key);
  }

  /**
   * helper function for deleting a Node with given key
   * @param node
   * @param key
   * @return
   */
  Node deleteRec(Node node, Key key) {
    if (node == null)
      return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = deleteRec(node.left, key);
    } else if(cmp > 0) {
      node.right = deleteRec(node.right, key);
    } else {
      if (node.left == null)
        return node.right;
      else if(node.right == null)
        return node.left;
      else {
        Node succPar = node;
        Node succ = node.right;
        while(succ.left != null) {
          succPar = succ;
          succ = succ.left;
        }
        if(succPar != node) {
          succPar.left = succ.right;
        } else {
          succPar.right = succ.right;
        }
        node.key = succ.key;
      }
    }
    return node;
  }

  /**
   * inorder traversal of BST
   */
  public void inorder() {
    inorderRec(root);
  }

  /**
   * helper function for inorder traversal
   * @param node
   */
  private void inorderRec(Node node) {
    if (node == null)
      return;
    inorderRec(node.left);
    System.out.print(node.key.toString() + " ");
    inorderRec(node.right);
  }

  /**
   * Driver function to test above functions
   * @param args
   */
  public static void main(String[] args) {
    BST<Integer> tree = new BST();
    tree.insert(50);
    tree.insert(30);
    tree.insert(20);
    tree.insert(40);
    tree.insert(70);
    tree.insert(60);
    tree.insert(80);
    System.out.println("Inorder traversal of the given tree");
    tree.inorder();
    BST.Node node = tree.search(30);
    if(node == null) {
      System.out.println("node not found");
    } else {
      System.out.println("element found");
    }
    tree.delete(50);
    System.out.println("Inorder traversal of the given tree");
    tree.inorder();
    node = tree.search(50);
    if(node == null) {
      System.out.println("node not found");
    } else {
      System.out.println("element found");
    }
  }

}

