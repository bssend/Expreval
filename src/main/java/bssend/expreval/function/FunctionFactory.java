package bssend.expreval.function;

import bssend.expreval.annotation.FunctionName;
import bssend.expreval.type.*;
import com.google.common.collect.Streams;
import lombok.NonNull;
import lombok.var;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.*;

public class FunctionFactory {

//    private static Reflections REFRECTIONS = new Reflections(
//            "bssend.expreval.function", new MethodAnnotationsScanner());
//
    private final static Set<FunctionDef> FUNCTION_DEFS
            = new LinkedHashSet<FunctionDef>();

    static {
        FUNCTION_DEFS.addAll(
                findBuiltInFunctions("bssend.expreval.function"));
    }

    /**
     * <pre>
     * Register function.
     * </pre>
     * @param def
     */
    public static void register(final FunctionDef def) {
        FUNCTION_DEFS.add(def);
    }

    /**
     * <pre>
     * Find registered function.
     * </pre>
     * @param name
     * @param argTypes
     * @return
     */
    public static Optional<FunctionDef> find(
            @NonNull final String name,
            @NonNull final List<Type> argTypes) {

        return FUNCTION_DEFS.stream()
                .filter(def -> def.getName().equals(name))
                .filter(def -> def.getParameterTypes().size() == argTypes.size())
                .filter(def -> Streams.zip(
                        def.getParameterTypes().stream(), argTypes.stream(),
                            (t1, t2) -> Type.isMatch(t1, t2)).allMatch(b -> b))
//                .filter(def -> def.getParameterTypes().equals(argTypes))
                .findFirst()
        ;
    }

    /**
     * <pre>
     * Find built in functions.
     * </pre>
     * @return
     */
    private static List<FunctionDef> findBuiltInFunctions(final String packageName) {

        List<FunctionDef> defs = new ArrayList<>();

        Reflections reflections
                = new Reflections(packageName, new MethodAnnotationsScanner());

        Set<Method> methods =
                reflections.getMethodsAnnotatedWith(FunctionName.class);

        for (var method : methods) {
            var def = method.getAnnotation(FunctionName.class);

            try {
                // Resolve return type.
                Type returnType = Type.ofValueClass(method.getReturnType())
                        .orElseThrow(() -> new RuntimeException(""));

                // Resolve parameter type.
                List<Type> paramTypes = new ArrayList<>();
                for (var clazz : method.getParameterTypes()) {
                    paramTypes.add(Type.ofValueClass(clazz)
                            .orElseThrow(() -> new RuntimeException("")));
                }

                // Register function.
                defs.add(FunctionDef.builder()
                        .name(def.name())
                        .returnType(returnType)
                        .parameterTypes(paramTypes.toArray(new Type[]{}))
                        .function(method));

            } catch (Exception ex) {
                //TODO: logging
                continue;
            }
        }

        return defs;
    }
}
