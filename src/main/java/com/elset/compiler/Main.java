package com.elset.compiler;
import com.elset.compiler.core.ElsetLanguageParserV1;
import com.elset.compiler.core.ElsetLanguageVisitorImplV1;
import com.elset.compiler.core.TreeUtils;
import com.elset.compiler.gen.ElsetLanguageLexer;
import com.elset.compiler.gen.ElsetLanguageVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;
import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        try {
            ANTLRInputStream input = new ANTLRInputStream(
                    new FileInputStream("src/main/resources/test.elset"));
            ElsetLanguageLexer lexer = new ElsetLanguageLexer(input);
            ElsetLanguageParserV1 parser = new ElsetLanguageParserV1(new CommonTokenStream(lexer));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ParseTree tree = parser.global_program();
            /*CharStream inputStream = null;
                try {
                inputStream = CharStreams.fromFileName("src/main/resources/coroutine_test.elset");
                } catch (IOException e) {
                e.printStackTrace();
            }  */
            if (!byteArrayOutputStream.toString().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Lexer error: " + byteArrayOutputStream.toString());
                return;
            }
            ElsetLanguageVisitor<String> listVisitor = new ElsetLanguageVisitorImplV1(parser, "RunProgram");
            String output = listVisitor.visit(tree);

            String[] ruleNames = parser.getRuleNames();
            //System.console().writer().println(TreeUtils.toPrettyTree(tree, Arrays.asList(ruleNames)));
            System.out.print(TreeUtils.toPrettyTree(tree, Arrays.asList(ruleNames)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


////////////////////////////////////////////


/*package com.elset.compiler;

import com.elset.compiler.core.ElsetLanguageParserV1;
import com.elset.compiler.core.ElsetLanguageVisitorImplV1;
import com.elset.compiler.core.TreeUtils;
import com.elset.compiler.gen.ElsetLanguageLexer;
import com.elset.compiler.gen.ElsetLanguageVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            ANTLRInputStream input = new ANTLRInputStream(
                    new FileInputStream("src/main/resources/test.elset"));
            ElsetLanguageLexer lexer = new ElsetLanguageLexer(input);
            ElsetLanguageParserV1 parser = new ElsetLanguageParserV1(new CommonTokenStream(lexer));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ParseTree tree = parser.global_program();

            CharStream inputStream = null;
                try {
                inputStream = CharStreams.fromFileName("src/main/resources/coroutine_test.elset");
                } catch (IOException e) {
                e.printStackTrace();
            }
           /* if (!byteArrayOutputStream.toString().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Lexer error: " + byteArrayOutputStream.toString());
                return;
            }
            ElsetLanguageVisitor<String> listVisitor = new ElsetLanguageVisitorImplV1(parser, "RunProgram");
            String output = listVisitor.visit(tree);*/

            //FileWriter fileWriter = new FileWriter("RunProgram.java");
            //fileWriter.write(output);

           /* String[] ruleNames = parser.getRuleNames();;
            fileWriter.write(TreeUtils.toPrettyTree(tree, Arrays.asList(ruleNames)));
            Trees.toStringTree(tree);
            fileWriter.close();

            String[] ruleNames = parser.getRuleNames();
            Trees.toStringTree(tree);
            //System.console().writer().println(TreeUtils.toPrettyTree(tree, Arrays.asList(ruleNames)));
            System.out.print(TreeUtils.toPrettyTree(tree, Arrays.asList(ruleNames)));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}*/

//Tree prettyTree = parser.global_program();
//TreeUtils tree.toPrettyTree();
//Tree prettyTree = TreeUtils.toPrettyTree(tree);
//Tree prettyTree = (Tree) new TreeUtils();