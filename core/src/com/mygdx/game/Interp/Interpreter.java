
package com.mygdx.game.Interp;

import java.io.*;

import com.mygdx.game.Interp.lexer.*;
import com.mygdx.game.Interp.node.*;
import com.mygdx.game.Interp.parser.*;

public class Interpreter {
	
	private static String pathChange = "../core/assets/cutScenes/";

    public void interpret(String fileName) {
        fileName = pathChange + fileName;
        try {
            Lexer lexer = null;
            FileReader fileReader = new FileReader(fileName);;
            PushbackReader pushbackReader = new PushbackReader(fileReader);
            try {
                lexer = new Lexer(pushbackReader);
            } catch (ExceptionInInitializerError e) {
                System.out.println("CORRUPTION DE FICHIERS: " + pushbackReader.ready() + " and "+ lexer +" found at "+e.getCause());
                return;
            }
            Parser parser = new Parser(lexer);
            Start tree = parser.parse();
            tree.apply(new SemanticVerifier());
            tree.apply(new InterpreterEngine());
        }
        catch (FileNotFoundException e) {
            System.err.println("Le fichier " + fileName + " n'a pas �t� trouv�.");
            System.err.println("Vouz �tes dans = " + System.getProperty("user.dir"));
            System.exit(1);
        }
        catch (ParserException e) {
            System.err.println("ERREUR DE SYNTAXE: " + e.getMessage());
            System.exit(1);
        }
        catch (LexerException e) {
            System.err.println("ERREUR LEXICALE: " + e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("ERREUR DE E/S: " + e.getMessage());
            System.exit(1);
        }
        catch (SemanticException e) {
            System.err.println("ERREUR SÉMANTIQUE: " + e.getMessage());
            System.exit(1);
        }
        catch (InterpreterException e) {
            System.err.println("ERREUR D'INTERPRÉTATION: " + e.getMessage());
            System.exit(1);
        }

    }
}
