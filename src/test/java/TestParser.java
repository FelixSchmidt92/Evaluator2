import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorLexer;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;

public class TestParser {
	

	@Test
	public void testParser() {
		System.out.println(ExpressionParser.parse("func(3)"));
	}

}
