package DTOs;

import java.util.ArrayList;

public abstract class Solution {

    protected ArrayList<Move> algorithm = new ArrayList<>();
    protected ArrayList<Integer> elementIndexes;
    protected ElementType elementType;
    protected ProgressInfo progressInfo;

    public abstract ArrayList<Move> getWholeAlg(Object solution);

}
