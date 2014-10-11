/**
 * @author David Bingham
 * @professor Dr. Gayler
 * @class CS 4150
 * @assignment Java Interpreter // Project part 1
 */

/**
 * <assignment_statement> -> <id> assignment_operator <expression>
 */

public class Assignment implements Statement
{

	private Expression expr;
	
	private Id var;
	
	/**
	 * @param var - cannot be null
	 * @param expr - cannot be null
	 * @throws IllegalArgumentException if either argument is null
	 */
	public Assignment(Id var, Expression expr)
	{
		if (var == null)
			throw new IllegalArgumentException ("Assignment: null Id argument");
		if (expr == null)
			throw new IllegalArgumentException ("Assignment: null expression argument");
		this.expr = expr;
		this.var = var;		
		Memory.store(var.getChar(), expr.evaluate());
	}

	public void execute()
	{
		Memory.store(var.getChar(), expr.evaluate());
	}

}
