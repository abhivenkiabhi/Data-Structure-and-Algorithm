package io.abhishek.designpattern.bst;

/**
 * RedBlackTree Impl and Insert and search Apis
 * @param <Key>
 * @author abhivenkiabhi(Abhishek Kumar)
 */
public class RedBlackTree<Key extends Comparable<Key> > {
  private Node root;
  /**
   * Represents the color
   */
  public enum Color {
    RED,
    BLACK
  }

  /**
   * encapsulated the properties of Node.
   */
 private class Node {
    private Key key;
    private Color color;
    private Node left, right, parent;

    public Node(Key key, Color color) {
      this.key = key;
      this.color = color;
      this.left = this.right = this.parent = null;
    }
  }

  /**
   * public constructor for RedBlackTree
   */
  public RedBlackTree() {
    root = null;
  }

  /**
   * function to insert key in RBT.
   * @param key the key
   */
  public void insert(Key key) {
    insertHelper(root, key);
    root.color = Color.BLACK;
    root.parent = null;
  }

  /**
   * Api to search the Key in the Tree
   * @param key the key
   * @return the Node having the key
   */
  public Node search(Key key) {
    return searchRec(root, key);
  }

  /**
   * helper function for searching the key in Tree
   * @param node the root Node
   * @param key the key
   * @return the node having that key
   */
  public Node searchRec(Node node, Key key) {
    if (node == null)
      return null;
    int cmp = key.compareTo(node.key);
    if (cmp == 0)
      return node;
    else if (cmp < 0) {
      return searchRec(node.left, key);
    } else {
      return searchRec(node.right, key);
    }
  }

  /**
   * helper function for inserting the key in Tree
   * @param node the root Node
   * @param key the key
   */
  private void insertHelper(Node node, Key key) {
    Node newNode = new Node(key, Color.RED);
    if (root == null) {
      root = new Node(key, Color.RED);
      return;
    } else {
      Node cur = node;
      while(true) {
        int cmp = key.compareTo(cur.key);
        if (cmp < 0) {
          if (cur.left == null) {
            cur.left = newNode;
            newNode.parent = cur;
            break;
          } else {
            cur = cur.left;
          }
        } else if (cmp >= 0) {
          if (cur.right == null) {
            cur.right = newNode;
            newNode.parent = cur;
            break;
          } else {
            cur = cur.right;
          }
        }
      }
      fixTree(newNode);
    }
  }

  /**
   * Balance the Tree to make sure it follows RBT properties
   * @param node which needs to be checked for balancing
   */
  private void fixTree(Node node) {
    while(node.parent != null && Color.RED.equals(node.parent.color)) { // check until parent becomes Black
      Node uncle = null;
      if (node.parent.parent != null && node.parent == node.parent.parent.left) { // parent is left of GrandParent
        uncle = node.parent.parent.right;

        if (uncle != null && Color.RED.equals(uncle.color)) { // uncle is red then flip colors of uncle,parent,GrandParent
          node.parent.color = Color.BLACK;
          uncle.color = Color.BLACK;
          node.parent.parent.color = Color.RED;
          node = node.parent.parent;
          continue;
        }

        if (node == node.parent.right) { // if it's making a triangle, rotate along parent
          //left rotation as well as right rotation
          node = node.parent;
          rotateLeft(node);
        }
        node.parent.color = Color.BLACK;
        node.parent.parent.color = Color.RED;
        rotateRight(node.parent.parent); // rotate around GrandParent
      } else {
        uncle = (node.parent.parent != null) ? node.parent.parent.left : null;

        if (uncle != null && Color.RED.equals(uncle.color)) {
          node.parent.color = Color.BLACK;
          uncle.color = Color.BLACK;
          node.parent.parent.color = Color.RED;
          node = node.parent.parent;
          continue;
        }

        if (node == node.parent.left) {
         node = node.parent;
         rotateRight(node);
        }
        node.parent.color = Color.BLACK;
        node.parent.parent.color = Color.RED;
        rotateLeft(node.parent.parent);
      }
    }
  }

  /**
   * rotate the tree to left around the node
   * @param node the node around which rotation will occur
   */
  private void rotateLeft(Node node) {
    if (node.parent != null) {
      if (node == node.parent.left) {
        node.parent.left = node.right;
      } else {
        node.parent.right = node.right;
      }
      node.right.parent = node.parent;
      node.parent = node.right;
      if (node.right.left != null) {
        node.right.left.parent = node;
      }
      node.right = node.right.left;
      node.parent.left = node;
    } else {
      Node right = node.right;
      root.right = right.left;
      if (right.left != null) {
        right.left.parent = root;
      }
      root.parent = right;
      right.left = root;
      right.parent = null;
      root = right;
    }
  }

  /**
   * rotate the tree to right around the node
   * @param node the node around which rotation will occur
   */
  private void rotateRight(Node node) {
    if (node.parent != null) {
      if (node == node.parent.left) {
        node.parent.left = node.left;
      } else {
        node.parent.right = node.left;
      }
      node.left.parent = node.parent;
      node.parent = node.left;
      if (node.left.right != null) {
        node.left.right.parent = node;
      }
      node.left = node.left.right;
      node.parent.right = node;
    } else {
      Node left = node.left;
      root.left = left.right;
      if (left.right != null) {
        left.right.parent = root;
      }
      root.parent = left;
      left.right = root;
      left.parent = null;
      root = left;
    }
  }

  /**
   * return the size of the tree
   * @return the size of the tree
   */
  public int size() {
    return size(root);
  }

  /**
   * return the size of the subtree rooted with node
   * @param node
   * @return the size
   */
  public int size(Node node) {
    if (node == null)
      return 0;
    int leftSize = 1 + size(node.left);
    int rightSize = 1 + size(node.right);
    return Math.max(leftSize, rightSize);
  }

  /**
   * traverse the node and return Black Node count
   * @param node
   * @return
   */
  public int blackNode(Node node) {
   if (node == null)
     return 0;
   int cnt = 0;
   cnt = (Color.BLACK.equals(node.color)) ? 1 : 0;
   return cnt + blackNode(node.left);
  }

  public static void main(String[] args) {
    RedBlackTree<Integer> rbt = new RedBlackTree<>();
    for(int i = 1; i < 10000; i++) {
      rbt.insert(i);
      System.out.println("tree size " + rbt.size());
      System.out.println("root " + rbt.root.key.toString());
      System.out.println("left size " + rbt.size(rbt.root.left));
      System.out.println("right size " + rbt.size(rbt.root.right));
    }
    int blackRoot = rbt.blackNode(rbt.root);
    int blackLeft = rbt.blackNode(rbt.root.left);
    int blackRight = rbt.blackNode(rbt.root.right);
    System.out.println("blackRoot " + blackRoot + " blackLeft {} " + blackLeft + " blackRight {} " + blackRight);

    System.out.println("------------------------");
  }

}
