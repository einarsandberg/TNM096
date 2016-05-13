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
}