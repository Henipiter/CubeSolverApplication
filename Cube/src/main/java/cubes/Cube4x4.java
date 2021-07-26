package cubes;

import exceptions.IncorectMoveException;

import java.util.logging.Logger;

public class Cube4x4 extends Cube{

    char[][] cube = new char[6][16];
    char[] center = new char[]{ 'w','y','o','r','g','b' };
    private Logger logger = Logger.getLogger("Cube4x4");


    public Cube4x4(){
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 16; j++)
                cube[i][j] = center[i];
    }

    public Cube4x4(char[][] cube){
        this.cube = cube;
    }

    @Override
    public void move(String direction){
        System.out.println("4x4");
    }
}