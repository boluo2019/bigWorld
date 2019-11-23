package others;

import java.util.HashSet;

public class Easy771JewelsAndStones {

	/**
	 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have. 
	 * Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
	 *
	 * The letters in J are guaranteed distinct, and all characters in J and S are letters.
	 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
	 *
	 * Example 1:
	 *
	 * Input: J = "aA", S = "aAAbbbb"
	 * Output: 3
	 * Example 2:
	 *
	 * Input: J = "z", S = "ZZ"
	 * Output: 0
	 * Note:
	 *
	 * S and J will consist of letters and have length at most 50.
	 * The characters in J are distinct.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/jewels-and-stones
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	public static int numJewelsInStones1(String J, String S) {
		// 注意审题，这是char包含的问题，不是字符串匹配问题
		int count = 0;
		HashSet<Character> targetChars = new HashSet<>(J.length());

		for (char c : J.toCharArray()) {
			targetChars.add(c);
		}

		for (int i = 0; i < S.length(); i++) {
			if (targetChars.contains(S.charAt(i))) {
				count++;
			}
		}

		return count;
	}

	public static int numJewelsInStones(String J, String S) {
		// J的数据量比较小的话，暴力法更快
		int count = 0;

		for (int i = 0; i < S.length(); i++) {
			for (int j = 0; j < J.length(); j++) {
				if (S.charAt(i) == J.charAt(j)) {
					count++;
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		System.out.println(numJewelsInStones("aA", "aAAbbbb"));
		System.out.println(numJewelsInStones("z", "ZZ"));
		System.out.println(numJewelsInStones("zz", "zzzz"));
	}
}
