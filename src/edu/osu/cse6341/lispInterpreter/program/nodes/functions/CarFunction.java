package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.Node;
import edu.osu.cse6341.lispInterpreter.program.types.AnyNatType;
import edu.osu.cse6341.lispInterpreter.program.types.IType;
import edu.osu.cse6341.lispInterpreter.program.types.ListType;

public class CarFunction extends BaseFunction {

	public CarFunction(){}

	private CarFunction(Node params){
	    super(params);
	}

    @Override
	public Node evaluate() throws Exception{
        ExpressionNode node = getListValue(((ExpressionNode)params).getAddress().evaluate());
        return node.getAddress();
	}

    @Override
    public IType typeCheck() throws Exception {
        assertLengthIsAsExpected(params.getLength());
        IType paramsType = params.typeCheck();
        assertTypeIsCorrectError(1, new ListType(1), paramsType);
        assertListIsNotEmpty(paramsType);
        return new AnyNatType();
    }

    @Override
	public BaseFunction newInstance(Node params){
		return new CarFunction(params);
	}

    @Override
    String getFunctionName() {
        return "CAR";
    }

    @Override
    int getExpectedLength() {
        return 2;
    }

}
