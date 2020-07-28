import java.util.ArrayList;
import java.util.List;

public class ColoredCell {
    private int x;
    private int y;
    private List<Integer> history;
    private List<ColoredCell> neighbours;

    public ColoredCell(int x, int y, int color) {
        this.x = x;
        this.y = y;
        history = new ArrayList<>();
        history.add(color);
        neighbours = new ArrayList<>();
    }

    public int getColor(int generation) {
        return history.get(generation);
    }

    public void appendColor(int color) {
        history.add(color);
    }

    public List<Integer> getHistory() {
        return history;
    }

    public List<ColoredCell> getNeighbours() {
        return neighbours;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addNeighbour(ColoredCell neighbour) {
        this.neighbours.add(neighbour);
    }


}
