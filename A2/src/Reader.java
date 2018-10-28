import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader 
{
	public static node[][] readFile(String filename)
	{
			node[][] maze;
			int width;
			int height;
			
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
			
			maze = new node[k][k];
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
						maze[i][j] = new node(i,j, value);
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
