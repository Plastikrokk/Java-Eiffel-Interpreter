/**
 * @author David Bingham
 * @professor Dr. Gayler
 * @class CS 4150
 * @assignment Java Interpreter // Project part 1
 */

public class Id implements Expression
{

	private char ch;
	
	/**
	 * @param ch - must be a valid identifier
	 * @throws IllegalArgumentException if ch is not a valid identifier
	 */
	public Id(char ch)
	{
		this.ch = Character.toLowerCase(ch);
	}

	@Override
	public int evaluate()
	{
		return Memory.fetch (ch);
	}
	
	public char getChar ()
	{
		return ch;
	}
	
	public void setValue (int value)
	{
		Memory.store(ch, value);
	}

}
