package ru.nsu.sxrose1.markdown;

import java.util.Objects;
import java.util.stream.Collectors;

/** Quote element. */
public final class Quote implements BlockElement {
    private final BlockElement content;

    /**
     * Quote element constructor.
     *
     * @param content contents of the quote.
     */
    public Quote(BlockElement content) {
        this.content = content;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return content.toString().lines().map(l -> "> " + l).collect(Collectors.joining("\n"));
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quote otherQuote)) {
            return false;
        }

        return Objects.equals(content, otherQuote.content);
    }
}
