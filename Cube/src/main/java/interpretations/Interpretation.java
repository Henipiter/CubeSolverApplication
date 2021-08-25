package interpretations;

import cubes.Cube1x1;
import lombok.Data;

@Data
public class Interpretation {


    public static char whichColorIsNextInOrder(int choosenSide,char colorOnLeft,char colorOnUp) {
        Cube1x1 cube1x1 = new Cube1x1();
        Interpretation1x1 interpretation1x1 = new Interpretation1x1();
        interpretation1x1.refreshCube(cube1x1);
        return interpretation1x1.whichColorIsNextInOrder(choosenSide,colorOnLeft, colorOnUp);
    }

    public char getColorOfOppositeSide(char sideColor){
        char[] colors = new char[] {'w','y','o','r','g','b'};
        int indexOfColor = new String(colors).indexOf(sideColor);
        return colors[(indexOfColor%2 +1)%2+indexOfColor/2*2];
    }




}
