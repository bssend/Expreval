package bssend.expreval.node.binaryexpr;

import bssend.expreval.exception.TypeResolveException;
import bssend.expreval.node.INode;
import bssend.expreval.node.Node;
import bssend.expreval.node.NodeType;
import bssend.expreval.compiler.Token;
import bssend.expreval.type.Type;
import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Supplier;

@Getter
public abstract class BinaryExprNode extends Node {

    protected class TypeResolveDispatcher {

        private final Type t1;
        private final Type t2;

        private Supplier<Type> stringOperator;
        private Supplier<Type> integerOperator;
        private Supplier<Type> numberOperator;
        private Supplier<Type> booleanOperator;

        public TypeResolveDispatcher(Type t1, Type t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public TypeResolveDispatcher ifString(final Supplier<Type> operator) {
            stringOperator = operator;
            return this;
        }

        public TypeResolveDispatcher ifInteger(final Supplier<Type> operator) {
            integerOperator = operator;
            return this;
        }

        public TypeResolveDispatcher ifNumber(final Supplier<Type> operator) {
            numberOperator = operator;
            return this;
        }

        public TypeResolveDispatcher ifBoolean(final Supplier<Type> operator) {
            booleanOperator = operator;
            return this;
        }

        public Type dispatch() {

            if (Type.isString(t1, t2)) {
                return Optional.ofNullable(stringOperator)
                        .map(op -> { setType(op.get()); return getType(); })
                        .orElseThrow(() -> error(t1, t2));
            }

            if (Type.isInteger(t1, t2)) {
                return Optional.ofNullable(integerOperator)
                        .map(op -> { setType(op.get()); return getType(); })
                        .orElseThrow(() -> error(t1, t2));
            }

            if (Type.isIntegerOrNumber(t1, t2)) {
                return Optional.ofNullable(numberOperator)
                        .map(op -> { setType(op.get()); return getType(); })
                        .orElseThrow(() -> error(t1, t2));
            }

            if (Type.isBoolean(t1, t2)) {
                return Optional.ofNullable(booleanOperator)
                        .map(op -> { setType(op.get()); return getType(); })
                        .orElseThrow(() -> error(t1, t2));
            }

            throw error(t1, t2);
        }

        private TypeResolveException error(Type t1, Type t2) {
            throw new TypeResolveException(
                    getToken() ,
                    String.format("Operator is not supported type %s %s",
                            t1.toString(),
                            t2.toString()));
        }
    }


    private final INode left;
    private final INode right;
    private final Token token;

    protected BinaryExprNode(
            @NonNull final INode left,
            @NonNull final INode right,
            @NonNull final Token token) {
        super(NodeType.BinaryExpr, null);
        this.left = left;
        this.right = right;
        this.token = token;
    }

}
