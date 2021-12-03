import java_cup.runtime.*;

%%
%public
%class Scanner
%standalone
%unicode
%cup
%type Symbol

%{
    StringBuilder string;
    private Symbol token(int tokenType) {
//        System.out.println(yytext());
        return new Symbol(tokenType);
    }
    private Symbol token(int tokenType, Object value) {
        return new Symbol(tokenType, value);
    }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [\t\f]
InputCharacter = [^\r\n]

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent = ( [^*] | \*+ [^/*] )*
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

double = ("-"|"+")?[0-9]+\.[0-9]*(("e"|"E")("-"|"+")?[0-9]+)?
hex= "0"("x"|"X")[0-9a-fA-F]+
integer = [0-9]+ | {hex}
ID = [a-zA-Z]([a-zA-Z0-9]|"_")*


%state STRING

%%

<YYINITIAL>{
                "__func__"          { return token(sym.__FUNC__);}
                "__line__"          { return token(sym.__LINE__);}
                "bool"              { return token(sym.BOOL); }
                "break"             { return token(sym.BREAK); }
                "btoi"              { return token(sym.BTOI); }
                "class"             { return token(sym.CLASS); }
                "continue"          { return token(sym.CONTINUE); }
                "define"            { return token(sym.DEFINE); }
                "double"            { return token(sym.DOUBLE); }
                "dtoi"              { return token(sym.DTOI); }
                "else"              { return token(sym.ELSE); }
                "for"               { return token(sym.FOR); }
                "if"                { return token(sym.IF); }
                "import"            { return token(sym.IMPORT); }
                "int"               { return token(sym.INT); }
                "itob"              { return token(sym.ITOB); }
                "itod"              { return token(sym.ITOD); }
                "new"               { return token(sym.NEW); }
                "NewArray"          { return token(sym.NEWARRAY); }
                "null"              { return token(sym.NULL); }
                "Print"             { return token(sym.PRINT); }
                "private"           { return token(sym.PRIVATE); }
                "public"            { return token(sym.PUBLIC); }
                "ReadInteger"       { return token(sym.READINTEGER); }
                "ReadLine"          { return token(sym.READLINE); }
                "return"            { return token(sym.RETURN); }
                "string"            { return token(sym.STRING); }
                "this"              { return token(sym.THIS); }
                "void"              { return token(sym.VOID); }
                "while"             { return token(sym.WHILE); }
                "true"              { return token(sym.TRUE); }
                "false"             { return token(sym.FALSE); }
                {ID}                { return token(sym.IDENT); }
                {integer}           { return token(sym.INTLITERAL); }
                {double}            { return token(sym.DOUBLELITERAL); }
                "+"					{ return token(sym.PLUS); }
                "-"					{ return token(sym.MINUS); }
                "*"					{ return token(sym.PROD); }
                "/"					{ return token(sym.DIV); }
                "%"					{ return token(sym.MOD); }
                "<"					{ return token(sym.LT); }
                "<="				{ return token(sym.LE); }
                ">"					{ return token(sym.GT); }
                ">="				{ return token(sym.GE); }
                "="					{ return token(sym.ASSIGN); }
                "+="                { return token(sym.PLUSASSIGN); }
                "-="                { return token(sym.MINUSASSIGN); }
                "*="                { return token(sym.MULTIPLYASSIGN); }
                "/="                { return token(sym.DIVIDEASSIGN); }
                "=="				{ return token(sym.EQ); }
                "!="				{ return token(sym.NE); }
                "&&"			    { return token(sym.AND); }
                "||"				{ return token(sym.OR); }
                "!"					{ return token(sym.NOT); }
                ";"					{ return token(sym.SEMICOLON); }
                ","					{ return token(sym.COMMA); }
                "."					{ return token(sym.DOT); }
                "["					{ return token(sym.LBRACK); }
                "]"					{ return token(sym.RBRACK); }
                "("					{ return token(sym.LPAREN); }
                ")"					{ return token(sym.RPAREN); }
                "{"					{ return token(sym.LCURLY); }
                "}"					{ return token(sym.RCURLY); }

                {Comment}           {/* ignore */ }
                {WhiteSpace}        { /* ignore */}
                \"                  { string = new StringBuilder("\""); yybegin(STRING); }
}
<STRING> {
    \" {
        string.append("\"");
	    yybegin(YYINITIAL);
	    return token(sym.STRINGLITERAL, string);
    }
    [^\n\r\"\\]+    { string.append(yytext()); }
    \\t             { string.append('\t'); }
    \\              { string.append('\\'); }
    \\\'            { string.append('\''); }
    \\\"            { string.append('\"'); }
    \\b             { string.append('\b'); }
    \\f             { string.append('\f'); }
    \\0             { string.append('\0'); }
    }
