package expreval.node;

public enum ValueType {
    String(1) ,
    Int(2) ,
    Decimal(3) ,
    Boolean(4) ,
    Untyped(-1)
    ;

    private int priority;

    ValueType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
