package algorytms;

import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EqualsBinaryTree {

	static class Node {
		public int value;
		public Node left;
		public Node right;

		Node(int value) {
			this.value = value;
		}
	}

	public static boolean equals(Node n1, Node n2) {
		if (n1 == null && n2 == null) {
			return true;
		}

		if (n1 == null || n2 == null) {
			return false;
		}

		if (n1.value != n2.value) {
			return false;
		}
		return equals(n1.left, n2.left) && equals(n1.right, n2.right);
	}

	public static void main(String[] args) {

		Node tree1 = new Node(1);
		tree1.left = new Node(2);
		tree1.right = new Node(3);
		tree1.right.right = new Node(3);

		Node tree2 = new Node(1);
		tree2.left = new Node(2);
		tree2.right = new Node(3);
		tree2.right.right = new Node(3);

		System.out.println(equals(tree1, tree2)); // true

		// Меняем значение в одном узле
		tree2.right.value = 4;
		System.out.println(equals(tree1, tree2)); // false

	}

}