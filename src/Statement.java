/**
 * @author David Bingham
 * @professor Dr. Gayler
 * @class CS 4150
 * @assignment Java Interpreter // Project part 1
 */

/*
 * <statement> -> <assignment_statement> | <print_statement> | <if_statement> | <loop_statement>
 */

public interface Statement 
{
	void execute();
}