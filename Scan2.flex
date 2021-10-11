
%%

%public
%class MainlexAnalysis
%standalone

%unicode

%{
    String name;

%}
esm = "(" | ")" | "{" | "}" | "+" |"-"| "*"|"%"|">="|"<="|"=="|"!="|"="|"&&"|"||"|"!"|";"|","|"["|"]"|"."|"<"|">"
// + - * / % < <= > >= = == != && || ! ; , . [ ] ( )
//neg = "-"+[0-9][0-9]*
//pos = "+"+[0-9][0-9]*
command =  "class"|"interface"| "null"| "this"| "extends"| "implements" |"for"|"this"|"while"|"if"|"else"|"return"|"break"|"continue"|"new"|"NewArray"|"Print"|"ReadInteger"|"ReadLine"|"dtoi"|"itod"|"btoi"|"itob"|"private"|"protected"|"public"
//  for, while,  return, break, continue, new, NewArray, Print, ReadInteger, ReadLine, dtoi, itod, btoi, itob, private, protected, public


LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
funct_type = "int" | "bool" | "void" | "double" | "string" | "object"
booleanlit = "false" | "true"
//stringlit = " \" "
 Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
 TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
 EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
 DocumentationComment = "/**" {CommentContent} "*"+ "/"
 CommentContent = ( [^*] | \*+ [^/*] )*
 InputCharacter = [^\r\n]

hexa = "0x"+[a-fA-F0-9][a-fA-F0-9_]* | "0X"+[a-fA-F0-9][a-fA-F0-9_]*

float = ("-" | "+")?[0-9][0-9]*\.([0-9]*("e" | "E")?("-" | "+")?[0-9]+)*



%state A




%%

//"name " [a-zA-Z]+   { name = yytext().substring(5);}
//[Hh] "ello"         {System.out.println(yytext()+" "+name+"!");}



<YYINITIAL> {
    {float} {System.out.println("T_DOUBLELITERAL " + yytext()) ;}


    {esm}    {System.out.println(yytext());}

    {command}   {System.out.println(yytext());}

    {funct_type} {System.out.println(yytext());}
    {booleanlit} {System.out.println("T_BOOLEANLITERAL " + yytext());}

    {hexa} {System.out.println("T_INTLITERAL " + yytext()) ;}
   // {float} {System.out.println("T_DOUBLELITERAL " + yytext()) ;}


    [0-9][0-9_]* {System.out.println("T_INTLITERAL " + yytext());}



    {WhiteSpace} { /* ignore */ }
    {Comment}    { /* ignore */ }

    \" { yybegin(A);System.out.print("T_STRINGLITERAL " + "\""  ) ; }

    [a-zA-Z0-9][a-zA-Z0-9_]* {System.out.println("T_ID "+ yytext());}

}


       // <A> [a-zA-Z0-9][a-zA-Z0-9_]* {yybegin(B); System.out.print(  yytext() );}

            <A> {
                    [a-zA-Z0-9][a-zA-Z0-9_]* { System.out.print(  yytext() );}

                    \" { yybegin(YYINITIAL); System.out.println("\" "); }


                }


