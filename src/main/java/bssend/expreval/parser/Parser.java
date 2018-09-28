package bssend.expreval.parser;

import bssend.expreval.exception.CompileException;
import bssend.expreval.exception.ParseException;
import bssend.expreval.node.INode;
import bssend.expreval.node.binaryexpr.*;
import bssend.expreval.node.literal.*;
import bssend.expreval.node.functioncall.FunctionCallNode;
import bssend.expreval.parser.util.ITokenSequence;
import bssend.expreval.parser.util.StringSequence;
import com.google.common.base.Strings;
import lombok.NonNull;
import lombok.var;

import java.util.ArrayList;
import java.util.Arrays;

import static bssend.expreval.parser.TokenType.*;

public class Parser implements IParser {

    private final IScanner scanner = new Scanner();

    private String source;

    @Override
    public INode parse(@NonNull final String s) {
        this.source = s;
        return andNode(scanner.scan(new StringSequence(s)));
    }

    /**
     * literal =
     *     string |
     *     integer |
     *     decimal |
     *     boolean
     * @param tokens
     * @return
     */
    private INode literalNode(@NonNull final ITokenSequence tokens) {

        var literalToken = tokens.next();
        switch (literalToken.getType()) {
            case STRING:
                return new StringNode(literalToken);
//                return LiteralNode.of(
//                        tokens.next().getContent());
            case INTEGER:
                return new IntNode(literalToken);
//                return LiteralNode.of(
//                        Integer.valueOf(tokens.next().getContent()));
            case NUMBER:
                return new NumberNode(literalToken);
//                return LiteralNode.of(
//                        Double.valueOf(tokens.next().getContent()));
            case BOOLEAN:
                return new BooleanNode(literalToken);
//                return LiteralNode.of(
//                        Boolean.valueOf(tokens.next().getContent()));
        }

        throw new ParseException(tokens.peek(),
                "Undefined literal token.");
    }

    /**
     * logical =
     *     compare
     *     compare "&&" compare
     *     compare "||" compare
     *
     * @param tokens
     * @return
     */
    private INode andNode(@NonNull final ITokenSequence tokens) {
        var returnNode = orNode(tokens);

        while (!tokens.isEnd()) {

            if (AMPERSAND_AMPERSAND != tokens.peek().getType())
                break;

            var operatorToken = tokens.next();
            //var operator = getBinaryOperator(tokens.next().getType());
            var rightNode = orNode(tokens);

            returnNode = new AndAlsoNode(returnNode , rightNode , operatorToken);

//            returnNode = BinaryExprNode
//                    .create(returnNode , rightNode , operator);
        }

        return returnNode;
    }
    /**
     * logical =
     *     compare
     *     compare "&&" compare
     *     compare "||" compare
     *
     * @param tokens
     * @return
     */
    private INode orNode(@NonNull final ITokenSequence tokens) {
        var returnNode = compareNode(tokens);

        while (!tokens.isEnd()) {

            if (PIPE_PIPE != tokens.peek().getType())
                break;

            var operatorToken = tokens.next();
            //var operator = getBinaryOperator(tokens.next().getType());
            var rightNode = compareNode(tokens);

            returnNode = new OrElseNode(returnNode , rightNode , operatorToken);
        }

        return returnNode;
    }

