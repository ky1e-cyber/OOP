package ru.nsu.sxrose1.markdown;

import java.util.Objects;

/** Hyperlink element. */
public final class Link implements InlineElement {
    private final InlineElement text;
    private final String url;

    /**
     * Link element constructor.
     *
     * @param text visible text.
     * @param url url link.
     */
    public Link(InlineElement text, String url) {
        this.text = text;
        this.url = url;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "[" + text.toString() + "](" + url + ")";
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Link otherLink)) {
            return false;
        }

        return Objects.equals(text, otherLink.text) && Objects.equals(url, otherLink.url);
    }
}
