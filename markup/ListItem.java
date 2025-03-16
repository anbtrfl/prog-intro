package markup;

import java.util.List;

public class ListItem extends UnitList {

    public ListItem(List<ParagraphOrList> elems) {
        super(elems);
        this.htmlTags = new HTMLTags("<li>", "</li>");
    }

    public void toHtml(StringBuilder stringBuilder) {
        super.toHtml(stringBuilder, getHTMLTags());
    }

    public HTMLTags getHTMLTags() {
        return htmlTags;
    }
}
