// Generated from D:/yapis\ElsetLanguage.g4 by ANTLR 4.9.2
package com.elset.compiler.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ElsetLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ElsetLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ElsetLanguageParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ElsetLanguageParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ElsetLanguageParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#digit_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit_expression(ElsetLanguageParser.Digit_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#intialize_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntialize_set(ElsetLanguageParser.Intialize_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#intialize_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntialize_element(ElsetLanguageParser.Intialize_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#assign_var_method_invocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_var_method_invocation(ElsetLanguageParser.Assign_var_method_invocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#assign_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_set(ElsetLanguageParser.Assign_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#assign_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_var(ElsetLanguageParser.Assign_varContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(ElsetLanguageParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#operations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperations(ElsetLanguageParser.OperationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#operarions_with_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperarions_with_set(ElsetLanguageParser.Operarions_with_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#simple_compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_compare(ElsetLanguageParser.Simple_compareContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#hard_compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHard_compare(ElsetLanguageParser.Hard_compareContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#while_cicle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_cicle(ElsetLanguageParser.While_cicleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#if_then}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_then(ElsetLanguageParser.If_thenContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#for_each}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_each(ElsetLanguageParser.For_eachContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#global_assign_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_assign_set(ElsetLanguageParser.Global_assign_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#global_assign_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_assign_var(ElsetLanguageParser.Global_assign_varContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ElsetLanguageParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#type_1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_1(ElsetLanguageParser.Type_1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#signature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature(ElsetLanguageParser.SignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#subprogram_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprogram_return(ElsetLanguageParser.Subprogram_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#subprogram_non_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprogram_non_return(ElsetLanguageParser.Subprogram_non_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#coroutine_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoroutine_return(ElsetLanguageParser.Coroutine_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#coroutine_non_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoroutine_non_return(ElsetLanguageParser.Coroutine_non_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#yield_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield(ElsetLanguageParser.YieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#block_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_return(ElsetLanguageParser.Block_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#block_non_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_non_return(ElsetLanguageParser.Block_non_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#signature_method_invokation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature_method_invokation(ElsetLanguageParser.Signature_method_invokationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#method_invokation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_invokation(ElsetLanguageParser.Method_invokationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ElsetLanguageParser#global_program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_program(ElsetLanguageParser.Global_programContext ctx);
}
