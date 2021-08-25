package interpretations;

import DTOs.*;
import cubes.Cube;
import cubes.Cube1x1;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;


@EqualsAndHashCode(callSuper = true)
@Data
public class Interpretation4x4Centers extends  Interpretation {
    private ArrayList<Center> centerArrayList;

    public Interpretation4x4Centers() {
        centerArrayList = new ArrayList<>();
    }

    public void interpretCenters(Cube cube) {
        centerArrayList = new ArrayList<>();
        Center center;
        for (int i = 0; i < 6; i++) {
            if (i == 1 || i == 5) {
                center = new Center(new char[]{
                        cube.getCube()[i][9], cube.getCube()[i][10],
                        cube.getCube()[i][6], cube.getCube()[i][5]
                });
            } else if (i == 3) {
                center = new Center(new char[]{
                        cube.getCube()[i][6], cube.getCube()[i][5],
                        cube.getCube()[i][9], cube.getCube()[i][10]
                });
            } else {
                center = new Center(new char[]{
                        cube.getCube()[i][5], cube.getCube()[i][6],
                        cube.getCube()[i][10], cube.getCube()[i][9]
                });
            }
            centerArrayList.add(center);
        }
    }

    public char getColorOfOppositeSide(int side){
        char sideColor = getColorOfCenter(side);
        return getColorOfOppositeSide(sideColor);
    }

    public char getColorOfCenter(int side){
        if(isWholeCenterInOneColor(side)){
            return centerArrayList.get(side).getColor()[0];
        }
        //TODO exception
        return '-';
    }

    public int getSideWithTheMostFieldsWithGivenColor(char color) {
        int max = 0;
        int whichSide = 0;
        int countField;
        for (int wall = 0; wall < 6; wall++) {
            countField = countFieldWithGivenColor(wall, color);
            if (countField > max) {
                whichSide = wall;
                max = countField;
            }
        }
        return whichSide;
    }

    public int getSideWithTheMostFieldsWithGivenColorFromGivenSides(char color, int[] sides) {
        int max = 0;
        int whichSide = 0;
        int countField;
        for(int wall : sides){
       // for (int wall = 0; wall < 6; wall++) {
            countField = countFieldWithGivenColor(wall, color);
            if (countField > max) {
                whichSide = wall;
                max = countField;
            }
        }
        return whichSide;

    }

    public int inWhichSideIsGivenColorFieldsExceptUpperSide(char color) {
        if (countFieldWithGivenColor(4, color) > 0) //front side is priority
            return 4;
        for (int wall = 1; wall < 6; wall++) {
            if (countFieldWithGivenColor(wall, color) > 0) {
                return wall;
            }
        }
        return 0;
    }

    public char getMostCompleteCenterColor(){
        int firstSide = inWhichSideIsTheGreatestAmountOfCentersWithSameColor(new int[]{0,1,2,3,4,5});
        return whichColorIsMostCommonInGivenSide(firstSide);
    }

    public char whichColorIsMostCommonInGivenSide(int side) {
        char[] colors = new char[]{'w', 'y', 'o', 'r', 'g', 'b'};
        int count;
        char result = 'w';
        int max = 0;
        for (char color : colors) {
            count = countFieldWithGivenColor(side, color);
            if (count == 4) {
                return color;
            }
            if (count > max) {
                max = count;
                result = color;
            }
        }
        return result;
    }

    public boolean isWholeCenterInOneColor(int side) {
        for (int field = 1; field < 4; field++) {
            if (centerArrayList.get(side).getColor()[field] != centerArrayList.get(side).getColor()[0])
                return false;
        }
        return true;
    }

    public int inWhichSideIsTheGreatestAmountOfCentersWithSameColor(int[] searchingSides) {
        char color;
        int count;
        int max = 0;
        int sideWithMax = 0;
        for (int side : searchingSides) {
            color = whichColorIsMostCommonInGivenSide(side);
            count = countFieldWithGivenColor(side, color);
            if (count > max) {
                max = count;
                sideWithMax = side;
            }
        }
        return sideWithMax;
    }

