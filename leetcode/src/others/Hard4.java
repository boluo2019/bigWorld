package others;

public class Hard4 {



	/**
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 *
	 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	 *
	 * You may assume nums1 and nums2 cannot be both empty.
	 *
	 * Example 1:
	 *
	 * nums1 = [1, 3]
	 * nums2 = [2]
	 *
	 * The median is 2.0
	 * Example 2:
	 *
	 * nums1 = [1, 2]
	 * nums2 = [3, 4]
	 *
	 * The median is (2 + 3)/2 = 2.5
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 */

	// 本题目的核心思想是二分法

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int left1 = 0, left2 = 0;
		int right1 = nums1.length - 1, right2 = nums2.length - 1;
// 中位数： 中位数左边子数组长度等于中位数右边子数组长度。
// 用i将nums1划分为[0~i)和[i, nums1.len)
// 用j将nums2划分为[0~j)和[j, nums2.len)
//
		int maxLeft = Math.max(nums1[left1], nums2[left2]);
		int minRight = Math.min(nums1[right1], nums2[right2]);

		return 0;
	}

	public static void main(String[] args) {

	}
}
