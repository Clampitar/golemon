
java -jar C:\Program_other\sablecc-3.7\sablecc-3.7\lib\sablecc.jar .\core\src\scene.sablecc
del .\core\build\classes\java\main\gdx\game\Scene\lexer\lexer.dat
move .\core\src\gdx\game\Scene\lexer\lexer.dat .\core\build\classes\java\main\gdx\game\Scene\lexer\lexer.dat
del .\core\build\classes\java\main\gdx\game\Scene\parser\parser.dat
move .\core\src\gdx\game\Scene\parser\parser.dat .\core\build\classes\java\main\gdx\game\Scene\parser\parser.dat