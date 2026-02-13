package ru.nsu.sxrose1.markdown;

import java.util.Objects;

/** Heading element. */
public final class Heading implements BlockElement {
    private final int level;
    private final InlineElement content;

    /**
     * Heading element constructor.
     *
     * @param level heading level.
     * @param content content of heading.
     */
    public Heading(int level, InlineElement content) {
        if (level < 1 || level > 6) {
            throw new IllegalArgumentException("Heading level must be in [1;6]");
        }

        this.level = level;
        this.content = content;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "#".repeat(level) + " " + content.toString();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Heading otherHeading)) {
            return false;
        }

        return level == otherHeading.level && Objects.equals(content, otherHeading.content);
    }
}
