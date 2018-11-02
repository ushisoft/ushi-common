package io.ushi.rest.query;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Criteria<T> {

    public enum Operation {
        /** =, or string with no operation character */
        equal,
        /** != */
        notEqual,
        /** < */
        lessThan,
        /** > */
        greaterThan,
        /** <= */
        lessThanOrEqualTo,
        /** >= */
        greaterThanOrEqualTo,
        /** contain % */
        like,
        /** (from, to), (], [), [] */
        between,
        /** string separated by comma */
        in
    }

    public static final Pattern betweenPattern = Pattern.compile("^[\\[\\(]([\\d\\s]+),([\\d\\s]+)[\\]\\)]$");

    private String field;

    private Operation operation;

    private T value;

    private Criteria() {
    }

    public static RequiredField field(String field) {
        return new Criteria.Builder(field);
    }

    public interface RequiredField {
        RequiredExpression expression(String expression);
    }

    public interface RequiredExpression {
        Criteria build();
    }

    private static class Builder implements RequiredField, RequiredExpression {

        private final Criteria instance = new Criteria();

        public Builder(String field) {
            instance.field = field;
        }

        @Override
        public RequiredExpression expression(String expression) {
            Matcher matcher;
            if (expression.startsWith("!=")) {
                instance.operation = Operation.notEqual;
                instance.value = expression.substring(2);
            } else if (expression.startsWith(">=")) {
                instance.operation = Operation.greaterThanOrEqualTo;
                instance.value = expression.substring(2);
            } else if (expression.startsWith("<=")) {
                instance.operation = Operation.lessThanOrEqualTo;
                instance.value = expression.substring(2);
            } else if (expression.startsWith("=")) {
                instance.operation = Operation.equal;
                instance.value = expression.substring(1);
            } else if (expression.startsWith(">")) {
                instance.operation = Operation.greaterThan;
                instance.value = expression.substring(1);
            } else if (expression.startsWith("<")) {
                instance.operation = Operation.lessThan;
                instance.value = expression.substring(1);
            } else if ((matcher = betweenPattern.matcher(expression)).find()) {
                instance.operation = Operation.between;
                instance.value = matcher.group(1);
            } else if (expression.contains("%")) {
                instance.operation = Operation.like;
                instance.value = expression;
            } else if (expression.contains(",")) { // not between phase, and contains comma
                instance.operation = Operation.in;
                instance.value = expression;
            } else {
                instance.operation = Operation.equal;
                instance.value = expression;
            }
            return this;
        }

        @Override
        public Criteria build() {
            return instance;
        }
    }
}
