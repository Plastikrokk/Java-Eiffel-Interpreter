public class Feature {
	
	private Compound comp; //<compound> -> <statement> | <compound><statement>
	
	private Id var; //id -> letter
	
	/**
	* @param comp - cannot be null
	* @param var - cannot be null
	* @throws IllegalArgumentException if either argument is null
	*
	*
	*<feature> -> feature <id> is do <compound> end
	*/
	public Feature(Id var, Compound comp)
	{
		if (var == null)
			throw new IllegalArgumentException ("null Id argument");		if (comp == null)
			throw new IllegalArgumentException ("null compound argument");
		this.comp = comp;
		this.var = var;
	}
	
	public void execute() {}
}