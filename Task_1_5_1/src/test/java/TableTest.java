import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.Table;

class TableTest {

    @Test
    void simpleTable() {
        Table table =
                new Table.Builder()
                        .withAlignments(Table.Alignment.LEFT, Table.Alignment.RIGHT)
                        .addRow("Name", "Age")
                        .addRow("Alice", 25)
                        .addRow("Bob", 30)
                        .build();

        String expected =
                """
                | Name  | Age |
                | :---- | --: |
                | Alice |  25 |
                | Bob   |  30 |
                """
                        .trim();

        assertEquals(expected, table.toString().trim());
    }

    @Test
    void tableRowLimit() {
        Table table =
                new Table.Builder().withRowLimit(1).addRow("H1", "H2").addRow("X", "Y").build();

        assertEquals(1, table.toString().lines().count() - 1);
    }

    @Test
    void alignmentMismatchThrows() {
        Table.Builder builder = new Table.Builder().withAlignments(Table.Alignment.LEFT);

        builder.addRow("A", "B");

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void tableEquality() {
        var t1 = new Table.Builder().addRow("A", "B").build();

        var t2 = new Table.Builder().addRow("A", "B").build();

        assertEquals(t1, t2);
    }
}
