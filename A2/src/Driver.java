
public class Driver {

	
	public static void main(String args[])
	{
		Reader r = new Reader();
		String fileName = args[0];
	    int size = Integer.parseInt(args[1]);

		//read in file to a 2D array of nodes to represent the maze.
		Reader read = new Reader(); 
		Node[][] maze = r.readFile(fileName, size);
		System.out.println("loaded " + fileName);
		System.out.println("size = " + size);
		
		
		
		//first solve with our "dumb implementation"
		DumbSolver DumbResult = new DumbSolver(size, maze);
		
		//then solve with our "smart implementation"
		
		
	}	
}
