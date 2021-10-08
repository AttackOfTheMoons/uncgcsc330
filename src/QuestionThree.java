public class QuestionThree
{
	public static void main(String[] args)
	{
		// Write a program to implement the AVL Tree in Java for the given set of elements and print the
		// post-order traversal for the tree
		// 7,18,3,22,4,24,29,10,2,89
		int[] x = {7, 18, 3, 22, 4, 24, 29, 10, 2, 89};
		AVLTree<Integer> avlTree = new AVLTree<>();
		for (int i : x)
		{
			avlTree.add(i);
		}
		System.out.println(avlTree.postOrder());
	}

	static class AVLTree<T extends Comparable<T>>
	{
		protected int size;
		private Node<T> root;

		AVLTree()
		{
			size = 0;
		}

		public String postOrder()
		{
			if (size == 0)
			{
				return "[]";
			}
			return "[" + root.postOrder() + "]";

		}

		private void swapHeights(Node<T> parentNode, Node<T> childNode)
		{
			parentNode.updateHeight();
			childNode.updateHeight();
		}

		private Node<T> rotateRight(Node<T> node)
		{
			Node<T> temp = node.left;
			node.left = temp.right;
			temp.right = node;
			swapHeights(node, temp);
			return temp;
		}

		private Node<T> rotateLeft(Node<T> node)
		{
			Node<T> temp = node.right;
			node.right = temp.left;
			temp.left = node;
			swapHeights(node, temp);
			return temp;
		}

		private Node<T> rotateRightLeft(Node<T> current)
		{
			current.right = rotateRight(current.right);
			current = rotateLeft(current);
			return current;
		}

		private Node<T> rotateLeftRight(Node<T> current)
		{
			current.left = rotateLeft(current.left);
			current = rotateRight(current);
			return current;
		}

		public void add(T t)
		{
			if (root == null)
			{
				root = new Node<>(t);
			}
			else
			{
				Node<T> newRoot = insert(root, new Node<>(t));
				if (newRoot == null)
				{
					return;
				}
				root = newRoot;
			}
			size++;
		}

		private Node<T> insert(Node<T> current, Node<T> newNode)
		{
			if (current == null)
			{
				return newNode;
			}
			int cmp = current.val.compareTo(newNode.val);
			// duplicate elements cannot be inserted
			assert cmp != 0;
			if (cmp > 0)
			{
				current.left = insert(current.left, newNode);
			}
			else
			{
				current.right = insert(current.right, newNode);
			}
			current.updateHeight();
			if (current.balanceFactor() > 1)
			{
				if (current.left.balanceFactor() < 0)
				{
					current = rotateLeftRight(current);
				}
				else
				{
					current = rotateRight(current);
				}
			}
			else if (current.balanceFactor() < -1)
			{
				if (current.right.balanceFactor() > 0)
				{
					current = rotateRightLeft(current);
				}
				else
				{
					current = rotateLeft(current);
				}
			}
			return current;
		}

		private static class Node<T extends Comparable<T>>
		{
			protected int height;
			protected T val;
			Node<T> left;
			Node<T> right;

			Node(T t)
			{
				this.val = t;
			}

			public int balanceFactor()
			{
				int leftHeight = -1;
				int rightHeight = -1;
				if (left != null)
				{
					leftHeight = left.height;
				}
				if (right != null)
				{
					rightHeight = right.height;
				}
				return leftHeight - rightHeight;
			}

			public void updateHeight()
			{
				if (left == null && right == null)
				{
					height = 0;
					return;
				}
				if (left == null)
				{
					height = 1 + right.height;
					return;
				}
				if (right == null)
				{
					height = 1 + left.height;
					return;
				}
				height = 1 + Integer.max(left.height, right.height);
			}

			public String postOrder()
			{
				StringBuilder stringBuilder = new StringBuilder();
				if (this.left != null)
				{
					stringBuilder.append(this.left.postOrder());
					stringBuilder.append(", ");
				}
				if (this.right != null)
				{
					stringBuilder.append(this.right.postOrder());
					stringBuilder.append(", ");
				}
				stringBuilder.append(this);
				return stringBuilder.toString();
			}

			@Override
			public String toString()
			{
				return "Node(" + val + ")";
			}
		}
	}
}
