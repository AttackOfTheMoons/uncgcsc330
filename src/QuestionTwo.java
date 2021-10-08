public class QuestionTwo
{
	public static void main(String[] args) throws Exception
	{
		// Write a program to find the maximum depth or height of a tree in java
		QuestionTwoTree questionTwoTree = new QuestionTwoTree();
		System.out.println("The maximum depth or height of question two's tree is " + questionTwoTree.getHeight());
	}

	private static class QuestionTwoTree
	{
		private final Node<Integer> root;

		QuestionTwoTree()
		{
			this.root = new Node<>(2);
			root.children = newArray(7, 5);
			root.children[0].children = newArray(2, 10, 6);
			root.children[0].children[2].children = newArray(5, 11);
			root.children[1].children = newArray(9);
			root.children[1].children[0].children = newArray(4);
		}

		private Node<Integer>[] newArray(Integer... numbers)
		{
			// Not best practice, this is a limitation of Java.
			Node<Integer>[] result = (Node<Integer>[]) new Node[numbers.length];
			for (int i = 0; i < numbers.length; i++)
			{
				result[i] = new Node<>(numbers[i]);
			}
			return result;
		}

		public int getHeight() throws Exception
		{
			return root.getHeight();
		}

		public int getMaximumDepth() throws Exception
		{
			return this.getHeight();
		}

		private static class Node<T>
		{
			public Node<T>[] children;
			public T val;

			Node(T val)
			{
				this.val = val;
			}

			public int getHeight() throws Exception
			{
				if (this.children == null)
				{
					return 0;
				}
				if (this.children.length == 1)
				{
					return 1 + this.children[0].getHeight();
				}
				if (this.children.length == 2)
				{
					return 1 + Integer.max(this.children[0].getHeight(), this.children[1].getHeight());
				}
				if (this.children.length > 2)
				{
					int maxHeight = this.children[0].getHeight();
					int index = 1;
					while (index < this.children.length)
					{
						maxHeight = Integer.max(maxHeight, this.children[index].getHeight());
						index++;
					}
					return maxHeight + 1;
				}
				else
				{
					throw new Exception("Unknown error when finding the height");
				}
			}
		}
	}
}
