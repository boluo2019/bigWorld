package others;

public class Easy1221SplitAStringInBalancedStrings {

	/**
	 * Balanced strings are those who have equal quantity of 'L' and 'R' characters.
	 *
	 * Given a balanced string s split it in the maximum amount of balanced strings.
	 *
	 * Return the maximum amount of splitted balanced strings.
	 *
	 *  
	 *
	 * Example 1:
	 *
	 * Input: s = "RLRRLLRLRL"
	 * Output: 4
	 * Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
	 * Example 2:
	 *
	 * Input: s = "RLLLLRRRLR"
	 * Output: 3
	 * Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
	 * Example 3:
	 *
	 * Input: s = "LLLLRRRR"
	 * Output: 1
	 * Explanation: s can be split into "LLLLRRRR".
	 * Example 4:
	 *
	 * Input: s = "RLRRRLLRLL"
	 * Output: 2
	 * Explanation: s can be split into "RL", "RRRLLRLL", since each substring contains an equal number of 'L' and 'R'
	 *  
	 *
	 * Constraints:
	 *
	 * 1 <= s.length <= 1000
	 * s[i] = 'L' or 'R'
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */


	public static int balancedStringSplit1(String s) {
		int count = 0;
		int LCurLen = 0;
		int RCurLen = 0;

		for (int i = 0; i < s.length(); i++) {
			if ('L' == s.charAt(i)) {
				LCurLen++;
			} else if ('R' == s.charAt(i)) {
				RCurLen++;
			}

			if (LCurLen == RCurLen) {
				count++;
				LCurLen = RCurLen = 0;
			}
		}

		return count;
	}

	// 下面这个比上面的快, 可能是因为charAt()要判断，所以更耗时？
	public static int balancedStringSplit(String s) {
		int count = 0;
		int LCurLen = 0;
		char[] chars = s.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if ('L' == chars[i]) {
				LCurLen++;
			} else {
				LCurLen--;
			}

			if (i > 0 && LCurLen == 0) {
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		System.out.println(balancedStringSplit("RLRRLLRLRL"));
		System.out.println(balancedStringSplit("RLLLLRRRLR"));
		System.out.println(balancedStringSplit("LLLLRRRR"));
		System.out.println(balancedStringSplit("RLRRRLLRLL"));
	}
}
