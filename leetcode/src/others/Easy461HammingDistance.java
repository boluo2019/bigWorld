package others;

public class Easy461HammingDistance {

	/**
	 * The Hamming distance between two integers is the number of positions at which
	 * the corresponding bits are different.
	 *
	 * Given two integers x and y, calculate the Hamming distance.
	 *
	 * Note:
	 * 0 ≤ x, y < 231.
	 *
	 * Example:
	 *
	 * Input: x = 1, y = 4
	 *
	 * Output: 2
	 *
	 * Explanation:
	 * 1   (0 0 0 1)
	 * 4   (0 1 0 0)
	 *        ↑   ↑
	 *
	 * The above arrows point to positions where the corresponding bits are different.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/hamming-distance
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */
	// 异或，相同为0，相异为1
	public static int hammingDistance1(int x, int y) {
		return Integer.bitCount(x ^ y);
	}

	public static int hammingDistance2(int x, int y) {
		int count = 0;

		while (x > 0 || y > 0) {
			if ((x & 1) != (y & 1)) {
				count++;
			}
			x >>= 1;
			y >>= 1;
		}

		return count;
	}

	public static int hammingDistance(int x, int y) {
		int num = x ^ y;
		int count = 0;

		while (num > 0) {
			count += num & 1;
			num >>= 1;
		}

		return count;
	}


	public static void main(String[] args) {
		System.out.println(hammingDistance1(1,4));
		System.out.println(hammingDistance2(1,4));
		System.out.println(hammingDistance(1,4));
	}
}
