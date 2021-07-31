package cubes;

import exceptions.IncorectMoveException;

public class CubePyraminx extends Cube{

    char[][] side = new char[4][9];

    @Override
    public void moveUsingString(String direction){

        System.out.println("Pyraminx");
    }
}