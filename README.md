# Script Lang Compiler - An ANTLR-based compiler for a custom script language

This project was created during the Compilers course that I took at the University of Szeged.

It is an ANTLR-based compiler for a script language.
It contains a grammar file with both the lexer and parser, and a custom AST representation for the code which is built during parsing,
and is used for the execution of the input program's statements.

## Funcionalities

Syntactic elements include:
- `;` - semicolons must be used at the end of all statements
- `print(expr)` - prints the value of the given expression
- `scan(expr)` - reads a user input and stores it in the given value
- `abs(expr)` - absolute value of the given expression
- block statements
- `if-elseif-else` conditions
- `for` and `while` loops
- `switch` statement
- variables - `int` and `double` types only
- variable declaration, assigment
- `del <var>` - deletes the variable named `<var>`
- ternary operator - they cannot be nested currently
- `==`, `!=` - equality operators
- `<`, `>`, `<=`, `>=`  - relational operators
- `+`, `-`, `*`, `/` - arithmetic operators
- `#` - one-line comments

## How to use

### Requirements

- ANTLR 4.11.1
- Java JDK 13+

### Execution

#### Linux

Execute these commands from the project root:
```
java -jar antlr-4.11.1-complete.jar ScriptLang.g4
javac -cp .:antlr-4.11.1-complete.jar *.java
java -cp .:antlr-4.11.1-complete.jar ScriptLangParser <path-to-your-input-file>
```

#### Windows

Execute these commands from the project root:
```
java -jar antlr-4.11.1-complete.jar ScriptLang.g4
javac -cp ".;antlr-4.11.1-complete.jar" *.java
java -cp ".;antlr-4.11.1-complete.jar" ScriptLangParser <path-to-your-input-file>
```
