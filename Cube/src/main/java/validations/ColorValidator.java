package validations;

import DTOs.Edge;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Vertices;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ColorValidator {

    protected boolean differentSumsOfColors;
    protected boolean nonUniqueVertices;
    protected boolean nonUniqueEdges;
    protected char[] center;

    private final Interpretation3x3Vertices interpretation3x3Vertices = new Interpretation3x3Vertices();
    protected Map<String, Integer> sameColorEdgesCounter;

    protected ColorValidator(Cube3x3 cube3x3) {
        interpretation3x3Vertices.interpretVertices(cube3x3);
        sameColorEdgesCounter = new HashMap<>();
        center = cube3x3.getCenter();
    }

    protected String createKey(char[] edgeColors) {
        Arrays.sort(edgeColors);
        return String.valueOf(edgeColors);
    }

    protected boolean checkCountsOfUniqueElements(int rightElementOccurrence) {
        for (Integer counter : sameColorEdgesCounter.values()) {
            if (counter != rightElementOccurrence) {
                return false;
            }
        }
        return true;
    }

    protected void countColors(char[][] cube, int numOfFields){
        sameColorEdgesCounter = new HashMap<>();
        for (int side = 0; side < 6; side++) {
            countColors(cube[side], numOfFields);
        }
    }

    protected void countColors(char[] fields, int numOfFields){
        for (int field = 0; field < numOfFields; field++) {
            putOrReplaceValueInMap(String.valueOf(fields[field]));
        }

    }

    protected void putOrReplaceValueInMap(String key) {
        if (!sameColorEdgesCounter.containsKey(key)) {
            sameColorEdgesCounter.put(key, 1);
        } else {
            int currentValue = sameColorEdgesCounter.get(key);
            sameColorEdgesCounter.remove(key);
            sameColorEdgesCounter.put(key, currentValue + 1);
        }
    }

    protected boolean areVerticesUnique(){
        sameColorEdgesCounter = new HashMap<>();
        for (int edgeIndex = 0; edgeIndex < 8; edgeIndex++) {
            putOrReplaceValueInMap(createKey(interpretation3x3Vertices.getVertexArrayList().get(edgeIndex).getColor()));
        }
        return !checkCountsOfUniqueElements(1);
    }

    protected boolean areEdgesUnique(int numberOfEdges, ArrayList<Edge> edgeArrayList) {
        sameColorEdgesCounter = new HashMap<>();
        for (int edgeIndex = 0; edgeIndex < numberOfEdges; edgeIndex++) {
            putOrReplaceValueInMap(createKey(edgeArrayList.get(edgeIndex).getColor()));
        }
        return !checkCountsOfUniqueElements(numberOfEdges / 12);
    }
}
