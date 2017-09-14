import java.lang.reflect.Array;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
//        String message = "Tron,TomsLaptop/10.0.0.57";
//        String[] splitMessage = message.split(",");
//        System.out.println(splitMessage[0]);
        int [][] players = new int [2][20];
        players [1][0] = 1;
        System.out.println(players[0][0]);
        System.out.println(Arrays.deepToString(players));

    }
}
