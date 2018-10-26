import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader 
{
	public char[][] readMaze(String filename)
	{
			char[][] maze;
			int width;
			int height;
			
			if(filename.equals("5x5maze"))
			{
				maze = new char[20][37];
				width = 20;
				height = 37;
			}
			else if(filename.equals("7x7maze"))
			{
				maze = new char[23][61];
				width = 23;
				height = 61;
			}else if(filename.equals("8x8maze"))
			{
				maze = new char[31][81];
				width = 31;
				height = 81;
			}else if(filename.equals("9x9maze"))
			{
				
			}else if(filename.equals("10x10maze"))
			{
				
			}else if(filename.equals("12x12maze"))
			{
				
			}else if(filename.equals("13x14maze"))
			{
				
			}else
			{
				System.out.println("error reading file");
			}
			
			try {
				Scanner in = new Scanner(new FileReader(filename));
				System.out.println("");
				for(int i = 0; i < width; i++)	//for each col.
				{
					String line = in.next();	//for each line
		
					for(int j = 0; j < height; j++)
					{
						maze[i][j] = line.charAt(j); //the next spot in the 2d array is the next character in the text file.
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
