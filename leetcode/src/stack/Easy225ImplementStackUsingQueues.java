package stack;

import java.util.Stack;

public class Easy225ImplementStackUsingQueues {

	/**
	 * Implement the following operations of a queue using stacks.
	 *
	 * push(x) -- Push element x to the back of queue.
	 * pop() -- Removes the element from in front of queue.
	 * peek() -- Get the front element.
	 * empty() -- Return whether the queue is empty.
	 * Example:
	 *
	 * MyQueue queue = new MyQueue();
	 *
	 * queue.push(1);
	 * queue.push(2);
	 * queue.peek();  // returns 1
	 * queue.pop();   // returns 1
	 * queue.empty(); // returns false
	 * Notes:
	 *
	 * You must use only standard operations of a stack --
	 * which means only push to top, peek/pop from top, size, and is empty operations are valid.
	 * Depending on your language, stack may not be supported natively.
	 * You may simulate a stack by using a list or deque (double-ended queue),
	 * as long as you use only standard operations of a stack.
	 * You may assume that all operations are valid
	 * (for example, no pop or peek operations will be called on an empty queue).
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	class MyQueue {
		private Stack<Integer> stack;

		/** Initialize your data structure here. */
		public MyQueue() {
			stack = new Stack<>();
		}

		/** Push element x to the back of queue. */
		public void push(int x) {
		}

		/** Removes the element from in front of queue and returns that element. */
		public int pop() {
			return 0;
		}

		/** Get the front element. */
		public int peek() {
			return 0;

		}

		/** Returns whether the queue is empty. */
		public boolean empty() {
			return true;

		}
	}

	/**
	 * Your MyQueue object will be instantiated and called as such:
	 * MyQueue obj = new MyQueue();
	 * obj.push(x);
	 * int param_2 = obj.pop();
	 * int param_3 = obj.peek();
	 * boolean param_4 = obj.empty();
	 */

	public static void main(String[] args) {

	}
}
