import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.MDList;
import ru.nsu.sxrose1.markdown.OrderedMDList;
import ru.nsu.sxrose1.markdown.Text;
import ru.nsu.sxrose1.markdown.UnorderedMDList;

import java.util.List;

class ListTest {
    @Test
    void unorderedListSerialization() {
        var list =
                new UnorderedMDList(
                        List.of(
                                new MDList.Item(new Text("A")),
                                new MDList.Item(new Text.Bold("B"))));

        String expected =
                """
                - A
                - **B**
                """
                        .trim();

        assertEquals(expected, list.toString());
    }

    @Test
    void orderedListSerialization() {
        var list =
                new OrderedMDList(
                        List.of(
                                new MDList.Item(new Text("First")),
                                new MDList.Item(new Text("Second"))));

        String expected =
                """
                1. First
                2. Second
                """
                        .trim();

        assertEquals(expected, list.toString());
    }

    @Test
    void nestedListSerialization() {
        var nested = new UnorderedMDList(List.of(new MDList.Item(new Text("Nested"))));

        var list = new OrderedMDList(List.of(new MDList.Item(new Text("Parent"), nested)));

        String expected =
                """
                1. Parent
                  - Nested
                """
                        .trim();

        assertEquals(expected, list.toString().trim());
    }

    @Test
    void listEquality() {
        var l1 = new UnorderedMDList(List.of(new MDList.Item(new Text("A"))));

        var l2 = new UnorderedMDList(List.of(new MDList.Item(new Text("A"))));

        assertEquals(l1, l2);
    }
}
