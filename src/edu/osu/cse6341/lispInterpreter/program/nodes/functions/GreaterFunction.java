package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.nodes.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.nodes.functions.valueretriver.NumericValueRetriever;

public class GreaterFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final NumericValueRetriever numericValueRetriever;

	public GreaterFunction(){
	    functionLengthAsserter = new FunctionLengthAsserter();
	    numericValueRetriever = new NumericValueRetriever();
    }

    @Override
    public Node evaluateLispFunction(Node params) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            getLispFunctionName(),
            expectedParameterLength(),
            params.getLength()
        );
        Node right = ((ExpressionNode) params).getData();
        int leftValue = numericValueRetriever.retrieveNumericValue(
            params.evaluate(true),
            1,
            getLispFunctionName()
        );
        int rightValue = numericValueRetriever.retrieveNumericValue(
            right.evaluate(true),
            2,
            getLispFunctionName()
        );
        boolean result = leftValue > rightValue;
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new GreaterFunction();
    }

    @Override
    public String getLispFunctionName() {
        return "GREATER";
    }

    @Override
    public int expectedParameterLength() {
        return 3;
    }
}
