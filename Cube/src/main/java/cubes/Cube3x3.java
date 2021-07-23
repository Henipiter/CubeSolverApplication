package cubes;

public class Cube3x3 extends Cube{

    char[][] side = new char[6][8];

    @Override
    public String message() {
        return "3x3";
    }

    public void y(){
        System.out.println("new Y");
    }

}
