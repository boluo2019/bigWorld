package stack;

import java.util.Stack;

public class Easy232ImplementQueueUsingStacks {

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

	static class MyQueue {
		private Stack<Integer> stackIn;
		private Stack<Integer> stackOut;

		/** Initialize your data structure here. */
		public MyQueue() {
			stackIn = new Stack<>();
			stackOut = new Stack<>();
		}

		/** Push element x to the back of queue. */
		public void push(int x) {
			stackIn.push(x);
		}

		/** Removes the element from in front of queue and returns that element. */
		public int pop() {
			checkIfNeedUpdate();
			return stackOut.pop();
		}

		/** Get the front element. */
		public int peek() {
			checkIfNeedUpdate();
			return stackOut.peek();

		}

		/** Returns whether the queue is empty. */
		public boolean empty() {
			return stackOut.isEmpty() && stackIn.empty();

		}

		private void checkIfNeedUpdate() {
			if (stackOut.isEmpty()) {
				while (!stackIn.isEmpty()) {
					stackOut.push(stackIn.pop());
				}
			}
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
		MyQueue myQueue = new MyQueue();
		myQueue.push(10);
		myQueue.push(11);
		myQueue.push(12);
		myQueue.push(13);

		System.out.println(myQueue.peek()); // 10
		System.out.println(myQueue.pop()); // 10
		System.out.println(myQueue.peek()); // 11
		System.out.println(myQueue.pop()); // 11
		System.out.println(myQueue.empty()); // false
		System.out.println(myQueue.peek()); // 12
		System.out.println(myQueue.pop()); // 12
		System.out.println(myQueue.peek()); // 13
		System.out.println(myQueue.pop()); // 13
		System.out.println(myQueue.empty()); // true
	}
}
