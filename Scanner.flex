
%%

%public
%class Scanner
%standalone
%unicode



LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [\t\f]
InputCharacter = [^\r\n]

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent = ( [^*] | \*+ [^/*] )*
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

items = "+"| "-" | "*" | "/" | "%" |"<" | "<=" | ">" | ">=" | "=" | "+=" | "-=" | "*=" | "/=" | "==" | "!=" |
      "&&"  | "||"  |"!"  |";" |"," | "." | "[" |  "]" | "(" | ")" | "{" | "}"
command ="__func__"| "__line__"| "bool"| "break" | "btoi"| "class"| "continue"| "define"| "double"| "dtoi" |"else"| "for"|
         "if"| "import"| "int"| "itob"| "itod"| "new"| "NewArray"| "null"| "Print"| "private"| "public"| "ReadInteger"|
         "ReadLine"| "return"| "string"| "this"| "void"| "while"

boolean = "true"|"false"

double = ("-"|"+")?[0-9]+\.[0-9]*(("e"|"E")("-"|"+")?[0-9]+)?
hex= "0"("x"|"X")[0-9a-fA-F]+
integer = [0-9]+ | {hex}
identifier = [a-zA-Z]([a-zA-Z0-9]|"_")*
stringLiteral = \"[^]\"

%state string




%%

<YYINITIAL> {
    {double} {System.out.println("T_DOUBLELITERAL " + yytext()) ;}


    {items}    {System.out.println(yytext());}

    {command}   {System.out.println(yytext());}

    {boolean} {System.out.println("T_BOOLEANLITERAL " + yytext());}

     {hex} {System.out.println("T_INTLITERAL " + yytext()) ;}

      [a-zA-Z0-9][a-zA-Z0-9_]* {System.out.println("T_ID "+ yytext());}

      [0-9][0-9_]* {System.out.println("T_INTLITERAL " + yytext());}

      \" { yybegin(A);System.out.print("T_STRINGLITERAL " + "\""  ) ; }


    {WhiteSpace} { /* ignore */ }
    {Comment}    { /* ignore */ }
}



 <string> {
            [a-zA-Z0-9][a-zA-Z0-9_]* { System.out.print(  yytext() );}

              \" { yybegin(YYINITIAL); System.out.println("\" "); }


            }


