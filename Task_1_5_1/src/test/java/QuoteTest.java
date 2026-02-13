import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.Heading;
import ru.nsu.sxrose1.markdown.Quote;
import ru.nsu.sxrose1.markdown.Text;

public class QuoteTest {
    @Test
    void quoteSerialization() {
        var quote = new Quote(new Heading(1, new Text("Title")));

        String expected =
                """
                > # Title
                """
                        .trim();

        assertEquals(expected, quote.toString());
    }
}
