package ru.nsu.sxrose1.markdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/** Table element. */
public final class Table implements BlockElement {
    /** Alignment of table columns. */
    public enum Alignment {
        LEFT,
        RIGHT,
        CENTER
    }

    /** Table row. */
    public record Row(List<InlineElement> cells) {
        public Row(List<InlineElement> cells) {
            this.cells = List.copyOf(cells);
        }

        /** {@inheritDoc} */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Row otherRow)) {
                return false;
            }

            return Objects.equals(cells, otherRow.cells);
        }
    }

    private final List<Row> rows;
    private final List<Alignment> alignments;

    /**
     * Table element constructor.
     *
     * @param rows rows of the table.
     * @param alignments alignments of rows.
     */
    private Table(List<Row> rows, List<Alignment> alignments) {
        this.rows = List.copyOf(rows);
        this.alignments = List.copyOf(alignments);
    }

    private int[] computeWidths(int columnCount) {
        int[] widths = new int[columnCount];

        for (var row : rows) {
            for (int i = 0; i < columnCount; i++) {
                String value = row.cells.get(i).toString();
                widths[i] = Math.max(widths[i], value.length());
            }
        }

        return widths;
    }

    private String pad(String value, int width, Alignment alignment) {
        int d = width - value.length();

        return switch (alignment) {
            case LEFT -> value + " ".repeat(d);
            case RIGHT -> " ".repeat(d) + value;
            case CENTER -> {
                int left = d / 2;
                int right = d - left;
                yield " ".repeat(left) + value + " ".repeat(right);
            }
        };
    }

    private String rowToString(Row row, int[] widths) {
        StringBuilder sb = new StringBuilder("|");

        for (int i = 0; i < widths.length; i++) {
            String value = row.cells.get(i).toString();
            sb.append(" ").append(pad(value, widths[i], alignments.get(i))).append(" |");
        }

        return sb.toString();
    }

    private String alignmentRow(int[] widths) {
        BiFunction<Integer, Alignment, String> getMarker =
                (width, alignment) -> {
                    return switch (alignment) {
                        case LEFT -> ":" + "-".repeat(width - 1);
                        case RIGHT -> "-".repeat(width - 1) + ":";
                        case CENTER -> ":" + "-".repeat(width - 2) + ":";
                    };
                };

        StringBuilder sb = new StringBuilder("|");

        for (int i = 0; i < widths.length; i++) {
            sb.append(" ").append(getMarker.apply(widths[i], alignments.get(i))).append(" |");
        }

        return sb.toString();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        if (rows.isEmpty()) {
            return "";
        }

        int columnCount = rows.getFirst().cells.size();
        int[] widths = computeWidths(columnCount);

        StringBuilder sb = new StringBuilder();

        sb.append(rowToString(rows.getFirst(), widths)).append("\n");

        sb.append(alignmentRow(widths)).append("\n");

        // rows
        for (int i = 1; i < rows.size(); i++) {
            sb.append(rowToString(rows.get(i), widths)).append("\n");
        }

        return sb.toString().trim();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Table table)) {
            return false;
        }

        return rows.equals(table.rows) && alignments.equals(table.alignments);
    }

    /** Table builder. */
    public static final class Builder {

        private final List<Row> rows = new ArrayList<>();
        private List<Alignment> alignments = new ArrayList<>();
        private int rowLimit = Integer.MAX_VALUE;

        /**
         * Set alignments for columns.
         *
         * @param alignments alignments.
         * @return builder.
         */
        public Builder withAlignments(Alignment... alignments) {
            this.alignments = List.of(alignments);
            return this;
        }

        /**
         * Set row limit.
         *
         * @param limit limit.
         * @return builder.
         */
        public Builder withRowLimit(int limit) {
            this.rowLimit = limit;
            return this;
        }

        private InlineElement convert(Object value) {
            if (value instanceof InlineElement e) {
                return e;
            }
            return new Text(String.valueOf(value));
        }

        /**
         * Add row to table.
         *
         * @param values values of row.
         * @return builder.
         */
        public Builder addRow(Object... values) {
            if (rows.size() >= rowLimit) {
                return this;
            }

            List<InlineElement> cells =
                    Arrays.stream(values).map(this::convert).collect(Collectors.toList());

            rows.add(new Row(cells));
            return this;
        }

        /**
         * Build table.
         *
         * @return new table.
         */
        public Table build() {
            if (rows.isEmpty()) {
                throw new IllegalStateException("Table must have at least one row");
            }

            int columnCount = rows.getFirst().cells.size();

            if (alignments.isEmpty()) {
                alignments = Collections.nCopies(columnCount, Alignment.LEFT);
            }

            if (alignments.size() != columnCount) {
                throw new IllegalStateException("Alignment count must match column count");
            }

            return new Table(rows, alignments);
        }
    }
}
