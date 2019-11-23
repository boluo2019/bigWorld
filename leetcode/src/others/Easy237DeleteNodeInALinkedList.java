package others;

public class Easy237DeleteNodeInALinkedList {

	/**
	 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
	 *
	 * Given linked list -- head = [4,5,1,9], which looks like following:
	 *
	 * 4 -> 5 -> 1 -> 9
	 *
	 * Example 1:
	 *
	 * Input: head = [4,5,1,9], node = 5
	 * Output: [4,1,9]
	 * Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
	 * Example 2:
	 *
	 * Input: head = [4,5,1,9], node = 1
	 * Output: [4,5,9]
	 * Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
	 *  
	 *
	 * Note:
	 *
	 * The linked list will have at least two elements.
	 * All of the nodes' values will be unique.
	 * The given node will not be the tail and it will always be a valid node of the linked list.
	 * Do not return anything from your function.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public void deleteNode(ListNode node) {
		// 由于题目中限定了要删除的节点不会是最后一个，所以node.next一定不为空。
		// 直接将下一个节点的内容覆盖该节点的内容，就达到了删除的目的。
		// 唯一要注意的是，要先修改常量值，然后再修改next。
		node.val = node.next.val;
		node.next = node.next.next;
	}

	public static void main(String[] args) {

	}
}
