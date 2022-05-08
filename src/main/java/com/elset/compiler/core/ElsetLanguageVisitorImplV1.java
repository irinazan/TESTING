package com.elset.compiler.core;

import com.google.common.base.Preconditions;
import com.elset.compiler.core.methods.Method;
import com.elset.compiler.core.methods.MethodType;
import com.elset.compiler.core.variables.Variable;
import com.elset.compiler.core.variables.VariableType;
import com.elset.compiler.gen.ElsetLanguageParser;
import com.elset.compiler.gen.ElsetLanguageVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElsetLanguageVisitorImplV1 implements ElsetLanguageVisitor<String> {

    private final ElsetLanguageParserV1 parserV1;
    private final VariableAndMethodRegister register = new VariableAndMethodRegister();
    private final String name;
    private int yieldCount;
    private boolean coroutineCheck;

    public ElsetLanguageVisitorImplV1(ElsetLanguageParserV1 parserV1, String name) {
        this.parserV1 = parserV1;
        this.name = name;
    }
    
 /*public class ContextException extends Exception {

	private final ErrorCode code;

	public ContextException(ErrorCode code) {
		super();
		this.code = code;
	    }

	public ContextException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	    }

	public ContextException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	    }

	public ContextException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	    }
	
	public ErrorCode getCode() {
		return this.code;
	    }
    }*/
    
    @Override
    public String visitProgram(ElsetLanguageParser.ProgramContext ctx) {
        register.registerMethodInvocation();
        String out = CompilerFields.MAIN_METHOD + ctx.block().accept(this);
        register.registerMethodInvocationEnded();
        return out;
    }

    @Override
    public String visitBlock(ElsetLanguageParser.BlockContext ctx) {
        register.registerNew​​VisibilityArea();
        StringBuilder out = new StringBuilder(CompilerFields.BEGIN).append(invokeAllStatementsForBlock(ctx.statement()));
        out.append(CompilerFields.END);
        register.registerVisibilityAreaEnded();
        return out.toString();
    }

    private StringBuilder invokeAllStatementsForBlock(List<ElsetLanguageParser.StatementContext> list) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            out.append(list.get(i).accept(this));
        }
        return out;
    }

    @Override
    public String visitStatement(ElsetLanguageParser.StatementContext ctx) {
        Statement statement = Statement.findStatement(ctx);
        Preconditions.checkNotNull(statement);
        return statement.invoke(ctx, this);
    }

    @Override
    public String visitDigit_expression(ElsetLanguageParser.Digit_expressionContext ctx) {
        validateDigitExpression(ctx);
        return concatExpr(ctx) + CompilerFields.SEPARATOR;
    }

    @Override
    public String visitIntialize_set(ElsetLanguageParser.Intialize_setContext ctx) {
        String ID = ((ElsetLanguageParser.Assign_setContext) ctx.parent).ID().getText();
        return VariableType.SET.getOutName() + " " + ID + CompilerFields.ASSIGN + CompilerFields.NEW_SET + CompilerFields.SEPARATOR + handleSetInitialization(ctx.ID(), ID);
    }

    private String handleSetInitialization(List<TerminalNode> list, String rootID) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Variable variable = Preconditions.checkNotNull(register.getVariable(list.get(i).getText()));
            if (variable.getVariableType() != VariableType.ELEMENT) {
                //throw new NoSuchElementException();
            }
            out.append(rootID).append(CompilerFields.DELIMITER).append(String.format(CompilerFields.ADD_NEW_ELEMENT, variable.getID())).append(CompilerFields.SEPARATOR);
        }
        return out.toString();
    }

    @Override
    public String visitIntialize_element(ElsetLanguageParser.Intialize_elementContext ctx) {
        return String.format(CompilerFields.NEW_ELEMENT, ctx.STRING()) + CompilerFields.SEPARATOR;
    }

    @Override
    public String visitAssign_var_method_invocation(ElsetLanguageParser.Assign_var_method_invocationContext ctx) {
        Variable variable = new Variable(ctx.ID().getText(), Preconditions.checkNotNull(VariableType.findByDisplayName(ctx.type().getText())), ctx.CONST() != null);
        Preconditions.checkState(register.registerVariable(variable));
        Method method = Preconditions.checkNotNull(register.getRegisteredMethod(ctx.method_invokation().ID().getText()));
        if (method.getMethodType() == MethodType.RETURN_OPTIONAL || method.getMethodType().getReturnedType() != variable.getVariableType()) {
            throw new UnsupportedOperationException();
        }
        return variable.getVariableType().getOutName() + " " + variable.getID() + CompilerFields.ASSIGN + ctx.method_invokation().accept(this);
    }

    private void validateDigitExpression(ElsetLanguageParser.Digit_expressionContext ctx) {
        if (ctx.ID() != null) {
            Variable variable = register.getVariable(ctx.ID().toString());
            if (variable == null || variable.getVariableType() != VariableType.INT) {
                //throw new ContextException("Invalid digit.");
            }
        }
        List<ElsetLanguageParser.Digit_expressionContext> expr = ctx.digit_expression();
        for (int i = 0; i < expr.size(); i++) {
            validateDigitExpression(expr.get(i));
        }
    }

    @Override
    public String visitAssign_var(ElsetLanguageParser.Assign_varContext ctx) {
        return processAssignVar(ctx, false);
    }

    private String processAssignVar(ElsetLanguageParser.Assign_varContext ctx, boolean global) {
        VariableType variableType = Preconditions.checkNotNull(VariableType.findByDisplayName(ctx.type_1().getText()));
        String out = variableType.getOutName() + " " + ctx.ID() + CompilerFields.ASSIGN + variableType.invokeInitLine(ctx, this);
        if (!global) {
            Preconditions.checkState(register.registerVariable(new Variable(ctx.ID().toString(), variableType, ctx.CONST() != null)));
        } else {
            Preconditions.checkState(register.registerGlobalVariable(new Variable(ctx.ID().toString(), variableType, ctx.CONST() != null)));
        }
        return out;
    }

    @Override
    public String visitAssign_set(ElsetLanguageParser.Assign_setContext ctx) {
        return processAssignSet(ctx, false);
    }

    private String processAssignSet(ElsetLanguageParser.Assign_setContext ctx, boolean global) {
        StringBuilder out = new StringBuilder();
        Operation operation = Operation.findOperation(ctx.intialize_set());
        Variable varForRegister;
        if (operation == null) {
            out.append(VariableType.SET.invokeInitLine(ctx, this));
            varForRegister = new Variable(ctx.ID().toString(), VariableType.SET, ctx.CONST() != null);
        } else {
            for (int i = 1; i < 2; i++) {
                Variable variable = Preconditions.checkNotNull(register.getVariable(ctx.intialize_set().ID(i).getText()));
                if (variable.getVariableType() != VariableType.SET) {
                    //throw new NoSuchElementException();
                }
            }
            out.append(VariableType.SET.getOutName()).append(" ").append(ctx.ID()).append(CompilerFields.ASSIGN);
            out.append(String.format(operation.getOverrideSet(), ctx.intialize_set().ID(0), ctx.intialize_set().ID(1))).append(CompilerFields.SEPARATOR);
            varForRegister = new Variable(ctx.ID().toString(), VariableType.SET);
        }
        if (!global) {
            Preconditions.checkState(register.registerVariable(varForRegister));
        } else {
            Preconditions.checkState(register.registerGlobalVariable(varForRegister));
        }
        return out.toString();
    }

    @Override
    public String visitPrint(ElsetLanguageParser.PrintContext ctx) {
        if (ctx.ID() != null) {
            Variable variable = Preconditions.checkNotNull(register.getVariable(ctx.ID().getText()));
            return String.format(CompilerFields.SOUT, variable.getID()) + CompilerFields.SEPARATOR;
        } else {
            return String.format(CompilerFields.SOUT, ctx.STRING() == null ? ctx.NUMBER() : ctx.STRING()) + CompilerFields.SEPARATOR;
        }
    }

    @Override
    public String visitOperations(ElsetLanguageParser.OperationsContext ctx) {
        Variable variable = register.getVariable(ctx.ID().toString());
        if (variable == null || variable.getVariableType() != VariableType.INT || variable.isConstant()) {
            throw new IllegalStateException();
        }
        validateDigitExpression(ctx.digit_expression());
        return variable.getID() + CompilerFields.ASSIGN + concatExpr(ctx.digit_expression()) + CompilerFields.SEPARATOR;
    }

    @Override
    //TODO rework.
    public String visitOperarions_with_set(ElsetLanguageParser.Operarions_with_setContext ctx) {
        Variable variable = Preconditions.checkNotNull(register.getVariable(ctx.ID(0).getText()));
        Variable el = Preconditions.checkNotNull(register.getVariable(ctx.ID(1).getText()));
        if (variable.getVariableType() != VariableType.SET || el.getVariableType() != VariableType.ELEMENT || variable.isConstant()) {
            throw new UnsupportedOperationException();
        }
        if (ctx.ADD() == null) {
            return variable.getID() + CompilerFields.DELIMITER + String.format(CompilerFields.REMOVE_NEW_ELEMENT, el.getID()) + CompilerFields.SEPARATOR;
        } else {
            return variable.getID() + CompilerFields.DELIMITER + String.format(CompilerFields.REMOVE_NEW_ELEMENT, el.getID()) + CompilerFields.SEPARATOR;
        }
    }

    @Override
    public String visitSimple_compare(ElsetLanguageParser.Simple_compareContext ctx) {
        List<ElsetLanguageParser.Digit_expressionContext> dctx = ctx.digit_expression();
        validateDigitExpression(dctx.get(0));
        validateDigitExpression(dctx.get(1));
        return concatExpr(ctx);
    }

    private String concatExpr(ParserRuleContext ctx) {
        StringBuilder out = new StringBuilder();
        List<Token> tokens = parserV1.getTokens(ctx.start, ctx.stop);
        for (int i = 0; i < tokens.size(); i++) {
            out.append(tokens.get(i).getText());
        }
        return out.toString();
    }

    @Override
    public String visitHard_compare(ElsetLanguageParser.Hard_compareContext ctx) {
        return ctx.NEGATION().getText() + ctx.O_BRACKET().getText() + visitSimple_compare(ctx.simple_compare()) + ctx.C_BRACKET().getText();
    }

    @Override
    public String visitWhile_cicle(ElsetLanguageParser.While_cicleContext ctx) {
        String out = String.format(CompilerFields.WHILE, handlerCompare(ctx.hard_compare(), ctx.simple_compare()));
        out += ctx.block().accept(this);
        return out;
    }

    private String handlerCompare(ElsetLanguageParser.Hard_compareContext htx, ElsetLanguageParser.Simple_compareContext stx) {
        return stx == null ? htx.accept(this) : stx.accept(this);
    }

    @Override
    public String visitIf_then(ElsetLanguageParser.If_thenContext ctx) {
        return String.format(CompilerFields.IF_ELSE, handlerCompare(ctx.hard_compare(), ctx.simple_compare()), ctx.block(0).accept(this), ctx.block(1).accept(this));
    }

    @Override
    public String visitFor_each(ElsetLanguageParser.For_eachContext ctx) {
        Variable variable = register.getVariable(ctx.ID(1).getText());
        if (variable == null || variable.getVariableType() != VariableType.SET) {
            throw new UnsupportedOperationException();
        }
        Preconditions.checkState(register.registerVariable(new Variable(ctx.ID(0).getText(), VariableType.ELEMENT)));
        return String.format(CompilerFields.FOR_EACH, CompilerFields.ELEMENT + " " + ctx.ID(0).getText(), variable.getID()) + ctx.block().accept(this);
    }

    @Override
    public String visitGlobal_assign_set(ElsetLanguageParser.Global_assign_setContext ctx) {
        return CompilerFields.GLOBAL_VAR + processAssignSet(ctx.assign_set(), true);
    }

    @Override
    public String visitGlobal_assign_var(ElsetLanguageParser.Global_assign_varContext ctx) {
        return CompilerFields.GLOBAL_VAR + processAssignVar(ctx.assign_var(), true);
    }

    @Override
    public String visitType(ElsetLanguageParser.TypeContext ctx) {
        VariableType variableType = VariableType.findByDisplayName(ctx.getText());
        return Preconditions.checkNotNull(variableType).getOutName();
    }

    @Override
    public String visitType_1(ElsetLanguageParser.Type_1Context ctx) {
        VariableType variableType = VariableType.findByDisplayName(ctx.getText());
        return Preconditions.checkNotNull(variableType).getOutName();
    }

    @Override
    public String visitSignature(ElsetLanguageParser.SignatureContext ctx) {
        StringBuilder builder = new StringBuilder();
        builder.append(CompilerFields.OPEN_BRACKET);
        for (int i = 0; i < ctx.ID().size(); i++) {
            VariableType variableType = VariableType.findByDisplayName(ctx.type(i).getText());
            Preconditions.checkNotNull(variableType);
            register.registerVariable(new Variable(ctx.ID(i).getText(), variableType));
            builder.append(ctx.type(i).accept(this)).append(" ").append(ctx.ID(i));
            if (i != ctx.ID().size() - 1) {
                builder.append(CompilerFields.COMMA);
            }
        }
        builder.append(CompilerFields.CLOSE_BRACKET);
        return builder.toString();
    }

    @Override
    public String visitSubprogram_return(ElsetLanguageParser.Subprogram_returnContext ctx) {
        Method method = register.getRegisteredMethod(ctx.ID().toString());
        if (method == null || method.getMethodType() == MethodType.RETURN_OPTIONAL) {
            throw new UnsupportedOperationException();
        }
        register.registerMethodInvocation();
        //NPE checked before.
        String s = CompilerFields.SUB_METHOD + method.getMethodType().getReturnedType().getOutName()
                + " " + ctx.ID() + ctx.signature().accept(this) + ctx.block_return().accept(this);
        register.registerMethodInvocationEnded();
        return s;
    }

    @Override
    public String visitSubprogram_non_return(ElsetLanguageParser.Subprogram_non_returnContext ctx) {
        Method method = register.getRegisteredMethod(ctx.ID().toString());
        if (method == null || method.getMethodType() != MethodType.RETURN_OPTIONAL) {
            throw new UnsupportedOperationException();
        }
        register.registerMethodInvocation();
        String s = CompilerFields.SUB_METHOD + CompilerFields.VOID + " " + ctx.ID() + handleSignature(ctx.signature());
        s += ctx.block_non_return() == null ? ctx.block().accept(this) : ctx.block_non_return().accept(this);
        register.registerMethodInvocationEnded();
        return s;
    }
	
	@Override
    public String visitCoroutine_return(ElsetLanguageParser.Coroutine_returnContext ctx) {
	
	int argumentNumber = 0;
	yieldCount = 0;
	coroutineCheck = true;  
	String out = CompilerFields.COROUTINE + CompilerFields.VOID;

	String template = "template <";
	String variable = "int state = 0;";
	    
        Method method = register.getRegisteredMethod(ctx.ID().toString());
        if (method == null || method.getMethodType() == MethodType.RETURN_OPTIONAL) {
            throw new UnsupportedOperationException();
        }
        register.registerMethodInvocation();
	
 	
	String states = "switch(state){" + "case 0:" + "goto start;";
        for (int i = 0; i < yieldCount - 1; i++) {
            states += "case " + (i + 1) + ":" + "goto state" + i + ";";
        }
        states += "default:throw std::runtime_error(\"Coroutine is over\");";
        states += "}" + "start:;";    
	    
        //NPE checked before.
        String s = CompilerFields.COROUTINE + method.getMethodType().getReturnedType().getOutName()
               /* + " " + ctx.ID() + ctx.signature().accept(this) + ctx.block_return().accept(this)*/;
        register.registerMethodInvocationEnded();
	    
	/*int p = getID("{");
        s = s.substring(0, p) +
                "{" +
                defVariables +
                "public:" +
                constructor +
                "auto next() {" +
                states +
                s.substring(p + 1) +
                "};";*/
	    
	 if (argumentNumber != 0) {
            s = template.substring(0, template.length() - 2) + ">" + out;
        }    
        return s;
    }

    @Override
    public String visitCoroutine_non_return(ElsetLanguageParser.Coroutine_non_returnContext ctx) {
        Method method = register.getRegisteredMethod(ctx.ID().toString());
        if (method == null || method.getMethodType() != MethodType.RETURN_OPTIONAL) {
            throw new UnsupportedOperationException();
        }
        register.registerMethodInvocation();
	yieldCount = 0;
	coroutineCheck = true;        
        String s = CompilerFields.COROUTINE + CompilerFields.VOID /*+ " " + ctx.ID() + handleSignature(ctx.signature())*/;
        //s += ctx.block_non_return() == null ? ctx.block().accept(this) : ctx.block_non_return().accept(this);
        String template = "template <";
        s += template.substring(0, template.length() - 2) + ">";
        register.registerMethodInvocationEnded();
        return s;
    }
	
    @Override
    public String visitYield(ElsetLanguageParser.YieldContext ctx) {
        if (!coroutineCheck) {
            //throw new ContextException("Yield is not applicable.");
        }
     String s = "state++;";
     s += "state" + yieldCount + ":;";
     yieldCount++;
     return s;
    }
	
    private String handleSignature(ElsetLanguageParser.SignatureContext ctx) {
        return ctx == null ? CompilerFields.OPEN_BRACKET + CompilerFields.CLOSE_BRACKET : ctx.accept(this);
    }

    @Override
    public String visitBlock_return(ElsetLanguageParser.Block_returnContext ctx) {
        register.registerNew​​VisibilityArea();
        Method method = Preconditions.checkNotNull(register.getRegisteredMethod(((ElsetLanguageParser.Subprogram_returnContext) ctx.parent).ID().getText()));
        StringBuilder out = new StringBuilder(CompilerFields.BEGIN).append(invokeAllStatementsForBlock(ctx.statement()));
        out.append(CompilerFields.RETURN);
        Variable variable = Preconditions.checkNotNull(register.getVariable(ctx.ID().getText()));
        if (variable.getVariableType() != method.getMethodType().getReturnedType()) {
            throw new UnsupportedOperationException();
        }
        out.append(" ").append(variable.getID()).append(CompilerFields.SEPARATOR).append(CompilerFields.END);
        register.registerVisibilityAreaEnded();
        return out.toString();
    }

    @Override
    public String visitBlock_non_return(ElsetLanguageParser.Block_non_returnContext ctx) {
        register.registerNew​​VisibilityArea();
        StringBuilder out = new StringBuilder(CompilerFields.BEGIN).append(invokeAllStatementsForBlock(ctx.statement()));
        out.append(CompilerFields.RETURN);
        out.append(CompilerFields.SEPARATOR);
        out.append(CompilerFields.END);
        register.registerVisibilityAreaEnded();
        return out.toString();
    }

    @Override
    public String visitSignature_method_invokation(ElsetLanguageParser.Signature_method_invokationContext ctx) {
        StringBuilder builder = new StringBuilder();
        builder.append(CompilerFields.OPEN_BRACKET);
        Method method = register.getRegisteredMethod(((ElsetLanguageParser.Method_invokationContext) ctx.parent).ID().getText());
        Preconditions.checkNotNull(method);
        if (method.getArguments().size() != ctx.ID().size()) {
            throw new UnsupportedOperationException();
        }
        for (int i = 0; i < ctx.ID().size(); i++) {
            Variable variable = register.getVariable(ctx.ID(i).getText());
            if (variable == null || method.getArguments().get(i) != variable.getVariableType()) {
                throw new UnsupportedOperationException();
            }
            builder.append(variable.getID());
        }
        return builder.append(CompilerFields.CLOSE_BRACKET).toString();
    }

    @Override
    public String visitMethod_invokation(ElsetLanguageParser.Method_invokationContext ctx) {
        Method method = register.getRegisteredMethod(ctx.ID().toString());
        if (method == null) {
            //throw new ContextEception("Invalid method");
        }
        return method.getID() + " " + handleSignatureOfInvocation(ctx) + CompilerFields.SEPARATOR;
    }

    private String handleSignatureOfInvocation(ElsetLanguageParser.Method_invokationContext ctx) {
        if (ctx.signature_method_invokation() == null) {
            Method method = Preconditions.checkNotNull(register.getRegisteredMethod(ctx.ID().getText()));
            if (method.getArguments().size() != 0) {
                throw new UnsupportedOperationException();
            }
            return CompilerFields.OPEN_BRACKET + CompilerFields.CLOSE_BRACKET;
        }
        return ctx.signature_method_invokation().accept(this);
    }

    @Override
    public String visitGlobal_program(ElsetLanguageParser.Global_programContext ctx) {
        StringBuilder out = new StringBuilder();
        register.registerMethod(new Method(CompilerFields.MAIN_PROGRAM, MethodType.RETURN_OPTIONAL, Collections.emptyList()));
        List<ElsetLanguageParser.Subprogram_non_returnContext> non_returnContexts = ctx.subprogram_non_return();
        List<ElsetLanguageParser.Subprogram_returnContext> returnContexts = ctx.subprogram_return();
	    List<ElsetLanguageParser.Coroutine_non_returnContext> coroutine_non_returnContexts = ctx.coroutine_non_return();
        List<ElsetLanguageParser.Coroutine_returnContext> coroutine_returnContexts = ctx.coroutine_return();
        List<ElsetLanguageParser.YieldContext> yieldContexts = ctx.yield_return();
        for (ElsetLanguageParser.Global_assign_varContext ct : ctx.global_assign_var()) {
            out.append(ct.accept(this));
        }
        for (ElsetLanguageParser.Global_assign_setContext ct : ctx.global_assign_set()) {
            out.append(ct.accept(this));
        }
        for (ElsetLanguageParser.Subprogram_returnContext ct : returnContexts) {
            VariableType variableType = Preconditions.checkNotNull(VariableType.findByDisplayName(ct.type().getText()));
            register.registerMethod(new Method(ct.ID().toString(), Preconditions.checkNotNull(MethodType.findByReturnedType(variableType)), collectMethodArguments(ct.signature())));
            out.append(ct.accept(this));
        }
        for (ElsetLanguageParser.Subprogram_non_returnContext ct : non_returnContexts) {
            register.registerMethod(new Method(ct.ID().toString(), MethodType.RETURN_OPTIONAL, collectMethodArguments(ct.signature())));
            out.append(ct.accept(this));
        }
	    for (ElsetLanguageParser.Coroutine_returnContext ct : coroutine_returnContexts) {
            VariableType variableType = Preconditions.checkNotNull(VariableType.findByDisplayName(ct.type().getText()));
            register.registerMethod(new Method(ct.ID().toString(), Preconditions.checkNotNull(MethodType.findByReturnedType(variableType)), collectMethodArguments(ct.signature())));
            out.append(ct.accept(this));
        }
        for (ElsetLanguageParser.Coroutine_non_returnContext ct : coroutine_non_returnContexts) {
            register.registerMethod(new Method(ct.ID().toString(), MethodType.RETURN_OPTIONAL, collectMethodArguments(ct.signature())));
            out.append(ct.accept(this));
        }
        for (ElsetLanguageParser.YieldContext ct : yieldContexts) {
            register.registerMethod(new Method(ct.ID().toString(), MethodType.RETURN_OPTIONAL, collectMethodArguments(ct.signature())));
            out.append(ct.accept(this));
        }
        out.append(ctx.program().accept(this));
        return out.toString();
    }

    private List<VariableType> collectMethodArguments(ElsetLanguageParser.SignatureContext ctx) {
        List<VariableType> variableTypes = new ArrayList<>();
        if (ctx == null) {
            return Collections.emptyList();
        }
        for (int i = 0; i < ctx.ID().size(); i++) {
            VariableType variableType = VariableType.findByDisplayName(ctx.type(i).getText());
            variableTypes.add(Preconditions.checkNotNull(variableType));
        }
        return variableTypes;
    }

    @Override
    public String visit(ParseTree parseTree) {
        String out = parseTree.accept(this);
        System.out.println(out);
        return String.format(CompilerFields.STATIC_CONTENT, name, out);
    }

    @Override
    public String visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public String visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public String visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
