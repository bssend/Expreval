package bssend.expreval.node.binaryexpr;

import bssend.expreval.exception.EvalException;
import bssend.expreval.exception.TypeResolveException;
import bssend.expreval.node.INode;
import bssend.expreval.parser.Token;
import bssend.expreval.type.Type;
import bssend.expreval.value.NumberValue;
import bssend.expreval.value.IntValue;
import bssend.expreval.value.InternalValue;
import bssend.expreval.visitor.EvalVisitor;
import bssend.expreval.visitor.TypeResolveVisitor;
import lombok.val;

import java.math.BigDecimal;

public class ModNode extends BinaryExprNode implements IBinaryExprNode {

    public ModNode(INode left, INode right, Token operatorToken) {
        super(left, right, operatorToken);
    }

    @Override
    public InternalValue eval(EvalVisitor visitor) {
        val v1 = this.getLeft().eval(visitor);
        val v2 = this.getRight().eval(visitor);

        if (Type.isInt(v1.getType()) && Type.isInt(v2.getType())) {
            return new IntValue(v1.intValue() % v2.intValue());
        }

        if (Type.isIntOrNumber(v1.getType()) && Type.isIntOrNumber(v2.getType())) {
            return new NumberValue(
                    BigDecimal.valueOf(v1.doubleValue())
                            .remainder(BigDecimal.valueOf(v2.doubleValue()))
                            .doubleValue());
        }

        throw new EvalException(
                String.format("operator is not supported type %s $s" ,
                        v1.getType().toString() ,
                        v2.getType().toString()));
    }

    @Override
    public Type resolveType(TypeResolveVisitor visitor) {
        val t1 = this.getLeft().resolveType(visitor);
        val t2 = this.getRight().resolveType(visitor);

        if (Type.isInt(t1) && Type.isInt(t2))
            return Type.INT_TYPE;

        if (Type.isIntOrNumber(t1) && Type.isIntOrNumber(t2))
            return Type.NUMBER_TYPE;

        throw new TypeResolveException(
                getOperatorToken(),
                String.format("operator is not supported type %s $s"
                        ,t1.toString() , t2.toString()));
    }
}
