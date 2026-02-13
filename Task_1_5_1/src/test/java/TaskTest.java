import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.Task;
import ru.nsu.sxrose1.markdown.Text;

public class TaskTest {
    @Test
    void taskSerialization() {
        Task checked = new Task(new Text.Code("X"), true);
        Task unchecked = new Task(new Text.Code("X"), false);

        Assertions.assertEquals("[x] `X`", checked.toString());
        Assertions.assertEquals("[ ] `X`", unchecked.toString());
    }
}
