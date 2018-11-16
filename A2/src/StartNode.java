/*
 * Class start node is a data structure to hold information about the beginning node for each color (i.e. the instance of each capitol letter in the maze)
 * The data the class holds is used to calculate and save the constraint value for each of the colors. This value is updated multiple times throughout the program's run.
 */

public class StartNode
{
    int x; //x coord
    int y; //y coord
    int constraint; //for determining most constrained next heuristic.
    char value;

    public StartNode(int x, int y, int constraint, char value)
    {
        this.x = x;
        this.y = y;
        this.constraint = constraint;
        this.value = value;
    }
}