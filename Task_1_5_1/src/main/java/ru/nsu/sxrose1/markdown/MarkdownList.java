package ru.nsu.sxrose1.markdown;

import java.util.List;
import java.util.Objects;

/** List element. */
public abstract class MarkdownList implements BlockElement {
    /**
     * Item of list.
     *
     * @param content content of item.
     * @param nested optional nested sublist.
     */
    public record Item(InlineElement content, MarkdownList nested) {
        public Item(InlineElement content) {
            this(content, null);
        }

        /**
         * Check if item has nested sublist.
         *
         * @return true if item has nested sublist
         */
        public boolean hasNested() {
            return Objects.nonNull(nested);
        }

        /** {@inheritDoc} */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Item otherItem)) {
                return false;
            }

            return Objects.equals(content, otherItem.content)
                    && Objects.equals(nested, otherItem.nested);
        }
    }

    protected final List<Item> items;

    /**
     * List element constructor.
     *
     * @param items list of elements.
     */
    protected MarkdownList(List<Item> items) {
        this.items = List.copyOf(items);
    }

    protected abstract String marker(int index);

    protected String indent(int level) {
        return "  ".repeat(level);
    }

    protected String serialize(int level) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            sb.append(indent(level))
                    .append(marker(i))
                    .append(" ")
                    .append(item.content().toString())
                    .append("\n");

            if (item.hasNested()) {
                sb.append(item.nested().serialize(level + 1));
            }
        }

        return sb.toString();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return serialize(0).trim();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (Objects.isNull(o) || getClass() != o.getClass()) {
            return false;
        }
        MarkdownList that = (MarkdownList) o;
        return items.equals(that.items);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), items);
    }
}
