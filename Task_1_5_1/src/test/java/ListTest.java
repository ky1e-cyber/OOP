import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.MarkdownList;
import ru.nsu.sxrose1.markdown.OrderedMarkdownList;
import ru.nsu.sxrose1.markdown.Text;
import ru.nsu.sxrose1.markdown.UnorderedMarkdownList;

class ListTest {
    @Test
    void unorderedListSerialization() {
        var list =
                new UnorderedMarkdownList(
                        List.of(
                                new MarkdownList.Item(new Text("A")),
                                new MarkdownList.Item(new Text.Bold("B"))));

        String expected =
                """
                - A
                - **B**
                """
                        .trim();

        Assertions.assertEquals(expected, list.toString());
    }

    @Test
    void orderedListSerialization() {
        var list =
                new OrderedMarkdownList(
                        List.of(
                                new MarkdownList.Item(new Text("First")),
                                new MarkdownList.Item(new Text("Second"))));

        String expected =
                """
                1. First
                2. Second
                """
                        .trim();

        Assertions.assertEquals(expected, list.toString());
    }


    @Test
    void nestedListSerialization() {
        var nested = new UnorderedMarkdownList(List.of(new MarkdownList.Item(new Text("Nested"))));

        var list =
                new OrderedMarkdownList(List.of(new MarkdownList.Item(new Text("Parent"), nested)));

        String expected =
                """
                1. Parent
                  - Nested
                """
                        .trim();

        Assertions.assertEquals(expected, list.toString().trim());
    }

    @Test
    void deeplyNestedMixedLists() {

        var level3 =
                new UnorderedMarkdownList(
                        List.of(
                                new MarkdownList.Item(new Text("L3-A")),
                                new MarkdownList.Item(new Text.Bold("L3-B"))));

        var level2 =
                new OrderedMarkdownList(
                        List.of(
                                new MarkdownList.Item(new Text("L2-A"), level3),
                                new MarkdownList.Item(new Text.Italic("L2-B"))));

        var level1 =
                new UnorderedMarkdownList(List.of(new MarkdownList.Item(new Text("Root"), level2)));

        String expected =
                """
                - Root
                  1. L2-A
                    - L3-A
                    - **L3-B**
                  2. *L2-B*
                """
                        .trim();

        Assertions.assertEquals(expected, level1.toString());
    }

    @Test
    void listEquality() {
        var l1 = new UnorderedMarkdownList(List.of(new MarkdownList.Item(new Text("A"))));

        var l2 = new UnorderedMarkdownList(List.of(new MarkdownList.Item(new Text("A"))));

        Assertions.assertEquals(l1, l2);
    }

    @Test
    void nestedListEquality() {
        var nested1 = new UnorderedMarkdownList(List.of(new MarkdownList.Item(new Text("X"))));

        var list1 = new OrderedMarkdownList(List.of(new MarkdownList.Item(new Text("A"), nested1)));

        var nested2 = new UnorderedMarkdownList(List.of(new MarkdownList.Item(new Text("X"))));

        var list2 = new OrderedMarkdownList(List.of(new MarkdownList.Item(new Text("A"), nested2)));

        Assertions.assertEquals(list1, list2);
        Assertions.assertEquals(list1.hashCode(), list2.hashCode());
    }
}
