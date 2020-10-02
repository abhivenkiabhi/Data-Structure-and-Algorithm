package io.abhishek.designpattern.bst;

/**
 * GenericBST API's implementation
 * @param <Key>
 * @param <Value>
 * @author abhivenkiabhi(Abhishek Kumar)
 */
public class GenericBST<Key extends Comparable<Key>, Value> {
  private Node root;

  /**
   * Node class which encapsulates properties of Node.
   */
  private class Node {
    private Key key;
    private Value value;
    private Node left, right;

    public Node(Key key, Value value) {
      this.key = key;
      this.value = value;
      this.left = null;
      this.right = null;
    }
  }

  /**
   * public constructor which initializes root as null
   */
  public GenericBST() {
    root = null;
  }

  /**
   * insert a key-value in to BST
   * @param key the key
   * @param value the value
   * @throws IllegalArgumentException if {@Code key} is {@Code null}
   */
  public void put(Key key, Value value) {
    if (key == null) {
      throw new IllegalArgumentException("first argument to put() is null");
    }
    root = put(root, key, value);
  }

  /**
   * helper recursive function to insert key-value Pair
   * @param root
   * @param key
   * @param value
   * @return root Node
   */
  private Node put(Node root, Key key, Value value) {
    if (root == null || root.key.compareTo(key) == 0) {
      root = new Node(key, value);
      return root;
    }
    int cmp = key.compareTo(root.key);
    if (cmp < 0) {
      root.left = put(root.left, key, value);
    } else {
      root.right = put(root.right, key, value);
    }
    return root;
  }

  /**
   * Returns the value associated with the given key
   * @param key the key
   * @return value associated with the key
   */
  public Value get(Key key) {
    if (key == null) {
      throw new IllegalArgumentException("argument to get() is null");
    }
    return get(root, key);
  }

  /**
   * helper function to get the Value for a Key
   * @param root
   * @param key
   * @return value associated with the key if found else null
   */
  private Value get(Node root, Key key) {
    if (root == null)
      return null;
    int cmp = key.compareTo(root.key);
    if (cmp == 0)
      return root.value;
    else if (cmp < 0)
      return get(root.left, key);
    else
      return get(root.right, key);
  }

  /**
   * remove the key from the BST
   * @param key the key
   */
  public void remove(Key key) {
    root = remove(root, key);
  }

  /**
   * helper function to remove Node(key-value) from BST
   * @param root
   * @param key
   * @return the root
   */
  private Node remove(Node root, Key key) {
    if (root == null)
      return null;
    int cmp = key.compareTo(root.key);
    if (cmp < 0) {
      root.left = remove(root.left, key);
    } else if (cmp > 0) {
      root.right = remove(root.right, key);
    } else {
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      } else {
        Node successorParent = root;
        Node successor = root.right;
        while(successor.left != null) {
          successorParent = successor;
          successor = successor.left;
        }
        if (successorParent != root) {
          successorParent.left = successor.right;
        } else {
          successorParent.right = successor.right;
        }
        root.key = successor.key;
        root.value = successor.value;
      }
    }
    return root;
  }

  /**
   * traverse the BST and print Key-value Pair
   */
  public void traverse() {
    inorder(root);
  }

  /**
   * helper function for traversal
   * @param root
   */
  private void inorder(Node root) {
    if(root == null)
      return;
    inorder(root.left);
    System.out.println(root.key.toString() + " " + root.value.toString());
    inorder(root.right);
  }

  public static void main(String[] args) {
    GenericBST<String, Integer> tree = new GenericBST<>();
    tree.put("Abhi", 1);
    tree.put("Shivam", 3);
    tree.put("Ram", 7);
    tree.put("Shyam", 9);
    tree.put("Aditya", 12);
    tree.put("ujjwal", 17);
    tree.traverse();
    System.out.println(tree.get("Ram"));
    System.out.println(tree.get("Shayam"));
    tree.remove("Aditya");
    tree.traverse();
  }
}

