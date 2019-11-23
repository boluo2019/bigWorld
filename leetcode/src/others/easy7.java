package others;

public class easy7 {

	/**
	 * Given a 32-bit signed integer, reverse digits of an integer.
	 *
	 * Example 1:
	 *
	 * Input: 123
	 * Output: 321
	 * Example 2:
	 *
	 * Input: -123
	 * Output: -321
	 * Example 3:
	 *
	 * Input: 120
	 * Output: 21
	 * Note:
	 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
	 *
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/reverse-integer
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */
	/**
	 * Integer.MIN_VALUE <= res * 10 + lastInt <= Integer.MAX_VALUE
	 */
	public static int reverse1(int x) {
		int res = 0;
		int maxIntDiv10 = Integer.MAX_VALUE / 10;
		int maxInt = Integer.MAX_VALUE - maxIntDiv10;

		int minIntDiv10 = Integer.MIN_VALUE / 10;
		int minIntMaxLastInt = Integer.MIN_VALUE - minIntDiv10;

		while (x != 0) {
			int lastInt = x % 10;

			// 出现溢出就返回
			if (res > maxIntDiv10 || res == maxIntDiv10 && lastInt > maxInt) return 0;
      if (res < minIntDiv10 || res == minIntDiv10 && lastInt < minIntMaxLastInt)
			res = res * 10 + lastInt;
			x = x / 10;
		}

		return res;
	}

	public static int reverse(int x) {
		long res = 0;

		while (x != 0) {
			int lastInt = x % 10;
			res = res * 10 + lastInt;
			x = x / 10;
		}
		return (int) res == res ? (int) res : 0;
	}

	public static void main(String[] args) {
		System.out.println(-1 % 10);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(reverse(-1));
		System.out.println(reverse(-100));
		System.out.println(reverse(100));
		System.out.println(reverse(Integer.MAX_VALUE));
		System.out.println(reverse(-Integer.MAX_VALUE));
		System.out.println(reverse(0));
		System.out.println(reverse(1463847412));
		System.out.println(reverse(1563847412));
	}


}
