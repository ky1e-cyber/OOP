import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.Table;
import ru.nsu.sxrose1.markdown.Text;

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

        Assertions.assertEquals(expected, table.toString().trim());
    }

    @Test
    void tableRowLimit() {
        Table table =
                new Table.Builder().withRowLimit(1).addRow("H1", "H2").addRow("X", "Y").build();

        Assertions.assertEquals(1, table.toString().lines().count() - 1);
    }

    @Test
    void alignmentMismatchThrows() {
        Table.Builder builder = new Table.Builder().withAlignments(Table.Alignment.LEFT);

        builder.addRow("A", "B");

        Assertions.assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void wideTable() {
        Table table =
                new Table.Builder()
                        .withAlignments(
                                Table.Alignment.LEFT,
                                Table.Alignment.CENTER,
                                Table.Alignment.RIGHT,
                                Table.Alignment.LEFT,
                                Table.Alignment.RIGHT)
                        .addRow("ID", "Name", "Score", "Status", "Rank")
                        .addRow(1, "Alice", new Text.Bold("99"), "OK", 5)
                        .addRow(2, "Bob", 75, new Text.Italic("Fail"), 10)
                        .build();

        String markdown = table.toString();

        Assertions.assertTrue(markdown.contains("Alice"));
        Assertions.assertTrue(markdown.contains("**99**"));
        Assertions.assertTrue(markdown.contains("*Fail*"));

        Assertions.assertEquals(4, markdown.lines().count());
    }

    @Test
    void tableEquality() {
        var t1 = new Table.Builder().addRow("A", "B").build();

        var t2 = new Table.Builder().addRow("A", "B").build();

        Assertions.assertEquals(t1, t2);
    }
}
