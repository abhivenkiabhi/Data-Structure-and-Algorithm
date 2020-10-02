package bst;

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
   * @param node
   * @param key
   * @param value
   * @return root Node
   */
  private Node put(Node node, Key key, Value value) {
    if (node == null || node.key.compareTo(key) == 0) {
      node = new Node(key, value);
      return node;
    }
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = put(node.left, key, value);
    } else {
      node.right = put(node.right, key, value);
    }
    return node;
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
   * @param node
   * @param key
   * @return value associated with the key if found else null
   */
  private Value get(Node node, Key key) {
    if (node == null)
      return null;
    int cmp = key.compareTo(node.key);
    if (cmp == 0)
      return node.value;
    else if (cmp < 0)
      return get(node.left, key);
    else
      return get(node.right, key);
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
   * @param node
   * @param key
   * @return the root
   */
  private Node remove(Node node, Key key) {
    if (node == null)
      return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = remove(node.left, key);
    } else if (cmp > 0) {
      node.right = remove(node.right, key);
    } else {
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      } else {
        Node successorParent = node;
        Node successor = node.right;
        while(successor.left != null) {
          successorParent = successor;
          successor = successor.left;
        }
        if (successorParent != node) {
          successorParent.left = successor.right;
        } else {
          successorParent.right = successor.right;
        }
        node.key = successor.key;
        node.value = successor.value;
      }
    }
    return node;
  }

  /**
   * traverse the BST and print Key-value Pair
   */
  public void traverse() {
    inorder(root);
  }

  /**
   * helper function for traversal
   * @param node
   */
  private void inorder(Node node) {
    if(node == null)
      return;
    inorder(node.left);
    System.out.println(node.key.toString() + " " + node.value.toString());
    inorder(node.right);
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

