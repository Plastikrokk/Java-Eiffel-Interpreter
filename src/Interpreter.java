import java.io.FileNotFoundException;

public class Interpreter
{

	public static void main(String[] args)
	{
		try
		{
			Parser p = new Parser("test1.e");
			Feature assn = p.parse();
			assn.execute();
			Memory.displayMemory();
		}
		catch (ParserException e)
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (LexicalException e)
		{
			e.printStackTrace();
		}
	}

}
