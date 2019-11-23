package others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class easy1 {

	public static void main(String[] args) {

	}


	/**
	 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	 *
	 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
	 *
	 * Example:
	 *
	 * Given nums = [2, 7, 11, 15], target = 9,
	 *
	 * Because nums[0] + nums[1] = 2 + 7 = 9,
	 * return [0, 1].
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/two-sum
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	// 涉及到的知识点： HashMap.containsKey(key)
	// 时间复杂度最坏的情况：O(n)
	// 空间复杂度最坏的情况：O(n)

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> visited = new HashMap<>(nums.length);

		for (int i = 0; i < nums.length; i++) {
			int secondNum = nums[i];
			int firstNum = target - secondNum;
			int firstIndex = visited.getOrDefault(firstNum, -1);

			if (firstIndex >= 0) {
				return new int[]{firstIndex, i};
			}

			visited.put(secondNum, i);
		}

		throw new IllegalArgumentException("invalid input");
	}
}
