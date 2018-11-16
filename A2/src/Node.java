/*
 * Class Node is a data structure to hold the coords and char values for each spot in the maze. 
 */
public class Node 
{
	public int x; //x coord
	public int y; //y coord
	public char value; //character value that is in (x,y) of the maze (i.e '_' or 'G').
	//constructor
	public Node(int x ,int y, char value)
	{
		this.x = x;
		this.y = y;
		this.value = value;
	}
}
