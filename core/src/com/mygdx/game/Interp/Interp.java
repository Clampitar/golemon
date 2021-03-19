
package com.mygdx.game.Interp;

import java.io.*;

import com.mygdx.game.Interp.lexer.*;
import com.mygdx.game.Interp.node.*;
import com.mygdx.game.Interp.parser.*;

public class Interp {

    public static void main(
            String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java interp.Interp nomficher");
            System.exit(1);
        }

        try {
            com.mygdx.game.Interp.lexer.Lexer lexer
                    = new com.mygdx.game.Interp.lexer.Lexer(new PushbackReader(new FileReader(args[0])));
            Parser parser = new Parser(lexer);

            Start tree = parser.parse();

            tree.apply(new SemanticVerifier());

            tree.apply(new InterpreterEngine());
        }
        catch (FileNotFoundException e) {
            System.err
                    .println("Le fichier " + args[0] + " n'a pas été trouvé.");
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
