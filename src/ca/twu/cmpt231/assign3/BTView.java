/**
 * This class is responsible for the view - generating the generating the graphics to display a tree
 * 
 * The codes is copied from: http://www.java2s.com/ref/java/java-algorithms-binary-search-tree-animation.html 
 */
package ca.twu.cmpt231.assign3;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

class BTView extends Pane {
  private BinarySearchTree<Integer> tree = new BinarySearchTree<>();
  private double radius = 15; // Tree node radius
  private double vGap = 50; // Gap between two levels in a tree

  /**
   * constructor
   * @param tree
   */
  BTView(BinarySearchTree<Integer> tree) {
    this.tree = tree;
    setStatus("Tree is empty");
  }/*  www. j a v  a  2s.com*/

  /**
   * set message to display
   * @param msg
   */
  public void setStatus(String msg) {
    getChildren().add(new Text(20, 20, msg));
  }

  /**
   * display tree
   */
  public void displayTree() {
    this.getChildren().clear(); // Clear the pane
    if (tree.getRoot() != null) {
      // Display tree recursively
      displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
    }
  }

  /** Display a subtree rooted at position (x, y) */
  protected void displayTree(BTNodeInt<Integer> root, double x, double y, double hGap) {
    if (root.getLeft() != null) {
      // Draw a line to the left node
      getChildren().add(new Line(x - hGap, y + vGap, x, y));
      // Draw the left subtree recursively
      displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2);
    }

    if (root.getRight() != null) {
      // Draw a line to the right node
      getChildren().add(new Line(x + hGap, y + vGap, x, y));
      // Draw the right subtree recursively
      displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2);
    }

    // Display a node
    Circle circle = new Circle(x, y, radius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    getChildren().addAll(circle, new Text(x - 4, y + 4, root.getElement() + ""));
  }
}