package bssend.expreval;

import bssend.expreval.exception.TypeResolveException;
import bssend.expreval.exception.VariableNotDefinedException;
import lombok.var;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionTest {

    @Test
    public void Add_String_String() throws Exception {
        var answer = Expression.builder()
                .compile("'foo' + 'bar'")
                .eval();
        assertEquals(answer.value(), "foobar");
    }

    @Test
    public void And_Int_Int() throws Exception {
        var answer = Expression.builder()
                .compile("1 + 2")
                .eval();
        assertEquals(answer.value(), 3);
    }

    @Test
    public void And_Number_Number() throws Exception {
        var answer = Expression.builder()
                .compile("1.21 + 2.13")
                .eval();
        assertEquals(answer.value(), 3.34);
    }

    @Test
    public void And_Boolean_Boolean() throws Exception {
        assertThrows(TypeResolveException.class, () -> {
            Expression.builder()
                    .compile("true + false");
        });
    }

    @Test
    public void And_Int_Number() throws Exception {
        var answer = Expression.builder()
                .compile("1 + 2.13")
                .eval();
        assertEquals(answer.value(), 3.13);
    }

    @Test
    public void And_Number_Int() throws Exception {
        var answer = Expression.builder()
                .compile("1.29 + 3")
                .eval();
        assertEquals(answer.value(), 4.29);
    }

    @Nested
    class SubOperator {
        @Test
        public void Sub_String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                var answer = Expression.builder()
                        .compile("'1' - '2'");
            });
        }

        @Test
        public void Sub_Int_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("1 - 2")
                    .eval();
            assertEquals(answer.value(), -1);
        }

        @Test
        public void Sub_Int_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("1 - 2.19")
                    .eval();
            assertEquals(answer.value(), -1.19);
        }

        @Test
        public void Sub_Number_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("5.11 - 0.01")
                    .eval();
            assertEquals(answer.value(), 5.1);
        }

        @Test
        public void Sub_Number_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("5.11 - 4")
                    .eval();
            assertEquals(answer.value(), 1.11);
        }

        @Test
        public void Sub_Boolean_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                var answer = Expression.builder()
                        .compile("true - false");
            });
        }
    }

    @Nested
    class MulOperator {
        @Test
        public void Mul_Number_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("2.12 * 3.35")
                    .eval();
            assertEquals(answer.value(), 7.102);
        }

        @Test
        public void Mul_Int_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("2 * 3")
                    .eval();
            assertEquals(answer.value(), 6);
        }

        @Test
        public void Mul_Number_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("2.11 * 3")
                    .eval();
            assertEquals(answer.value(), 6.33);
        }

        @Test
        public void Mul_Int_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("3 * 2.11")
                    .eval();
            assertEquals(answer.value(), 6.33);
        }

        @Test
        public void Mul_String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                var answer = Expression.builder()
                        .compile("'1' * '2'");
            });
        }

        @Test
        public void Mul_Boolean_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                var answer = Expression.builder()
                        .compile("false * false");
            });
        }
    }

    @Nested
    class DivOperator {
        @Test
        public void Div_Int_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("4 / 3")
                    .eval();
            assertEquals(answer.value(), 1);
        }

        @Test
        public void Div_Number_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("4.44 / 2.22")
                    .eval();
            assertEquals(answer.value(), 2.0);
        }

        @Test
        public void Div_String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("'1' / '2'");
            });
        }

        @Test
        public void Div_Boolean_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("false / false");
            });
        }
    }

    @Nested
    class ModOperator {
        @Test
        public void Int_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("4 % 3")
                    .eval();
            assertEquals(answer.value(), 1);
        }

        @Test
        public void Number_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("4.44 % 2.22")
                    .eval();
            assertEquals(answer.value(), 0.0);
        }

        @Test
        public void Number_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("4.45 % 2")
                    .eval();
            assertEquals(answer.value(), 0.45);
        }

        @Test
        public void String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("'1' % '2'");
            });
        }

        @Test
        public void Boolean_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("false % false");
            });
        }
    }

    @Nested
    class EqOperator {
        @Test
        public void String_String() throws Exception {
            var answer = Expression.builder()
                    .compile("'1' == '2'")
                    .eval();
            assertEquals(answer.value(), false);
        }

        @Test
        public void Int_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("3 == 3")
                    .eval();
            assertEquals(answer.value(), true);
        }

        @Test
        public void Number_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("4.44 == 2.22")
                    .eval();
            assertEquals(answer.value(), false);
        }

        @Test
        public void Boolean_Boolean() throws Exception {
            var answer = Expression.builder()
                    .compile("false == false")
                    .eval();
            assertEquals(answer.value(), true);
        }
    }

    @Nested
    class NeOperator {
        @Test
        public void String_String() throws Exception {
            var answer = Expression.builder()
                    .compile("'1' != '2'")
                    .eval();
            assertEquals(answer.value(), true);
        }

        @Test
        public void Int_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("3 != 3")
                    .eval();
            assertEquals(answer.value(), false);
        }

        @Test
        public void Number_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("4.44 != 2.22")
                    .eval();
            assertEquals(answer.value(), true);
        }

        @Test
        public void Boolean_Boolean() throws Exception {
            var answer = Expression.builder()
                    .compile("false != false")
                    .eval();
            assertEquals(answer.value(), false);
        }
    }

    @Nested
    class LessThanOperator {
        @Test
        public void String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("'1' < '2'");
            });
        }

        @Test
        public void Int_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("3 < 3")
                    .eval();
            assertEquals(answer.value(), false);
        }

        @Test
        public void Number_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("4.44 < 2.22")
                    .eval();
            assertEquals(answer.value(), false);
        }

        @Test
        public void Boolean_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("false < false")
                        .eval();
            });
        }
    }

    @Nested
    class LessThanOrEqualOperator {
        @Test
        public void String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("'1' <= '2'");
            });
        }

        @Test
        public void Int_Int() throws Exception {
            var answer = Expression.builder()
                    .compile("3 <= 3")
                    .eval();
            assertEquals(answer.value(), true);
        }

        @Test
        public void Number_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("4.44 <= 2.22")
                    .eval();
            assertEquals(answer.value(), false);
        }

        @Test
        public void Boolean_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("false <= false");
            });
        }
    }

    @Nested
    class GreaterThanOperator {
        @Test
        public void String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("'1' > '2'");
            });
        }

        @Test
        public void Int_Int() throws Exception {
            var answer = Expression.builder().compile("3 > 3").eval();
            assertEquals(answer.value(), false);
        }

        @Test
        public void Number_Number() throws Exception {
            var answer = Expression.builder().compile("4.44 > 2.22").eval();
            assertEquals(answer.value(), true);
        }

        @Test
        public void Boolean_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("false > false");
            });
        }
    }

    @Nested
    class GreaterThanOrEqualOperator {
        @Test
        public void String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("'1' >= '2'");
            });
        }

        @Test
        public void Int_Int() throws Exception {
            var answer = Expression.builder().compile("3 >= 3").eval();
            assertEquals(answer.value(), true);
        }

        @Test
        public void Number_Number() throws Exception {
            var answer = Expression.builder().compile("4.44 >= 5.22").eval();
            assertEquals(answer.value(), false);
        }

        @Test
        public void Boolean_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("false > false");
            });
        }
    }

    @Nested
    class AndAlsoOperator {
        @Test
        public void String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("'1' && '2'");
            });
        }

        @Test
        public void Int_Int() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("3 && 3");
            });
        }

        @Test
        public void Number_Number() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("4.44 && 2.22");
            });
        }

        @Test
        public void Boolean_Boolean() throws Exception {
            var answer = Expression.builder()
                    .compile("false && true")
                    .eval();
            assertEquals(answer.value(), false);
        }
    }

    @Nested
    class OrElseOperator {
        @Test
        public void String_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("'1' || '2'");
            });
        }

        @Test
        public void Int_Int() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("3 || 3");
            });
        }

        @Test
        public void Number_Number() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("4.44 || 2.22");
            });
        }

        @Test
        public void Boolean_Boolean() throws Exception {
            var answer = Expression.builder()
                    .compile("false || true")
                    .eval();
            assertEquals(answer.value(), true);
        }
    }

    @Nested
    class Function {
        @Test
        public void Trunc() throws Exception {
            var answer = Expression.builder()
                    .compile("trunc(20.21)")
                    .eval();
            assertEquals(answer.value(), 20);
        }

        @Test
        public void Trunc_NotFound() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("trunc('bbb')");
            });
        }

        @Test
        public void Replace() throws Exception {
            var answer = Expression.builder()
                    .compile("replace('foobar', 'foo', 'boo')")
                    .eval();
            assertEquals(answer.value(), "boobar");
        }
    }

    @Nested
    class Pattern {
        @Test
        public void Complex_Pattern_1() throws Exception {
            var answer = Expression.builder()
                    .compile("5 * 1 + 2")
                    .eval();
            assertEquals(answer.value(), 7);
        }

        @Test
        public void Complex_Pattern_2() throws Exception {
            var answer = Expression.builder()
                    .compile("5 * (1 + 2)")
                    .eval();
            assertEquals(answer.value(), 15);
        }

        @Test
        public void Complex_Pattern_3() throws Exception {
            var answer = Expression.builder()
                    .compile("5 * (1 + 2) == 15 || 5 * 1 + 2 == 15")
                    .eval();
            assertEquals(answer.value(), true);
        }
    }

    @Nested
    class Variable {
        @Test
        public void Variable_1() throws Exception {
            var answer = Expression.builder()
                    .var("a", 120)
                    .var("b", 50)
                    .compile("a + b")
                    .eval();
            assertEquals(answer.value(), 170);
        }

        @Test
        public void Variable_2() throws Exception {
            var answer = Expression.builder()
                    .var("target", "bar")
                    .var("replacement", "baz")
                    .compile("replace('foobarbaz', target, replacement)")
                    .eval();
            assertEquals(answer.value(), "foobazbaz");
        }

        @Test
        public void Variable_NotDefined() throws Exception {
            assertThrows(VariableNotDefinedException.class, () -> {
                Expression.builder()
                        .var("unde", 20.25)
                        .compile("trunc(undef)");
            });
        }
    }

    @Nested
    class Not {
        @Test
        public void Not_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("!'aaa'")
                        .eval();
            });
        }

        @Test
        public void Not_Integer() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("!123")
                        .eval();
            });
        }

        @Test
        public void Not_Number() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("!123.456")
                        .eval();
            });
        }

        @Test
        public void Not_Boolean() throws Exception {
            var answer = Expression.builder()
                    .compile("!true")
                    .eval();
            assertEquals(answer.value(), false);
        }

        @Test
        public void Not_Boolean_2() throws Exception {
            var answer = Expression.builder()
                    .compile("!!true")
                    .eval();
            assertEquals(answer.value(), true);
        }
    }

    @Nested
    class Plus {
        @Test
        public void Plus_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("+'aaa'")
                        .eval();
            });
        }

        @Test
        public void Plus_Integer() throws Exception {
            var answer = Expression.builder()
                    .compile("+123")
                    .eval();
            assertEquals(answer.value(), 123);
        }

        @Test
        public void Plus_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("+123.456")
                    .eval();
            assertEquals(answer.value(), 123.456);
        }

        @Test
        public void Plus_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("+true")
                        .eval();
            });
        }
    }

    @Nested
    class Minus {
        @Test
        public void Plus_String() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("-'aaa'")
                        .eval();
            });
        }

        @Test
        public void Plus_Integer() throws Exception {
            var answer = Expression.builder()
                    .compile("-123")
                    .eval();
            assertEquals(answer.value(), -123);
        }

        @Test
        public void Plus_Number() throws Exception {
            var answer = Expression.builder()
                    .compile("-123.456")
                    .eval();
            assertEquals(answer.value(), -123.456);
        }

        @Test
        public void Plus_Number_2() throws Exception {
            var answer = Expression.builder()
                    .compile("-(-123.456)")
                    .eval();
            assertEquals(answer.value(), 123.456);
        }

        @Test
        public void Plus_Boolean() throws Exception {
            assertThrows(TypeResolveException.class, () -> {
                Expression.builder()
                        .compile("-true")
                        .eval();
            });
        }
    }

}
