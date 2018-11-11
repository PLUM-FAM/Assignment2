import java.util.*;

public class SmartSolver 
{
    final private int size;
    final private Node[][] maze;

    /* TODO:
     * 1.) Need a method to calculate constraint value for each of the colors
     *  in the current maze. Lowest value (lowest number of available moves) is 
     *  most constrained. Start with color with lowest value.
     * 2.) Recalculate constraints after each color has finished. 
     */

    private Stack<Character> colorsFilled = new Stack<Character>();

    Reader reader = new Reader();
    Random rand = new Random();

    public SmartSolver(int size, String fileName)
    {
        Node[][] maze = reader.readFile(fileName, size);
        System.out.println("---------------------------------- ");
		System.out.println("Started smart solver for maze size: " + size);
        System.out.println("Initial maze: ");
        printMaze(size, maze);
		this.size = size;
		this.maze = maze;
    }

    public void populateStartNodeArray()
    {
        StartNode startB = new StartNode(reader.getStartX('B'), reader.getStartY('B'), 0);
        StartNode startA = new StartNode(reader.getStartX('A'), reader.getStartY('A'), 0);
        StartNode startW = new StartNode(reader.getStartX('W'), reader.getStartY('W'), 0);
        StartNode startR = new StartNode(reader.getStartX('R'), reader.getStartY('R'), 0);
        StartNode startP = new StartNode(reader.getStartX('P'), reader.getStartY('P'), 0);
        StartNode startD = new StartNode(reader.getStartX('D'), reader.getStartY('D'), 0);
        StartNode startO = new StartNode(reader.getStartX('O'), reader.getStartY('O'), 0);
        StartNode startG = new StartNode(reader.getStartX('G'), reader.getStartY('G'), 0);
        StartNode startK = new StartNode(reader.getStartX('K'), reader.getStartY('K'), 0);
        StartNode startY = new StartNode(reader.getStartX('Y'), reader.getStartY('Y'), 0);
        StartNode startQ = new StartNode(reader.getStartX('Q'), reader.getStartY('Q'), 0);
    }

    //helper method for printing 2D arrays.
		private void printMaze(int x, Node[][] maze) {
			System.out.println("\nMaze " + x + ": ");
			for(int i = 0; i < x; i++)
			{
				for(int j = 0; j < x; j++)
				{
					System.out.print(maze[i][j].value);
				}
				System.out.println("");
			}
			System.out.println("");
		}
}