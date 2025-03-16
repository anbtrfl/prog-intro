package markup;

public interface Elem {

    void toMarkdown(StringBuilder stringBuilder);

    void toHtml(StringBuilder stringBuilder);

    Tags getTags();

    HTMLTags getHTMLTags();
}
