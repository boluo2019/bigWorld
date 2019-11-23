package others;

import java.util.HashSet;
import java.util.Set;

public class medium3 {

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
	// 参考：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/
	// 400ms
	public static int lengthOfLongestSubstring1(String s) {
		Set<Character> visited = new HashSet<>(s.length());

//		int targetStart = 0, targetEnd = 0;
		int start = 0;
		int maxSubStrLength = 0;
		while (start < s.length() - maxSubStrLength) {
			int currentIndex = start;

			if (maxSubStrLength == 0) {
				maxSubStrLength = 1;
			}

			while (currentIndex < s.length()) {
				char c = s.charAt(currentIndex);
				if (visited.contains(c)) {
					maxSubStrLength = Math.max(currentIndex - start, maxSubStrLength);
					visited.clear();

					break;
				} else {
					currentIndex++;
					visited.add(c);
				}
			}

			if (currentIndex == s.length()) {
				maxSubStrLength = Math.max(currentIndex - start, maxSubStrLength);
				break;
			}

			if (currentIndex > start && s.charAt(currentIndex) == s.charAt(currentIndex - 1)) {
				start = currentIndex;
			} else {
				start++;
			}
		}
//		System.out.println(targetStart + ", " + targetEnd);

		return maxSubStrLength;
	}

	// 14ms
	public static int lengthOfLongestSubstring2(String s) {
		char[] chars = s.toCharArray();
		int maxLength = 0, leftIndex = 0, currentIndex = leftIndex;
		Set<Character> visited = new HashSet<>(s.length());

		while (leftIndex < chars.length & currentIndex < chars.length) {
			char c = chars[currentIndex];
			if (visited.contains(c)) {
				visited.remove(chars[leftIndex++]);
			} else {
				visited.add(chars[currentIndex++]);
				maxLength = Math.max(maxLength, currentIndex - leftIndex);
			}
		}

		return maxLength;
	}

	// 2ms
	public static int lengthOfLongestSubstring3(String s) {
		char[] chars = s.toCharArray();
		int maxLength = 0, left = 0, right = left;

		for (; right < s.length(); right++) {
			char c = chars[right];
			// [left, right]从左向右找到第一个和右边重复的字符，然后将重复字符的下一个字符设为新的left。
			for (int current = left; current < right; current++) {
				if (chars[current] == c) {
					maxLength = Math.max(maxLength, right - left); // 只有出现重复字符才会更新最长子串长度值
					left = current + 1; // 只有出现重复字符时，左边才需要更新。所以有可能左边一直不会更新。
					break;
				}
			}
		}

		// 时间复杂度做多为O(N^2)(没有重复字符的情况)；最少为O(2N)（有重复字符的情况）

		// 对于没有出现重复字符的情况，maxLength一直是0，所以此时的滑动窗口长度就是最大子串的长度
		return Math.max(maxLength, right - left);
	}

	// 11ms-12ms 这个竟然没有上一个快，我猜是因为测试集的数据量比较小，hashset的优势没有体现出来。
	public static int lengthOfLongestSubstring(String s) {
		char[] chars = s.toCharArray();
		int maxLength = 0, left = 0, right = left;
		Set<Character> window = new HashSet<>();

		for (; right < s.length(); right++) {
			char c = chars[right];
			// [left, right]从左向右找到第一个和右边重复的字符，然后将重复字符的下一个字符设为新的left。
			if (window.contains(c)) {
				for (int current = left; current < right; current++) {
					window.remove(chars[current]); // O(1)
					if (chars[current] == c) {
						maxLength = Math.max(maxLength, right - left); // 只有出现重复字符才会更新最长子串长度值
						left = current + 1; // 只有出现重复字符时，左边才需要更新。所以有可能左边一直不会更新。
						break;
					}
				}
			}

			window.add(c);
		}

		// 时间复杂度做多为O(2N)(没有重复字符的情况)；最少为O(N)（有重复字符的情况）

		// 对于没有出现重复字符的情况，maxLength一直是0，所以此时的滑动窗口长度就是最大子串的长度
		return Math.max(maxLength, right - left);
	}

	//			如果想要字符串的内容，那么就需要用该函数来求最大长度，便于记录子串的下标。
	private static int maxLength(int currentIndex, int start, int maxSubStrLength) {
		int subLength = currentIndex - start;
		if (subLength > maxSubStrLength) {
			maxSubStrLength = subLength;
//						targetStart = start;
//						targetEnd = currentIndex;
		}
		return maxSubStrLength;
	}

	public static void main(String[] args) {
		System.out.println("au = " + lengthOfLongestSubstring("au"));
		System.out.println("abcabcbb = " + lengthOfLongestSubstring("abcabcbb"));
		System.out.println("bbbbb = " + lengthOfLongestSubstring("bbbbb"));
		System.out.println("pwwkew = " + lengthOfLongestSubstring("pwwkew"));
		System.out.println(" = " + lengthOfLongestSubstring(""));
		System.out.println("  = " + lengthOfLongestSubstring(" "));
	}
}
