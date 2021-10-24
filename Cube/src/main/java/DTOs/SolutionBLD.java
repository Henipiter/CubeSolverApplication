package DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class SolutionBLD implements Solution {
    private ArrayList<Move> algorithm;
    private String marks;
    private ArrayList<Integer> elementIndexes;
}
