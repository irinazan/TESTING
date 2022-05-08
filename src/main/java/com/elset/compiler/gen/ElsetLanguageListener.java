// Generated from D:/yapis\ElsetLanguage.g4 by ANTLR 4.9.2
package com.elset.compiler.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ElsetLanguageParser}.
 */
public interface ElsetLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ElsetLanguageParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ElsetLanguageParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ElsetLanguageParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ElsetLanguageParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ElsetLanguageParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ElsetLanguageParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#digit_expression}.
	 * @param ctx the parse tree
	 */
	void enterDigit_expression(ElsetLanguageParser.Digit_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#digit_expression}.
	 * @param ctx the parse tree
	 */
	void exitDigit_expression(ElsetLanguageParser.Digit_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#intialize_set}.
	 * @param ctx the parse tree
	 */
	void enterIntialize_set(ElsetLanguageParser.Intialize_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#intialize_set}.
	 * @param ctx the parse tree
	 */
	void exitIntialize_set(ElsetLanguageParser.Intialize_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#intialize_element}.
	 * @param ctx the parse tree
	 */
	void enterIntialize_element(ElsetLanguageParser.Intialize_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#intialize_element}.
	 * @param ctx the parse tree
	 */
	void exitIntialize_element(ElsetLanguageParser.Intialize_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#assign_var_method_invocation}.
	 * @param ctx the parse tree
	 */
	void enterAssign_var_method_invocation(ElsetLanguageParser.Assign_var_method_invocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#assign_var_method_invocation}.
	 * @param ctx the parse tree
	 */
	void exitAssign_var_method_invocation(ElsetLanguageParser.Assign_var_method_invocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#assign_set}.
	 * @param ctx the parse tree
	 */
	void enterAssign_set(ElsetLanguageParser.Assign_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#assign_set}.
	 * @param ctx the parse tree
	 */
	void exitAssign_set(ElsetLanguageParser.Assign_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#assign_var}.
	 * @param ctx the parse tree
	 */
	void enterAssign_var(ElsetLanguageParser.Assign_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#assign_var}.
	 * @param ctx the parse tree
	 */
	void exitAssign_var(ElsetLanguageParser.Assign_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(ElsetLanguageParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(ElsetLanguageParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#operations}.
	 * @param ctx the parse tree
	 */
	void enterOperations(ElsetLanguageParser.OperationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#operations}.
	 * @param ctx the parse tree
	 */
	void exitOperations(ElsetLanguageParser.OperationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#operarions_with_set}.
	 * @param ctx the parse tree
	 */
	void enterOperarions_with_set(ElsetLanguageParser.Operarions_with_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#operarions_with_set}.
	 * @param ctx the parse tree
	 */
	void exitOperarions_with_set(ElsetLanguageParser.Operarions_with_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#simple_compare}.
	 * @param ctx the parse tree
	 */
	void enterSimple_compare(ElsetLanguageParser.Simple_compareContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#simple_compare}.
	 * @param ctx the parse tree
	 */
	void exitSimple_compare(ElsetLanguageParser.Simple_compareContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#hard_compare}.
	 * @param ctx the parse tree
	 */
	void enterHard_compare(ElsetLanguageParser.Hard_compareContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#hard_compare}.
	 * @param ctx the parse tree
	 */
	void exitHard_compare(ElsetLanguageParser.Hard_compareContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#while_cicle}.
	 * @param ctx the parse tree
	 */
	void enterWhile_cicle(ElsetLanguageParser.While_cicleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#while_cicle}.
	 * @param ctx the parse tree
	 */
	void exitWhile_cicle(ElsetLanguageParser.While_cicleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#if_then}.
	 * @param ctx the parse tree
	 */
	void enterIf_then(ElsetLanguageParser.If_thenContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#if_then}.
	 * @param ctx the parse tree
	 */
	void exitIf_then(ElsetLanguageParser.If_thenContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#for_each}.
	 * @param ctx the parse tree
	 */
	void enterFor_each(ElsetLanguageParser.For_eachContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#for_each}.
	 * @param ctx the parse tree
	 */
	void exitFor_each(ElsetLanguageParser.For_eachContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#global_assign_set}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_assign_set(ElsetLanguageParser.Global_assign_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#global_assign_set}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_assign_set(ElsetLanguageParser.Global_assign_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#global_assign_var}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_assign_var(ElsetLanguageParser.Global_assign_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#global_assign_var}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_assign_var(ElsetLanguageParser.Global_assign_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ElsetLanguageParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ElsetLanguageParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#type_1}.
	 * @param ctx the parse tree
	 */
	void enterType_1(ElsetLanguageParser.Type_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#type_1}.
	 * @param ctx the parse tree
	 */
	void exitType_1(ElsetLanguageParser.Type_1Context ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#signature}.
	 * @param ctx the parse tree
	 */
	void enterSignature(ElsetLanguageParser.SignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#signature}.
	 * @param ctx the parse tree
	 */
	void exitSignature(ElsetLanguageParser.SignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#subprogram_return}.
	 * @param ctx the parse tree
	 */
	void enterSubprogram_return(ElsetLanguageParser.Subprogram_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#subprogram_return}.
	 * @param ctx the parse tree
	 */
	void exitSubprogram_return(ElsetLanguageParser.Subprogram_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#subprogram_non_return}.
	 * @param ctx the parse tree
	 */
	void enterSubprogram_non_return(ElsetLanguageParser.Subprogram_non_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#subprogram_non_return}.
	 * @param ctx the parse tree
	 */
	void exitSubprogram_non_return(ElsetLanguageParser.Subprogram_non_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#block_return}.
	 * @param ctx the parse tree
	 */



	////////////////////////////
	void enterCoroutine_return(ElsetLanguageParser.Coroutine_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#coroutine_return}.
	 * @param ctx the parse tree
	 */
	void exitCoroutine_return(ElsetLanguageParser.Coroutine_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#block_return}.
	 * @param ctx the parse tree
	 */

	void enterCoroutine_non_return(ElsetLanguageParser.Coroutine_non_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#coroutine_non_return}.
	 * @param ctx the parse tree
	 */
	void exitCoroutine_non_return(ElsetLanguageParser.Coroutine_non_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#block_return}.
	 * @param ctx the parse tree
	 */

	void enterYield(ElsetLanguageParser.YieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#yield_return}.
	 * @param ctx the parse tree
	 */
	void exitYield(ElsetLanguageParser.YieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#block_return}.
	 * @param ctx the parse tree
	 */

	////////////////









	void enterBlock_return(ElsetLanguageParser.Block_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#block_return}.
	 * @param ctx the parse tree
	 */
	void exitBlock_return(ElsetLanguageParser.Block_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#block_non_return}.
	 * @param ctx the parse tree
	 */
	void enterBlock_non_return(ElsetLanguageParser.Block_non_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#block_non_return}.
	 * @param ctx the parse tree
	 */
	void exitBlock_non_return(ElsetLanguageParser.Block_non_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#signature_method_invokation}.
	 * @param ctx the parse tree
	 */
	void enterSignature_method_invokation(ElsetLanguageParser.Signature_method_invokationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#signature_method_invokation}.
	 * @param ctx the parse tree
	 */
	void exitSignature_method_invokation(ElsetLanguageParser.Signature_method_invokationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#method_invokation}.
	 * @param ctx the parse tree
	 */
	void enterMethod_invokation(ElsetLanguageParser.Method_invokationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#method_invokation}.
	 * @param ctx the parse tree
	 */
	void exitMethod_invokation(ElsetLanguageParser.Method_invokationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ElsetLanguageParser#global_program}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_program(ElsetLanguageParser.Global_programContext ctx);
	/**
	 * Exit a parse tree produced by {@link ElsetLanguageParser#global_program}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_program(ElsetLanguageParser.Global_programContext ctx);
}
