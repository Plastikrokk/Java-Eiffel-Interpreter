/*
 * <if_statement> -> if <boolean_expression> then <compound> else <compound> end
 */

public class If_Statement implements Statement 
{
	private Boolean_Expression bool;
	
	private Compound comp1, comp2;
	
	public If_Statement () {}
	
	public If_Statement (Boolean_Expression bool, Compound comp1, Compound comp2) 
	{
		if (bool == null)
			throw new IllegalArgumentException ("If_Statement: Boolean cannot be null.");
		if (comp1 == null || comp2 == null)
			throw new IllegalArgumentException ("If_Statement: Argument compounds cannot be null.");
		else 
		{
			this.bool = bool;
			this.comp1 = comp1;
			this.comp2 = comp2;
		}
	}
	
	@Override
	public void execute() 
	{
		if (bool.execute()) 
			comp1.execute();
		else
			comp2.execute();	
	}
}