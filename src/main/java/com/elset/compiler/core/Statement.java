package com.elset.compiler.core;

import com.elset.compiler.gen.ElsetLanguageParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import javax.annotation.Nullable;

public enum Statement {

    ASSIGN(ElsetLanguageParser.Assign_varContext.class, (ctx, visitor) -> {
        return ctx.assign_var().accept(visitor);
    }),
    ASSIGN_SET(ElsetLanguageParser.Assign_setContext.class, (ctx, visitor) -> {
        return ctx.assign_set().accept(visitor);
    }),
    PRINT(ElsetLanguageParser.PrintContext.class, (ctx, visitor) -> {
        return ctx.print().accept(visitor);
    }),
    OPERATION_INT(ElsetLanguageParser.OperationsContext.class, (ctx, visitor) -> {
        return ctx.operations().accept(visitor);
    }),
    WHILE_CICLE(ElsetLanguageParser.While_cicleContext.class, (ctx, visitor) -> {
        return ctx.while_cicle().accept(visitor);
    }),
    IF_THEN(ElsetLanguageParser.If_thenContext.class, (ctx, visitor) -> {
        return ctx.if_then().accept(visitor);
    }),
    METHOD_INVOCATION(ElsetLanguageParser.Method_invokationContext.class, (ctx, visitor) -> {
        return ctx.method_invokation().accept(visitor);
    }),
    METHOD_INVOCATION_AND_ASSIGN(ElsetLanguageParser.Assign_var_method_invocationContext.class, (ctx, visitor) -> {
        return ctx.assign_var_method_invocation().accept(visitor);
    }),
    SET_OPERATIONS(ElsetLanguageParser.Operarions_with_setContext.class, (ctx, visitor) -> {
        return ctx.operarions_with_set().accept(visitor);
    }),
    FOR_EACH(ElsetLanguageParser.For_eachContext.class, (ctx, visitor) -> {
        return ctx.for_each().accept(visitor);
    });

    private final Class clazz;
    private final InvocationAction<String> action;

    Statement(Class clazz, InvocationAction<String> action) {
        this.clazz = clazz;
        this.action = action;
    }

    @Nullable
    public static Statement findStatement(ParserRuleContext ctx) {
        for (Statement statement : Statement.values()) {
            if (ctx.getRuleContext(statement.clazz, 0) != null) {
                return statement;
            }
        }
        return null;
    }

    public String invoke(ElsetLanguageParser.StatementContext ctx, ParseTreeVisitor<String> visitor) {
        return action.invoke(ctx, visitor);
    }

    private interface InvocationAction<T> {

        T invoke(ElsetLanguageParser.StatementContext ctx, ParseTreeVisitor<T> visitor);

    }

}
