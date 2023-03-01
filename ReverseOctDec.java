import java.io.IOException;
import java.util.Arrays;

public class ReverseOctDec {
	public static void main(String[] args) throws IOException {
		int[][] mainList = new int[16][];
		int sizeMainList = 0;
		BestScannerEver in = new BestScannerEver(System.in);

		int[] line = new int[16];
		int size = 0;
		while (in.hasNext()) {
			while (!in.isLineSeparator() && in.hasNext()) {
				line[size] = in.nextIntOrOct();
				size++;
				if (size == line.length) {
					line = Arrays.copyOf(line, size * 2);
				}
			}
			if (in.isLineSeparator()) {
				line = Arrays.copyOf(line, size);
				mainList[sizeMainList] = line;
				sizeMainList++;
				if (sizeMainList == mainList.length) {
					mainList = Arrays.copyOf(mainList, sizeMainList * 2);
				}
				line = new int[16];
				size = 0;
				in.toNextLine();
			}
		}
		mainList = Arrays.copyOf(mainList, sizeMainList);

		// блок вывода
		for (int i = mainList.length - 1; i >= 0; i--) {
			for (int j = mainList[i].length - 1; j >= 0; j--) {
				System.out.print(mainList[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}
