/**
 * @author David Bingham
 * @professor Dr. Gayler
 * @class CS 4150
 * @assignment Java Interpreter // Project part 1
 */

public class Constant_Expression implements Expression
{
	private int value;
	
	public Constant_Expression (int value)
	{
		this.value = value;
	}
	
	@Override
	public int evaluate()
	{
		return value;
	}
}