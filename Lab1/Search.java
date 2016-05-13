import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.lang.Object;
import java.util.*;

/* Solves 8-puzzle using A* search, with either h=number of tiles in wrong place,
or h= manhattan distance*/

public class Search
{
	Board startBoard;
	Board endBoard;
	Board currBoard;
	boolean useManhattanDist = false;
	private static final int SIZE = 100;
	//private List<Board> visited;
	private HashSet<Board> visited = new HashSet<Board>();

	public PriorityQueue<Board> queue = new PriorityQueue<Board>(SIZE, new Comparator<Board>()
	{
		@Override
		public int compare(Board b1, Board b2)
		{	
			return b1.getF() - b2.getF();
		}

	});

	public Search()
	{
		init();
		run();
	}
	public void init()
	{
		List<Tile> tilesStart = new ArrayList<Tile>();
		/*tilesStart.add(new Tile(1));
		tilesStart.add(new Tile(0)); // value 0 is the empty space
		tilesStart.add(new Tile(2));
		tilesStart.add(new Tile(4));
		tilesStart.add(new Tile(5));
		tilesStart.add(new Tile(3));
		tilesStart.add(new Tile(7));
		tilesStart.add(new Tile(8));
		tilesStart.add(new Tile(6));*/

		/*tilesStart.add(new Tile(8));
		tilesStart.add(new Tile(6)); 
		tilesStart.add(new Tile(7));
		tilesStart.add(new Tile(2));
		tilesStart.add(new Tile(5));
		tilesStart.add(new Tile(4));
		tilesStart.add(new Tile(3));
		tilesStart.add(new Tile(0));
		tilesStart.add(new Tile(1));*/

		tilesStart.add(new Tile(8));
		tilesStart.add(new Tile(1)); 
		tilesStart.add(new Tile(2));
		tilesStart.add(new Tile(0));
		tilesStart.add(new Tile(4));
		tilesStart.add(new Tile(3));
		tilesStart.add(new Tile(7));
		tilesStart.add(new Tile(6));
		tilesStart.add(new Tile(5));


		List<Tile> tilesEnd = new ArrayList<Tile>();
		tilesEnd.add(new Tile(1));
		tilesEnd.add(new Tile(2));
		tilesEnd.add(new Tile(3));
		tilesEnd.add(new Tile(4));
		tilesEnd.add(new Tile(5));
		tilesEnd.add(new Tile(6));
		tilesEnd.add(new Tile(7));
		tilesEnd.add(new Tile(8));
		tilesEnd.add(new Tile(0)); // value 0 is the empty space

		startBoard = new Board(tilesStart);
		endBoard = new Board(tilesEnd);

		startBoard.setG(0);
		if (useManhattanDist)
		{
			int distance = 0;
			for (int j = 0; j < startBoard.getTiles().size(); j++)
			{
				distance+=manhattanDistance(startBoard.getTileIndex(startBoard.getTileByValue(j).getValue()), 
					endBoard.getTileIndex(startBoard.getTileByValue(j).getValue()));
			}
			startBoard.setH(distance);
			
		}
		else
		{
			startBoard.setH(tilesOutOfPosition(startBoard));
		}
		
	}
	// first heuristic
	private int tilesOutOfPosition(Board b)
	{
		int badTiles = 0;
		for (int i = 0; i < b.getTiles().size(); i++)
		{
			if (b.getTiles().get(i).getValue() != endBoard.getTiles().get(i).getValue())
			{
				badTiles++;
			}
		}
		return badTiles;
	}
	private void addToQueue(Board nextState) 
	{
		if (nextState != null && !visited.contains(nextState))
		{
			this.queue.add(nextState);
		}
				
	}
	public Board createBoard(Tile neighbour)
	{
		List<Tile> tempTiles = new ArrayList<Tile>();
		for (int i = 0; i < currBoard.getTiles().size(); i++)
		{
			tempTiles.add(currBoard.getTiles().get(i));
		}
		
		Collections.swap(tempTiles, currBoard.getTileIndex(0), 
				currBoard.getTileIndex(neighbour.getValue()));
		
		Board b = new Board(tempTiles);

		return b;
	}

	private void run()
	{
		queue.add(startBoard);
		long startTime = System.currentTimeMillis();
		while(!queue.isEmpty())
		{
			currBoard = queue.poll();
			
			visited.add(currBoard);
			if (currBoard.getH() == 0) // reached goal
			{
				System.out.println("Goal reached!");
				System.out.println("Number of moves: " + currBoard.getG());
				System.out.println("Duration (s): " + (System.currentTimeMillis() - startTime)/1000.0);
				return;
			}
			List<Tile> neighbours = new ArrayList<Tile>();
			neighbours = currBoard.getMoveableTiles(currBoard.getTileByValue(0)); // get neighbours of 0
						
			for (int i = 0; i < neighbours.size(); i++)
			{
				Board b = createBoard(neighbours.get(i));
				if (useManhattanDist)
				{
					int distance = 0;
					for (int j = 0; j < b.getTiles().size(); j++)
					{
						distance+=manhattanDistance(b.getTileIndex(b.getTileByValue(j).getValue()), endBoard.getTileIndex(b.getTileByValue(j).getValue()));
					}
					b.setH(distance);
				}
				else
				{
					b.setH(tilesOutOfPosition(b));
				}
				b.setG(currBoard.getG()+1);
				addToQueue(b);
			}
		}
		System.out.println(visited.size());

	}
	static int manhattanDistance(int a, int b) 
	{
	    return Math.abs(a / 3 - b / 3) + Math.abs(a % 3 - b % 3);
	}	
	
	public static void main(String[] args)
	{
		Search search = new Search();
	}
}