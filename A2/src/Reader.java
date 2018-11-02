import java.io.FileNotFoundException;
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
	int size = 0;
	
	public Reader()
	{
		
	}
	
	public Node[][] readFile(String filename, int size)
	{
		try {			
			maze = new Node[size][size];
			width = size;
			height = size;
				
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
							if(possibleColorsForMaze.contains(maze[i][j].value) != true) //if not already added
							//add to list of possible colors for this maze
							possibleColorsForMaze.add(maze[i][j].value);
						}
					}
				}
				
				//set possible values for each node based on the colors used in the maze
				for(int i = 0; i < width; i++)
				{
					for(int j = 0; j < height; j++)
					{
						if(maze[i][j].value != '_') //sets for EVERYTHING but _ 
						{
							for(int l = 0; l < (possibleColorsForMaze.size()); l++)
							{
								maze[i][j].possible.add(possibleColorsForMaze.get(l));
							}
						}
					}
				}		
			}
			
			catch(IOException e)
			{
				System.out.println("Error reading file");
			}
			return maze;
	}	
}
	

