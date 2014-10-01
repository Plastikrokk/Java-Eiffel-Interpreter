import java.util.ArrayList;
import java.util.List;

/*
 *<compound> -> <statement> | <compound> <statement>
 */

public class Compound
{
	private Statement state;
	private Compound comp;
	
	public Compound (Statement state)
	{
		this.state = state;
	}
	
	public Compound (Statement state, Compound comp)
	{
		this.state = state;
		this.comp = comp;
	}
	
	public void execute()
	{
		if (comp == null)
			state.execute();
		else
		{
			state.execute();
			comp.execute();
		}
	}
}