package DTOs;

import lombok.Getter;

import java.io.IOException;

@Getter
public class CubeOrientationCache {
    public static char frontColorBLD;
    public static char upperColorBLD;
    public static char crossColorLBL;

    public static void readColorBlindSetup() {
        CubeOrientation cubeOrientation = new CubeOrientation();
        try {
            cubeOrientation.deserialize();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        frontColorBLD = cubeOrientation.getFrontColorBLD();
        upperColorBLD = cubeOrientation.getUpperColorBLD();
        crossColorLBL = cubeOrientation.getCrossColorLBL();
    }

    public static void setColorBlindSetup() {
        CubeOrientation cubeOrientation = new CubeOrientation();
        cubeOrientation.setUpperColorBLD(upperColorBLD);
        cubeOrientation.setFrontColorBLD(frontColorBLD);
        cubeOrientation.setCrossColorLBL(crossColorLBL);
        try {
            cubeOrientation.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
