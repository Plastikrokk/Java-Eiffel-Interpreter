import java.io.FileNotFoundException;
/**
 * 
 * @author David Bingham
 * @professor Dr. Gayler
 * @class CS 4150
 * @assignment French Press (Java-Eiffel interpreter GET IT? LOLOLOLOL)
 * @ActualAssignment Java Interpreter // Project part 1
 *
 */
public class Interpreter
{

	public static void main(String[] args)
	{
		try
		{
			Parser p = new Parser("test1.e");
			Feature assn = p.parse();
			assn.execute();
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
