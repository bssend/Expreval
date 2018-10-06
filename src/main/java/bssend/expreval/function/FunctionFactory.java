package bssend.expreval.function;

import bssend.expreval.annotation.Function;
import bssend.expreval.exception.FunctionNotFoundException;
import bssend.expreval.type.Type;
import lombok.NonNull;
import lombok.var;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FunctionFactory {

    private static Reflections REFRECTIONS
            = new Reflections("bssend.expreval.function",
                new MethodAnnotationsScanner());

    public static Method create(
            @NonNull final String name,
            @NonNull final List<Type> argTypes) {

        Set<Method> methods =
                REFRECTIONS.getMethodsAnnotatedWith(Function.class);

        var candidates = methods.stream()
                .filter(m -> m.getAnnotation(Function.class).value().equals(name))
                .collect(Collectors.toList());

        if (candidates.size() == 0)
            throw new FunctionNotFoundException(
                    "function not found (" + name + ")");

        candidates = candidates.stream()
                .filter(m -> m.getParameterCount() == argTypes.size())
                .collect(Collectors.toList());

        if (candidates.size() == 0)
            throw new FunctionNotFoundException(
                    "function parameter count not matched (" + name + ")");

        candidates = candidates.stream()
                .filter(m -> {
                    for (int i = 0; i < argTypes.size(); i++) {
                        if (!Type.isMatchJavaType(argTypes.get(i),
                                m.getParameterTypes()[i]))
                            return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());

        if (candidates.size() == 0)
            throw new FunctionNotFoundException(
                    "function parameter type not matched (" + name + ")");

        return candidates.stream()
                .findFirst().get();
    }
}
