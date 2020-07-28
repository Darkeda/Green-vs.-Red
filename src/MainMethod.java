import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMethod {
    public static void main(String[] args) throws IOException {

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        // Enter grid Size:
        int[] numbersInArray = readIntArrayFromCSV(sc);
        int width = numbersInArray[0];
        int height = numbersInArray[1];


        List<String> matrixInBinary = new ArrayList<>();
        //  Enter matrix
        for (int i = 0; i < height; i++) {
            matrixInBinary.add(sc.readLine());
        }


        //  Enter cell and turns
        int[] simulationParameters = readIntArrayFromCSV(sc);
        GreenVsRed GVR = new GreenVsRed(width, height, matrixInBinary);

        int y = simulationParameters[0];
        int x = simulationParameters[1];
        int turns = simulationParameters[2];

        int result = GVR.runSimulation(y, x, turns);
        System.out.println(result);
    }


    private static int[] readIntArrayFromCSV(BufferedReader sc) throws IOException {

        return Arrays.stream(sc.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();

    }
}
