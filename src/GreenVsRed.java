import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreenVsRed {
    private int width;
    private int height;
    private Map<String, ColoredCell> cellMap;

    public GreenVsRed(int width, int height, List<String> matrix) {
        this.width = width;
        this.height = height;
        this.cellMap = new HashMap<>();
        fillCellMap(matrix);
        cellMap.values().forEach(cell -> fillNeighbours(cell));
    }

    /**
     * Fills the cell map.
     *
     * @param list A list of rows as integers either 1 or 0
     */
    public void fillCellMap(List<String> list) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String coor = String.valueOf(i) + j;
                int color = Integer.parseInt(String.valueOf(list.get(i).charAt(j)));
                cellMap.put(coor, new ColoredCell(i, j, color));
            }
        }
    }


    /**
     * Begins the simulation from a given starting point for a given number of generations.
     *
     * @param y          the y of the initial point
     * @param x          the x of the initial point
     * @param turnsToRun the number of generations
     * @return the numbers of turns that the given cell was green
     */
    public int runSimulation(int y, int x, int turnsToRun) {
        int numberOfTurns = 0;
        for (int i = 0; i < turnsToRun; i++) {
            runNextGeneration(i);
        }

        List<Integer> his = cellMap.get(String.valueOf(x) + y).getHistory();

        for (int i = 0; i < his.size(); i++) {
            if (his.get(i) == 1) {
                numberOfTurns++;
            }
        }

        return numberOfTurns;
    }

    /**
     * Runs the generation for a given integer.
     *
     * @param generation The generations to run
     */
    public void runNextGeneration(int generation) {
        cellMap.values().forEach(cell -> {

            int greens = 0;

            for (int i = 0; i < cell.getNeighbours().size(); i++) {
                ColoredCell neighbour = cell.getNeighbours().get(i);
                if (neighbour.getColor(generation) != 0) {
                    greens++;
                }
            }

            if (cell.getColor(generation) == 1) { // if green
                if (greens == 2 || greens == 3 || greens == 6) {
                    cell.appendColor(1); // stays green
                } else {
                    cell.appendColor(0); // becomes red
                }

            } else { // if red
                if (greens == 3 || greens == 6) {
                    cell.appendColor(1); // becomes green
                } else {
                    cell.appendColor(0); // stays red
                }
            }
        });
    }

    /**
     * Fills Neighbours of a colored cell using the cell map.
     *
     * @param cell The cell to fill
     */
    public void fillNeighbours(ColoredCell cell) {
        List<String> keys = new ArrayList<>();
        keys.add(String.valueOf(cell.getX() - 1) + (cell.getY())); // up
        keys.add(String.valueOf(cell.getX() + 1) + (cell.getY())); // down
        keys.add(String.valueOf(cell.getX()) + (cell.getY() - 1)); // left
        keys.add(String.valueOf(cell.getX()) + (cell.getY() + 1)); // right
        keys.add(String.valueOf(cell.getX() - 1) + (cell.getY() - 1)); // top left
        keys.add(String.valueOf(cell.getX() - 1) + (cell.getY() + 1)); //  top right
        keys.add(String.valueOf(cell.getX() + 1) + (cell.getY() - 1)); //  bottom left
        keys.add(String.valueOf(cell.getX() + 1) + (cell.getY() + 1)); //  bottom right

        keys.forEach(key -> {
            if (cellMap.containsKey(key)) {
                cell.addNeighbour(cellMap.get(key));
            }
        });
    }
}
