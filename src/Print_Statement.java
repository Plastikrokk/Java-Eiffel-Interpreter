/**
 * @author David Bingham
 * @professor Dr. Gayler
 * @class CS 4150
 * @assignment Java Interpreter // Project part 1
 */

/*
 * <print_statement> -> print (<expression>)
 */

public class Print_Statement implements Statement 
{
	private Expression PrintExpression;
	//Assign the expression given to out private expression if it is not null
	public Print_Statement (Expression e) 
	{
		//check if null
		if (e == null)
			throw new IllegalArgumentException ("Print_Statement: Expression cannot be null");
		else
			this.PrintExpression = e;
	}
	
	@Override
	public void execute() 
	{
		System.out.println(PrintExpression.evaluate());
	}
}