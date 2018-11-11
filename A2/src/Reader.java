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

	int[] startB = new int[2];
	int[] startA = new int[2];
	int[] startW = new int[2];
	int[] startR = new int[2];
	int[] startP = new int[2];
	int[] startD = new int[2];
	int[] startO = new int[2];
	int[] startG = new int[2];
	int[] startK = new int[2];
	int[] startY = new int[2];
	int[] startQ = new int[2];
	
	public Reader()
	{
		
	}

	public int getStartX(char color)
	{
		switch(color)
		{
			case 'B':
				return startB[0];
			
			case 'A':
				return startA[0];
			
			case 'W':
				return startW[0];

			case 'R':
				return startR[0];
			
			case 'P':
				return startP[0];

			case 'D':
				return startD[0];

			case 'O':
				return startO[0];

			case 'G':
				return startG[0];

			case 'K':
				return startK[0];

			case 'Y':
				return startY[0];

			case 'Q':
				return startQ[0];

			default:
				return -1;
		}
	}

	public int getStartY(char color)
	{
		switch(color)
		{
			case 'B':
				return startB[1];
			
			case 'A':
				return startA[1];
			
			case 'W':
				return startW[1];

			case 'R':
				return startR[1];
			
			case 'P':
				return startP[1];

			case 'D':
				return startD[1];

			case 'O':
				return startO[1];

			case 'G':
				return startG[1];

			case 'K':
				return startK[1];

			case 'Y':
				return startY[1];

			case 'Q':
				return startQ[1];

			default:
				return -1;
		}
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
							//System.out.println(maze[i][j].value);
							//System.out.println(possibleColorsForMaze.contains(maze[i][j].value));
							if(possibleColorsForMaze.contains(maze[i][j].value) != true) //if not already added
							{
								//System.out.println("entered if");
								//add to list of possible colors for this maze
								possibleColorsForMaze.add(maze[i][j].value);
								//System.out.println(possibleColorsForMaze.size());
								switch(maze[i][j].value)
								{
									case 'B':
										startB[0] = i;
										startB[1] = j;
										break;
									
									case 'A':
										startA[0] = i;
										startA[1] = j;
										break;
									
									case 'W':
										startW[0] = i;
										startW[1] = j;
										break;

									case 'R':
										startR[0] = i;
										startR[1] = j;
										break;
									
									case 'P':
										startP[0] = i;
										startP[1] = j;
										break;

									case 'D':
										startD[0] = i;
										startD[1] = j;
										break;

									case 'O':
										startO[0] = i;
										startO[1] = j;
										break;

									case 'G':
										startG[0] = i;
										startG[1] = j;
										break;

									case 'K':
										startK[0] = i;
										startK[1] = j;
										break;

									case 'Y':
										startY[0] = i;
										startY[1] = j;
										break;

									case 'Q':
										startQ[0] = i;
										startQ[1] = j;
										break;

									default:
										break;
								}
							}

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
	

