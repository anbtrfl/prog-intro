package markup;

public class Text implements Elem {

    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(text);
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(text);
    }

    @Override
    public Tags getTags() {
        return null;
    }

    @Override
    public HTMLTags getHTMLTags() {
        return null;
    }
}
