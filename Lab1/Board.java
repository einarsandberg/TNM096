import java.io.*;
import java.util.StringTokenizer;
import java.lang.Object;
import java.util.*;
public class Board
{
	private List<Tile> tiles;
	int left, right, up, down;
	private int h;
	private int f;
	private int g;
	public Board(List<Tile> theTiles)
	{
		tiles = new ArrayList<Tile>(theTiles);
	}

	public List<Tile> getTiles()
	{
		return tiles;
	}

	public Tile getTileByValue(int theValue)
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			if (tiles.get(i).getValue() == theValue)
			{
				return tiles.get(i);
			}
		}
		return null;
	}

	public int getTileIndex(int theValue)
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			if (tiles.get(i).getValue() == theValue)
			{
				return i;
			}
		}
		return -1;
	}

	public int getHoleIndex()
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			if (tiles.get(i).getValue() == 0)
			{
				return i;
			}
		}
		return -1;
	}

	/*public Tile getTileByPosition(int thePosition)
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			if (tiles.get(i).getPosition() == thePosition)
			{
				return tiles.get(i);
			}
		}
		return null;
	}*/
	public List<Tile> getMoveableTiles(Tile theTile)
	{
		int pos = getTileIndex(theTile.getValue());
		setDirections(pos);
		List<Tile> neighbours = new ArrayList<Tile>();

		for(int i = 0; i < tiles.size(); i++)
		{
			if (i == left || i == right ||
				i == up || i == down)
			{
				neighbours.add(tiles.get(i));
			}

		}
		return neighbours;
	}

	public void setDirections(int pos)
	{
		switch(pos)
		    {
		        case 0:
		            left = -1;
		            right = pos + 1;
		            up = -1;
		            down = pos + 3;
		            break;
		        case 1:
		            left = pos - 1;
		            right = pos + 1;
		            up = -1;
		            down = pos + 3;
		            break;
		        case 2:
		            left = pos - 1;
		            right = -1;
		            down = pos + 3;
		            up = -1;
		            break;
		        case 3:
		            left = -1;
		            right = pos + 1;
		            up = pos - 3;
		            down = pos + 3;
		            break;
		        case 4:
		            left = pos - 1;
		            right = pos + 1;
		            down = pos + 3;
		            up = pos - 3;
		            break;
		        case 5:
		            right = -1;
		            left = pos -1;
		            down = pos + 3;
		            up = pos - 3;
		            break;
		        case 6:
		            left = -1;
		            right = pos + 1;
		            up = pos - 3;
		            down = -1;
		            break;
		        case 7:
		            left = pos -1;
		            right = pos +1;
		            down = -1;
		            up = pos - 3;
		            break;
		        case 8:
		            left = pos -1;
		            right = -1;
		            down = -1;
		            up = pos -3;
		            break;
		    }

	}
	public int getF()
	{
		return (g + h);
	}
	public int getH()
	{
		return h;
	}
	public int getG()
	{
		return g;
	}
	public void setG(int val)
	{
		g = val;
	}
	public void setH(int val)
	{
		h = val;
	}


	public void print()
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			//System.out.print(tiles.get(i).getValue() + " ");

		   System.out.print(tiles.get(i).getValue() + " ");
		   //System.out.println(tiles.get(i).getValue() + " ");
		    if (i == 2   || i == 5 || i == 8)
		        System.out.println("");
		}
		System.out.println("");
	}



}