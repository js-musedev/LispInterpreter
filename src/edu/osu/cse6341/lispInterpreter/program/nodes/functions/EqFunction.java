package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.AnyBoolType;
import edu.osu.cse6341.lispInterpreter.program.types.AnyNatType;
import edu.osu.cse6341.lispInterpreter.program.types.IType;

public class EqFunction extends BaseFunction {

	public EqFunction(){}

	private EqFunction(Node params){
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        Node right = ((ExpressionNode)params).getData();
        int leftValue = getNumericValue(params.evaluate());
        int rightValue = getNumericValue(right.evaluate());
        boolean result = leftValue == rightValue;
		return new AtomNode(result);
	}

    @Override
    public IType typeCheck() throws Exception {
        assertLengthIsAsExpected(params.getLength());
        IType paramsType = params.typeCheck();
        assertTypeIsCorrectError(1, new AnyNatType(), paramsType);
        Node right = ((ExpressionNode)params).getData();
        IType rightType = right.typeCheck();
        assertTypeIsCorrectError(2, new AnyNatType(), rightType);
        return new AnyBoolType();
    }

    @Override
	public BaseFunction newInstance(Node listNode){
		return new EqFunction(listNode);
	}

    @Override
    String getFunctionName() {
        return "EQ";
    }

    @Override
    int getExpectedLength() {
        return 3;
    }

}
