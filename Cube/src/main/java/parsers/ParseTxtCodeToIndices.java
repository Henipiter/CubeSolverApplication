package parsers;

import lombok.Getter;

@Getter
public class ParseTxtCodeToIndices {

    private int elementIndex;
    private int fieldIndex;

    public ParseTxtCodeToIndices(String code){
        String indexes = code.split(":")[0];
        elementIndex = Integer.parseInt(indexes.split("-")[0]);
        fieldIndex = Integer.parseInt(indexes.split("-")[1]);
    }


}
