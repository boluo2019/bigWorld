public class medium3 {

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("abcabcbb"));
		System.out.println(lengthOfLongestSubstring("bbbbb"));
		System.out.println(lengthOfLongestSubstring("pwwkew"));
	}
	/**
	 * Given a string, find the length of the longest substring without repeating characters.
	 *
	 * Example 1:
	 *
	 * Input: "abcabcbb"
	 * Output: 3
	 * Explanation: The answer is "abc", with the length of 3.
	 * Example 2:
	 *
	 * Input: "bbbbb"
	 * Output: 1
	 * Explanation: The answer is "b", with the length of 1.
	 * Example 3:
	 *
	 * Input: "pwwkew"
	 * Output: 3
	 * Explanation: The answer is "wke", with the length of 3.
	 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	// 暴力法，时间复杂度为O(n^3)
	// 正解：滑动窗口，HashSet
	public static int lengthOfLongestSubstring(String s) {

		return 0;
	}
}
