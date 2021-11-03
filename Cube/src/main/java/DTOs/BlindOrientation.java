package DTOs;

import lombok.Getter;
import parsers.FileReader;
import parsers.ParseTxtCodeToValue;

import java.util.List;

@Getter
public class BlindOrientation {
    private char frontColor;
    private char upperColor;
    private static final String BLIND_ORIENTATION = "src/main/resources/blind_orientation.txt";

    public BlindOrientation(){
        readColorFromResource();
    }

    private void readColorFromResource(){
        List<String> properties = FileReader.getFileLines(BLIND_ORIENTATION);
        frontColor = ParseTxtCodeToValue.getMarks(properties.get(0)).charAt(0);
        upperColor = ParseTxtCodeToValue.getMarks(properties.get(1)).charAt(0);
    }
}