    public int countFieldWithGivenColor(int side, char color) {
        int count = 0;
        for (int field = 0; field < 4; field++) {
            if (centerArrayList.get(side).getColor()[field] == color) {
                count++;
            }
        }
        return count;
    }

    public boolean isStripesOnGivenSides(int sourceSide, int destinationSide, char color) {
        return isStripe(sourceSide, color) && isTwoFieldsFormBlankStripe(destinationSide, color);
    }

    public boolean isStripe(int side, char color) {
        return countFieldWithGivenColor(side, color) >= 2 && isTwoFieldsFormStripe(side, color);
    }

    public boolean isTwoFieldsFormStripe(int side, int color) {
        int[][] pairs = new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}};
        for (int[] pair : pairs) {
            if (centerArrayList.get(side).getColor()[pair[1]] == color
                    && centerArrayList.get(side).getColor()[pair[0]] == color) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoFieldsFormBlankStripe(int side, int color) {
        int[][] pairs = new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}};
        for (int[] pair : pairs) {
            if (centerArrayList.get(side).getColor()[pair[1]] != color
                    && centerArrayList.get(side).getColor()[pair[0]] != color) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoFieldsFormLengthwiseStripe(int side, int color) {
        int[][] pairs = new int[][]{{0, 3}, {1, 2}};
        for (int[] pair : pairs) {
            if (centerArrayList.get(side).getColor()[pair[0]] == centerArrayList.get(side).getColor()[pair[1]]
                    && centerArrayList.get(side).getColor()[pair[0]] == color) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoFieldsFormLengthwiseBlankStripe(int side, int color) {
        int[][] pairs = new int[][]{{0, 3}, {1, 2}};
        for (int[] pair : pairs) {
            if (centerArrayList.get(side).getColor()[pair[1]] != color
                    && centerArrayList.get(side).getColor()[pair[0]] != color) {
                return true;
            }
        }
        return false;
    }

    public boolean isStripesAreInOneLine(int sideSource, int sideDestination, int color) {
        int[][] pairs = new int[][]{{0, 3}, {1, 2}};
        for (int[] pair : pairs) {
            if (centerArrayList.get(sideSource).getColor()[pair[0]] == centerArrayList.get(sideSource).getColor()[pair[1]]
                    && centerArrayList.get(sideSource).getColor()[pair[0]] == color
                    && centerArrayList.get(sideDestination).getColor()[pair[0]] != color
                    && centerArrayList.get(sideDestination).getColor()[pair[1]] != color
            ) {
                return true;
            }
        }
        return false;
    }

    public boolean isStripesAreNotInOneLine(int sideSource, int sideDestination, int color) {
        int[][] pairs = new int[][]{{0, 3}, {1, 2}};
        for (int i =0;i<2;i++){
            int[] pair = pairs[i];
            int[] secondPair = pairs[(i+1)%2];
            if (centerArrayList.get(sideSource).getColor()[pair[0]] == centerArrayList.get(sideSource).getColor()[pair[1]]
                    && centerArrayList.get(sideSource).getColor()[pair[0]] == color
                    && centerArrayList.get(sideDestination).getColor()[secondPair[0]] != color
                    && centerArrayList.get(sideDestination).getColor()[secondPair[1]] != color
            ) {
                return true;
            }
        }
        return false;
    }

    public int getNumOfFieldsOnGivenSideWithGivenColor(int side, char color) {
        for (int field = 0; field < 4; field++) {
            if (centerArrayList.get(side).getColor()[field] == color)
                return field;
        }
        return -1;
    }

    public boolean isFieldInGivenColor(int side, int field, char color) {
        return centerArrayList.get(side).getColor()[field] == color;
    }

    public void printAlgorithm(ArrayList<InspectMove> alg) {
        for (InspectMove i : alg) {
            if (i.getMoveEnum() != MoveEnum.BLANK)
                System.out.print(i + " ");
        }
        System.out.println(" ");
    }

    public ArrayList<Center> getCenterArrayList() {
        return centerArrayList;
    }

}
