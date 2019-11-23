package others;

public class Easy938RangeSumOfBST {

	/**
	 * Given the root node of a binary search tree,
	 * return the sum of values of all nodes with value between L and R (inclusive). （注：包含）
	 *
	 * The binary search tree is guaranteed to have unique values.
	 *
	 * Example 1:
	 *
	 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
	 * Output: 32
	 * Example 2:
	 *
	 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
	 * Output: 23
	 *  
	 *
	 * Note:
	 *
	 * The number of nodes in the tree is at most 10000.
	 * The final answer is guaranteed to be less than 2^31.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 *
	 */


	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public static int rangeSumBST(TreeNode root, int L, int R) {
		int sum = 0;

		if (root != null) {
			if (root.val >= L && root.val <= R) {
				sum += root.val;
			}

			if (root.val > L) { // 剪枝，二分搜索树的左子树上所有节点的值一定小于根节点的值
				sum += rangeSumBST(root.left, L, R);
			}

			if (root.val < R) { // 剪枝,二分搜索树的右子树上的所有节点的值一定大于根节点的值
				sum += rangeSumBST(root.right, L, R);
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);
		root.right.right = new TreeNode(18);

		System.out.println(rangeSumBST(root, 7, 15));
	}
}
