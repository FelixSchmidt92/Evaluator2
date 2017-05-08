package de.uni_due.s3.evaluator.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import de.uni_due.s3.evaluator.openmath.OpenMathObject;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorLexer;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;

public class ExpressionParser {

	public static OpenMathObject parse(String expression) {
		ANTLRInputStream antlrInputStream = new ANTLRInputStream(expression);
		EvaluatorLexer evaluatorLexer = new EvaluatorLexer(antlrInputStream);
		CommonTokenStream commonTokenStream = new CommonTokenStream(evaluatorLexer);
		EvaluatorParser evaluatorParser = new EvaluatorParser(commonTokenStream);
		ParseTree tree = evaluatorParser.expression();
		ExpressionToOpenMathVisitor visitor = new ExpressionToOpenMathVisitor();
		return visitor.visit(tree);
	}
}
