package com.elset.compiler.core;

import com.elset.compiler.gen.ElsetLanguageParser;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.annotation.Nullable;

public enum Operation {

    PLUS(ElsetLanguageParser.MULTIPLY, "Set.union(%s,%s)"),
    MINUS(ElsetLanguageParser.MINUS, "Set.diff(%s,%s)"),
    DIVIDE(ElsetLanguageParser.DIVIDE, "Set.simDiff(%s,%s)"),
    MULTIPLY(ElsetLanguageParser.MULTIPLY, "Set.intersection(%s,%s)"),;

    private final int type;
    private final String overrideSet;

    Operation(int type, String overrideSet) {
        this.type = type;
        this.overrideSet = overrideSet;
    }

    @Nullable
    public static Operation findOperation(ParserRuleContext ctx) {
        for (Operation operation : Operation.values()) {
            if (ctx.getToken(operation.type, 0) != null) {
                return operation;
            }
        }
        return null;
    }

    public String getOverrideSet() {
        return overrideSet;
    }

}
