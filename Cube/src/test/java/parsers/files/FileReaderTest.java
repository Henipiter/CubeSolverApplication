package parsers.files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parsers.FileReader;

import java.util.List;


class FileReaderTest {


    @Test
    void getFileLines() {
        List<String> a = FileReader.getFileLines("src/main/resources/vertexSetup.txt");
        Assertions.assertEquals(31, a.size());
    }
}
