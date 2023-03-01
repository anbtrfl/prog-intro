import java.util.Arrays;

public class IntList {
	private int[] ints;
	private int size = 0;

	public IntList(int startSize) {
		this.ints = new int[startSize];
	}

	public void addElem(int elem) {
		if (ints.length == size) {
			ints = Arrays.copyOf(ints, size * 2);
		}
		ints[size] = elem;
		size++;
	}

	public int get(int index) {
		return ints[index];
	}

	public void set(int index, int elem) {
		ints[index] = elem;
	}


	private int[] getList() {
		ints = Arrays.copyOf(ints, size);
		return ints;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		int[] forOutput = getList();
		for (int elem : forOutput) {
			output.append(elem);
			output.append(" ");
		}
		return output.substring(0, output.length() - 1);
	}

	public String toStringLastL() {
		StringBuilder output = new StringBuilder();
		int[] forOutput = getList();
		output.append(forOutput[0]);
		output.append(" ");
		for (int i = 1; i < forOutput.length - 2; i += 2) {
			if (forOutput[i] == forOutput[i + 2]) {
				continue;
			}
			output.append(forOutput[i + 1]);
			output.append(" ");
		}
		output.append(forOutput[forOutput.length - 1]);
		return output.toString();
	}
}
