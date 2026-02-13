import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.Image;
import ru.nsu.sxrose1.markdown.Link;
import ru.nsu.sxrose1.markdown.Text;

class LinksTest {

    @Test
    void linkSerialization() {
        var link = new Link(new Text("Google"), "https://google.com");
        Assertions.assertEquals("[Google](https://google.com)", link.toString());
    }

    @Test
    void imageSerialization() {
        var image = new Image(new Text("alt"), "img.png");
        Assertions.assertEquals("![alt](img.png)", image.toString());
    }
}
