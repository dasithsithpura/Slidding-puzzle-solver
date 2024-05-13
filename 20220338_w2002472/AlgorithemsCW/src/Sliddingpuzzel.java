import java.util.LinkedList;
import java.util.ArrayList;

public class Sliddingpuzzel {

    private ArrayList<Point> positions = new ArrayList<>(); // Arraylist to store the positions

    /**
     * This method solves the puzzle by traversing the slidding puzzle
     * @param slidding
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public void solve(String[][] slidding, int startX, int startY, int endX, int endY) {

        Point position = new Point(startX, startY);
        LinkedList<Point> node = new LinkedList<>();
        Point[][] CaveLengths = new Point[slidding.length][slidding[0].length];

        node.addLast(position);
        CaveLengths[startY][startX] = position;

        boolean validator = true;

        while (node.size() != 0 && validator) {

            Point currPos = node.pollFirst();

            for (Direction dir : Direction.values()) {

                Point nextPos = move(slidding, CaveLengths, currPos, dir, endX, endY);

                if (nextPos != null) {

                    node.addLast(nextPos);
                    CaveLengths[nextPos.getY()][nextPos.getX()] = new Point(currPos.getX(), currPos.getY());

                    if (nextPos.getY() == endY && nextPos.getX() == endX) {

                        Point tmp = nextPos;

                        while (tmp != position) {
                            tmp = CaveLengths[tmp.getY()][tmp.getX()];
                            positions.add(0, tmp);
                        }
                        validator = false;
                    }
                }
            }
        }
        System.out.println("\n----------Well Come to Slidding Puzzle----------\n");
        System.out.println("The shorted path found: ");

        positions.add(new Point(endX, endY));

        for(int i = 0; i<positions.size(); i++) {

            if (i == 0) {

                System.out.println(i+1+"."+" Started At " + positions.get(i));
            } else {
                if (positions.get(i).getX() > positions.get(i - 1).getX()) {
                    System.out.println(i+1+"."+" Move RIGHT to " + positions.get(i));
                } else if (positions.get(i).getX() < positions.get(i - 1).getX()) {
                    System.out.println(i+1+"."+" Move LEFT  to " + positions.get(i));
                } else if (positions.get(i).getY() > positions.get(i - 1).getY()) {
                    System.out.println(i+1+"."+" Move DOWN  to " + positions.get(i));
                } else if (positions.get(i).getY() < positions.get(i - 1).getY()) {
                    System.out.println(i+1+"."+" Move UP    to " + positions.get(i));
                }
            }
        }
        System.out.println(positions.size()+1+"."+" Done!");
    }

    /**
     * This method moves the point in the icecave
     * @param iceCave
     * @param CaveLengths
     * @param currPos
     * @param dir
     * @param end_A
     * @param end_B
     * @return
     */
    public Point move (String[][]iceCave, Point[][]CaveLengths, Point currPos, Direction dir, int end_A, int end_B) {

        int x = currPos.getX();
        int y = currPos.getY();

        int diffX;
        int diffY;


        if (dir == Direction.LEFT) {
            diffX = -1;
        }
        else if (dir == Direction.RIGHT) {
            diffX = 1;
        }
        else {
            diffX = 0;
        }


        if (dir == Direction.UP) {
            diffY = -1;
        }
        else if (dir == Direction.DOWN) {
            diffY = 1;
        }
        else {
            diffY = 0;
        }


        int i = 1;
        while (x + i * diffX >= 0
                && x + i * diffX < iceCave[0].length
                && y + i * diffY >= 0
                && y + i * diffY < iceCave.length
                && !(iceCave[y + i * diffY][x + i * diffX]).equals("0")) {
            i++;

            if(iceCave[y + (i-1) * diffY][x + (i-1) * diffX].equals("F")) {
                return new Point(end_A,end_B);
            }
        }
        i--;

        if (CaveLengths[y + i * diffY][x + i * diffX] != null) {
            return null;
        }
        return new Point(x + i * diffX, y + i * diffY);
    }

    /**
     * This method returns the positions
     */
    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
}