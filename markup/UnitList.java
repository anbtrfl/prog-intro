package markup;

import java.util.List;

public abstract class UnitList {
    protected HTMLTags htmlTags;
    protected List<ParagraphOrList> elems;

    public UnitList(List<ParagraphOrList> elems) {
        this.elems = elems;
    }

    public void toHtml(StringBuilder stringBuilder, HTMLTags htmlTags) {
        stringBuilder.append(htmlTags.getOpen());
        for (ParagraphOrList elem : elems) {
            elem.toHtml(stringBuilder);
        }
        stringBuilder.append(htmlTags.getClose());
    }
}
