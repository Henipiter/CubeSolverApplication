package parsers.files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parsers.FileReader;

import java.io.IOException;
import java.util.List;


class FileReaderTest {

    

    @Test
    void getFileLines() throws IOException {
        List<String> a = FileReader.getFileLines("src/main/resources/vertexSetup.txt");
        Assertions.assertEquals(5, a.size());
    }
}