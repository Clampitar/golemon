# Golemeon

## dependencies

sablecc (https://sablecc.org/downloads)

## how to run

`java -jar /path/to/sablecc.jar scene.sablecc`

build the project once

copy+paste lexer.dat and parser.dat to build/classes/java/main/gdx/game/scene/lexer and .../parser

## controls

- wasd: movement
- z: pickup item/ navigate menu
- x: enter/exit menu
- b: test battle animation
- m: test cutscenes

# Scene interpreter language

A scripting language for cutscenes. Meant to be used in tandem with a graphical program
(currently being tested with the unfinished video game Golemon)

## Functionalities

- Syntax similar to C
- Functionning loops and method calls
- native methods `frameAdvance`, `say` and `moveCam`
- basic arithmetic
- primitive types int, string and boolean

## known limitations

- cannot read other files than itself
- fixed amount of assets from the graphical program that can be manipulated
