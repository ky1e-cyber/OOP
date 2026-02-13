package ru.nsu.sxrose1.markdown;

import java.util.Objects;

public final class CodeBlock implements BlockElement {
    private final String text;

    /**
     * Code block element constructor.
     *
     * @param text content of the block
     */
    public CodeBlock(String text) {
        this.text = text;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "```\n" + text + "\n```";
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodeBlock otherBlock)) {
            return false;
        }

        return Objects.equals(text, otherBlock.text);
    }
}
