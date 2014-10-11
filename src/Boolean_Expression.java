/**
 * @author David Bingham
 * @professor Dr. Gayler
 * @class CS 4150
 * @assignment Java Interpreter // Project part 1
 */

/*
 * <boolean_expression> -> <relational_operator> <expression> <expression>
 */

public class Boolean_Expression
{
	private Relational_Operator op;
	
	private Expression expr1;
	
	private Expression expr2;
	
	public Boolean_Expression () {}
	
	/*
	 * Preconditon: expr1 & expr2 cannot be null
	 * @param op
	 * @param expr1
	 * @param expr2
	 * @throws IllegalArgumentException if neither expr1 or expr2 is null
	 */
	public Boolean_Expression (Relational_Operator op, Expression expr1, Expression expr2) 
	{
		if (expr1 == null || expr2 == null) 
			throw new IllegalArgumentException ("Boolean_Expression: Expressions cannot be null");
		this.op = op;
		this.expr1 = expr1; 
		this.expr2 = expr2;
	}
	
	/*
	 * @return value of the expression
	 */
	public boolean execute ()
	{
		boolean test = false;
		switch (op)
		{
			case le_operator:
				test = (expr1.evaluate() <= expr2.evaluate());
				break;
			case lt_operator:
				test = (expr1.evaluate() < expr2.evaluate());
				break;
			case ge_operator:
				test = (expr1.evaluate() >= expr2.evaluate());
				break;
			case gt_operator:
				test = (expr1.evaluate() > expr2.evaluate());
				break;
			case eq_operator:
				test = (expr1.evaluate() == expr2.evaluate());
				break;
			case ne_operator:
				test = (expr1.evaluate() != expr2.evaluate());
				break;
		}
		return test;
	}
}