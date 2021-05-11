
package gdx.game.Interp;

import java.io.*;

import com.badlogic.gdx.graphics.OrthographicCamera;

import gdx.game.MyGdxGame;
import gdx.game.Player;
import gdx.game.Interp.lexer.*;
import gdx.game.Interp.node.*;
import gdx.game.Interp.parser.*;

public class Interpreter {
	
	private static String pathChange = "cutScenes/";
	
	private InterpreterEngine iEngine;
	private Start tree;
	MyGdxGame game;
	
	public int read() {
		try {
			tree.apply(iEngine);
		} catch (InterpreterException e) {
            System.err.println("ERREUR D'INTERPR�TATION: " + e.getMessage());
            game.say("ERREUR D'INTERPR�TATION");
        }
		return iEngine.frameDelay;
	}

    public Interpreter(String fileName, Player player, OrthographicCamera cam, MyGdxGame game) {
    	this.game = game;
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
            iEngine = new InterpreterEngine(player, game, cam,  semantics);
            
        }
        catch (FileNotFoundException e) {
            System.err.println("Le fichier " + fileName + " n'a pas �t� trouv�.");
            System.err.println("Vouz �tes dans = " + System.getProperty("user.dir"));
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
            System.err.println("ERREUR S�MANTIQUE: " + e.getMessage());
            game.say("ERREUR S�MANTIQUE");
        }

    }
}
