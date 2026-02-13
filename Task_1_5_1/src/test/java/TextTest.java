import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.Text;

class TextTest {
    @Test
    void plainTextSerialization() {
        var text = new Text("hello");
        Assertions.assertEquals("hello", text.toString());
    }

    @Test
    void boldSerialization() {
        var text = new Text.Bold("hello");
        Assertions.assertEquals("**hello**", text.toString());
    }

    @Test
    void italicSerialization() {
        var text = new Text.Italic("hi");
        Assertions.assertEquals("*hi*", text.toString());
    }

    @Test
    void strikethroughSerialization() {
        var text = new Text.Strikethrough("old");
        Assertions.assertEquals("~~old~~", text.toString());
    }

    @Test
    void codeSerialization() {
        var text = new Text.Code("int x = 1;");
        Assertions.assertEquals("`int x = 1;`", text.toString());
    }

    @Test
    void equalityWorks() {
        Assertions.assertEquals(new Text.Bold("a"), new Text.Bold("a"));
        Assertions.assertNotEquals(new Text.Bold("a"), new Text.Bold("b"));
        Assertions.assertNotEquals((Text) new Text.Bold("a"), (Text) new Text.Italic("a"));
    }
}
