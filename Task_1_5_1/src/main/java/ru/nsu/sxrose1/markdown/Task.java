package ru.nsu.sxrose1.markdown;

import java.util.Objects;

/** Task element. */
public final class Task implements InlineElement {
    private final InlineElement content;
    private final boolean checked;

    /**
     * Task element constructor.
     *
     * @param content content for task.
     * @param checked task checked.
     */
    public Task(InlineElement content, boolean checked) {
        this.content = content;
        this.checked = checked;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "[" + (checked ? "x" : " ") + "] " + content.toString();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task otherTask)) {
            return false;
        }

        return Objects.equals(content, otherTask.content) && checked == otherTask.checked;
    }
}
