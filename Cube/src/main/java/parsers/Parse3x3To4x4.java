package parsers;

import cubes.Cube;
import cubes.Cube3x3;
import cubes.Cube4x4;

public class Parse3x3To4x4 {

    private Cube4x4 cube4x4;

    public Parse3x3To4x4(Cube cube4x4){
        this.cube4x4 = (Cube4x4)cube4x4;
    }

    public Cube3x3 parseTo3x3() throws Exception{
        throwExceptionIf4x4HasNotPairedEdgesOrCenters();
        Cube3x3 cube3x3 = new Cube3x3(copyFieldsColors());
        cube3x3.setCenter(copyCentersColors());
        return cube3x3;
    }

    private char[][] copyFieldsColors(){
        char[][] cubeArray = new char[6][8];
        int[] vertexOrder3x3 = new int[]{0, 2, 5, 7};
        int[] vertexOrder4x4 = new int[]{0, 3, 12, 15};
        int[] edgeOrder3x3 = new int[]{1, 3, 4, 6};
        int[] edgeOrder4x4 = new int[]{1, 4, 7, 13};
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                cubeArray[side][vertexOrder3x3[i]] = cube4x4.getCube()[side][vertexOrder4x4[i]];
                cubeArray[side][edgeOrder3x3[i]] = cube4x4.getCube()[side][edgeOrder4x4[i]];
            }
        }
        return cubeArray;
    }

    private char[] copyCentersColors(){
        char[] centerArray = new char[6];
        for (int side = 0; side < 6; side++) {
            centerArray[side] = cube4x4.getCube()[side][5];
        }
        return centerArray;
    }

    private void throwExceptionIf4x4HasNotPairedEdgesOrCenters() throws Exception {
        if(!(is4x4HasPairedEdges() && is4x4HasPairedCenters() && is4x4HasPairedCenters())){
            throw new Exception("Cannot parse 4x4 to 3x3, if edges are not paired");
        }
    }

    private boolean is4x4HasPairedEdges() {
        int[][] edgeOrder4x4 = new int[][]{{1, 2}, {4, 8}, {7, 11}, {13, 14}};
        for (int side = 0; side < 6; side++) {
            for (int[] edgeField : edgeOrder4x4) {
                if(cube4x4.getCube()[side][edgeField[0]] != cube4x4.getCube()[side][edgeField[1]]){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean is4x4HasPairedCenters() {
        int[] centerOrder4x4 = new int[]{5,6,9,10};
        for (int side = 0; side < 6; side++) {
            for( int i=1;i<4;i++){
                if(cube4x4.getCube()[side][centerOrder4x4[0]] != cube4x4.getCube()[side][centerOrder4x4[i]]){
                    return false;
                }
            }
        }
        return true;
    }

}
