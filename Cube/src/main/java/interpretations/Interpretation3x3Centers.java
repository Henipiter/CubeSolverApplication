package interpretations;

import cubes.Cube;

public class Interpretation3x3Centers {

    public static int getCenterNumberWithGivenColor(Cube cube, char color){
        for( int i=0;i<6;i++){
            if( color == cube.getCenter()[i]){
                return i;
            }
        }
        return -1;
    }

}
