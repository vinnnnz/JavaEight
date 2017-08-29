package com.vinz.ds;

public class BTree<T> {

	Node root;
	
	class Node {
		double data;
		Node left, right;
		
		public Node(double data) {
			this.data = data;
			left = right = null;
		}
	}
	
	BTree(double data) {
		root = new Node(data);
	}
	
	BTree() {
		root = null;
	}
	
	public boolean find(double data) {
		
		Node current = root;
		while(current != null) {
			if(current.data == data) {
				return true;
			} else if(current.data > data) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		
		return false;
	}
}
