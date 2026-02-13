package ru.nsu.sxrose1.markdown;

import java.util.List;

public final class UnorderedMarkdownList extends MarkdownList {
    /**
     * Unordered list constructor.
     *
     * @param items items of the list.
     */
    public UnorderedMarkdownList(List<Item> items) {
        super(items);
    }

    @Override
    protected String marker(int index) {
        return "-";
    }
}
