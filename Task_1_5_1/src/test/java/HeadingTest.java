import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.Heading;
import ru.nsu.sxrose1.markdown.Text;

class HeadingTest {

    @Test
    void headingLevel3() {
        var heading = new Heading(3, new Text("Title"));
        Assertions.assertEquals("### Title", heading.toString());
    }

    @Test
    void headingEquality() {
        var h1 = new Heading(2, new Text.Bold("X"));
        var h2 = new Heading(2, new Text.Bold("X"));

        Assertions.assertEquals(h1, h2);
    }

    @Test
    void invalidLevelThrows() {
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> new Heading(7, new Text("Bad")));
    }
}
