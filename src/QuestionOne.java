import java.util.Iterator;
import java.util.LinkedList;

public class QuestionOne
{
	public static void main(String[] args)
	{
		OurStack<Integer> stack = new OurStack<>();
		// Insert at least 10 elements into the stack
		for (int i = 0; i < 10; i++)
		{
			stack.push(i);
		}
		// Show push and pop operations on the stack.
		stack.push(10);
		stack.push(15);
		stack.push(-1);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		// Print the order of the elements in the stack
		// We will print from top to bottom
		System.out.println(stack);
		// Print the first and last elements of the stack
		String output = "The first element of the stack (top) is " + stack.getFirstElement() +
			"\nThe last element of the stack (bottom) is " + stack.getLastElement();
		System.out.println(output);
	}

	private static class OurStack<T> implements Iterable<T>
	{
		private final LinkedList<T> stack;

		OurStack()
		{
			this.stack = new LinkedList<>();
		}

		public void push(T t)
		{
			stack.push(t);
		}

		public T pop()
		{
			return stack.pop();
		}

		@Override
		public String toString()
		{
			return stack.toString();
		}

		public T getFirstElement()
		{
			return stack.getFirst();
		}

		public T getLastElement()
		{
			return stack.getLast();
		}

		@Override
		public Iterator<T> iterator()
		{
			return stack.iterator();
		}
	}
}