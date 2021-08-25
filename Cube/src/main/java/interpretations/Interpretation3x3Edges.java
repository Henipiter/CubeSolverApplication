package interpretations;

import DTOs.Edge;
import cubes.Cube;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Interpretation3x3Edges {

    private final ArrayList<Edge> edgeArrayList = new ArrayList<>();

    public Interpretation3x3Edges() {
        saveEdgePositionOnWallsAndFields();
    }

    public void interpretEdges(Cube cube) {
        for (Edge edge : edgeArrayList) {
            char colorOfFirstEdge = cube.getCube()[edge.getWall()[0]][edge.getField()[0]];
            char colorOfSecondEdge = cube.getCube()[edge.getWall()[1]][edge.getField()[1]];
            edge.setColor(new char[]{colorOfFirstEdge, colorOfSecondEdge});
        }
    }

    private void saveEdgePositionOnWallsAndFields() {
        edgeArrayList.addAll(addSingleEdge(new int[]{0, 5}, new int[]{1, 1}));
        edgeArrayList.addAll(addSingleEdge(new int[]{0, 3}, new int[]{4, 1}));
        edgeArrayList.addAll(addSingleEdge(new int[]{0, 4}, new int[]{6, 1}));
        edgeArrayList.addAll(addSingleEdge(new int[]{0, 2}, new int[]{3, 1}));

        edgeArrayList.addAll(addSingleEdge(new int[]{2, 5}, new int[]{3, 3}));
        edgeArrayList.addAll(addSingleEdge(new int[]{3, 5}, new int[]{3, 4}));
        edgeArrayList.addAll(addSingleEdge(new int[]{3, 4}, new int[]{4, 4}));
        edgeArrayList.addAll(addSingleEdge(new int[]{2, 4}, new int[]{4, 3}));

        edgeArrayList.addAll(addSingleEdge(new int[]{1, 5}, new int[]{0, 6}));
        edgeArrayList.addAll(addSingleEdge(new int[]{1, 3}, new int[]{4, 6}));
        edgeArrayList.addAll(addSingleEdge(new int[]{1, 4}, new int[]{6, 6}));
        edgeArrayList.addAll(addSingleEdge(new int[]{1, 2}, new int[]{3, 6}));
    }

    private ArrayList<Edge> addSingleEdge(int[] sides, int[] fieldsFirstEdge) {
        ArrayList<Edge> edgeList = new ArrayList<>();
        Edge edge;
        edge = Edge.builder().wall(sides).field(fieldsFirstEdge).build();
        edgeList.add(edge);
        return edgeList;
    }

    public int countFieldsWithGivenColor(char color, char[] colors){
        int counter=0;
        for(char c : colors){
            if(c==color)
                counter++;
        }
        return counter;
    }

    public int getIndexFieldOfEdgeWithGivenColor(int edgeIndex, char color){
        if(edgeArrayList.get(edgeIndex).getColor()[0] == color){
            return 0;
        }
        return 1;
    }

    public char[] getColorOnCircumferenceFromGivenSide(int side){
        return getColorsFromEdgesFromSide(side, true);
    }

    public char[] getColorOnInnerSideFromGivenSide(int side){
        return getColorsFromEdgesFromSide(side, false);
    }

    public char[] getColorFromAllEdgesFromGivenSide(int side){
        String colors = String.valueOf(getColorOnCircumferenceFromGivenSide(side)) +
                String.valueOf(getColorOnInnerSideFromGivenSide(side));
        return colors.toCharArray();
    }

    private char[] getColorsFromEdgesFromSide(int side, boolean isCircumference){
        int[] fieldIndex = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        char[] colors = new char[4];
        int addToIndex=0;
        if(!isCircumference){
            addToIndex=1;
        }
        ArrayList<Edge> edges = getEdgesOnGivenSide(side);
        for (int i = 0; i < 4; i++) {
            colors[i] = edges.get(i).getColor()[(fieldIndex[i]+addToIndex)%2];
        }
        return colors;
    }

    public boolean isCollisionWithDifferentSide(int side, char crossColor){
        ArrayList<Edge> edges = getEdgesOnGivenSide(side);
        int[] circumferenceFields = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        for(int i=1;i<4;i+=2){
            if( edges.get(i).getColor()[(circumferenceFields[i]+1)%2] == crossColor){
                return true;
            }
        }
        return false;
    }

    public int getSideWhichHaveCollisionWithGivenSide(int side, char crossColor){
        ArrayList<Edge> edges = getEdgesOnGivenSide(side);
        int[] circumferenceFields = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        for(int i=1;i<4;i+=2){
            int innerFieldIndex = (circumferenceFields[i]+1)%2;
            if( edges.get(i).getColor()[innerFieldIndex] == crossColor){
                return edges.get(i).getWall()[circumferenceFields[i]];
            }
        }
        return -1;
    }

    public int getFreeSlotOnCross(char crossColor){
        int[] crossEdges = getIndexesOfEdgesOnGivenSide(1);
        for (int i = 0; i < 4; i++) {
            if( getEdgeArrayList().get(crossEdges[i]).getColor()[0] != crossColor){
                return crossEdges[i];
            }
        }
        return 0;
    }

    public boolean isSolvedCross(char crossColor){
        for (int i = 8; i <12 ; i++) {
            if(edgeArrayList.get(i).getColor()[0] != crossColor) {
                return false;
            }
        }
        return true;
    }

    public int getEdgeIndexFromSideWithGivenColorOnCircumference(int side, char color){
        int[] edgeIndexes = getIndexesOfEdgesOnGivenSide(side);
        int[] circumferenceFields = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        for (int i = 0; i < 4; i++) {
            if(edgeArrayList.get(edgeIndexes[i]).getColor()[circumferenceFields[i]]==color)
                return i;
        }
        return -1;
    }

    public int getEdgeIndexFromSideWithGivenColorOnInnerSide(int side, char color){
        int[] edgeIndexes = getIndexesOfEdgesOnGivenSide(side);
        int[] circumferenceFields = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        for (int i = 0; i < 4; i++) {
            if(edgeArrayList.get(edgeIndexes[i]).getColor()[(circumferenceFields[i]+1)%2]==color)
                return i;
        }
        return -1;
    }

    public ArrayList<Edge> getEdgesOnGivenSide(int side){
        ArrayList<Edge> edges = new ArrayList<>();
        int[] edgeIndex = getIndexesOfEdgesOnGivenSide(side);
        for(int i=0;i<4;i++){
            edges.add(edgeArrayList.get(edgeIndex[i]));
        }
        return edges;
    }

    public boolean isFieldOnCircumference(int side, int edgeIndex, int edgeFieldIndex){
        int[] edgeIndexes = getIndexesOfEdgesOnGivenSide(side);
        int[] circumferenceFields = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        int sideEdgeNumber = getSideEdgeNumber(side, edgeIndex);
        return edgeIndexes[sideEdgeNumber]==edgeIndex && circumferenceFields[sideEdgeNumber]==edgeFieldIndex;
    }

    public int getSideEdgeNumber(int side,int edgeIndex){
        int[] edgeIndexes = getIndexesOfEdgesOnGivenSide(side);
        for (int i = 0; i < 4; i++) {
            if(edgeIndexes[i] ==edgeIndex){
                return i;
            }
        }
        return -1;
    }

    public int[] getIndexesOfEdgesOnGivenSide(int side){
        switch (side){
            case 1:
                return new int[]{10,9,11,8};
            case 2:
                return new int[]{3,7,11,4};
            case 3:
                return new int[]{1,6,9,5}; // R2,R, -, R'
            case 4:
                return new int[]{2,6,10,7};
            case 5:
                return new int[]{0,4,8,5};
        }
        return null;
    }

    public int[] getIndexesOfFieldsOnEdgesOnGivenSide(int side){
        switch (side){
            case 2:
            case 3:
                return new int[]{0,1,0,1};
            case 4:
            case 5:
                return new int[]{0,0,0,0};
        }
        return null;
    }




}
