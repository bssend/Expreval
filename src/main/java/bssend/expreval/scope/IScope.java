package bssend.expreval.scope;

import bssend.expreval.value.*;

import java.util.Optional;

public interface IScope {
    void addVar(String name, StringValue value);
    void addVar(String name, IntegerValue value);
    void addVar(String name, NumberValue value);
    void addVar(String name, BooleanValue value);
    Optional<Value> find(String name);
}
