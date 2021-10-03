package interpretations;

import cubes.Cube;
import cubes.Cube1x1;
import lombok.Data;

@Data
public class Interpretation {

    public static char whichColorIsNextInOrder(int chosenSide, char colorOnLeft, char colorOnUp) {
        Interpretation1x1 interpretation1x1 = new Interpretation1x1();
        interpretation1x1.refreshCube(new Cube1x1());
        return interpretation1x1.whichColorIsNextInOrder(chosenSide, colorOnLeft, colorOnUp);
    }

    public static char getColorOfOppositeSide(char sideColor) {
        char[] colors = new char[]{'w', 'y', 'o', 'r', 'g', 'b'};
        int indexOfColor = new String(colors).indexOf(sideColor);
        return colors[(indexOfColor % 2 + 1) % 2 + indexOfColor / 2 * 2];
    }

    public static char[] getColorOrder(char colorOnUp) {
        Interpretation1x1 interpretation1x1 = new Interpretation1x1();
        interpretation1x1.refreshCube(new Cube1x1());
        return interpretation1x1.getColorOrder(colorOnUp);
    }

    public static char[] getCenterArray(Cube cube) {
        char[] centerArray = new char[6];
        int[] centerOrder = new int[]{0, 1, 5, 3, 4, 2};
        for (int i = 0; i < 6; i++) {
            centerArray[i] = cube.getCenter()[centerOrder[i]];
        }
        return centerArray;
    }
}
