import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.sxrose1.markdown.CodeBlock;

public class CodeBlockTest {
    @Test
    void multiLineCodeBlockSerialization() {
        String code =
                """
                int x = 1;
                int y = 2;
                System.out.println(x + y);
                """
                        .trim();

        var block = new CodeBlock(code);

        Assertions.assertEquals("```\n" + code + "\n```", block.toString());
    }
}
