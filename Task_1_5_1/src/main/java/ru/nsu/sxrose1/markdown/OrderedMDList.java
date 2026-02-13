package ru.nsu.sxrose1.markdown;

import java.util.List;

public class OrderedMDList extends MDList {
    /**
     * Ordered list element constructor.
     *
     * @param items items of the list.
     */
    public OrderedMDList(List<Item> items) {
        super(items);
    }

    @Override
    protected String marker(int index) {
        return (index + 1) + ".";
    }
}
