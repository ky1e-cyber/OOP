import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.Text;

class TextTest {

    @Test
    void plainTextSerialization() {
        var text = new Text("hello");
        assertEquals("hello", text.toString());
    }

    @Test
    void boldSerialization() {
        var text = new Text.Bold("hello");
        assertEquals("**hello**", text.toString());
    }

    @Test
    void italicSerialization() {
        var text = new Text.Italic("hi");
        assertEquals("*hi*", text.toString());
    }

    @Test
    void strikethroughSerialization() {
        var text = new Text.Strikethrough("old");
        assertEquals("~~old~~", text.toString());
    }

    @Test
    void codeSerialization() {
        var text = new Text.Code("int x = 1;");
        assertEquals("`int x = 1;`", text.toString());
    }

    @Test
    void equalityWorks() {
        assertEquals(new Text.Bold("a"), new Text.Bold("a"));
        assertNotEquals(new Text.Bold("a"), new Text.Bold("b"));
        assertNotEquals((Text) new Text.Bold("a"), (Text) new Text.Italic("a"));
    }
}
