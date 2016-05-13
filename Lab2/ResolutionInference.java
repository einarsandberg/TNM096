import java.util.*;

public class ResolutionInference
{
	List<String> literals1;
	List<String> literals2;

	Clause c1;
	Clause c2;

	public ResolutionInference()
	{
		literals1 = new ArrayList<String>();
		literals2 = new ArrayList<String>();

		literals1.add("A");
		literals1.add("B");
		
		literals2.add("-A");
		literals2.add("D");
		
		c1 = new Clause(literals1);
		c2 = new Clause(literals2);

		run();
	}

	private void run()
	{
		getResolvents();
	}

	private void getResolvents()
	{
		List<String> c1Literals = c1.getLiterals();
		List<String> c2Literals = c2.getLiterals();

		List<String> res = new ArrayList<String>();
		List<String> complementaryLiterals = new ArrayList<String>();

		for (int i = 0; i < c1Literals.size(); i++)
		{

			for (int j = 0; j < c2Literals.size(); j++)
			{
				if (containsComplementaryLiteral(c1Literals.get(i), c1Literals.get(j)))
				{
					complementaryLiterals.add(c1Literals.get(i));
				}
			}
		}
		for (int i = 0; i < complementaryLiterals.size(); i++)
		{
			System.out.println(complementaryLiterals.get(i));
		}

	}
	private boolean containsComplementaryLiteral(String s1, String s2)
	{
		if (s1.equals("-" + s2) || s2.equals("-" + s1))
		{
			return true;
		}
		return false;
	}


	public static void main(String[] args)
	{
		ResolutionInference resInference = new ResolutionInference();
	}
}