package markup;

import java.util.List;

public class Strong extends Unit implements Elem {
    public Strong(List<Elem> elems) {
        super(elems);
        this.tags = new Tags("__", "__");
        this.htmlTags = new HTMLTags("<strong>", "</strong>");
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
