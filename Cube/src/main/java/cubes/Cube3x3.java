package cubes;

import exceptions.IncorectMoveException;

import java.util.logging.Logger;

import static java.util.Arrays.deepEquals;

public class Cube3x3 extends Cube{

    char[][] cube = new char[6][8];
    char[] center = new char[]{ 'w','y','o','r','g','b' };
    private Logger logger = Logger.getLogger("Cube3x3");


    public void move(String direction){

        System.out.println("3x3");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Cube3x3)) {
            return false;
        }
        Cube3x3 c = (Cube3x3) o;
        return deepEquals(c.cube,this.cube);
    }
}
