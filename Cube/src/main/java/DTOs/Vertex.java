package DTOs;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Vertex {
    char[] color;
    int[] wall;
    int[] field;
}
