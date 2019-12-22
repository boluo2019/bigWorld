package stack;

public class Easy682BaseballGame {

	/**
	 * You're now a baseball game point recorder.
	 *
	 * Given a list of strings, each string can be one of the 4 following types:
	 *
	 * Integer (one round's score): Directly represents the number of points you get in this round.
	 * "+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
	 * "D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
	 * "C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
	 * Each round's operation is permanent and could have an impact on the round before and the round after.
	 *
	 * You need to return the sum of the points you could get in all the rounds.
	 *
	 * Example 1:
	 * Input: ["5","2","C","D","+"]
	 * Output: 30
	 * Explanation:
	 * Round 1: You could get 5 points. The sum is: 5.
	 * Round 2: You could get 2 points. The sum is: 7.
	 * Operation 1: The round 2's data was invalid. The sum is: 5.
	 * Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
	 * Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
	 * Example 2:
	 * Input: ["5","-2","4","C","D","9","+","+"]
	 * Output: 27
	 * Explanation:
	 * Round 1: You could get 5 points. The sum is: 5.
	 * Round 2: You could get -2 points. The sum is: 3.
	 * Round 3: You could get 4 points. The sum is: 7.
	 * Operation 1: The round 3's data is invalid. The sum is: 3.
	 * Round 4: You could get -4 points (the round 3's data has been removed). The sum is: -1.
	 * Round 5: You could get 9 points. The sum is: 8.
	 * Round 6: You could get -4 + 9 = 5 points. The sum is 13.
	 * Round 7: You could get 9 + 5 = 14 points. The sum is 27.
	 * Note:
	 * The size of the input list will be between 1 and 1000.
	 * Every integer represented in the list will be between -30000 and 30000.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/baseball-game
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	/**
	 * 栈顶指针的移动
	 * 本地值得关注的点：java中 switch 和 if-else 性能比较
	 * 1. case条件比较多时，switch的性能明显比较好。https://mp.weixin.qq.com/s/8Q87XZPEfGhfInbAuRAeWA
	 * 2. https://stackoverflow.com/questions/10287700/difference-between-jvms-lookupswitch-and-tableswitch
	 */

	public static int calPoints1(String[] ops) {
		int[] stack = new int[ops.length];
		int topIndex = -1;
		int sum = 0;

		for (int i = 0; i < ops.length; i++) {
			if ("+".equals(ops[i])) {
				if (i - 2 < 0) {
					throw new IllegalArgumentException("+之前至少有2个integer");
				}
				stack[++topIndex] = stack[topIndex - 1] + stack[topIndex - 2];
				sum += stack[topIndex];
			} else if ("C".equals(ops[i])) {
				sum -= stack[topIndex];
				--topIndex;
			} else if ("D".equals(ops[i])) {
				if (i - 1 < 0) {
					throw new IllegalArgumentException("+之前至少有1个integer");
				}
				stack[++topIndex] = 2 * stack[topIndex - 1];
				sum += stack[topIndex];
			} else { // integer
				stack[++topIndex] = Integer.parseInt(ops[i]);
				sum += stack[topIndex];
			}
		}

		return sum;
	}

	public static int calPoints(String[] ops) {
		int[] stack = new int[ops.length];
		int topIndex = -1;
		int sum = 0;

		for (int i = 0; i < ops.length; i++) {
			switch (ops[i]) {
				case "+": {
					if (i - 2 < 0) {
						throw new IllegalArgumentException("+之前至少有2个integer");
					}
					stack[++topIndex] = stack[topIndex - 1] + stack[topIndex - 2];
					sum += stack[topIndex];
					break;
				}
				case "C": {
					sum -= stack[topIndex];
					--topIndex;
					break;
				}
				case "D": {
					if (i - 1 < 0) {
						throw new IllegalArgumentException("+之前至少有1个integer");
					}
					stack[++topIndex] = 2 * stack[topIndex - 1];
					sum += stack[topIndex];
					break;
				}
				default: {
					stack[++topIndex] = Integer.parseInt(ops[i]);
					sum += stack[topIndex];
				}
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		System.out.println(calPoints(new String[]{"5","-2","4","C","D","9","+","+"})); // 27
		System.out.println(calPoints(new String[]{"5","2","C","D","+"})); // 30
		System.out.println(calPoints(new String[]{"1","D","D","D"})); // 30
	}
}
