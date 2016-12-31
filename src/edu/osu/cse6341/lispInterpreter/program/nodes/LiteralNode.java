package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class LiteralNode implements IParsable, IExpressionChild{
	
	private String value;

	@Override
	public void parse(){
		IToken token =  Tokenizer.getTokenizer().getNextToken();
		assertTokenIsAtom(token);
		value = token.toString();
	}

	@Override
	public String toString(){
		return value;
	}

	@Override
	public IExpressionChild newInstance(){
		return new LiteralNode();
	}
	
	private static void assertTokenIsAtom(IToken token){
		TokenKind tokenKind = token.getTokenKind();
		if(tokenKind == TokenKind.NUMERIC_TOKEN || tokenKind == TokenKind.LITERAL_TOKEN) return;
		System.out.println("Expected NUMERIC or LITERAL token, Actual Token:" + 
			tokenKind.toString() +"\tValue: " + token.toString());
	}
}