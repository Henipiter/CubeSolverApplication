package interpretations;

import cubes.Cube;
import lombok.Data;

@Data
public class Interpretation {

    private final char[] centerOrder;

    public Interpretation(char[] centerOrder) {
        this.centerOrder = centerOrder;
    }

    public char whichColorIsNextInOrder(int chosenSide, char colorOnLeft, char colorOnUp) {
        Interpretation1x1 interpretation1x1 = new Interpretation1x1(centerOrder);
        return interpretation1x1.whichColorIsNextInOrder(chosenSide, colorOnLeft, colorOnUp);
    }

    public static int getSideWithColor(char color, char[] centerArray) {
        for (int i = 0; i < 6; i++) {
            if (centerArray[i] == color) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexOfColor(char sideColor) {
        return new String(centerOrder).indexOf(sideColor);
    }

    public char getColorOfOppositeSide(char sideColor) {
        int indexOfColor = getIndexOfColor(sideColor);
        return centerOrder[(indexOfColor % 2 + 1) % 2 + indexOfColor / 2 * 2];
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
