package io.abhishek.designpattern.bst;

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
   * @param root
   * @param key
   * @return root Node
   */
  private Node insertRec(Node root, Key key) {
    if (root == null || key.compareTo(root.key) == 0) {
      root = new Node(key);
      return root;
    }
    int cmp = key.compareTo(root.key);
    if (cmp < 0) {
      root.left = insertRec(root.left, key);
    } else if(cmp > 0) {
      root.right = insertRec(root.right, key);
    }
    return root;
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
   * @param root
   * @param key
   * @return Node if found
   */
  private Node searchRec(Node root, Key key) {
    if (root == null || key.compareTo(root.key) == 0)
      return root;
    int cmp = key.compareTo(root.key);
    if (cmp < 0) {
      return searchRec(root.left, key);
    } else {
      return searchRec(root.right, key);
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
   * @param root
   * @param key
   * @return
   */
  Node deleteRec(Node root, Key key) {
    if (root == null)
      return null;
    int cmp = key.compareTo(root.key);
    if (cmp < 0) {
      root.left = deleteRec(root.left, key);
    } else if(cmp > 0) {
      root.right = deleteRec(root.right, key);
    } else {
      if (root.left == null)
        return root.right;
      else if(root.right == null)
        return root.left;
      else {
        Node succPar = root;
        Node succ = root.right;
        while(succ.left != null) {
          succPar = succ;
          succ = succ.left;
        }
        if(succPar != root) {
          succPar.left = succ.right;
        } else {
          succPar.right = succ.right;
        }
        root.key = succ.key;
      }
    }
    return root;
  }

  /**
   * inorder traversal of BST
   */
  public void inorder() {
    inorderRec(root);
  }

  /**
   * helper function for inorder traversal
   * @param root
   */
  private void inorderRec(Node root) {
    if (root == null)
      return;
    inorderRec(root.left);
    System.out.print(root.key.toString() + " ");
    inorderRec(root.right);
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

