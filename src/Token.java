/**
 * @author David Bingham
 * @professor Dr. Gayler
 * @class CS 4150
 * @assignment Java Interpreter // Project part 1
 */

public class Token
{
	private int rowNumber;
	
	private int columnNumber;
	
	private String lexeme;
	
	private TokenType tokType;
	
	/*
	* @param rowNumber must be POSITIVE
	* @param columnNumber must be POSITIVE
	* @param lexeme cannot be NULL or EMPTY
	* @param tokType cannot be NULL
	* @param IllegalArgumentException if ANY precondition is not satisfied
	*/
	public Token (int rowNumber, int columnNumber, String lexeme, TokenType tokType) 
	{
		if (rowNumber <= 0)
			throw new IllegalArgumentException ("Invalid row number argument!");
		if (columnNumber <= 0)
			throw new IllegalArgumentException ("Invalid column number argument!");
		if (lexeme == null || lexeme.length() == 0)
			throw new IllegalArgumentException ("Invalid lexeme argument!");
		if (tokType == null)
			throw new IllegalArgumentException ("Invalid TokenType argument!");
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
		this.lexeme = lexeme;
		this.tokType = tokType;
	}
	
	public int getRowNumber()
	{
		return rowNumber;
	}
	
	public int getColumnNumber()
	{
		return columnNumber;
	}
	
	public String getLexeme()
	{
		return lexeme;
	}
	
	public TokenType getTokType()
	{
		return tokType;
	}
}