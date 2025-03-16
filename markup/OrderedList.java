package markup;

import java.util.List;

public class OrderedList implements ParagraphOrList {
    private final List<ListItem> elems;
    protected HTMLTags htmlTags;

    public OrderedList(List<ListItem> elems) {
        this.elems = elems;
        this.htmlTags = new HTMLTags("<ol>", "</ol>");
    }


    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(htmlTags.getOpen());
        for (ListItem elem : elems) {
            elem.toHtml(stringBuilder);
        }
        stringBuilder.append(htmlTags.getClose());
    }


    @Override
    public HTMLTags getHTMLTags() {
        return htmlTags;
    }

    @Override
    public Tags getTags() {
        return null;
    }
}
