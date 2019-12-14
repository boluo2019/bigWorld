package stack;

import java.util.Arrays;
import java.util.Stack;

public class Easy155MinStack {

	/**
	 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
	 *
	 * push(x) -- Push element x onto stack.
	 * pop() -- Removes the element on top of the stack.
	 * top() -- Get the top element.
	 * getMin() -- Retrieve the minimum element in the stack.
	 *  
	 *
	 * Example:
	 *
	 * MinStack minStack = new MinStack();
	 * minStack.push(-2);
	 * minStack.push(0);
	 * minStack.push(-3);
	 * minStack.getMin();   --> Returns -3.
	 * minStack.pop();
	 * minStack.top();      --> Returns 0.
	 * minStack.getMin();   --> Returns -2.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/min-stack
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	static class MinStack2 {
		private Stack<Integer> stackMin;
		private Stack<Integer> stackAll;

		/** initialize your data structure here. */
		public MinStack2() {
			stackMin = new Stack<>();
			stackAll = new Stack<>();
		}

		public void push(int x) {
			if (stackMin.empty()) {
				stackMin.push(x);
			} else if (x <= stackMin.peek()) {
				stackMin.push(x);
			}

			stackAll.push(x);
		}

		public void pop() {
			int top = stackAll.pop();
			if (top == stackMin.peek()) {
				stackMin.pop();
			}
		}

		public int top() {
			return stackAll.peek();
		}

		public int getMin() {
			checkIfNeedUpdate();
			return stackMin.peek();
		}

		private void checkIfNeedUpdate() {
			if (stackMin.empty() && !stackAll.empty()) {
				stackMin.push(stackAll.peek());
			}
		}
	}

	static class MinStack {
		private int[] arrayMin;
		private int nextIndexMin = 0;
		private int[] arrayAll;
		private int nextIndexAll = 0;

		/** initialize your data structure here. */
		public MinStack() {
			arrayMin = new int[4]; // 最终测试结果，4或者8比较好，比1或者16快1ms。
			arrayAll = new int[4];
		}

		private int[] checkIfNeedResize(int[] array) {
			return Arrays.copyOf(array, array.length * 2);
		}

		public void push(int x) {
			if (nextIndexMin == arrayMin.length) {
				arrayMin = checkIfNeedResize(arrayMin);
			}

			if (nextIndexAll == arrayAll.length) {
				arrayAll = checkIfNeedResize(arrayAll);
			}

			if (nextIndexMin > 0) {
				if (x <= arrayMin[nextIndexMin - 1]) {
					arrayMin[nextIndexMin++] = x;
				}
			} else {
				arrayMin[nextIndexMin++] = x;
			}

			arrayAll[nextIndexAll++] = x;
		}

		public void pop() {
			int top = arrayAll[--nextIndexAll];
			if (top == arrayMin[nextIndexMin - 1]) {
				--nextIndexMin;
			}
		}

		public int top() {
			return arrayAll[nextIndexAll - 1];
		}

		public int getMin() {
			if (nextIndexMin == 0 && nextIndexAll > 0) {
				arrayMin[nextIndexMin++] = arrayAll[nextIndexAll - 1];
			}

			return arrayMin[nextIndexMin - 1];
		}
	}


	/**
	 * Your MinStack object will be instantiated and called as such:
	 * MinStack obj = new MinStack();
	 * obj.push(x);
	 * obj.pop();
	 * int param_3 = obj.top();
	 * int param_4 = obj.getMin();
	 */

	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());
	}
}