    /**
     * compare =
     *     expr
     *     expr "==" expr
     *     expr "!=" expr
     *     expr ">" expr
     *     expr "<" expr
     *     expr ">=" expr
     *     expr "<=" expr
     * @param tokens
     * @return
     */
    private INode compareNode(@NonNull final ITokenSequence tokens) {
        var returnNode = exprNode(tokens);

        while (!tokens.isEnd()) {

            if (!Arrays.asList(EQUAL_EQUAL, BANG_EQUAL, OPEN_ANGLE, OPEN_ANGLE_EQUAL, CLOSE_ANGLE, CLOSE_ANGLE_EQUAL)
                    .contains(tokens.peek().getType()))
                break;

            var opToken = tokens.next();
//            var operator = getBinaryOperator(tokens.next().getType());
            var rightNode = exprNode(tokens);

            switch (opToken.getType()) {
                case EQUAL_EQUAL:
                    returnNode = new EqNode(returnNode, rightNode, opToken);
                    break;
                case BANG_EQUAL:
                    returnNode = new NeNode(returnNode, rightNode, opToken);
                    break;
                case OPEN_ANGLE:
                    returnNode = new LtNode(returnNode, rightNode, opToken);
                    break;
                case OPEN_ANGLE_EQUAL:
                    returnNode = new LteNode(returnNode, rightNode, opToken);
                    break;
                case CLOSE_ANGLE:
                    returnNode = new GtNode(returnNode, rightNode, opToken);
                    break;
                case CLOSE_ANGLE_EQUAL:
                    returnNode = new GteNode(returnNode, rightNode, opToken);
                    break;
            }
//
//            returnNode = BinaryExprNode
//                    .create(returnNode , rightNode , operator);
        }

        return returnNode;
    }


    /**
     * expr =
     *     term
     *     term + term
     *     term - term
     * @param tokens
     * @return
     */
    private INode exprNode(@NonNull final ITokenSequence tokens) {
        var returnNode = termNode(tokens);

        while (!tokens.isEnd()) {

            if (!Arrays.asList(PLUS, MINUS)
                    .contains(tokens.peek().getType()))
                break;

            var operatorToken = tokens.next();
            //var operator = getBinaryOperator(tokens.next().getType());
            var rightNode = termNode(tokens);

            switch (operatorToken.getType()) {
                case PLUS:
                    returnNode = new AddNode(returnNode , rightNode , operatorToken);
                    break;
                case MINUS:
                    returnNode = new SubNode(returnNode , rightNode , operatorToken);
                    break;
            }
//            returnNode = BinaryExprNode
//                    .create(returnNode , rightNode , operator);
        }

        return returnNode;
    }

    /**
     * term =
     *     factor
     *     factor * factor
     *     factor / factor
     *     factor % factor
     *
     * @param tokens
     * @return
     */
    private INode termNode(@NonNull final ITokenSequence tokens) {
        var returnNode = factorNode(tokens);

        while (!tokens.isEnd()) {

            if (!Arrays.asList(STAR, SLASH, TILDE)
                    .contains(tokens.peek().getType()))
                break;

            var operatorToken = tokens.next();
//            var operator = getBinaryOperator(tokens.next().getType());
            var rightNode = factorNode(tokens);

            switch (operatorToken.getType()) {
                case STAR:
                    returnNode = new MulNode(returnNode, rightNode, operatorToken);
                    break;
                case SLASH:
                    returnNode = new DivNode(returnNode, rightNode, operatorToken);
                    break;
                case TILDE:
                    returnNode = new ModNode(returnNode, rightNode, operatorToken);
                    break;
            }
//            returnNode = BinaryExprNode
//                    .create(returnNode , rightNode , operator);
        }

        return returnNode;
    }


