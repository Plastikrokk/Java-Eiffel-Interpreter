/*
 * <loop_statement> -> from <assignment_statement> until <boolean_expression> loop <compound> end
 */

public class Loop_Statement implements Statement
{
	private Assignment assign;
	private Boolean_Expression bool;
	private Compound comp;
	
	public Loop_Statement() {}
	
	public Loop_Statement (Assignment assign, Boolean_Expression bool, Compound comp) 
	{
		if (assign == null)
			throw new IllegalArgumentException ("Loop_Statement: The parameters cannot be null.");
		else 
		{
			this.assign = assign;
			this.bool = bool;
			this.comp = comp;
		}
	}
	
	@Override
	public void execute () 
	{
		assign.execute();
		while (bool.execute() != true)
			comp.execute();
	}
}