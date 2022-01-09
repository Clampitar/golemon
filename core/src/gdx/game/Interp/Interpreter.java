
package gdx.game.Interp;

import java.io.*;
import java.rmi.ServerError;

import gdx.game.ScenePlayer;
import gdx.game.Interp.lexer.*;
import gdx.game.Interp.node.*;
import gdx.game.Interp.parser.*;

public class Interpreter {
	
	private static String pathChange = "cutScenes/";
	
	private InterpreterEngine iEngine;
	private Start tree;
	ScenePlayer game;
	
	public int read() {
		try {
			tree.apply(iEngine);
		} catch (InterpreterException e) {
            System.err.println("ERREUR D'INTERPRETATION: " + e.getMessage());
            game.say("ERREUR D'INTERPRETATION");
        }
		return iEngine.frameDelay;
	}

    public Interpreter(String fileName, ScenePlayer game) {
    	this.game = game;
        fileName = pathChange + fileName;
        try {
            Lexer lexer = null;
            FileReader fileReader = new FileReader(fileName);;
            PushbackReader pushbackReader = new PushbackReader(fileReader);
            try {
                String userDir = System.getProperty("user.dir");
              //  System.setProperty("user.dir", "C:\\Git\\golemon\\core\\src");
                lexer = new Lexer(pushbackReader);
              //  System.setProperty("user.dir", "C:\\Git\\golemon\\core\\assets");
            } catch (ExceptionInInitializerError e) {
                System.out.println("CORRUPTION DE FICHIERS: "+e.getCause());
                System.out.println("You are at "+System.getProperty("user.dir")
                                    +" going for "+fileName);
            }
            Parser parser = new Parser(lexer);
            tree = parser.parse();
            
            SemanticInfo semantics = new SemanticInfo();
            tree.apply(new SemanticVerifierPhase1(semantics));
            tree.apply(new SemanticVerifierPhase2(semantics));
            iEngine = new InterpreterEngine(game, semantics);
            
        }
        catch (FileNotFoundException e) {
            System.err.println("Le fichier " + fileName + " n'a pas été trouvé.");
            System.err.println("Vouz étes dans = " + System.getProperty("user.dir"));
            System.exit(1);
        }
        catch (ParserException e) {
            System.err.println("ERREUR DE SYNTAXE: " + e.getMessage());
            game.say("ERREUR DE SYNTAXE");
        }
        catch (LexerException e) {
            System.err.println("ERREUR LEXICALE: " + e.getMessage());
            game.say("ERREUR LEXICALE");
        }
        catch (IOException e) {
            System.err.println("ERREUR DE E/S: " + e.getMessage());
            game.say("ERREUR DE E/S");
        }
        catch (SemanticException e) {
            System.err.println("ERREUR SEMANTIQUE: " + e.getMessage());
            game.say("ERREUR SÉMANTIQUE");
        }

    }
}
