package compiler.Scanner;
import java_cup.runtime.*;
import compiler.Parser.sym;
%%

%public
%class Scanner
%standalone
%unicode
%type Symbol
%cup

%{
    StringBuffer string = new StringBuffer();
  public Symbol token(int tokenType){

      return new Symbol(tokenType, yytext());
  }
%}

    LineTerminator = \r|\n|\r\n
    InputCharacter = [^\r\n]
    WhiteSpace = {LineTerminator} | [ \t\f]

    Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
    TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"

    EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
    DocumentationComment = "/**" {CommentContent} "*"+ "/"
    CommentContent = ( [^*] | \*+ [^/*] )*

    Identifier = [:jletter:] [:jletterdigit:]*

    DecIntegerLiteral = [0-9]+
    Types = "void" | "int" | "double" | "bool" | "string"
    Boolean = "true" | "false"
    OtherReserved = "class"| "interface"| "null"| "this"| "extends"| "implements"| "for"| "while"| "if"| "else"| "return"| "break"| "continue"| "new"| "NewArray"| "Print"| "ReadInteger"| "ReadLine"| "dtoi"| "itod"| "btoi"| "itob"| "private"| "protected"| "public"
    Sign = "+"|"-"
    Punctuations = {Sign}|"*"|"/"|"%"|"<"|"<="|">"|">="|"="|"=="|"!="|"&&"|"||"|"!"|";"|","|"."|"["|"]"|"("|")"|"{"|"}"

    HexIntegerLiteral = 0(x|X)[a-fA-F0-9]+

    IntegerLiteral = {DecIntegerLiteral} | {HexIntegerLiteral}

    Numpart = [0-9]+(\.|\.[0-9])[0-9]*
    Exp = (e|E){Sign}?[0-9][0-9]*
    DoubleLiteral = {Sign}?{Numpart}{Exp}?

    StringLiteral = \"[^(\\n|\\r)]~\"

%state STRING


%%

<YYINITIAL> {



    "void"               { return token(sym.VOID); }
    "int"                { return token(sym.INT); }
    "double"             { return token(sym.DOUBLE); }
    "bool"               { return token(sym.BOOL); }
    "string"			 { return token(sym.STRING); }
    {Boolean}			 { return token(sym.T_BOOLEANLITERAL); }
    "class"              { return token(sym.CLASS);}
    "define"             { }
    "null"               { return token(sym.NULL);}
    "this"               { return token(sym.THIS);}
    "extends"            { return token(sym.EXTENDS);}
    "implements"         { return token(sym.IMPLEMENTS);}
    "for"                { return token(sym.FOR);}
    "while"              { return token(sym.WHILE);}
    "if"                 { return token(sym.IF);}
    "else"               { return token(sym.ELSE);}
    "return"             { return token(sym.RETURN);}
    "break"              { return token(sym.BREAK);}
    "continue"           { return token(sym.CONTINUE);}
    "new"                { return token(sym.NEW);}
    "NewArray"           { return token(sym.NEWARRAY);}
    "Print"              { return token(sym.PRINT);}
    "ReadInteger"        { return token(sym.READINTEGER);}
    "ReadLine"           { return token(sym.READLINE);}
    "dtoi"               { return token(sym.DTOI);}
    "itod"               { return token(sym.ITOD);}
    "btoi"               { return token(sym.BTOI);}
    "itob"               { return token(sym.ITOB);}
    "private"            { return token(sym.PRIVATE);}
    "public"             { return token(sym.PUBLIC);}


    {Identifier}          { return token(sym.T_ID);}

    {IntegerLiteral}      { return token(sym.T_INTLITERAL);}
    {DoubleLiteral}       { return token(sym.T_DOUBLELITERAL);}
    {StringLiteral}       { return token(sym.T_STRINGLITERAL); }

	"="					  { return token(sym.ASSIGN); }
    "=="				  { return token(sym.EQ); }
	"+"					  { return token(sym.PLUS); }

    {Comment}             {  }

    {WhiteSpace}          { }

	"-"					 { return token(sym.MINUS); }
	"*"					 { return token(sym.PROD); }
	"/"					 { return token(sym.DIV); }
	"%"					 { return token(sym.MOD); }
    "<"					 { return token(sym.LESS); }
    "<="				 { return token(sym.LESSEQ); }
    ">"					 { return token(sym.GT); }
    ">="				 { return token(sym.GTEQ); }
    "!="				 { return token(sym.NOTEQ); }
	"!"			    	 { return token(sym.NOT); }
	"&&"				 { return token(sym.AND); }
	"||"				 { return token(sym.OR); }
	";"					 { return token(sym.SEMI); }
	","					 { return token(sym.COMMA); }
	"."					 { return token(sym.DOT); }
	"["					 { return token(sym.LBRACK); }
	"[]"				 { return token(sym.BRACK); }
	"]"					 { return token(sym.RBRACK); }
	"("					 { return token(sym.LPAREN); }
	")"					 { return token(sym.RPAREN); }
    "{"                  {return token(sym.OPENAC);}
    "}"                  {return token(sym.CLOSEAC);}

}