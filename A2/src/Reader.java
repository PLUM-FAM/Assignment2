import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader 
{
	public static char[][] readFile(String filename)
	{
			char[][] maze;
			int width;
			int height;
			
			int k;
			if(filename.equals("5x5maze"))
			{
				k = 5;
			}
			else if(filename.equals("7x7maze"))
			{
				k = 7;
			}else if(filename.equals("8x8maze"))
			{
				k = 8;
			}else if(filename.equals("9x9maze"))
			{
				k = 9;
			}else if(filename.equals("10x10maze"))
			{
				k = 10;
			}else if(filename.equals("12x12maze"))
			{
				k = 12;
			}else if(filename.equals("13x14maze"))
			{
				k = 14;
			}else
			{
				k = 0;
				System.out.println("error reading file");
			}
			
			maze = new char[k][k];
			width = k;
			height = k;	
			
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
