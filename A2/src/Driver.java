
public class Driver {

	
	public static void main(String args[])
	{
		Reader r = new Reader();
		
		char[][] five = Reader.readFile("5x5maze.txt");
		char[][] seven = Reader.readFile("7x7maze.txt");
		char[][] eight = Reader.readFile("8x8maze.txt");
		char[][] nine = Reader.readFile("9x9maze.txt");
		char[][] ten = Reader.readFile("10x10maze.txt");
		char[][] twelve = Reader.readFile("12x12maze.txt");
		char[][] fourteen = Reader.readFile("14x14maze.txt");
		
		printMaze(5,five);
		printMaze(7,seven);
		
	}
	
	private static void printMaze(int x, char[][] maze) {
		System.out.println("\nMaze " + x + ": ");
		for(int i = 0; i < x-1; i++)
		{
			for(int j = 0; j < x-1; j++)
			{
				System.out.print(maze[j][i]);
			}
			System.out.println("");
		}
	}
}
