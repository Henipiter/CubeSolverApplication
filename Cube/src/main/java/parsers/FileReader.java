package parsers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileReader {

    private static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public static List<String> getFileLines(String path)   {
        String string = null;
        try {
            string = readFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(string.split("\n"));
    }

}
