package others;

public class medium2 {

	public static void main(String[] args) {

	}

	/**
	 * You are given two non-empty linked lists representing two non-negative integers.
	 * The digits are stored in reverse order and each of their nodes contain a single digit.
	 * Add the two numbers and return it as a linked list.
	 *
	 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	 *
	 * Example:
	 *
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 * Explanation: 342 + 465 = 807.
	 *
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/add-two-numbers
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */


	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode currentNode = new ListNode(0);
		ListNode resHeadNode = currentNode;

		while (l1 != null && l2 != null) {
			int sum = currentNode.val + l1.val + l2.val;

			currentNode.val = sum >= 10 ? sum % 10 : sum;

			l1 = l1.next;
			l2 = l2.next;

			if (l1 != null || l2 != null || sum >= 10) {
				currentNode.next = new ListNode(sum >= 10 ? sum / 10 : 0);
				currentNode = currentNode.next;
			}
		}

		while (l1 != null) {
			int sum = currentNode.val + l1.val;
			if (sum >= 10) {
				currentNode.val = sum % 10;

				l1 = l1.next;
				currentNode.next = new ListNode(sum / 10);
				currentNode = currentNode.next;
			} else {
				currentNode.val = sum;
				currentNode.next = l1.next;
				break;
			}
		}

		while (l2 != null) {
			int sum = currentNode.val + l2.val;
			if (sum >= 10) {
				currentNode.val = sum % 10;

				l2 = l2.next;
				currentNode.next = new ListNode(sum / 10);
				currentNode = currentNode.next;
			} else {
				currentNode.val = sum;
				currentNode.next = l2.next;
				break;
			}
		}

		return resHeadNode;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
