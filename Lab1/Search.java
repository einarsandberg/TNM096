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
	private List<Board> visited;

	public PriorityQueue<Board> queue = new PriorityQueue<Board>(SIZE, new Comparator<Board>()
	{
		@Override
		public int compare(Board b1, Board b2)
		{
			if (b1.getF() > b2.getF())
				return b2.getF();
			else
				return b1.getF();
			//return b1.getF() - b2.getF();
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
		tilesStart.add(new Tile(1));
		tilesStart.add(new Tile(0)); // value 0 is the empty space
		tilesStart.add(new Tile(2));
		tilesStart.add(new Tile(4));
		tilesStart.add(new Tile(5));
		tilesStart.add(new Tile(3));
		tilesStart.add(new Tile(7));
		tilesStart.add(new Tile(8));
		tilesStart.add(new Tile(6));

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
		visited = new ArrayList<Board>();
	}
	public boolean hasVisited(Board b)
	{
		boolean hasVisited = false;
		for (int i = 0; i < visited.size(); i++)
		{
			for (int j = 0; j < visited.get(i).getTiles().size(); j++)
			{
				if (b.getTiles().get(j).getValue() == visited.get(i).getTiles().get(j).getValue())
				{
					hasVisited = true;
				}
				else
				{
					hasVisited = false;
					break;
				}
			}
		}
		return hasVisited;
	}
	// first heuristic
	private int tilesOutOfPosition(Board b)
	{
		int badTiles = 0;
		List<Tile> tiles = new ArrayList<Tile>(b.getTiles());

		for (int i = 0; i < tiles.size(); i++)
		{
			if (tiles.get(i).getValue() != endBoard.getTiles().get(i).getValue())
			{
				badTiles++;
			}
		}
		return badTiles;
	}
	private void addToQueue(Board nextState) 
	{
		if (hasVisited(nextState))
		{
			System.out.println("CONTAINS");
		}
		if (nextState != null && !hasVisited(nextState))
		{
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
		
		int val = currBoard.getTileIndex(neighbour.getValue());

		Collections.swap(tempTiles, currBoard.getTileIndex(0), 
				currBoard.getTileIndex(neighbour.getValue()));
		

		Board b = new Board(tempTiles);
		/*for (int i = 0; i < b.getTiles().size(); i++)
		{
			
			b.getTileByValue(i).setPosition(b.getTiles().indexOf(currBoard.getTileByValue(i)));
		}*/

		return b;
	}

	private void run()
	{
		queue.clear();
		queue.add(startBoard);
		int j = 0;
		while(!queue.isEmpty())
		{
			j++;
			currBoard = queue.poll();
			//currBoard.print();
			System.out.println("");
			currBoard.print();
			

			int index = currBoard.getHoleIndex(); // get index of the empty tile
			visited.add(currBoard);
			int badTiles = tilesOutOfPosition(currBoard);
			if (badTiles == 0) // reached goal
			{
				System.out.println("Goal reached!");
				return;
			}
			List<Tile> neighbours = new ArrayList<Tile>();
			neighbours = currBoard.getMoveableTiles(currBoard.getTileByValue(0)); // get neighbours of 0
			g++;
			System.out.println("Possible moves: " + neighbours.size());
			for (int i = 0; i < neighbours.size(); i++)
			{
				Board b = newBoard(neighbours.get(i));
				b.setG(g);
				b.setH(badTiles);
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