package DTOs;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Vertex {
    String[] name;
    char[] color;
    int[] wall;
    int[] field;
    ArrayList<MoveEnum> algorithm;


}
