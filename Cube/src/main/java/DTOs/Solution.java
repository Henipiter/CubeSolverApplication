package DTOs;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Solution {

    protected String message;
    protected ArrayList<Move> algorithm = new ArrayList<>();
    protected ArrayList<Integer> beginIndex;
    protected ArrayList<Integer> endIndex;
    protected ElementType elementType;
    protected ProgressInfo progressInfo;

    protected String marks;
    protected char color;
    protected InfoType infoType;

    private Solution(ArrayList<Move> algorithm, String message) {
        this.algorithm = algorithm;
        this.message = message;
        this.infoType = InfoType.DEFAULT;
    }

    private Solution(ArrayList<Move> algorithm, String message, char color,
                     ElementType elementType, ProgressInfo progressInfo) {
        this.algorithm = algorithm;
        this.color = color;
        this.message = message;
        this.infoType = InfoType.COLOR;
        this.elementType = elementType;
        this.progressInfo = progressInfo;
    }

    private Solution(ArrayList<Move> algorithm, String message, ArrayList<Integer> beginIndex, ArrayList<Integer> endIndex,
                     ElementType elementType, ProgressInfo progressInfo) {
        this.algorithm = algorithm;
        this.message = message;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.elementType = elementType;
        this.infoType = InfoType.INDEX;
        this.progressInfo = progressInfo;
    }

    private Solution(ArrayList<Move> algorithm, String message, String marks,
                     ArrayList<Integer> beginIndex, ArrayList<Integer> endIndex, ElementType elementType) {
        this.algorithm = algorithm;
        this.message = message;
        this.marks = marks;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.elementType = elementType;
        this.infoType = InfoType.INDEX;
        this.progressInfo = ProgressInfo.NONE;
    }

    public static ArrayList<Move> getWholeAlg(ArrayList<Solution> solution) {
        ArrayList<Move> alg = new ArrayList<>();
        for (Solution x : solution) {
            alg.addAll(x.algorithm);
        }
        return alg;
    }

    public static String getWholeMarks(ArrayList<Solution> solution) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Solution x : solution) {
            stringBuilder.append(x.marks).append(" ");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    public Solution(InfoType infoType) {
        this.infoType = infoType;
    }

    public static Solution parity(ArrayList<Move> algorithm, String message, ArrayList<Integer> beginIndexes, ArrayList<Integer> endIndexes) {
        return new Solution(algorithm, message, beginIndexes, endIndexes, ElementType.EDGE, ProgressInfo.NONE);
    }

    public static Solution firstCross(ArrayList<Move> algorithm, ArrayList<Integer> beginIndexes, ArrayList<Integer> endIndexes) {
        String number = " (" + Counter.getAndIncrement() + ")";
        return new Solution(algorithm, "First cross" + number, beginIndexes, endIndexes, ElementType.EDGE, ProgressInfo.NONE);
    }

    public static Solution firstIncorrectCross(ArrayList<Move> algorithm, ArrayList<Integer> beginIndexes, ArrayList<Integer> endIndexes) {
        return new Solution(algorithm, "Correct bottom cross", beginIndexes, endIndexes, ElementType.EDGE, ProgressInfo.CROSS);
    }

    public static Solution firstLayer(ArrayList<Move> algorithm, ArrayList<Integer> beginIndexes, ArrayList<Integer> endIndexes) {
        String number = " (" + Counter.getAndIncrement() + ")";
        return new Solution(algorithm, "First layer" + number, beginIndexes, endIndexes, ElementType.VERTEX, ProgressInfo.CROSS);
    }

    public static Solution secondLayer(ArrayList<Move> algorithm, ArrayList<Integer> beginIndexes, ArrayList<Integer> endIndexes) {
        String number = " (" + Counter.getAndIncrement() + ")";
        return new Solution(algorithm, "Second layer" + number, beginIndexes, endIndexes, ElementType.EDGE, ProgressInfo.FIRST_LAYER);
    }

    public static Solution secondCross(ArrayList<Move> algorithm, char color) {
        return new Solution(algorithm, "Second cross", color, ElementType.EDGE, ProgressInfo.NONE);
    }

    public static Solution secondIncorrectCross(ArrayList<Move> algorithm, ArrayList<Integer> beginIndexes, ArrayList<Integer> endIndexes) {
        return new Solution(algorithm, "Correct second cross", beginIndexes, endIndexes, ElementType.EDGE, ProgressInfo.NONE);
    }

    public static Solution permutation(ArrayList<Move> algorithm, ArrayList<Integer> beginIndexes, ArrayList<Integer> endIndexes) {
        return new Solution(algorithm, "Permute vertices", beginIndexes, endIndexes, ElementType.VERTEX, ProgressInfo.NONE);
    }

    public static Solution orientation(ArrayList<Move> algorithm, char color) {
        String number = " (" + Counter.getAndIncrement() + ")";
        return new Solution(algorithm, "Orient vertices" + number, color, ElementType.ALL, ProgressInfo.NONE);
    }

    public static Solution center4x4(ArrayList<Move> algorithm, String message, char baseColor) {
        return new Solution(algorithm, message, baseColor, ElementType.CENTER, ProgressInfo.NONE);
    }

    public static Solution pairEdges4x4(ArrayList<Move> algorithm, ArrayList<Integer> beginIndex, ArrayList<Integer> endIndex) {
        String number = " (" + Counter.getAndIncrement() + ")";
        return new Solution(algorithm, "Pair edge" + number,
                beginIndex, endIndex, ElementType.EDGE, ProgressInfo.NONE);
    }

    public static Solution blind(ArrayList<Move> algorithm, String message, String marks,
                                 ArrayList<Integer> elementIndexes, ElementType elementType) {
        return new Solution(algorithm, message, marks, elementIndexes, elementIndexes, elementType);
    }

    public static Solution blind(ArrayList<Move> algorithm, String message) {
        return new Solution(algorithm, message, "", null, null, null);
    }

    public static Solution rotate(ArrayList<Move> alg) {
        return new Solution(alg, "Rotate cube");
    }

    public static Solution rotateBlind(ArrayList<Move> alg) {
        Solution solution = new Solution(alg, "Rotate cube");
        solution.setMarks("");
        return solution;
    }

    public ArrayList<Move> getAlgorithm() {
        return algorithm;
    }
}
