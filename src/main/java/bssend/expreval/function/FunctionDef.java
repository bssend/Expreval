package bssend.expreval.function;

import bssend.expreval.type.Type;
import bssend.expreval.value.Value;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class FunctionDef {

    public static class FunctionDefBuilder {

        private String name;
        private List<Type> parameterTypes;
        private Type returnType;

        public FunctionDefBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FunctionDefBuilder parameterTypes(Type... types) {
            this.parameterTypes = Arrays.asList(types);
            return this;
        }

        public FunctionDefBuilder returnType(Type type) {
            this.returnType = type;
            return this;
        }

        public FunctionDef function(Function0 func) {
            return new FunctionDef(name, parameterTypes, returnType, func);
        }

        public FunctionDef function(Function1 func) {
            return new FunctionDef(name, parameterTypes, returnType, func);
        }

        public FunctionDef function(Function2 func) {
            return new FunctionDef(name, parameterTypes, returnType, func);
        }

        public FunctionDef function(Function3 func) {
            return new FunctionDef(name, parameterTypes, returnType, func);
        }

        public FunctionDef function(Function4 func) {
            return new FunctionDef(name, parameterTypes, returnType, func);
        }

        public FunctionDef function(Function5 func) {
            return new FunctionDef(name, parameterTypes, returnType, func);
        }

        public FunctionDef function(Method staticMethod) {
            return new FunctionDef(name, parameterTypes, returnType, staticMethod);
        }

    }

    private final String name;
    private final List<Type> parameterTypes;
    private final Type returnType;
    private final Function0 function0;
    private final Function1 function1;
    private final Function2 function2;
    private final Function3 function3;
    private final Function4 function4;
    private final Function5 function5;
    private final Method staticMethod;

    public FunctionDef(String name, List<Type> parameterTypes, Type returnType, Function0 func) {
        this.name = name;
        this.parameterTypes = new ArrayList<>(parameterTypes);
        this.returnType = returnType;
        this.function0 = func;
        this.function1 = null;
        this.function2 = null;
        this.function3 = null;
        this.function4 = null;
        this.function5 = null;
        this.staticMethod = null;
    }

    public FunctionDef(String name, List<Type> parameterTypes, Type returnType, Function1 func) {
        this.name = name;
        this.parameterTypes = new ArrayList<>(parameterTypes);
        this.returnType = returnType;
        this.function0 = null;
        this.function1 = func;
        this.function2 = null;
        this.function3 = null;
        this.function4 = null;
        this.function5 = null;
        this.staticMethod = null;
    }

    public FunctionDef(String name, List<Type> parameterTypes, Type returnType, Function2 func) {
        this.name = name;
        this.parameterTypes = new ArrayList<>(parameterTypes);
        this.returnType = returnType;
        this.function0 = null;
        this.function1 = null;
        this.function2 = func;
        this.function3 = null;
        this.function4 = null;
        this.function5 = null;
        this.staticMethod = null;
    }

    public FunctionDef(String name, List<Type> parameterTypes, Type returnType, Function3 func) {
        this.name = name;
        this.parameterTypes = new ArrayList<>(parameterTypes);
        this.returnType = returnType;
        this.function0 = null;
        this.function1 = null;
        this.function2 = null;
        this.function3 = func;
        this.function4 = null;
        this.function5 = null;
        this.staticMethod = null;
    }

    public FunctionDef(String name, List<Type> parameterTypes, Type returnType, Function4 func) {
        this.name = name;
        this.parameterTypes = new ArrayList<>(parameterTypes);
        this.returnType = returnType;
        this.function0 = null;
        this.function1 = null;
        this.function2 = null;
        this.function3 = null;
        this.function4 = func;
        this.function5 = null;
        this.staticMethod = null;
    }

    public FunctionDef(String name, List<Type> parameterTypes, Type returnType, Function5 func) {
        this.name = name;
        this.parameterTypes = new ArrayList<>(parameterTypes);
        this.returnType = returnType;
        this.function0 = null;
        this.function1 = null;
        this.function2 = null;
        this.function3 = null;
        this.function4 = null;
        this.function5 = func;
        this.staticMethod = null;
    }

    public FunctionDef(String name, List<Type> parameterTypes, Type returnType, Method staticMethod) {
        this.name = name;
        this.parameterTypes = new ArrayList<>(parameterTypes);
        this.returnType = returnType;
        this.function0 = null;
        this.function1 = null;
        this.function2 = null;
        this.function3 = null;
        this.function4 = null;
        this.function5 = null;
        this.staticMethod = staticMethod;
    }

    public static FunctionDefBuilder builder() {
        return new FunctionDefBuilder();
    }

    public Value call(List<Value> args) throws Exception {

        if (!Objects.isNull(staticMethod))
            return (Value)staticMethod.invoke(null, args.toArray());

        switch (args.size()) {
            case 0:
                return function0.apply();
            case 1:
                return function1.apply(args.get(0));
            case 2:
                return function2.apply(args.get(0), args.get(1));
            case 3:
                return function3.apply(args.get(0), args.get(1), args.get(2));
            case 4:
                return function4.apply(args.get(0), args.get(1), args.get(2), args.get(3));
            case 5:
                return function5.apply(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4));
        }

        throw new RuntimeException("unreachable statement");
    }
}
