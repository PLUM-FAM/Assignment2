import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader 
{
	Node[][] maze;
	int width;
	int height;
	ArrayList<Character> possibleColorsForMaze = new ArrayList<Character>();
	String fileName;
	
	public Reader()
	{
		
	}
	
	public Node[][] readFile(String filename)
	{
			
			int k;
			if(filename.equals("5x5maze.txt"))
			{
				k = 5;
			}
			else if(filename.equals("7x7maze.txt"))
			{
				k = 7;
			}else if(filename.equals("8x8maze.txt"))
			{
				k = 8;
			}else if(filename.equals("9x9maze.txt"))
			{
				k = 9;
			}else if(filename.equals("10x10maze.txt"))
			{
				k = 10;
			}else if(filename.equals("12x12maze.txt"))
			{
				k = 12;
			}else if(filename.equals("14x14maze.txt"))
			{
				k = 14;
			}else
			{
				k = 0;
				System.out.println("error reading filename");
			}
			
			maze = new Node[k][k];
			width = k;
			height = k;
			
			try {
				Scanner in = new Scanner(new FileReader(filename));
				for(int i = 0; i < width; i++)	//for each col.
				{
					String line = in.next();	//for each line
		
					for(int j = 0; j < height; j++)
					{
						char value = line.charAt(j); //the next spot in the 2D array is the next character in the text file.
						ArrayList<Character> possible = new ArrayList<Character>(); //possible colors for the current node. initially empty
						maze[i][j] = new Node(i,j, value, possible);
						
						if(maze[i][j].value != '_')
						{
							//add to list of possible colors for this maze
							possibleColorsForMaze.add(maze[i][j].value);
						}
					}
				}
				
				initializePossible(maze);
				
				//set possible values for each node based on the colors used in the maze
			}
			catch(IOException e)
			{
				System.out.println("Error reading file");
			}
			return maze;
	}

	
	//set the possible values for each node in maze based on what the node is currently and whats around it
	public void initializePossible(Node[][] maze) 
	{
		for(int i = 0; i < width; i++)	//for each col.
		{
			for(int j = 0; j < height; j++)
			{
				
			}
		}
	}
	
}
