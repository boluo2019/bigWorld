package stack;

public class Easy844BackspaceStringCompare {

	/**
	 * Given two strings S and T, return if they are equal when both are typed into empty text editors.
	 * # means a backspace character.
	 *
	 * Example 1:
	 *
	 * Input: S = "ab#c", T = "ad#c"
	 * Output: true
	 * Explanation: Both S and T become "ac".
	 * Example 2:
	 *
	 * Input: S = "ab##", T = "c#d#"
	 * Output: true
	 * Explanation: Both S and T become "".
	 * Example 3:
	 *
	 * Input: S = "a##c", T = "#a#c"
	 * Output: true
	 * Explanation: Both S and T become "c".
	 * Example 4:
	 *
	 * Input: S = "a#c", T = "b"
	 * Output: false
	 * Explanation: S becomes "c" while T becomes "b".
	 * Note:
	 *
	 * 1 <= S.length <= 200
	 * 1 <= T.length <= 200
	 * S and T only contain lowercase letters and '#' characters.
	 * Follow up:
	 *
	 * Can you solve it in O(N) time and O(1) space?
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	/**
	 * 双指针
	 */

	public static boolean backspaceCompare1(String S, String T) {
		// 解法1： O(S.len+T.len) time, O(1) space.
		char backspace = '#';
		char[] schars = S.toCharArray();
		int sResIndex = 0;
		int sIndex = 0;
		char[] tchars = T.toCharArray();
		int tResIndex = 0;
		int tIndex = 0;

		while (sIndex < schars.length) {
			if (backspace != schars[sIndex]) {
				schars[sResIndex++] = schars[sIndex];
			} else if (sResIndex > 0)  {
				sResIndex--;
			}
			sIndex++;
		}

		while (tIndex < tchars.length) {
			if (backspace != tchars[tIndex]) {
				tchars[tResIndex++] = tchars[tIndex];
			} else if (tResIndex > 0)  {
				tResIndex--;
			}
			tIndex++;
		}

		if (sResIndex != tResIndex) {
			return false;
		}

		for (int i = 0; i < sResIndex; i++) {
			if (tchars[i] != schars[i]) {
				return false;
			}
		}

		return true;
	}

	public static boolean backspaceCompare(String S, String T) {
		char backspace = '#';
		char[] schars = S.toCharArray();
		int sResIndex = getResIndex(schars, backspace);
		char[] tchars = T.toCharArray();
		int tResIndex = getResIndex(tchars, backspace);

		if (sResIndex != tResIndex) {
			return false;
		}

		for (int i = 0; i < sResIndex; i++) {
			if (tchars[i] != schars[i]) {
				return false;
			}
		}

		return true;
	}

	public static int getResIndex(char[] chars, char backspace) {
		int resIndex = 0;

		for (int index = 0; index < chars.length; index++){
			if (backspace != chars[index]) {
				chars[resIndex++] = chars[index];
			} else if (resIndex > 0) {
				resIndex--;
			}
		}

		return resIndex;
	}

	public static void main(String[] args) {
		System.out.println(backspaceCompare("ab#c", "ad#c")); // true
		System.out.println(backspaceCompare("ab##", "c#d#")); // true
		System.out.println(backspaceCompare("a##c", "#a#c")); // true
		System.out.println(backspaceCompare("a#c", "b")); // false
	}
}
