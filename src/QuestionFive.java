public class QuestionFive
{
	public static void main(String[] args)
	{
		// Construct a Red-Black tree by using the following:
		// 14, 17, 11, 7, 53, 4, 13, 12, 8, 60, 19, 16, 20
		int[] x = {14, 17, 11, 7, 53, 4, 13, 12, 8, 60, 19, 16, 20};
		RedBlackBST<Integer> redBlackBST = new RedBlackBST<>();
		for (int i : x)
		{
			redBlackBST.insert(i);
		}
		System.out.println(redBlackBST.inOrder());

	}

	private static class RedBlackBST<T extends Comparable<T>>
	{
		private static final boolean RED = true;
		private static final boolean BLACK = false;
		protected int size = 0;
		private Node root;

		public String inOrder()
		{
			if (size == 0)
			{
				return "[]";
			}
			return "[" + root.inOrder() + "]";
		}

		public T search(T t)
		{
			Node x = root;
			while (x != null)
			{
				int cmp = t.compareTo(x.val);
				if (cmp == 0)
				{
					return x.val;
				}
				else if (cmp < 0)
				{
					x = x.left;
				}
				else
				{
					x = x.right;
				}
			}
			return null;
		}

		public void insert(T t)
		{
			System.out.println("Inserting " + t);
			root = insert(root, t);
			root.color = BLACK;
		}

		Node rotateLeft(Node h)
		{
			Node x = h.right;
			h.right = x.left;
			x.left = h;
			x.color = h.color;
			h.color = RED;
			return x;
		}

		Node rotateRight(Node h)
		{
			Node x = h.left;
			h.left = x.right;
			x.right = h;
			x.color = h.color;
			h.color = RED;
			return x;
		}
		void colorFlip(Node h)
		{
			h.color = !h.color;
			h.left.color = !h.left.color;
			h.right.color = !h.right.color;
		}

		boolean isRed(Node h)
		{
			return h != null ? h.color == RED : BLACK;
		}

		private Node insert(Node current, T t)
		{
			if (current == null)
			{
				size++;
				return new Node(t);
			}
			if (isRed(current.left) && isRed(current.right))
			{
				colorFlip(current);
			}

			int cmp = t.compareTo(current.val);
			if (cmp == 0)
			{
				return current;
			}
			else if (cmp < 0)
			{
				current.left = insert(current.left, t);
			}
			else
			{
				current.right = insert(current.right, t);
			}
			if (isRed(current.right) && !isRed(current.left))
			{
				current = rotateLeft(current);
			}
			if (isRed(current.left) && isRed(current.left.left))
			{
				current = rotateRight(current);
			}
			return current;
		}

		private class Node
		{
			private final T val;
			private Node left, right;
			private boolean color;

			Node(T val)
			{
				this.val = val;
				this.color = RED;
			}

			private String inOrder()
			{
				StringBuilder stringBuilder = new StringBuilder();
				if (this.left != null)
				{
					stringBuilder.append(this.left.inOrder()).append(", ");
				}
				stringBuilder.append(this);
				if (this.right != null)
				{
					stringBuilder.append(", ").append(this.right.inOrder());
				}
				return stringBuilder.toString();
			}

			@Override
			public String toString()
			{
				return "" + this.val;
			}
		}
	}
}
