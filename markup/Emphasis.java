package markup;

import java.util.List;

public class Emphasis extends Unit implements Elem {

    public Emphasis(List<Elem> elems) {
        super(elems);
        this.tags = new Tags("*", "*");
        this.htmlTags = new HTMLTags("<em>", "</em>");
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
