
public class Driver {

	
	public static void main(String args[])
	{
		Reader r = new Reader();
		String fileName = args[0];
		int size;
		switch(fileName)
		{
		case "5x5maze.txt":
			size =  5;
			break;
		case "7x7maze.txt":
			size = 7;
			break;
		case "8x8maze.txt":
			size = 8;
			break;
		case "9x9maze.txt":
			size = 9;
			break;
		case "10x10maze.txt":
			size = 10;
			break;
		case "11x11maze.txt":
			size = 11;
			break;
		case "12x12maze.txt":
			size = 12;
			break;
		default:
			size = -1;
			System.out.println("invalid file name");
			break;
		}
		char[][] maze = Reader.readFile(fileName);
		System.out.println("loaded " + fileName);
		System.out.println("size = " + size);
		
		//first solve with our "dumb implementation"
		DumbSolver DumbResult = new DumbSolver(size, maze);
		
		
	}	
}
