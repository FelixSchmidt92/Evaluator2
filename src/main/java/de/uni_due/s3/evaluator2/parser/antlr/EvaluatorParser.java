// Generated from EvaluatorParser.g4 by ANTLR 4.7
package de.uni_due.s3.evaluator2.parser.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EvaluatorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LeftParenthesis=1, RightParenthesis=2, ListOpen=3, ListClose=4, Circumflex=5, 
		Plus=6, Minus=7, BooleanNot=8, Multiplication=9, Division=10, Modulus=11, 
		LessThan=12, LessThanOrEqual=13, GreaterThan=14, GreaterThanOrEqual=15, 
		Equal=16, NotEqual=17, BooleanAnd=18, BooleanOr=19, ExerciseVariable=20, 
		FillInVariable=21, Variable=22, True=23, False=24, FunctionName=25, ArgumentSeparator=26, 
		ListArgumentSeparator=27, Integer=28, Float=29, String=30, WS=31;
	public static final int
		RULE_expression = 0, RULE_unaryOperator = 1, RULE_binaryOperatorBoolean = 2, 
		RULE_binaryOperatorRelational = 3, RULE_binaryOperatorArithLine = 4, RULE_binaryOperatorArithPoint = 5, 
		RULE_constant = 6, RULE_function = 7, RULE_list = 8;
	public static final String[] ruleNames = {
		"expression", "unaryOperator", "binaryOperatorBoolean", "binaryOperatorRelational", 
		"binaryOperatorArithLine", "binaryOperatorArithPoint", "constant", "function", 
		"list"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'{'", "'}'", "'^'", "'+'", "'-'", "'!'", "'*'", "'/'", 
		"'%'", "'<'", "'<='", "'>'", "'>='", null, "'!='", "'&&'", "'||'", null, 
		null, null, null, null, null, "','", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LeftParenthesis", "RightParenthesis", "ListOpen", "ListClose", 
		"Circumflex", "Plus", "Minus", "BooleanNot", "Multiplication", "Division", 
		"Modulus", "LessThan", "LessThanOrEqual", "GreaterThan", "GreaterThanOrEqual", 
		"Equal", "NotEqual", "BooleanAnd", "BooleanOr", "ExerciseVariable", "FillInVariable", 
		"Variable", "True", "False", "FunctionName", "ArgumentSeparator", "ListArgumentSeparator", 
		"Integer", "Float", "String", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "EvaluatorParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EvaluatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParenthesisInExpressionContext extends ExpressionContext {
		public TerminalNode LeftParenthesis() { return getToken(EvaluatorParser.LeftParenthesis, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(EvaluatorParser.RightParenthesis, 0); }
		public ParenthesisInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitParenthesisInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorArithLineInExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorArithLineContext binaryOperatorArithLine() {
			return getRuleContext(BinaryOperatorArithLineContext.class,0);
		}
		public BinaryOperatorArithLineInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorArithLineInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorRelationalInExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorRelationalContext binaryOperatorRelational() {
			return getRuleContext(BinaryOperatorRelationalContext.class,0);
		}
		public BinaryOperatorRelationalInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorRelationalInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatValueInExpressionContext extends ExpressionContext {
		public Token value;
		public TerminalNode Float() { return getToken(EvaluatorParser.Float, 0); }
		public FloatValueInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFloatValueInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryOperatorInExpressionContext extends ExpressionContext {
		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryOperatorInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitUnaryOperatorInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerValueInExpressionContext extends ExpressionContext {
		public Token value;
		public TerminalNode Integer() { return getToken(EvaluatorParser.Integer, 0); }
		public IntegerValueInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitIntegerValueInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextValueInExpressionContext extends ExpressionContext {
		public Token value;
		public TerminalNode String() { return getToken(EvaluatorParser.String, 0); }
		public TextValueInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitTextValueInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableInExpressionContext extends ExpressionContext {
		public Token name;
		public TerminalNode Variable() { return getToken(EvaluatorParser.Variable, 0); }
		public VariableInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitVariableInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstantInExpressionContext extends ExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitConstantInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorArithPointInExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorArithPointContext binaryOperatorArithPoint() {
			return getRuleContext(BinaryOperatorArithPointContext.class,0);
		}
		public BinaryOperatorArithPointInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorArithPointInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorBooleanInExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorBooleanContext binaryOperatorBoolean() {
			return getRuleContext(BinaryOperatorBooleanContext.class,0);
		}
		public BinaryOperatorBooleanInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorBooleanInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionInExpressionContext extends ExpressionContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public FunctionInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFunctionInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ListInExpressionContext extends ExpressionContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ListInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitListInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExerciseVarNameInExpressionContext extends ExpressionContext {
		public Token name;
		public TerminalNode ExerciseVariable() { return getToken(EvaluatorParser.ExerciseVariable, 0); }
		public ExerciseVarNameInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitExerciseVarNameInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FillInVarNameInExpressionContext extends ExpressionContext {
		public Token name;
		public TerminalNode FillInVariable() { return getToken(EvaluatorParser.FillInVariable, 0); }
		public FillInVarNameInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFillInVarNameInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorCircumflexInExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Circumflex() { return getToken(EvaluatorParser.Circumflex, 0); }
		public BinaryOperatorCircumflexInExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorCircumflexInExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Variable:
				{
				_localctx = new VariableInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(19);
				((VariableInExpressionContext)_localctx).name = match(Variable);
				}
				break;
			case True:
			case False:
				{
				_localctx = new ConstantInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(20);
				constant();
				}
				break;
			case FunctionName:
				{
				_localctx = new FunctionInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(21);
				function();
				}
				break;
			case LeftParenthesis:
				{
				_localctx = new ParenthesisInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				match(LeftParenthesis);
				setState(23);
				expression(0);
				setState(24);
				match(RightParenthesis);
				}
				break;
			case Plus:
			case Minus:
			case BooleanNot:
				{
				_localctx = new UnaryOperatorInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				unaryOperator();
				setState(27);
				expression(12);
				}
				break;
			case ListOpen:
				{
				_localctx = new ListInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(29);
				list();
				}
				break;
			case Integer:
				{
				_localctx = new IntegerValueInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(30);
				((IntegerValueInExpressionContext)_localctx).value = match(Integer);
				}
				break;
			case Float:
				{
				_localctx = new FloatValueInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(31);
				((FloatValueInExpressionContext)_localctx).value = match(Float);
				}
				break;
			case ExerciseVariable:
				{
				_localctx = new ExerciseVarNameInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(32);
				((ExerciseVarNameInExpressionContext)_localctx).name = match(ExerciseVariable);
				}
				break;
			case FillInVariable:
				{
				_localctx = new FillInVarNameInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(33);
				((FillInVarNameInExpressionContext)_localctx).name = match(FillInVariable);
				}
				break;
			case String:
				{
				_localctx = new TextValueInExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(34);
				((TextValueInExpressionContext)_localctx).value = match(String);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(56);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryOperatorCircumflexInExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(37);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(38);
						match(Circumflex);
						setState(39);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryOperatorArithPointInExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(40);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(41);
						binaryOperatorArithPoint();
						setState(42);
						expression(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryOperatorArithLineInExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(44);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(45);
						binaryOperatorArithLine();
						setState(46);
						expression(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryOperatorRelationalInExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(48);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(49);
						binaryOperatorRelational();
						setState(50);
						expression(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryOperatorBooleanInExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(52);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(53);
						binaryOperatorBoolean();
						setState(54);
						expression(8);
						}
						break;
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryOperatorContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode Plus() { return getToken(EvaluatorParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(EvaluatorParser.Minus, 0); }
		public TerminalNode BooleanNot() { return getToken(EvaluatorParser.BooleanNot, 0); }
		public UnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperatorContext unaryOperator() throws RecognitionException {
		UnaryOperatorContext _localctx = new UnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_unaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			((UnaryOperatorContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << BooleanNot))) != 0)) ) {
				((UnaryOperatorContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryOperatorBooleanContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode BooleanAnd() { return getToken(EvaluatorParser.BooleanAnd, 0); }
		public TerminalNode BooleanOr() { return getToken(EvaluatorParser.BooleanOr, 0); }
		public BinaryOperatorBooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperatorBoolean; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorBooleanContext binaryOperatorBoolean() throws RecognitionException {
		BinaryOperatorBooleanContext _localctx = new BinaryOperatorBooleanContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_binaryOperatorBoolean);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			((BinaryOperatorBooleanContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==BooleanAnd || _la==BooleanOr) ) {
				((BinaryOperatorBooleanContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryOperatorRelationalContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode Equal() { return getToken(EvaluatorParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(EvaluatorParser.NotEqual, 0); }
		public TerminalNode LessThan() { return getToken(EvaluatorParser.LessThan, 0); }
		public TerminalNode LessThanOrEqual() { return getToken(EvaluatorParser.LessThanOrEqual, 0); }
		public TerminalNode GreaterThan() { return getToken(EvaluatorParser.GreaterThan, 0); }
		public TerminalNode GreaterThanOrEqual() { return getToken(EvaluatorParser.GreaterThanOrEqual, 0); }
		public BinaryOperatorRelationalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperatorRelational; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorRelational(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorRelationalContext binaryOperatorRelational() throws RecognitionException {
		BinaryOperatorRelationalContext _localctx = new BinaryOperatorRelationalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_binaryOperatorRelational);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			((BinaryOperatorRelationalContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LessThan) | (1L << LessThanOrEqual) | (1L << GreaterThan) | (1L << GreaterThanOrEqual) | (1L << Equal) | (1L << NotEqual))) != 0)) ) {
				((BinaryOperatorRelationalContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryOperatorArithLineContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode Plus() { return getToken(EvaluatorParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(EvaluatorParser.Minus, 0); }
		public BinaryOperatorArithLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperatorArithLine; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorArithLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorArithLineContext binaryOperatorArithLine() throws RecognitionException {
		BinaryOperatorArithLineContext _localctx = new BinaryOperatorArithLineContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_binaryOperatorArithLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			((BinaryOperatorArithLineContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Plus || _la==Minus) ) {
				((BinaryOperatorArithLineContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryOperatorArithPointContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode Multiplication() { return getToken(EvaluatorParser.Multiplication, 0); }
		public TerminalNode Division() { return getToken(EvaluatorParser.Division, 0); }
		public TerminalNode Modulus() { return getToken(EvaluatorParser.Modulus, 0); }
		public BinaryOperatorArithPointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperatorArithPoint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitBinaryOperatorArithPoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorArithPointContext binaryOperatorArithPoint() throws RecognitionException {
		BinaryOperatorArithPointContext _localctx = new BinaryOperatorArithPointContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_binaryOperatorArithPoint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			((BinaryOperatorArithPointContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Multiplication) | (1L << Division) | (1L << Modulus))) != 0)) ) {
				((BinaryOperatorArithPointContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public Token name;
		public TerminalNode True() { return getToken(EvaluatorParser.True, 0); }
		public TerminalNode False() { return getToken(EvaluatorParser.False, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			((ConstantContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==True || _la==False) ) {
				((ConstantContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Token name;
		public ExpressionContext expression;
		public List<ExpressionContext> arguments = new ArrayList<ExpressionContext>();
		public TerminalNode LeftParenthesis() { return getToken(EvaluatorParser.LeftParenthesis, 0); }
		public TerminalNode RightParenthesis() { return getToken(EvaluatorParser.RightParenthesis, 0); }
		public TerminalNode FunctionName() { return getToken(EvaluatorParser.FunctionName, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> ArgumentSeparator() { return getTokens(EvaluatorParser.ArgumentSeparator); }
		public TerminalNode ArgumentSeparator(int i) {
			return getToken(EvaluatorParser.ArgumentSeparator, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(73);
			((FunctionContext)_localctx).name = match(FunctionName);
			setState(74);
			match(LeftParenthesis);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << ListOpen) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << Variable) | (1L << True) | (1L << False) | (1L << FunctionName) | (1L << Integer) | (1L << Float) | (1L << String))) != 0)) {
				{
				setState(75);
				((FunctionContext)_localctx).expression = expression(0);
				((FunctionContext)_localctx).arguments.add(((FunctionContext)_localctx).expression);
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ArgumentSeparator) {
					{
					{
					setState(76);
					match(ArgumentSeparator);
					setState(77);
					((FunctionContext)_localctx).expression = expression(0);
					((FunctionContext)_localctx).arguments.add(((FunctionContext)_localctx).expression);
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(85);
			match(RightParenthesis);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListContext extends ParserRuleContext {
		public ExpressionContext expression;
		public List<ExpressionContext> arguments = new ArrayList<ExpressionContext>();
		public TerminalNode ListOpen() { return getToken(EvaluatorParser.ListOpen, 0); }
		public TerminalNode ListClose() { return getToken(EvaluatorParser.ListClose, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> ListArgumentSeparator() { return getTokens(EvaluatorParser.ListArgumentSeparator); }
		public TerminalNode ListArgumentSeparator(int i) {
			return getToken(EvaluatorParser.ListArgumentSeparator, i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvaluatorParserVisitor ) return ((EvaluatorParserVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(ListOpen);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << ListOpen) | (1L << Plus) | (1L << Minus) | (1L << BooleanNot) | (1L << ExerciseVariable) | (1L << FillInVariable) | (1L << Variable) | (1L << True) | (1L << False) | (1L << FunctionName) | (1L << Integer) | (1L << Float) | (1L << String))) != 0)) {
				{
				setState(88);
				((ListContext)_localctx).expression = expression(0);
				((ListContext)_localctx).arguments.add(((ListContext)_localctx).expression);
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ListArgumentSeparator) {
					{
					{
					setState(89);
					match(ListArgumentSeparator);
					setState(90);
					((ListContext)_localctx).expression = expression(0);
					((ListContext)_localctx).arguments.add(((ListContext)_localctx).expression);
					}
					}
					setState(95);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(98);
			match(ListClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!g\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2&\n\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\7\2;\n\2\f\2\16\2>\13\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\7\tQ\n\t\f\t\16\tT\13\t\5\tV\n\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\7\n^\n\n\f\n\16\na\13\n\5\nc\n\n\3\n\3\n\3\n\2\3\2\13\2\4\6"+
		"\b\n\f\16\20\22\2\b\3\2\b\n\3\2\24\25\3\2\16\23\3\2\b\t\3\2\13\r\3\2\31"+
		"\32\2p\2%\3\2\2\2\4?\3\2\2\2\6A\3\2\2\2\bC\3\2\2\2\nE\3\2\2\2\fG\3\2\2"+
		"\2\16I\3\2\2\2\20K\3\2\2\2\22Y\3\2\2\2\24\25\b\2\1\2\25&\7\30\2\2\26&"+
		"\5\16\b\2\27&\5\20\t\2\30\31\7\3\2\2\31\32\5\2\2\2\32\33\7\4\2\2\33&\3"+
		"\2\2\2\34\35\5\4\3\2\35\36\5\2\2\16\36&\3\2\2\2\37&\5\22\n\2 &\7\36\2"+
		"\2!&\7\37\2\2\"&\7\26\2\2#&\7\27\2\2$&\7 \2\2%\24\3\2\2\2%\26\3\2\2\2"+
		"%\27\3\2\2\2%\30\3\2\2\2%\34\3\2\2\2%\37\3\2\2\2% \3\2\2\2%!\3\2\2\2%"+
		"\"\3\2\2\2%#\3\2\2\2%$\3\2\2\2&<\3\2\2\2\'(\f\r\2\2()\7\7\2\2);\5\2\2"+
		"\16*+\f\f\2\2+,\5\f\7\2,-\5\2\2\r-;\3\2\2\2./\f\13\2\2/\60\5\n\6\2\60"+
		"\61\5\2\2\f\61;\3\2\2\2\62\63\f\n\2\2\63\64\5\b\5\2\64\65\5\2\2\13\65"+
		";\3\2\2\2\66\67\f\t\2\2\678\5\6\4\289\5\2\2\n9;\3\2\2\2:\'\3\2\2\2:*\3"+
		"\2\2\2:.\3\2\2\2:\62\3\2\2\2:\66\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2"+
		"=\3\3\2\2\2><\3\2\2\2?@\t\2\2\2@\5\3\2\2\2AB\t\3\2\2B\7\3\2\2\2CD\t\4"+
		"\2\2D\t\3\2\2\2EF\t\5\2\2F\13\3\2\2\2GH\t\6\2\2H\r\3\2\2\2IJ\t\7\2\2J"+
		"\17\3\2\2\2KL\7\33\2\2LU\7\3\2\2MR\5\2\2\2NO\7\34\2\2OQ\5\2\2\2PN\3\2"+
		"\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SV\3\2\2\2TR\3\2\2\2UM\3\2\2\2UV\3\2"+
		"\2\2VW\3\2\2\2WX\7\4\2\2X\21\3\2\2\2Yb\7\5\2\2Z_\5\2\2\2[\\\7\35\2\2\\"+
		"^\5\2\2\2][\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`c\3\2\2\2a_\3\2\2\2"+
		"bZ\3\2\2\2bc\3\2\2\2cd\3\2\2\2de\7\6\2\2e\23\3\2\2\2\t%:<RU_b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}