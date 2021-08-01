package cubes;

import exceptions.IncorectMoveException;

import java.util.logging.Logger;

import static java.util.Arrays.deepEquals;

public class CubePyraminx extends Cube{

    char[][] cube = new char[4][9];
    private Logger logger = Logger.getLogger("CubePyraminx");

    @Override
    public void moveUsingString(String direction){

        System.out.println("Pyraminx");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CubePyraminx)) {
            return false;
        }
        CubePyraminx c = (CubePyraminx) o;
        return deepEquals(c.cube,this.cube);
    }
}