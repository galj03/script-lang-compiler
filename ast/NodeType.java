package ast;

public enum NodeType {
    CONSTANT,
    TERNARY_OP,
    BINARY_OP,
    UNARY_OP,
    
    ABS,
    PRINT,
    SCAN,

    IDENTIFIER,
    ASSIGNMENT,
    DECLARATION,
    DELETE,

    IF,
    WHILE,
    FOR,
    SWITCH,

    CASESTMT,
    BLOCKSTMT,
}
