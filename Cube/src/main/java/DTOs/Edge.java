package DTOs;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Edge {
    private char[] color;
    private int[] wall;
    private int[] field;
}
