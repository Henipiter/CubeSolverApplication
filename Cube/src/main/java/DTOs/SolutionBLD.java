package DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class SolutionBLD {
    private ArrayList<Move> algorithm;
    private String marks;
    private Integer elementIndexes;
}
