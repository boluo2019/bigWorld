package stack;

import java.util.Stack;

public class Easy20ValidParentheses {

	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
	 *
	 * An input string is valid if:
	 *
	 * Open brackets must be closed by the same type of brackets.
	 * Open brackets must be closed in the correct order.
	 * Note that an empty string is also considered valid.
	 *
	 * Example 1:
	 *
	 * Input: "()"
	 * Output: true
	 * Example 2:
	 *
	 * Input: "()[]{}"
	 * Output: true
	 * Example 3:
	 *
	 * Input: "(]"
	 * Output: false
	 * Example 4:
	 *
	 * Input: "([)]"
	 * Output: false
	 * Example 5:
	 *
	 * Input: "{[]}"
	 * Output: true
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/valid-parentheses
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	public static boolean isValid1(String s) {
		char[] chars = s.toCharArray();
		Stack<Character> parenthese = new Stack<>();
		for (char c : chars) {
			if ('[' == c || '{' == c || '(' == c) {
				parenthese.push(c);
			} else if (!parenthese.isEmpty() && (
					')' == c && '(' == parenthese.peek() ||
							']' == c && '[' == parenthese.peek() ||
							'}' == c && '{' == parenthese.peek())) {
				parenthese.pop();
			} else {
				return false;
			}
		}
		return parenthese.empty();
	}

	public static boolean isValid(String s) {
		char[] chars = s.toCharArray();
		char[] stack = new char[chars.length / 2 + 1];
		int curIndex = -1;

		for (char c : chars) {
			if ('[' == c || '{' == c || '(' == c) {
				stack[++curIndex] = c;
			} else if (curIndex >= 0  && (
					')' == c && '(' == stack[curIndex] ||
							']' == c && '[' == stack[curIndex] ||
							'}' == c && '{' == stack[curIndex])) {
				--curIndex;
			} else {
				return false;
			}
		}
		return curIndex == -1;
	}

	public static void main(String[] args) {
		System.out.println(isValid("()")); // true
		System.out.println(isValid("()[]{}")); // true
		System.out.println(isValid("(]")); // false
		System.out.println(isValid("([)]")); // false
		System.out.println(isValid("{[]}")); // false
		System.out.println(isValid("")); // false
		System.out.println(isValid("(")); // false
		System.out.println(isValid(")")); // false
		System.out.println(isValid("]")); // false
		System.out.println(isValid("}")); // false
	}
}