    /**
     * factor =
     *     literal |
     *     function_call |
     *     [OPEN_PAREN] expr [CLOSE_PAREN]
     * @param tokens
     * @return
     */
    private INode factorNode(@NonNull final ITokenSequence tokens) {

        if (tokens.isEnd())
            throw new IllegalArgumentException("Tokens must not be terminated.");

        switch (tokens.peek().getType()) {
            case IDENTIFIER:
                return functionCallNode(tokens);
            case STRING:
            case INTEGER:
            case NUMBER:
            case BOOLEAN:
                return literalNode(tokens);
            case OPEN_PAREN:
                var openParenToken = tokens.next(); // OPEN_PAREN

                var exprNode = exprNode(tokens);

                if (tokens.isEnd())
                    throw new ParseException(
                            "CLOSE_PAREN is required at the end of factor.");

                var closeParenToken = tokens.next(); // CLOSE_PAREN

                if (closeParenToken.getType() != CLOSE_PAREN)
                    throw new ParseException(closeParenToken ,
                            "CLOSE_PAREN is required at the end of factor.");

                return exprNode;
            default:
                break;
        }
        throw new IllegalArgumentException(".");

//        // function call
//        if (tokens.peek().getType() == IDENTIFIER)
//            return functionCallNode(tokens);
//
//        // literal
//        if (Arrays.asList(STRING, INTEGER, NUMBER, BOOLEAN)
//                    .contains(tokens.peek().getType()))
//            return literalNode(tokens);
//
//        // factor
//        if (tokens.next().getType() == OPEN_PAREN) {
//
//            var exprNode = exprNode(tokens);
//
//            if (tokens.isEnd() || tokens.next().getType() != CLOSE_PAREN)
//                throw new ExprCompileException("CLOSE_PAREN not found.");
//
//            return exprNode;
//        }
//
//        if (tokens.isEnd() || tokens.next().getType() != OPEN_PAREN)
//            throw new ExprCompileException("OPEN_PAREN not found.");
//
//        var exprNode = exprNode(tokens);
//
//        if (tokens.isEnd() || tokens.next().getType() != CLOSE_PAREN)
//            throw new ExprCompileException("CLOSE_PAREN not found.");

//        return exprNode;
    }

    /**
     * <pre>
     * function_call =
     *     [IDENTIFIER] [OPEN_PAREN] factor *([COMMA]factor) [CLOSE_PAREN]
     * </pre>
     * @param tokens
     * @return
     */
    private INode functionCallNode(@NonNull final ITokenSequence tokens) {

        if (tokens.isEnd() || tokens.peek().getType() != IDENTIFIER)
            throw new IllegalArgumentException("first token must be IDENTIFIER.");

        var functionName = tokens.next().getContent();

        if (tokens.isEnd() || tokens.next().getType() != OPEN_PAREN)
            throw new ParseException(
                    "OPEN_PAREN is required at the beginning of function call.");

        var arguments = new ArrayList<INode>();

        while (!tokens.isEnd() && tokens.peek().getType() != CLOSE_PAREN) {
            if (arguments.size() > 0 && tokens.next().getType() != COMMA)
                throw new ParseException(
                    "COMMA is required to delimit arguments of function call.");

            var argument = factorNode(tokens);
            arguments.add(argument);
        }

        if (tokens.isEnd() || tokens.next().getType() != CLOSE_PAREN)
            throw new ParseException(
                    "CLOSE_PAREN is required at the end of function call.");

        return FunctionCallNode.of(functionName, arguments);
    }

    private BinaryOperatorType getBinaryOperator(TokenType tokenType) {
        switch (tokenType) {
            case PLUS:
                return BinaryOperatorType.Add;
            case MINUS:
                return BinaryOperatorType.Sub;
            case STAR:
                return BinaryOperatorType.Mul;
            case SLASH:
                return BinaryOperatorType.Div;
            case TILDE:
                return BinaryOperatorType.Mod;
            case EQUAL_EQUAL:
                return BinaryOperatorType.Eq;
            case BANG_EQUAL:
                return BinaryOperatorType.Ne;
            case OPEN_ANGLE:
                return BinaryOperatorType.Lt;
            case OPEN_ANGLE_EQUAL:
                return BinaryOperatorType.Lte;
            case CLOSE_ANGLE:
                return BinaryOperatorType.Gt;
            case CLOSE_ANGLE_EQUAL:
                return BinaryOperatorType.Gte;
            case AMPERSAND_AMPERSAND:
                return BinaryOperatorType.And;
            case PIPE_PIPE:
                return BinaryOperatorType.Or;
        }
        throw new IllegalArgumentException("can't convert binary operator.");
    }}
