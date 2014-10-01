public enum TokenType
{
	
	FEATURE_TOK, ID_TOK, IS_TOK, DO_TOK, END_TOK, ASSIGN_TOK, PRINT_TOK, LPAR_TOK, RPAR_TOK, 
		IF_TOK, THEN_TOK, ELSE_TOK, FROM_TOK, UNTIL_TOK, LOOP_TOK, 
		LIT_INT_TOK, LE_TOK, LT_TOK, GE_TOK, GT_TOK, EQ_TOK, NE_TOK, ADD_TOK, SUB_TOK, MUL_TOK, DIV_TOK,
		EOS_TOK
}

/**
* ID_TOK - letter
* LIT_INT_TOK - Literal Integer token -> digit literal_integer | digit
* ASSIGN_TOK - assignment operator -> :=
* LE_TOK - less than/equal to -> <=
* LT_TOK - less than -> <
* GE_TOK - greater than/equal to -> >=
* GT_TOK - greater than -> >
* EQ_TOK - equals -> =
* NE_TOK - not equal to -> /=
* ADD_TOK - add operator -> +
* SUB_TOK - subtraction operator -> -
* MUL_TOK - multiplication operator -> *
* DIV_TOK - division operator -> /
*/
