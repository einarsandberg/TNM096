import java.util.*;

public class Clause
{
	List<String> literals;
	public Clause(List<String> theLiterals)
	{
		literals = new ArrayList<String>();

		for (int i = 0; i < theLiterals.size(); i++)
			literals.add(theLiterals.get(i));

	}

	public List<String> getLiterals()
	{
		return literals;
	}
	public void print()
	{
		for (int i = 0; i < literals.size(); i++)
		{
			if (i == literals.size() - 1)
				System.out.print(literals.get(i));

			else
				System.out.print(literals.get(i) + " v ");
		}
		System.out.println("");
	}
}