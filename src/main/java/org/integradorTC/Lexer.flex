/* ============ SECCIÓN 1: CÓDIGO DE USUARIO ============ */
import java.io.*;
import java_cup.runtime.Symbol;

%%
/* ============ SECCIÓN 2: OPCIONES Y MACROS ============ */

/* Opciones de JFlex */
%public
%class Lexer
%cup
%line
%column

%{
    /* Métodos auxiliares para crear los tokens de CUP de forma más limpia */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

NUM = [0-9]+
TEXTO = \"[^\"]*\"
ID = [a-z][a-zA-Z0-9]*
IDCLASE = [A-Z]+

%%
/* ============ SECCIÓN 3: REGLAS LÉXICAS ============ */

/* Palabras reservadas */
"class"          { return symbol(sym.TOK_CLASS); }
"new"            { return symbol(sym.TOK_NEW); }
"this"           { return symbol(sym.TOK_THIS); }
"int"            { return symbol(sym.TOK_INT); }
"bool"           { return symbol(sym.TOK_BOOL); }
"string"         { return symbol(sym.TOK_STRING); }
"void"           { return symbol(sym.TOK_VOID); }
"return"         { return symbol(sym.TOK_RETURN); }
"if"             { return symbol(sym.TOK_IF); }
"else"           { return symbol(sym.TOK_ELSE); }
"while"          { return symbol(sym.TOK_WHILE); }
"true"           { return symbol(sym.TOK_TRUE); }
"false"          { return symbol(sym.TOK_FALSE); }
"animalprint"    { return symbol(sym.TOK_ANIMALPRINT); }

/* Símbolos - Llaves */
"{"              { return symbol(sym.TOK_LLAVEABRE); }
"}"              { return symbol(sym.TOK_LLAVECIERRA); }

/* Símbolos - Paréntesis */
"("              { return symbol(sym.TOK_PARENTABRE); }
")"              { return symbol(sym.TOK_PARENTCIERRA); }

/* Símbolos - Corchetes */
"["              { return symbol(sym.TOK_CORCHABRE); }
"]"              { return symbol(sym.TOK_CORCHIERRA); }

/* Símbolos - Comillas */
"\""             { return symbol(sym.TOK_COMILLAS); }

/* Símbolos - Operadores y puntuación */
"."              { return symbol(sym.TOK_PUNTO); }
"="              { return symbol(sym.TOK_ASIGNACION); }
"=="             { return symbol(sym.TOK_IGUAL); }
"!="             { return symbol(sym.TOK_DISTINTO); }
">"              { return symbol(sym.TOK_MAYOR); }
"<"              { return symbol(sym.TOK_MENOR); }
">>"             { return symbol(sym.TOK_MAYORIGUAL); }
"<<"             { return symbol(sym.TOK_MENORIGUAL); }
"+"              { return symbol(sym.TOK_MAS); }
"-"              { return symbol(sym.TOK_MENOS); }
"*"              { return symbol(sym.TOK_MULTIPLICA); }
"/"              { return symbol(sym.TOK_DIVIDE); }
","              { return symbol(sym.TOK_COMA); }
"!"              { return symbol(sym.TOK_FINAL); }

/* Números, texto e identificadores */
{NUM}            { return symbol(sym.TOK_NUM, yytext()); }
{TEXTO}          {
                    String texto = yytext().substring(1, yytext().length() - 1);
                    return symbol(sym.TOK_TEXTO, texto);
                 }
{ID}             { return symbol(sym.TOK_ID, yytext()); }
{IDCLASE}        { return symbol(sym.TOK_IDCLASE, yytext()); }

/* Espacios en blanco - ignorar */
[ \t\r\n\f]      { /* ignorar */ }

/* Error en token desconocido */
.                { System.err.println("Token desconocido: " + yytext()); }


