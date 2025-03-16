package markup;

import java.util.List;

public abstract class Unit {

    protected Tags tags;
    protected HTMLTags htmlTags;
    protected List<Elem> elems;

    public Unit(List<Elem> elems) {
        this.elems = elems;
    }

    public void toMarkdown(StringBuilder stringBuilder, Tags tags) {
        stringBuilder.append(tags.getOpen());
        for (Elem elem : elems) {
            elem.toMarkdown(stringBuilder);
        }
        stringBuilder.append(tags.getClose());
    }

    public void toHtml(StringBuilder stringBuilder, HTMLTags htmlTags) {
        stringBuilder.append(htmlTags.getOpen());
        for (Elem elem : elems) {
            elem.toHtml(stringBuilder);
        }
        stringBuilder.append(htmlTags.getClose());
    }
}
