package markup;

import java.util.List;

public class UnorderedList implements ParagraphOrList {
    private final List<ListItem> elems;
    private final HTMLTags htmlTags;

    public UnorderedList(List<ListItem> elems) {
        this.elems = elems;
        this.htmlTags = new HTMLTags("<ul>", "</ul>");
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
