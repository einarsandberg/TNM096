import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.lang.Object;
import java.util.*;
public class Search
{
	Board startBoard;
	Board endBoard;
	Board currBoard;
	int g;
	private static final int SIZE = 100;
	//private List<Board> visited;
	private HashSet<Board> visited = new HashSet<Board>();

	public PriorityQueue<Board> queue = new PriorityQueue<Board>(SIZE, new Comparator<Board>()
	{
		@Override
		public int compare(Board b1, Board b2)
		{	
			/*if (b1.getF() <= b2.getF())
			{
				return b2.getF();
			}
			return b1.getF();*/
			
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

		tilesStart.add(new Tile(8));
		tilesStart.add(new Tile(6)); 
		tilesStart.add(new Tile(7));
		tilesStart.add(new Tile(2));
		tilesStart.add(new Tile(5));
		tilesStart.add(new Tile(4));
		tilesStart.add(new Tile(3));
		tilesStart.add(new Tile(0));
		tilesStart.add(new Tile(1));

		/*tilesStart.add(new Tile(6));
		tilesStart.add(new Tile(4)); 
		tilesStart.add(new Tile(7));
		tilesStart.add(new Tile(8));
		tilesStart.add(new Tile(5));
		tilesStart.add(new Tile(0));
		tilesStart.add(new Tile(3));
		tilesStart.add(new Tile(2));
		tilesStart.add(new Tile(1));*/


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
		g = 0;

		startBoard = new Board(tilesStart);
		endBoard = new Board(tilesEnd);

		startBoard.setG(g);
		startBoard.setH(tilesOutOfPosition(startBoard));

		//visited = new ArrayList<Board>();
	}
	public boolean equal(Board b, Board c)
	{
		boolean equal = false;
		int count= 0;
		int i;
		for (i = 0; i < b.getTiles().size();i++)
		{
			if (b.getTiles().get(i).getValue() == c.getTiles().get(i).getValue())
			{
				count++;
			}
		}
		if (count == i)
		{
			return true; // all elements are equal
		}
		else
		{
			return false;
		}

		
	}
	// first heuristic
	private int tilesOutOfPosition(Board b)
	{
		int badTiles = 0;
		//List<Tile> tiles = new ArrayList<Tile>(b.getTiles());

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
		boolean visit = false;
		/*for (int i = 0; i < visited.size(); i++)
		{
			if (equal(nextState, visited.get(i)))
			{
				visit = true;
				break;
			}
		}*/
		if (nextState != null && !visited.contains(nextState))
		{
			//nextState.setG(currBoard.getG()+1);
			this.queue.add(nextState);
		}
				
	}
	public Board newBoard(Tile neighbour)
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
			int badTiles = currBoard.getH();
			if (badTiles == 0) // reached goal
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
				Board b = newBoard(neighbours.get(i));
				b.setH(tilesOutOfPosition(b));
				b.setG(currBoard.getG()+1);
				addToQueue(b);
			}
		}





		/*int badTiles = tilesOutOfPosition(b);
		List<Tile> neighbours = new ArrayList<Tile>();
		neighbours = b.getMoveableTiles(b.getTileByValue(0));*/ // get neighbours to 0 (the hole)


	}
	public static void main(String[] args)
	{
		Search search = new Search();
	}
}