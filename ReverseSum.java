import java.util.*;

public class ReverseSum {
    public static void main (String[] args) {
        List<int[]> mainList = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int maxWide = 0;

        while (in.hasNextLine()) {
            Scanner oneLineScanner = new Scanner(in.nextLine());
            int len = 0;
            int i = 0;
            int[] oneLine = new int[len];        // sqrt(M) по памяти = N
            while (oneLineScanner.hasNextInt()){
                if (i == len) {
                    oneLine = Arrays.copyOf(oneLine, oneLine.length + 1);
                    len = oneLine.length;
                }
                oneLine[i] = oneLineScanner.nextInt();
                i ++;
            }
            mainList.add(oneLine);
            oneLineScanner.close();
            int wide = oneLine.length;
            if (wide > maxWide) {
                maxWide = wide;
            }
        }
        in.close();

        int maxHigh = mainList.size();

        // блок преобразования
        int[] sumRows = new int[maxHigh];
        int[] sumColumns = new int[maxWide];

        for (int i = 0; i < maxHigh; i++) {
            for (int j = 0; j < mainList.get(i).length; j++) {
                sumRows[i] += mainList.get(i)[j];
                sumColumns[j] += mainList.get(i)[j];
            }
        }

        // блок вывода
        for (int i = 0; i < mainList.size(); i++) {
            for (int j = 0; j < mainList.get(i).length; j++) {
                System.out.print(sumRows[i]
                        + sumColumns[j]
                        - mainList.get(i)[j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}