rm -r ../src/main/java/de/uni_due/s3/evaluator/parser/antlr4/
java org.antlr.v4.Tool -o ..\src\main\java\de\uni_due\s3\evaluator\parser\antlr4\ -no-listener -visitor -package de.uni_due.s3.evaluator.parser.antlr4 EvaluatorLexer.g4
java org.antlr.v4.Tool -o ..\src\main\java\de\uni_due\s3\evaluator\parser\antlr4\ -no-listener -visitor -package de.uni_due.s3.evaluator.parser.antlr4 EvaluatorParser.g4

