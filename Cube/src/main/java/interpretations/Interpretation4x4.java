package interpretations;

import DTOs.*;
import calculations.CalculateCenters4x4;
import cubes.Cube;
import cubes.Cube1x1;
import cubes.Cube4x4;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Interpretation4x4 {
    private ArrayList<Center> centerArrayList;
    private ArrayList<Edge> edgeArrayList;
    private ArrayList<Vertex> vertexArrayList;

    public Interpretation4x4() {
        centerArrayList = new ArrayList<>();
        edgeArrayList = new ArrayList<>();
        vertexArrayList = new ArrayList<>();
    }


    public void interpretCenters(Cube cube) {
        centerArrayList = new ArrayList<>();
        char[][] cubeTemp = cube.getCube();
        Center center;
        for (int i = 0; i < 6; i++) {
            if (i == 1 || i == 5) {
                center = new Center(new char[]{
                        cubeTemp[i][9], cubeTemp[i][10],
                        cubeTemp[i][6], cubeTemp[i][5]
                });
            } else if (i == 3) {
                center = new Center(new char[]{
                        cubeTemp[i][6], cubeTemp[i][5],
                        cubeTemp[i][9], cubeTemp[i][10]
                });
            } else {
                center = new Center(new char[]{
                        cubeTemp[i][5], cubeTemp[i][6],
                        cubeTemp[i][10], cubeTemp[i][9]
                });
            }
            centerArrayList.add(center);
        }
    }

    public void interpretEdges(Cube4x4 cube) {
        //TODO
    }

    public void interpretVertexes(Cube4x4 cube) {
        //TODO
    }

    public int inWhichSideIsTheMostWhiteFields(char color) {
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

    public char whichColorIsNextInOrder(int choosenSide) {
        Cube1x1 cube1x1 = new Cube1x1();
        Interpretation1x1 interpretation1x1 = new Interpretation1x1();
        interpretation1x1.refreshCube(cube1x1);
        char colorOnLeft = centerArrayList.get(2).getColor()[0];
        char colorOnUp = centerArrayList.get(0).getColor()[0];

        return interpretation1x1.whichColorIsNextInOrder(choosenSide,colorOnLeft, colorOnUp);
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
        return isStripe(sourceSide, color) && isStripe(destinationSide, color);
    }

    public boolean isStripe(int side, char color) {
        return countFieldWithGivenColor(side, color) == 2 && isTwoFieldsFormStripe(side, color);
    }

    //if fields form stripe
    public boolean isTwoFieldsFormStripe(int side, int color) {
        int[][] pairs = new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}};
        for (int[] pair : pairs) {
            if (centerArrayList.get(side).getColor()[pair[0]] == centerArrayList.get(side).getColor()[pair[1]]
                    && centerArrayList.get(side).getColor()[pair[0]] == color) {
                return true;
            }
        }
        return false;
    }

    //if stipe is lengthwise, if not - stripe is across
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

    public boolean isStripesAreInOneLine(int sideSource, int sideDestination, int color) {
        int[][] pairs = new int[][]{{0, 3}, {1, 2}};
        for (int[] pair : pairs) {
            if (centerArrayList.get(sideSource).getColor()[pair[0]] == centerArrayList.get(sideSource).getColor()[pair[1]]
                    && centerArrayList.get(sideSource).getColor()[pair[0]] == color
                    && centerArrayList.get(sideDestination).getColor()[pair[0]] == centerArrayList.get(sideDestination).getColor()[pair[1]]
                    && centerArrayList.get(sideDestination).getColor()[pair[0]] == color
            ) {
                return true;
            }
        }
        return false;
    }

    private boolean isColorInTheSamePlaceInCenterOnBothSides(int sideSource, int sideDestination, char color) {
        return getNumOfFieldsOnGivenSideWithGivenColor(sideSource, color) == getNumOfFieldsOnGivenSideWithGivenColor(sideDestination, color);
    }

    private boolean isCenterWithoutGivenColor(int side, char color) {
        for (int field = 0; field < 4; field++) {
            if (centerArrayList.get(side).getColor()[field] != color)
                return true;
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
            if (i.getMove() != MoveEnum.BLANK)
                System.out.print(i + " ");
        }
        System.out.println(" ");
    }

    public ArrayList<Center> getCenterArrayList() {
        return centerArrayList;
    }

    public ArrayList<Edge> getEdgeArrayList() {
        return edgeArrayList;
    }

    public ArrayList<Vertex> getVertexArrayList() {
        return vertexArrayList;
    }
}
