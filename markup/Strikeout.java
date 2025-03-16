package markup;

import java.util.List;

public class Strikeout extends Unit implements Elem {
    public Strikeout(List<Elem> elems) {
        super(elems);
        this.tags = new Tags("~", "~");
        this.htmlTags = new HTMLTags("<s>", "</s>");
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
