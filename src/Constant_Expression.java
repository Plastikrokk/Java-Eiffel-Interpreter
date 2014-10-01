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