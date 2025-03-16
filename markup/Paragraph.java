package markup;

import java.util.List;

public class Paragraph extends Unit implements ParagraphOrList {
    public Paragraph(List<Elem> elems) {
        super(elems);
        this.tags = new Tags("", "");
        this.htmlTags = new HTMLTags("", "");
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        super.toMarkdown(stringBuilder, getTags());
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        super.toHtml(stringBuilder, getHTMLTags());
    }

    @Override
    public Tags getTags() {
        return tags;
    }

    @Override
    public HTMLTags getHTMLTags() {
        return htmlTags;
    }
}
