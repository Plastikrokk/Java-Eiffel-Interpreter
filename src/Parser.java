/**
 * @author David Bingham
 * @professor Dr. Gayler
 * @class CS 4150
 * @assignment Java Interpreter // Project part 1
 */

import java.io.FileNotFoundException;

public class Parser
{
	private LexicalAnalyzer lex;
	
	public Parser (String fileName) throws FileNotFoundException, LexicalException
	{
		lex = new LexicalAnalyzer (fileName);
	}
	
	public Feature parse() throws ParserException, LexicalException
	{
		Token tok = lex.getNextToken();
		match (tok, TokenType.FEATURE_TOK);
		Id var = getId();
		tok = lex.getNextToken();
		match (tok, TokenType.IS_TOK);
		tok = lex.getNextToken();
		match (tok, TokenType.DO_TOK);
		Compound comp = getCompound();
		tok = lex.getNextToken();
		match (tok, TokenType.END_TOK);
		return new Feature (var, comp);
	}
	
	private Compound getCompound() throws ParserException, LexicalException
	{
		Token tok = lex.getLookaheadToken();
		if (tok.getTokType() == TokenType.ID_TOK || tok.getTokType() == TokenType.PRINT_TOK || tok.getTokType() == TokenType.IF_TOK || tok.getTokType() == TokenType.FROM_TOK)
		{
			Statement state = getStatement();
			Compound comp = getCompound();
			if (comp == null)
				return new Compound(state);
			else 
				return new Compound(state, comp);
		}
		else 
			return null;
	}
	
	private Statement getStatement() throws ParserException, LexicalException
	{
		Token tok = lex.getLookaheadToken();

		if (tok.getTokType() == TokenType.ID_TOK) 
			return getAssignStatement();
			
		else if (tok.getTokType() == TokenType.PRINT_TOK)
			return getPrintStatement();
			
		else if (tok.getTokType() == TokenType.IF_TOK)
			 return getIfStatement();
			
		else if (tok.getTokType() == TokenType.FROM_TOK)
			return getLoopStatement();
			
		else 
			throw new ParserException ("Parser: getStatement: Illegal statement type." + tok.getRowNumber() + ", " + tok.getColumnNumber());
	}
	
	private Assignment getAssignStatement() throws ParserException, LexicalException
	{
		Id var = getId();
		Token tok = lex.getNextToken();
		if (tok.getTokType() == TokenType.ASSIGN_TOK)
			return new Assignment (var, getExpression());
		else
			throw new ParserException ("Parser: getAssignStatement: Illegal assignment type.");
	}
	
