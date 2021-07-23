import cubes.Cube2x2;
import cubes.Cube3x3;
import methods.BLDs.BLDFactory;
import methods.MethodStrategy;

public class Application {

    public static void main(String[] args) {

        Cube2x2 cube2x2 = new Cube2x2();
        Cube3x3 cube3x3 = new Cube3x3();



        MethodStrategy methodStrategy;
        String a;

        methodStrategy = new MethodStrategy(new BLDFactory());
        a = methodStrategy.solve(cube3x3);
        System.out.println(a);

        methodStrategy = new MethodStrategy(new BLDFactory());
        a = methodStrategy.solve(cube2x2);
        System.out.println(a);


    }

}
