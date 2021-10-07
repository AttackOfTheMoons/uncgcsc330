public class QuestionThree
{
	public static void main(String[] args)
	{
		AVLTree<Integer> avlTree = new AVLTree<>();
		int[] x = {4, 2, 1, 3, 5, 6, 7};
		for (int i : x)
		{
			avlTree.add(i);
		}
		System.out.println(avlTree.postOrder());
	}

	private static class AVLTree<T extends Comparable<T>>
	{
		private Node<T> root;
		protected int size;

		AVLTree()
		{
			size = 0;
		}

		public boolean search(T t)
		{
			if (root == null)
			{
				return false;
			}
			Node<T> current = root;
			while (current != null)
			{
				if (current.val.compareTo(t) == 0)
				{
					return true;
				}
				else if (current.val.compareTo(t) > 0)
				{
					current = current.left;
				}
				else
				{
					current = current.right;
				}
			}
			return false;
		}

		public String postOrder()
		{
			if (size == 0)
			{
				return "[]";
			}
			return "[" + root.postOrder() + "]";

		}

		private Node<T> rotateRight(Node<T> node)
		{
			Node<T> temp = node.left;
			node.left = temp != null ? temp.right : null;
			temp.right = node;
			return temp;
		}

		private Node<T> rotateLeft(Node<T> node)
		{
			Node<T> temp = node.right;
			node.right = temp.left;
			temp.left = node;
			return temp;
		}

		public boolean add(T t)
		{
			System.out.println("Adding " + t);
			if (root == null)
			{
				root = new Node<>(t);
			}
			else
			{
				Node<T> newRoot = insert(root, new Node<>(t));
				if (newRoot == null)
				{
					return false;
				}
				root = newRoot;
			}
			size++;
			return true;
		}

		private Node<T> insert(Node<T> current, Node<T> newNode)
		{
			if (current == null)
			{
				return newNode;
			}
			int cmp = current.val.compareTo(newNode.val);
			if (cmp == 0)
			{
				return null;
			}
			if (cmp > 0)
			{
				// System.out.println("left insert " + newNode);
				current.left = insert(current.left, newNode);
			}
			else
			{
				current.right = insert(current.right, newNode);
				// System.out.println("right insert of " + current.right);
				if (current.right.balanceFactor() < -1)
				{
					if (newNode.val == current.right.right.val)
					{
						current.right = rotateLeft(current.right);
					}
					else
					{
						current.right.right = rotateRight(current.right.right);
						current.right = rotateLeft(current.right);
					}
				}
			}
			return current;
		}

		private static class Node<T extends Comparable<T>>
		{
			T val;
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
					leftHeight = left.getHeight();
				}
				if (right != null)
				{
					rightHeight = right.getHeight();
				}
				return leftHeight - rightHeight;
			}

			public int getHeight()
			{
				if (left == null && right == null)
				{
					return 0;
				}
				if (left == null)
				{
					return 1 + right.getHeight();
				}
				if (right == null)
				{
					return 1 + left.getHeight();
				}
				return 1 + Integer.max(left.getHeight(), right.getHeight());
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
				return "Node( val: " + this.val + " balance: " + this.balanceFactor() + ")";
			}

		}

	}
}
