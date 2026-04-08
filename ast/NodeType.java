package ast;

public enum NodeType {
    CONSTANT, //DONE
    TERNARY_OP,
    BINARY_OP, //DONE
    UNARY_OP, //DONE
    
    ABS, //DONE
    PRINT, //DONE
    SCAN, //DONE?

    IDENTIFIER, //DONE
    ASSIGNMENT,
    DECLARATION, //DONE
    DELETE, //DONE

    IF,
    WHILE,
    FOR,
    SWITCH,

    CASESTMT,
    BLOCKSTMT,
}
