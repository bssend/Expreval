package bssend.expreval.scope;

import bssend.expreval.value.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Scope implements IScope {

    private final Map<String, Value> vars
            = new HashMap<String, Value>();

    @Override
    public void addVar(String name, StringValue value) {
        vars.put(name, value);
    }

    @Override
    public void addVar(String name, IntegerValue value) {
        vars.put(name, value);
    }

    @Override
    public void addVar(String name, NumberValue value) {
        vars.put(name, value);
    }

    @Override
    public void addVar(String name, BooleanValue value) {
        vars.put(name, value);
    }

    @Override
    public Optional<Value> find(String name) {
        return Optional.ofNullable(vars.get(name));
    }
}
