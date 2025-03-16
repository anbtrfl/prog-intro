package markup;

public class HTMLTags {
    private final String open;
    private final String close;

    public HTMLTags(String open, String close) {
        this.open = open;
        this.close = close;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }
}
