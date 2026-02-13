package ru.nsu.sxrose1.markdown;

import java.util.List;

public final class OrderedMarkdownList extends MarkdownList {
    /**
     * Ordered list element constructor.
     *
     * @param items items of the list.
     */
    public OrderedMarkdownList(List<Item> items) {
        super(items);
    }

    @Override
    protected String marker(int index) {
        return (index + 1) + ".";
    }
}
