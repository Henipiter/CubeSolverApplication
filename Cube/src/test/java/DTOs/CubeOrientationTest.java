package DTOs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class CubeOrientationTest {
    private static final String BLIND_ORIENTATION = "src/main/resources/blind_orientation.ser";

    @Test
    void serializeAndDeserialize() throws IOException, ClassNotFoundException {
        CubeOrientation cubeOrientation = new CubeOrientation();
        cubeOrientation.setFrontColorBLD('u');
        cubeOrientation.setUpperColorBLD('y');
        cubeOrientation.serialize();
        cubeOrientation = new CubeOrientation();
        cubeOrientation.deserialize();
        Assertions.assertEquals('u', cubeOrientation.getFrontColorBLD());
        Assertions.assertEquals('y', cubeOrientation.getUpperColorBLD());
    }

    @Test
    void deserializeIfFileDoesNotExist() throws IOException, ClassNotFoundException {
        File file = new File(BLIND_ORIENTATION);
        file.delete();
        CubeOrientation cubeOrientation = new CubeOrientation();
        cubeOrientation.deserialize();
        Assertions.assertEquals('g', cubeOrientation.getFrontColorBLD());
        Assertions.assertEquals('w', cubeOrientation.getUpperColorBLD());

    }
}