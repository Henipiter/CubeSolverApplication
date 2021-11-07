package DTOs;

import lombok.Data;
import parsers.FileReader;

import java.io.*;

@Data
public class CubeOrientation implements Serializable {

    public char frontColorBLD;
    public char upperColorBLD;
    public char crossColorLBL;
    private static final String CUBE_ORIENTATION = "src/main/resources/blind_orientation.ser";

    public void serialize() throws IOException {
        whetherFieldsNotInitialized();
        CubeOrientation cubeOrientation = new CubeOrientation();
        cubeOrientation.setFrontColorBLD(frontColorBLD);
        cubeOrientation.setUpperColorBLD(upperColorBLD);
        cubeOrientation.setCrossColorLBL(crossColorLBL);

        FileOutputStream fileOutputStream
                = new FileOutputStream(CUBE_ORIENTATION);
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(cubeOrientation);
        objectOutputStream.flush();
        objectOutputStream.close();

    }

    public void deserialize() throws IOException, ClassNotFoundException {
        whetherFileExists();
        FileInputStream fileInputStream
                = new FileInputStream(CUBE_ORIENTATION);
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        CubeOrientation cubeOrientation = (CubeOrientation) objectInputStream.readObject();
        objectInputStream.close();
        frontColorBLD = cubeOrientation.getFrontColorBLD();
        upperColorBLD = cubeOrientation.getUpperColorBLD();
        crossColorLBL = cubeOrientation.getCrossColorLBL();
    }

    private void whetherFileExists() throws IOException {
        if(!FileReader.isFileExists(CUBE_ORIENTATION)){
            frontColorBLD = 'g';
            upperColorBLD = 'w';
            crossColorLBL = 'w';
            serialize();
        }
    }

    private void whetherFieldsNotInitialized(){
        if(frontColorBLD =='\0') {
            frontColorBLD = 'g';
        }
        if(upperColorBLD =='\0') {
            frontColorBLD = 'w';
        }
    }
}
