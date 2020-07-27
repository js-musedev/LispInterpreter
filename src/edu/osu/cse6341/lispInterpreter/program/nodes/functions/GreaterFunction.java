package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
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
            FunctionNameConstants.GREATER,
            expectedParameterLength(),
            params.getLength()
        );
        Node right = ((ExpressionNode) params).getData();
        int leftValue = numericValueRetriever.retrieveNumericValue(
            params.evaluate(true),
            1,
            FunctionNameConstants.GREATER
        );
        int rightValue = numericValueRetriever.retrieveNumericValue(
            right.evaluate(true),
            2,
            FunctionNameConstants.GREATER
        );
        boolean result = leftValue > rightValue;
        return new AtomNode(result);
    }

    @Override
    public LispFunction newFunctionInstance() {
        return new GreaterFunction();
    }

    @Override
    public int expectedParameterLength() {
        return 3;
    }
}
