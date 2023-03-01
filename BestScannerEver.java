import java.io.*;
import java.nio.charset.Charset;


public class BestScannerEver implements AutoCloseable {
    private final String charset = "utf8";
    // Reader
    private final InputStreamReader reader;
    // Буффер
    private final char[] buffer = new char[8192];
    // Указатель буффера
    private int iterator = 0;
    // Счетчик действительных элементов буффера
    private int amountElems = 0;
    // Разделитель строк
    private final char lineSeparator = System.lineSeparator().charAt(0);

    // Конструкторы
    public BestScannerEver(File file) throws IOException {
        this.reader = new FileReader(file, Charset.forName(charset));
    }
    public BestScannerEver(InputStream inputStream) throws IOException {
        this.reader = new InputStreamReader(inputStream, charset);
    }


    // Публичные методы
    public String next() throws IOException {
        final StringBuilder accum = new StringBuilder();
        skipWhitespaces();
        while (!Character.isWhitespace(buffer[iterator])) {
            accum.append(read());
            if (!hasNext()) {
                break;
            }
        }
        return accum.toString();
    }

    public boolean hasNext() throws IOException {
        check();
        return iterator < amountElems;
    }

    public char read() throws IOException {
        check();
        return buffer[iterator++];
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    public int nextIntOrOct() throws IOException {
        String number = next();
        if (number.toLowerCase().endsWith("o")) {
            return (int) Long.parseLong(number.substring(0, number.length() - 1), 8);
        }
        return Integer.parseInt(number);
    }

    public boolean hasNextInt() throws IOException {
        check();
        return getStartOfNumber() < amountElems;
    }

    public boolean isLineSeparator() {
        return buffer[iterator] == lineSeparator;
    }

    public void toNextLine() throws IOException {
        check();
        iterator += System.lineSeparator().length();
    }

    // Приватные методы
    private void skipWhitespaces() throws IOException {
        while (Character.isWhitespace(buffer[iterator])) {
            read();
            check();
        }
    }

    private int getStartOfNumber() throws IOException {
        while (isNumber(buffer[iterator])) {
            read();
            check();
        }
        return iterator;
    }

    private boolean isNumber(char smb) {
        return !Character.isDigit(smb) && smb != '-';
    }

    private void check() throws IOException {
        if (iterator >= amountElems) {
            update(iterator - amountElems);
        }
    }

    private void update(int diff) throws IOException {
        amountElems = reader.read(buffer);
        iterator = diff;
    }
}