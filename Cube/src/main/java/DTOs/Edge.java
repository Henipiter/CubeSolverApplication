package DTOs;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Builder
public class Edge {
    private String[] name;
    private char[] color;
    private int[] wall;
    private int[] field;
    private  ArrayList<MoveEnum> algorithm;


}
