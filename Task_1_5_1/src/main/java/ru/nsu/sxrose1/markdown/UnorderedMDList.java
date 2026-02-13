package ru.nsu.sxrose1.markdown;

import java.util.List;

public class UnorderedMDList extends MDList {
    /**
     * Unordered list constructor.
     *
     * @param items items of the list.
     */
    public UnorderedMDList(List<Item> items) {
        super(items);
    }

    @Override
    protected String marker(int index) {
        return "-";
    }
}
