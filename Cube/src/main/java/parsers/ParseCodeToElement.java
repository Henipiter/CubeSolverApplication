package parsers;

import DTOs.FileElement;

import java.util.ArrayList;
import java.util.List;


public class ParseCodeToElement {

    public static List<FileElement> getElementsFromFile(String filePath){
        return getElements(FileReader.getFileLines(filePath));
    }

    public static List<FileElement> getElements(List<String> elementMarks) {
        List<FileElement> fileElements = new ArrayList<>();
        ParseTxtCodeToIndices parseTxtCodeToIndices;
        for (String mark : elementMarks) {
            if(mark.equals("")){
                continue;
            }
            parseTxtCodeToIndices = new ParseTxtCodeToIndices(mark);
            int elementIndex = parseTxtCodeToIndices.getElementIndex();
            int fieldIndex = parseTxtCodeToIndices.getFieldIndex();
            String data = ParseTxtCodeToValue.getMarks(mark);

            fileElements.add(new FileElement(elementIndex, fieldIndex, data));
        }
        return fileElements;
    }
}
