public class QuestionFour
{
	public static void main(String[] args)
	{
		// Implement a balanced BST from the given keys and print the in-order traversal for the tree in Java.
		// 17,12,22,10,14,20,27
		int[] x = {17, 12, 22, 10, 14, 20, 27};
		QuestionThree.AVLTree<Integer> avlTree = new QuestionThree.AVLTree<>();
		for (int i : x)
		{
			avlTree.add(i);
		}
		System.out.println(avlTree.inOrder());
	}

}
