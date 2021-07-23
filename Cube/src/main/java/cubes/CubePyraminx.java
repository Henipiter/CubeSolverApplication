package cubes;

public class CubePyraminx extends Cube{

    char[][] side = new char[4][9];

    @Override
    public String message() {
        return "Pyraminx";
    }

}