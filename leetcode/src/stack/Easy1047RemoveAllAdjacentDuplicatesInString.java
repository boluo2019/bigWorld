package stack;

public class Easy1047RemoveAllAdjacentDuplicatesInString {

	/**
	 * Given a string S of lowercase letters, a duplicate removal consists of
	 * choosing two adjacent and equal letters, and removing them.
	 *
	 * We repeatedly make duplicate removals on S until we no longer can.
	 *
	 * Return the final string after all such duplicate removals have been made. 
	 * It is guaranteed the answer is unique.
	 *
	 *  
	 *
	 * Example 1:
	 *
	 * Input: "abbaca"
	 * Output: "ca"
	 * Explanation:
	 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal,
	 * and this is the only possible move.  The result of this move is that the string is "aaca",
	 * of which only "aa" is possible, so the final string is "ca".
	 *  
	 *
	 * Note:
	 *
	 * 1 <= S.length <= 20000
	 * S consists only of English lowercase letters.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	public static String removeDuplicates(String S) {
		char[] chars = S.toCharArray();

		int nextResIndex = 0;
		int i = 0;

		while (i < chars.length) {
			// 注意下面应该是nextResIndex - 1
			while (i < chars.length && nextResIndex > 0 && chars[nextResIndex - 1] == chars[i]) {
				nextResIndex--;
				i++;
			}

			if (i < chars.length) {
				chars[nextResIndex++] = chars[i++];
			}
		}

		return new String(chars, 0, nextResIndex);
	}

	public static void main(String[] args) {
		System.out.println(removeDuplicates("abbaca")); // ca
	}
}
