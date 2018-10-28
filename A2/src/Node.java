import java.util.ArrayList;
public class Node 
{
	public int x;
	public int y;
	public char value;
	public ArrayList<Character> possible;
	//constructor
	public Node(int x ,int y, char value, ArrayList<Character> possible)
	{
		this.x = x;
		this.y = y;
		this.value = value;
		this.possible = possible;
	}
}
