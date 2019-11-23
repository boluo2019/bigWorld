package others;

public class Easy1252CellsWithOddValuesInAMatrix {

	/**
	 * Given n and m which are the dimensions of a matrix initialized by zeros and
	 * given an array indices where indices[i] = [ri, ci].
	 * For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.
	 *
	 * Return the number of cells with odd values in the matrix after applying the increment to all indices.
	 *
	 *  [0,0] -> [0,1] -> [2,2]
	 *  [0,0] -> [1,2] -> [2,2]
	 *
	 * Example 1:
	 *
	 *
	 * Input: n = 2, m = 3, indices = [[0,1],[1,1]]
	 * Output: 6
	 * Explanation: Initial matrix = [[0,0,0],[0,0,0]].
	 * After applying first increment it becomes [[1,2,1],[0,1,0]].
	 * The final matrix will be [[1,3,1],[1,3,1]] which contains 6 odd numbers.
	 * Example 2:
	 *
	 *
	 * Input: n = 2, m = 2, indices = [[1,1],[0,0]]
	 * Output: 0
	 * Explanation: Final matrix = [[2,2],[2,2]]. There is no odd number in the final matrix.
	 *  
	 *
	 * Constraints:
	 *
	 * 1 <= n <= 50
	 * 1 <= m <= 50
	 * 1 <= indices.length <= 100
	 * 0 <= indices[i][0] < n
	 * 0 <= indices[i][1] < m
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/cells-with-odd-values-in-a-matrix
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */


	// time: O(l*(m+n) + n*m), l = indcies.len
	// space: O(n*m)
	public static int oddCells1(int n, int m, int[][] indices) {
		int[][] cells = new int[n][m];

		for (int i = 0; i < indices.length; i++) {
			int rowIndex = indices[i][0];
			int colIndex = indices[i][1];

			for (int j = 0; j < m; j++) {
				cells[rowIndex][j] += 1;
			}

			for (int j = 0; j < n; j++) {
				cells[j][colIndex] += 1;
			}
		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				count += cells[i][j] % 2 == 0 ? 0 : 1;
			}
		}

		return count;
	}

	// time: O(l + n*m)
	// space: O(n+m)
	public static int oddCells2(int n, int m, int[][] indices) {
		int[] rowCount = new int[n];
		int[] columnCount = new int[m];

		for (int i = 0; i < indices.length; i++) {
			int rowIndex = indices[i][0];
			int colIndex = indices[i][1];

			rowCount[rowIndex] += 1;
			columnCount[colIndex] += 1;
		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if ((rowCount[i] + columnCount[j]) % 2 != 0) {
					count++;
				}
			}
		}

		return count;
	}

	// time: O(l + n + m)
	// space: O(n+m)
	// 加1之后，row中的值为奇数col中的值为偶数或者row中为偶数col中为奇数时，二者和为奇数。
	// 因此只需计算二者各自奇数偶数的数量。
	public static int oddCells(int n, int m, int[][] indices) {
		int[] rowCount = new int[n];
		int[] colCount = new int[m];

		for (int i = 0; i < indices.length; i++) {
			int rowIndex = indices[i][0];
			int colIndex = indices[i][1];

			rowCount[rowIndex] += 1;
			colCount[colIndex] += 1;
		}

		int rowOddCount = 0;
		int colOddCount = 0;
		for (int j = 0; j < n; j++) {
			rowOddCount += (rowCount[j] % 2 == 0) ? 0 : 1;
		}

		for (int i = 0; i < m; i++) {
			colOddCount += (colCount[i] % 2 == 0) ? 0 : 1;
		}

		return rowOddCount * (m - colOddCount) + colOddCount * (n - rowOddCount);
	}

	public static void main(String[] args) {
		System.out.println(oddCells(2,3, new int[][]{{0,1},{1,1}}));
		System.out.println(oddCells(2,2, new int[][]{{1,1},{0,0}}));
	}
}
