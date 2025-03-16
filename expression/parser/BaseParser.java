package expression.parser;

public abstract class BaseParser {
    protected static final char END = 0;
    protected char ch;
    protected CharSource source;

    protected void next() {
        ch = source.hasNext() ? source.next() : END;
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);

    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean eof() {
        return take(END);
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw source.error("Expected '" + expected + "', found '" + take() + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }


}