	private Loop_Statement getLoopStatement() throws ParserException, LexicalException
	{
		Token tok = lex.getNextToken();
		if (tok.getTokType() == TokenType.FROM_TOK)
		{
			Assignment assign = getAssignStatement();
			tok = lex.getNextToken();
			if (tok.getTokType() == TokenType.UNTIL_TOK)
			{
				Boolean_Expression bool = getBooleanExpression();
				tok = lex.getNextToken();
				if (tok.getTokType() == TokenType.LOOP_TOK)
				{
					Compound comp = getCompound();
					tok = lex.getNextToken();
					if (tok.getTokType() == TokenType.END_TOK)
						return new Loop_Statement (assign, bool, comp);
					else 
						throw new ParserException ("end expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
				}
				else
					throw new ParserException ("loop expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
			}
			else
				throw new ParserException ("until expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
		}	
		else
			throw new ParserException ("from expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
	}
	
	private If_Statement getIfStatement() throws ParserException, LexicalException
	{
		Token tok = lex.getNextToken();
		if (tok.getTokType() == TokenType.IF_TOK)
		{
			Boolean_Expression bool = getBooleanExpression();
			tok = lex.getNextToken();
			if (tok.getTokType() == TokenType.THEN_TOK)
			{
				Compound comp1 = getCompound();
				tok = lex.getNextToken();
				if (tok.getTokType() == TokenType.ELSE_TOK)
				{
					Compound comp2 = getCompound();
					tok = lex.getNextToken();
					if (tok.getTokType() == TokenType.END_TOK) 
						return new If_Statement (bool, comp1, comp2);
					else 
						throw new ParserException ("end expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
				}
				else
					throw new ParserException ("else expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
			}
			else
				throw new ParserException ("then expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
		}
		else
			throw new ParserException ("if expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
	}
	
	private Print_Statement getPrintStatement() throws ParserException, LexicalException
	{
		Token tok = lex.getNextToken();
		Expression expr;
		if (tok.getTokType() == TokenType.PRINT_TOK)
		{
			tok = lex.getNextToken();
			if(tok.getTokType() == TokenType.LPAR_TOK)
			{	
				expr = getExpression();
				tok = lex.getNextToken();
				if (tok.getTokType() == TokenType.RPAR_TOK) {}
				else
					throw new ParserException ("')' expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
			}
			else
				throw new ParserException ("'(' expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());

			return new Print_Statement(expr);
		}	
		else
			throw new ParserException ("print expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
	}
	
	
	private Boolean_Expression getBooleanExpression() throws ParserException, LexicalException
	{
		Relational_Operator rp = getRelationalOperator();
		Expression expr1 = getExpression();
		Expression expr2 = getExpression();
		return new Boolean_Expression (rp, expr1, expr2);
	}
	
	private Relational_Operator getRelationalOperator() throws ParserException, LexicalException
	{
		Relational_Operator rp;
		Token tok = lex.getNextToken();
		if (tok.getTokType() == TokenType.LE_TOK)
			rp = Relational_Operator.le_operator;
		else if (tok.getTokType() == TokenType.LT_TOK)
			rp = Relational_Operator.lt_operator;
		else if (tok.getTokType() == TokenType.GE_TOK)
			rp = Relational_Operator.ge_operator;
		else if (tok.getTokType() == TokenType.GT_TOK)
			rp = Relational_Operator.gt_operator;
		else if (tok.getTokType() == TokenType.EQ_TOK)
			rp = Relational_Operator.eq_operator;
		else if (tok.getTokType() == TokenType.NE_TOK)
			rp = Relational_Operator.ne_operator;
		else
			throw new ParserException ("Relational Operarator expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
		return rp;
	}
	
	private ArithmeticOperator getArithmeticOperator() throws ParserException, LexicalException
	{
		ArithmeticOperator op;
		Token tok = lex.getNextToken();
		if (tok.getTokType() == TokenType.ADD_TOK)
			op = ArithmeticOperator.ADD_OP;
		else if (tok.getTokType() == TokenType.SUB_TOK)
			op = ArithmeticOperator.SUB_OP;
		else if (tok.getTokType() == TokenType.MUL_TOK)
			op = ArithmeticOperator.MUL_OP;
		else if (tok.getTokType() == TokenType.DIV_TOK)
			op = ArithmeticOperator.DIV_OP;
		else
			throw new ParserException ("Aretihmetic Operator expected at line number " + tok.getRowNumber() + " and column number " + tok.getColumnNumber());
		return op;
	}
	
	private Expression getBinaryExpression() throws ParserException, LexicalException
	{
		ArithmeticOperator op = getArithmeticOperator();
		Expression expr1 = getExpression();
		Expression expr2 = getExpression();
		return new BinaryExpression (op, expr1, expr2);
	}
	
	private Expression getExpression() throws ParserException, LexicalException
	{
		Expression expr;
		Token tok = lex.getLookaheadToken();
		if (tok.getTokType() == TokenType.ID_TOK || tok.getTokType() == TokenType.LIT_INT_TOK)
			expr = getUrnaryExpression();
		else
			expr = getBinaryExpression();
		return expr;
	}
	
	private Expression getUrnaryExpression() throws ParserException, LexicalException
	{
		Expression expr;
		Token tok = lex.getLookaheadToken();
		if (tok.getTokType() == TokenType.ID_TOK)
			expr = getId();
		else if (tok.getTokType() == TokenType.LIT_INT_TOK)
			expr = getConstant();
		else
			throw new ParserException ("Urnary expression expected at row " + tok.getRowNumber() + " and column " + tok.getColumnNumber());
		return expr;
	}
	
	private Id getId() throws LexicalException, ParserException
	{
		Token tok = lex.getNextToken();
		match (tok, TokenType.ID_TOK);
		return new Id (tok.getLexeme().charAt(0));
	}
	
	private Expression getConstant() throws ParserException, LexicalException
	{
		Token tok = lex.getNextToken();
		match (tok, TokenType.LIT_INT_TOK);
		int value = Integer.parseInt (tok.getLexeme());
		return new Constant_Expression (value);
	}
	
	private void match (Token tok, TokenType tokType) throws ParserException, LexicalException
	{
		if (tok.getTokType() != tokType)
			throw new ParserException (tokType.name() + " expected at row " + tok.getRowNumber() + " and column " + tok.getColumnNumber());
	}
}







