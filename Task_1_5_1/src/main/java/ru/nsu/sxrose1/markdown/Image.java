package ru.nsu.sxrose1.markdown;

import java.util.Objects;

/** Embedded image element. */
public final class Image implements InlineElement {
    private final InlineElement noimg;
    private final String url;

    /**
     * Image element constructor.
     *
     * @param noimg content to display when there's no image.
     * @param url image url.
     */
    public Image(InlineElement noimg, String url) {
        this.noimg = noimg;
        this.url = url;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "![" + noimg.toString() + "](" + url + ")";
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Image otherImg)) {
            return false;
        }

        return Objects.equals(noimg, otherImg.noimg) && Objects.equals(url, otherImg.url);
    }
}
