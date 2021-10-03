package DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Vertex {
    String[] name;
    char[] color;
    int[] wall;
    int[] field;
    ArrayList<MoveEnum> algorithm;
}
