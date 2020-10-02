package io.abhishek.designpattern.bst;

/**
 * class responsible for all BST operation
 */
public class BST {
  private Node root;
  public BST() {
    root = null;
  }
  /**
   * insert a Node in BST
   * @param key
   */
  public void insert(int key) {
    root = insertRec(root, key);
  }

  /**
   * helper function for inserting a node in BST.
   * @param root
   * @param key
   * @return root Node
   */
  private Node insertRec(Node root, int key) {
    if (root == null || root.val == key) {
      root = new Node(key);
      return root;
    }
    if (root.val > key) {
      root.left = insertRec(root.left, key);
    } else if(root.val < key) {
      root.right = insertRec(root.right, key);
    }
    return root;
  }

  /**
   *
   * @param key
   * @return node if found otherwise null
   */
  public Node search(int key) {
    return searchRec(root, key);
  }

  /**
   * helper function to search Node in BST
   * @param root
   * @param key
   * @return Node if found
   */
  private Node searchRec(Node root, int key) {
    if (root == null || root.val == key)
      return root;
    if (root.val > key) {
      return searchRec(root.left, key);
    } else {
      return searchRec(root.right, key);
    }
  }

  /**
   * delete a node with given key
   * @param key
   */
  public void delete(int key) {
    root = deleteRec(root, key);
  }

  /**
   * helper function for deleting a Node with given key
   * @param root
   * @param key
   * @return
   */
  Node deleteRec(Node root, int key) {
    if (root == null)
      return null;
    if (root.val > key) {
      root.left = deleteRec(root.left, key);
    } else if(root.val < key) {
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
        root.val = succ.val;
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
    System.out.print(root.val + " ");
    inorderRec(root.right);
  }

  /**
   * Driver function to test above functions
   * @param args
   */
  public static void main(String[] args) {
    BST  tree = new BST();
    tree.insert(50);
    tree.insert(30);
    tree.insert(20);
    tree.insert(40);
    tree.insert(70);
    tree.insert(60);
    tree.insert(80);
    System.out.println("Inorder traversal of the given tree");
    tree.inorder();
    Node node = tree.search(30);
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
class Node {
  int val;
  Node left;
  Node right;
  public Node(int val) {
    this.val = val;
    this.left = this.right = null;
  }
  public Node(int val, Node left, Node right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
