package parsers.files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


class FileReaderTest {

    @Test
    void a() throws IOException {
        String a = FileReader.readFile("src/main/resources/vertexSetup.txt");
        Assertions.assertEquals("setup", a);
    }

    @Test
    void getFileLines() throws IOException {
        List<String> a = FileReader.getFileLines("src/main/resources/vertexSetup.txt");
        Assertions.assertEquals(5, a.size());
    }
}