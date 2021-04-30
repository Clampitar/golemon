
package gdx.game.Interp;

import java.io.*;

import gdx.game.MyGdxGame;
import gdx.game.Player;
import gdx.game.Interp.lexer.*;
import gdx.game.Interp.node.*;
import gdx.game.Interp.parser.*;

public class Interpreter {
	
	private static String pathChange = "cutScenes/";
	
	private InterpreterEngine iEngine;
	private Start tree;
	
	public int read() {
		try {
			tree.apply(iEngine);
		} catch (InterpreterException e) {
            System.err.println("ERREUR D'INTERPRÉTATION: " + e.getMessage());
            System.exit(1);
        }
		return iEngine.frameDelay;
	}

    public Interpreter(String fileName, Player player, MyGdxGame game) {
        fileName = pathChange + fileName;
        try {
            Lexer lexer = null;
            FileReader fileReader = new FileReader(fileName);;
            PushbackReader pushbackReader = new PushbackReader(fileReader);
            try {
                lexer = new Lexer(pushbackReader);
            } catch (ExceptionInInitializerError e) {
                System.out.println("CORRUPTION DE FICHIERS: "+e.getCause());
                return;
            }
            Parser parser = new Parser(lexer);
            tree = parser.parse();
            
            SemanticInfo semantics = new SemanticInfo();
            tree.apply(new SemanticVerifierPhase1(semantics));
            tree.apply(new SemanticVerifierPhase2(semantics));
            iEngine = new InterpreterEngine(player, game, semantics);
            
        }
        catch (FileNotFoundException e) {
            System.err.println("Le fichier " + fileName + " n'a pas été trouvé.");
            System.err.println("Vouz Êtes dans = " + System.getProperty("user.dir"));
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

    }
}
