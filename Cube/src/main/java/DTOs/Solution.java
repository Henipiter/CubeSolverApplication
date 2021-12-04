package DTOs;

import java.util.ArrayList;

public class Solution {

    protected ArrayList<Move> algorithm = new ArrayList<>();
    protected ArrayList<Integer> elementIndexes;
    protected ElementType elementType;
    protected ProgressInfo progressInfo;

    protected String message;
    protected String marks;
    protected char color;
    protected InfoType infoType;

    public Solution(ArrayList<Move> algorithm, String message) {
        this.algorithm = algorithm;
        this.message = message;
        this.infoType = InfoType.DEFAULT;
    }

    public Solution(ArrayList<Move> algorithm, String message, char color,
                       ElementType elementType, ProgressInfo progressInfo) {
        this.algorithm = algorithm;
        this.color = color;
        this.message = message;
        this.infoType = InfoType.COLOR;
        this.elementType = elementType;
        this.progressInfo = progressInfo;
    }

    public Solution(ArrayList<Move> algorithm, String message, ArrayList<Integer> elementIndexes,
                       ElementType elementType, ProgressInfo progressInfo) {
        this.algorithm = algorithm;
        this.elementIndexes = elementIndexes;
        this.message = message;
        this.infoType = InfoType.INDEX;
        this.elementType = elementType;
        this.progressInfo = progressInfo;
    }

    public Solution(InfoType infoType) {
        this.infoType = infoType;
    }

    public void setElementIndexes(ArrayList<Integer> elementIndexes) {
        this.elementIndexes = elementIndexes;
    }

    public Solution(ArrayList<Move> algorithm, String marks, ArrayList<Integer> elementIndexes,
                       ElementType elementType) {
        this.algorithm = algorithm;
        this.marks = marks;
        this.elementIndexes = elementIndexes;
        this.elementType = elementType;
        this.progressInfo = ProgressInfo.NONE;
    }

    public static ArrayList<Move> getWholeAlg(ArrayList<Solution> solution) {
        ArrayList<Move> alg = new ArrayList<>();
        solution.forEach(moves -> alg.addAll(moves.algorithm));
        return alg;
    }

    public static String getWholeMarks(ArrayList<Solution> solution) {
        StringBuilder stringBuilder = new StringBuilder();
        solution.forEach(moves -> stringBuilder.append(moves.marks).append(" "));
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }
    public ArrayList<Move> getAlgorithm() {
        return algorithm;
    }
}
