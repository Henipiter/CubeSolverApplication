package methods;


import cubes.Cube;

public class MethodStrategy {

    private SolvingMethod solvingMethod;

    public MethodStrategy(SolvingMethod solvingMethod){
        this.solvingMethod = solvingMethod;
    }

    public String solve(Cube cube){

        return solvingMethod.solve(cube);
    }

}
