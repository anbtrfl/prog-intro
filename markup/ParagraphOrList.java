package markup;

public interface ParagraphOrList {

    void toMarkdown(StringBuilder stringBuilder);

    void toHtml(StringBuilder stringBuilder);

    HTMLTags getHTMLTags();

    Tags getTags();
}
