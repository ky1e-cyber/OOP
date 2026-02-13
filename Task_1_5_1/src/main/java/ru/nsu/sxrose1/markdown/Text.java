package ru.nsu.sxrose1.markdown;

import java.util.Objects;

/** Text element. */
public class Text implements InlineElement {
    protected final String value;

    /**
     * Text constructor.
     *
     * @param content content of the text element.
     */
    public Text(String content) {
        this.value = content;
    }

    protected String enc() {
        return "";
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

        Text text = (Text) o;
        return Objects.equals(value, text.value);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), value);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return enc() + value + enc();
    }

    /** Bold text element. */
    public static final class Bold extends Text {
        /**
         * Bold text constructor.
         *
         * @param content content of the text element.
         */
        public Bold(String content) {
            super(content);
        }

        @Override
        protected String enc() {
            return "**";
        }
    }

    /** Italic text element. */
    public static final class Italic extends Text {
        /**
         * Italic text constructor.
         *
         * @param content content of the text element.
         */
        public Italic(String content) {
            super(content);
        }

        @Override
        protected String enc() {
            return "*";
        }
    }

    /** Strikethrough text element. */
    public static final class Strikethrough extends Text {
        /**
         * Strikethrough text constructor.
         *
         * @param content content of the text element.
         */
        public Strikethrough(String content) {
            super(content);
        }

        @Override
        protected String enc() {
            return "~~";
        }
    }

    /** Inline code text element. */
    public static final class Code extends Text {
        /**
         * Inline code text constructor.
         *
         * @param content content of the text element.
         */
        public Code(String content) {
            super(content);
        }

        @Override
        protected String enc() {
            return "`";
        }
    }
}
