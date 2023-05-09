
package gdx.game.Scene;

import java.io.*;
import gdx.game.Scene.Interpreter.*;
import gdx.game.Scene.Semantics.*;
import gdx.game.Scene.lexer.*;
import gdx.game.Scene.node.*;
import gdx.game.Scene.parser.*;

public class SceneReader {
	
	private static String pathChange = "cutScenes/";
	
	private InterpreterEngine iEngine;
	private Start tree;
	ScenePlayer game;
    SceneThread sceneThread;

    public void start(){
        sceneThread.start();
    }
	
	protected void read() {
		try {
			tree.apply(iEngine);
		} catch (InterpreterException e) {
            System.err.println("ERREUR D'INTERPRETATION: " + e.getMessage());
            game.say("ERREUR D'INTERPRETATION");
        }
		return;
	}

    public boolean spendFrame(){
        sceneThread.interrupt();
        return !sceneThread.isAlive();
    }



    public SceneReader(String fileName, ScenePlayer game) {
    	this.game = game;
        fileName = pathChange + fileName;
        sceneThread = new SceneThread(this);
        try {
            Lexer lexer = null;
            FileReader fileReader = new FileReader(fileName);;
            PushbackReader pushbackReader = new PushbackReader(fileReader);
            try {
                String userDir = System.getProperty("user.dir");
                lexer = new Lexer(pushbackReader);
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
