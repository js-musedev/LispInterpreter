package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IParsable;

public interface IExpressionChild extends IParsable{

	IExpressionChild newInstance();
}