package DTOs;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Edge {
    String[] name;
    char[] color;
    int[] wall;
    int[] field;
    ArrayList<MoveEnum> algorithm;


}
