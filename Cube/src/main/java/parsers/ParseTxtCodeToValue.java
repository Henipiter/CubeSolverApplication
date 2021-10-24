package parsers;

public class ParseTxtCodeToValue {

    public static String getMarks(String code){
        return code.split(":")[1];
    }
}
