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
		literals1.add("C");
		literals1.add("-D");
		literals1.add("A");
		
		literals2.add("-A");
		literals2.add("-B");
		literals2.add("-A");
		literals2.add("K");

		c1 = new Clause(literals1);
		c2 = new Clause(literals2);
		System.out.println("First clause: ");
		c1.print();
		System.out.println("");
		System.out.println("Second clause: ");
		c2.print();
		run();
	}

	private void run()
	{
		List<String> resolvents = new ArrayList<String>();
		Clause resClause = getResolvents();
		System.out.println("");
		System.out.println("Resolvent clause: ");
		resClause.print();

	}

	private Clause getResolvents()
	{
		List<String> c1Literals = c1.getLiterals();
		List<String> c2Literals = c2.getLiterals();

		List<String> res = new ArrayList<String>();

		for (Iterator<String> it = c1Literals.iterator(); it.hasNext();)
		{
			String literal = it.next();
			for (Iterator<String> it2 = c2Literals.iterator(); it2.hasNext();)
			{
				String literal2 = it2.next();
				if (containsComplementaryLiteral(literal, literal2))
				{
					it.remove();
					it2.remove();
					break;
				}

			}
		}

		/*for (int i = 0; i < c1Literals.size(); i++)
		{
			for (int j = 0; j < c2Literals.size(); j++)
			{
				if (containsComplementaryLiteral(c1Literals.get(i), c2Literals.get(j)))
				{
					c1Literals.remove(i);
					c2Literals.remove(j);
				}
			}
		}*/

		res.addAll(c1Literals);
		res.addAll(c2Literals);
		Clause resClause = new Clause(res);

		return resClause;

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