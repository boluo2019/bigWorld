package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Easy496NextGreaterElementI {

	/**
	 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
	 * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
	 *
	 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
	 * If it does not exist, output -1 for this number.
	 *
	 * Example 1:
	 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
	 * Output: [-1,3,-1]
	 * Explanation:
	 *     For number 4 in the first array, you cannot find the next greater number for it in the second array,
	 *     so output -1.
	 *     For number 1 in the first array, the next greater number for it in the second array is 3.
	 *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
	 * Example 2:
	 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
	 * Output: [3,-1]
	 * Explanation:
	 *     For number 2 in the first array, the next greater number for it in the second array is 3.
	 *     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
	 * Note:
	 * All elements in nums1 and nums2 are unique.
	 * The length of both nums1 and nums2 would not exceed 1000.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */


	/**
	 * 本题主要考察单调栈的应用
	 * Monotone Stack
	 * 类似的应用：视野总和,柱状图中的最大矩形,求最大区间，接雨水，BadHairDay，
	 * 参考链接1：https://blog.csdn.net/lucky52529/article/details/89155694
	 * 参考链接2：https://www.cnblogs.com/grandyang/p/8887985.html
	 * 参考链接3：https://kuaibao.qq.com/media/17053800?refer=cp_1026
	 */

	// 5ms
	public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
		// 单调栈，对nums2的每个元素a入一次栈，遍历a右边的元素b，如果b大于a，则将a->b存入map，a出栈，b入栈，否则，b入栈。
		// 将a记作a1，b当做a继续，知道遇到b>a，依出栈a2->b, a1->b, a->b.
		Stack<Integer> stack = new Stack<>();
		Map<Integer, Integer> map = new HashMap<>(nums2.length);
		int i = 0;
		while (i < nums2.length) {
			if (stack.isEmpty()) {
				stack.push(nums2[i]);
			}

			while (++i < nums2.length) {
				while (!stack.empty() && nums2[i] > stack.peek()) {
					map.put(stack.peek(), nums2[i]);
					stack.pop();
				}
				stack.push(nums2[i]);
			}
		}
		while (!stack.empty()) {
			map.put(stack.pop(), -1);
		}

		int[] res = new int[nums1.length];
		for (i = 0; i < nums1.length; i++) {
			res[i] = map.get(nums1[i]);
		}

		return res;
	}

	// 2ms
	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
		// java原生的stack效率太差，可以用数组代替
		// 结果直接覆盖nums1就可以，重复利用数组。
		int[] stack = new int[nums2.length];
		int topIndex = -1; // stack.empty
		Map<Integer, Integer> map = new HashMap<>(nums2.length);

		int index = 0;
		while (index < nums2.length) {
			while (topIndex > -1 && index < nums2.length && nums2[index] > stack[topIndex]) {
				map.put(stack[topIndex], nums2[index]);
				topIndex--;
			}

			// 只有当栈顶元素大于等于下一个元素时，才会将下个元素入栈
			if (index < nums2.length) { // 注意内部的while循环有可能会将nums2遍历完
				stack[++topIndex] = nums2[index++];
			}
		}

		while (topIndex > -1) {
			map.put(stack[topIndex--], -1);
		}

		for (index = 0; index < nums1.length; index++) {
			nums1[index] = map.get(nums1[index]);
		}

		return nums1;
	}

	public static void main(String[] args) {
		int[] res = nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
		Arrays.stream(res).forEach(a -> System.out.print(a + ",")); // -1, 3, -1
		System.out.println();
		res = nextGreaterElement(new int[]{2,4}, new int[]{1,2,3,4});
		Arrays.stream(res).forEach(a -> System.out.print(a + ",")); // 3, -1
		System.out.println();
		res = nextGreaterElement(new int[]{1,3,5,2,4}, new int[]{6,5,4,3,2,1,7});
		Arrays.stream(res).forEach(a -> System.out.print(a + ",")); // 3, -1
		System.out.println();
	}
}
