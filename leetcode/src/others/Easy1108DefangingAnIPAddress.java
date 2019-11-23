package others;

public class Easy1108DefangingAnIPAddress {

	/**
	 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
	 *
	 * A defanged IP address replaces every period "." with "[.]".
	 *
	 *  
	 *
	 * Example 1:
	 *
	 * Input: address = "1.1.1.1"
	 * Output: "1[.]1[.]1[.]1"
	 * Example 2:
	 *
	 * Input: address = "255.100.50.0"
	 * Output: "255[.]100[.]50[.]0"
	 *  
	 *
	 * Constraints:
	 *
	 * The given address is a valid IPv4 address.
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/defanging-an-ip-address
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */

	public static String defangIPaddr1(String address) {
		return address.replaceAll("\\.", "[.]");
	}

	public static String defangIPaddr(String address) {
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < address.length(); i++) {
			if ('.' == address.charAt(i)) {
				res.append("[.]");
			} else {
				res.append(address.charAt(i));
			}
		}

		return res.toString();
	}

	public static void main(String[] args) {

	}
}
