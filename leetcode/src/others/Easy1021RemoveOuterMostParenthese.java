package others;

public class Easy1021RemoveOuterMostParenthese {

	/**
	 * A valid parentheses string is either empty (""), "(" + A + ")", or A + B,
	 * where A and B are valid parentheses strings, and + represents string concatenation. 
	 * For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
	 *
	 * A valid parentheses string S is primitive if it is nonempty,
	 * and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.
	 *
	 * Given a valid parentheses string S, consider its primitive decomposition:
	 * S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
	 *
	 * Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.
	 *
	 *  
	 *
	 * Example 1:
	 *
	 * Input: "(()())(())"
	 * Output: "()()()"
	 * Explanation:
	 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
	 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
	 * Example 2:
	 *
	 * Input: "(()())(())(()(()))"
	 * Output: "()()()()(())"
	 * Explanation:
	 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
	 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
	 * Example 3:
	 *
	 * Input: "()()"
	 * Output: ""
	 * Explanation:
	 * The input string is "()()", with primitive decomposition "()" + "()".
	 * After removing outer parentheses of each part, this is "" + "" = "".
	 *  
	 *
	 * Note:
	 *
	 * S.length <= 10000
	 * S[i] is "(" or ")"
	 * S is a valid parentheses string
	 *  
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/remove-outermost-parentheses
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	public static String removeOuterParentheses1(String S) {
		StringBuilder res = new StringBuilder();
		boolean mark = false; // 当前是否正在遍历一个primitive的括号字符串
		char[] chars = S.toCharArray();
		int countLP = 0;

		for (int i = 0; i < chars.length; i++) {
			if (mark == false) {
				if ('(' == chars[i]) {
					mark = true;
				} else {
					throw new IllegalArgumentException("invalid input: " + S);
				}
			} else {
				if ('(' == chars[i]) {
					countLP++;
					res.append('(');
				}	else { // )
					if (countLP == 0) {
						mark = false;
					} else {
						res.append(')');
						countLP--;
					}
				}
			}
		}

		return res.toString();
	}

	public static String removeOuterParentheses(String S) {
		StringBuilder res = new StringBuilder();
		char[] chars = S.toCharArray();
		int countLP = 0;

		for (int i = 0; i < chars.length; i++) {
			if ('(' == chars[i]) {
				countLP++;

				if (countLP > 1) {
					res.append('(');
				}
			}	else {
				if (countLP > 1) {
					res.append(')');
				}

				countLP--;
			}
		}

		return res.toString();
	}

	public static void main(String[] args) {
		System.out.println(removeOuterParentheses("()"));
		System.out.println(removeOuterParentheses("(())"));
		System.out.println(removeOuterParentheses("()()"));
		System.out.println(removeOuterParentheses("(()())(())"));
		System.out.println(removeOuterParentheses("(()())(())(()(()))"));
	}
}
