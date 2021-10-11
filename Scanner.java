// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: Scanner.flex



// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int string = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1, 1
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\u10ff\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\0\1\1\1\3\23\0\1\4"+
    "\1\5\2\0\1\6\1\7\1\0\2\6\1\10\1\11"+
    "\1\6\1\11\1\12\1\13\1\14\11\15\1\0\1\6"+
    "\1\4\1\16\1\4\2\0\1\17\3\20\1\21\1\20"+
    "\2\22\1\23\2\22\1\24\1\22\1\25\1\22\1\26"+
    "\1\22\1\27\5\22\1\30\2\22\1\6\1\0\1\6"+
    "\1\0\1\31\1\0\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\22\1\43\1\44\1\45"+
    "\1\46\1\47\1\50\1\22\1\51\1\52\1\53\1\54"+
    "\1\55\1\56\1\30\1\57\1\22\1\6\1\60\1\6"+
    "\u0182\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[512];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\3\1\4\1\3\1\1\2\3"+
    "\2\5\4\6\1\1\15\6\1\1\1\7\2\0\1\2"+
    "\1\10\1\0\3\6\1\0\12\6\1\3\15\6\3\0"+
    "\1\5\3\6\2\0\23\6\1\0\1\2\1\0\1\10"+
    "\2\6\2\0\10\6\1\11\2\6\2\0\7\6\1\0"+
    "\3\6\1\0\2\6";

  private static int [] zzUnpackAction() {
    int [] result = new int[129];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\61\0\142\0\142\0\223\0\304\0\142\0\142"+
    "\0\365\0\u0126\0\u0157\0\u0188\0\u01b9\0\u01ea\0\u021b\0\u024c"+
    "\0\u027d\0\u02ae\0\u02df\0\u0310\0\u0341\0\u0372\0\u03a3\0\u03d4"+
    "\0\u0405\0\u0436\0\u0467\0\u0498\0\u04c9\0\u04fa\0\u052b\0\u055c"+
    "\0\u058d\0\u05be\0\u05ef\0\u0620\0\u0651\0\u0682\0\u06b3\0\u06e4"+
    "\0\u0715\0\u0746\0\u0777\0\u07a8\0\u07d9\0\u080a\0\u083b\0\u086c"+
    "\0\u089d\0\u08ce\0\u08ff\0\u0930\0\u01ea\0\u0961\0\u0992\0\u09c3"+
    "\0\u09f4\0\u0a25\0\u0a56\0\u0a87\0\u0ab8\0\u0ae9\0\u0b1a\0\u0b4b"+
    "\0\u0b7c\0\u0bad\0\u0bde\0\u0c0f\0\u0c40\0\u0682\0\u0c71\0\u0ca2"+
    "\0\u0cd3\0\u0d04\0\u0d35\0\u0d66\0\u0d97\0\u0dc8\0\u0df9\0\u0e2a"+
    "\0\u0e5b\0\u0e8c\0\u0ebd\0\u0eee\0\u0f1f\0\u0f50\0\u0f81\0\u0fb2"+
    "\0\u0fe3\0\u1014\0\u1045\0\u1076\0\u10a7\0\u10d8\0\u1109\0\u0bde"+
    "\0\u113a\0\u113a\0\u116b\0\u119c\0\u11cd\0\u11fe\0\u122f\0\u1260"+
    "\0\u1291\0\u12c2\0\u12f3\0\u1324\0\u1355\0\u1386\0\u01ea\0\u13b7"+
    "\0\u13e8\0\u1419\0\u144a\0\u147b\0\u14ac\0\u14dd\0\u150e\0\u153f"+
    "\0\u1570\0\u15a1\0\u15d2\0\u1603\0\u1634\0\u1665\0\u1696\0\u16c7"+
    "\0\u16f8";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[129];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\2\4\1\5\1\6\1\7\1\10\1\11\1\6"+
    "\1\12\1\10\1\13\1\14\1\15\1\6\6\16\1\17"+
    "\1\20\1\21\1\16\1\22\1\16\1\23\1\24\1\25"+
    "\1\26\1\27\2\16\1\30\3\16\1\31\1\16\1\32"+
    "\1\33\1\34\1\35\1\16\1\36\1\37\1\16\1\40"+
    "\5\3\1\7\6\3\2\41\1\3\12\41\1\3\26\41"+
    "\1\3\63\0\1\4\74\0\1\10\51\0\1\10\65\0"+
    "\2\42\1\10\52\0\1\43\2\0\1\44\2\0\1\10"+
    "\54\0\1\45\1\0\2\15\12\0\1\46\42\0\1\45"+
    "\1\0\2\15\57\0\2\16\1\0\41\16\15\0\2\16"+
    "\1\0\17\16\1\47\21\16\15\0\2\16\1\0\32\16"+
    "\1\50\6\16\15\0\2\16\1\0\17\16\1\51\21\16"+
    "\32\0\1\52\43\0\2\16\1\0\30\16\1\53\1\16"+
    "\1\54\1\16\1\55\4\16\15\0\2\16\1\0\25\16"+
    "\1\56\2\16\1\57\10\16\15\0\2\16\1\0\17\16"+
    "\1\60\10\16\1\61\3\16\1\55\4\16\15\0\2\16"+
    "\1\0\25\16\1\62\13\16\15\0\2\16\1\0\13\16"+
    "\1\63\14\16\1\64\10\16\15\0\2\16\1\0\20\16"+
    "\1\65\5\16\1\66\1\67\4\16\1\70\4\16\15\0"+
    "\2\16\1\0\17\16\1\71\15\16\1\72\3\16\15\0"+
    "\2\16\1\0\32\16\1\73\2\16\1\74\3\16\15\0"+
    "\2\16\1\0\17\16\1\75\21\16\15\0\2\16\1\0"+
    "\34\16\1\76\4\16\15\0\2\16\1\0\22\16\1\77"+
    "\7\16\1\100\6\16\15\0\2\16\1\0\30\16\1\101"+
    "\10\16\15\0\2\16\1\0\22\16\1\102\16\16\61\0"+
    "\1\10\14\0\2\41\1\0\12\41\1\0\26\41\13\0"+
    "\1\45\1\0\2\42\43\0\10\103\1\104\50\103\2\44"+
    "\1\4\1\5\55\44\14\0\2\45\3\0\1\105\14\0"+
    "\1\105\36\0\2\106\1\0\3\106\10\0\6\106\35\0"+
    "\2\16\1\0\37\16\1\107\1\16\15\0\2\16\1\0"+
    "\23\16\1\110\15\16\15\0\2\16\1\0\13\16\1\111"+
    "\25\16\40\0\1\112\4\0\1\113\30\0\2\16\1\0"+
    "\30\16\1\114\10\16\15\0\2\16\1\0\17\16\1\115"+
    "\21\16\15\0\2\16\1\0\30\16\1\116\10\16\15\0"+
    "\2\16\1\0\13\16\1\117\25\16\15\0\2\16\1\0"+
    "\27\16\1\120\11\16\15\0\2\16\1\0\20\16\1\121"+
    "\20\16\15\0\2\16\1\0\35\16\1\122\3\16\15\0"+
    "\2\16\1\0\33\16\1\123\5\16\15\0\2\16\1\0"+
    "\25\16\1\124\13\16\15\0\2\16\1\0\32\16\1\65"+
    "\6\16\15\0\2\16\1\0\31\16\1\125\7\16\15\0"+
    "\2\16\1\0\34\16\1\65\4\16\15\0\2\16\1\0"+
    "\30\16\1\126\10\16\15\0\2\16\1\0\37\16\1\65"+
    "\1\16\15\0\2\16\1\0\25\16\1\114\13\16\15\0"+
    "\2\16\1\0\23\16\1\127\15\16\15\0\2\16\1\0"+
    "\14\16\1\130\24\16\15\0\2\16\1\0\34\16\1\131"+
    "\4\16\15\0\2\16\1\0\32\16\1\132\6\16\15\0"+
    "\2\16\1\0\23\16\1\133\15\16\15\0\2\16\1\0"+
    "\35\16\1\134\3\16\15\0\2\16\1\0\23\16\1\135"+
    "\15\16\15\0\2\16\1\0\23\16\1\136\15\16\1\0"+
    "\10\103\1\137\60\103\1\137\2\103\1\140\45\103\11\0"+
    "\1\141\2\0\2\142\57\0\2\16\1\0\1\143\40\16"+
    "\15\0\2\16\1\0\27\16\1\67\11\16\15\0\2\16"+
    "\1\0\16\16\1\144\22\16\55\0\1\145\46\0\1\146"+
    "\32\0\2\16\1\0\25\16\1\65\13\16\15\0\2\16"+
    "\1\0\13\16\1\147\25\16\15\0\2\16\1\0\23\16"+
    "\1\65\15\16\15\0\2\16\1\0\33\16\1\133\5\16"+
    "\15\0\2\16\1\0\34\16\1\150\4\16\15\0\2\16"+
    "\1\0\23\16\1\151\15\16\15\0\2\16\1\0\14\16"+
    "\1\136\24\16\15\0\2\16\1\0\17\16\1\65\21\16"+
    "\15\0\2\16\1\0\33\16\1\134\5\16\15\0\2\16"+
    "\1\0\30\16\1\152\10\16\15\0\2\16\1\0\14\16"+
    "\1\65\1\16\1\65\22\16\15\0\2\16\1\0\36\16"+
    "\1\153\2\16\15\0\2\16\1\0\25\16\1\154\13\16"+
    "\15\0\2\16\1\0\35\16\1\155\3\16\15\0\2\16"+
    "\1\0\23\16\1\156\15\16\15\0\2\16\1\0\33\16"+
    "\1\65\5\16\15\0\2\16\1\0\17\16\1\157\21\16"+
    "\15\0\2\16\1\0\16\16\1\65\22\16\15\0\2\16"+
    "\1\0\25\16\1\123\13\16\1\0\10\103\1\137\2\103"+
    "\1\4\45\103\14\0\2\142\57\0\2\16\1\0\32\16"+
    "\1\160\6\16\15\0\2\16\1\0\4\16\1\161\1\121"+
    "\33\16\47\0\1\162\60\0\1\163\26\0\2\16\1\0"+
    "\24\16\1\65\14\16\15\0\2\16\1\0\23\16\1\164"+
    "\15\16\15\0\2\16\1\0\27\16\1\123\11\16\15\0"+
    "\2\16\1\0\32\16\1\67\6\16\15\0\2\16\1\0"+
    "\13\16\1\165\25\16\15\0\2\16\1\0\23\16\1\166"+
    "\15\16\15\0\2\16\1\0\32\16\1\167\6\16\15\0"+
    "\2\16\1\0\27\16\1\170\11\16\15\0\2\16\1\0"+
    "\32\16\1\171\6\16\15\0\2\16\1\0\27\16\1\172"+
    "\11\16\35\0\1\173\62\0\1\173\36\0\2\16\1\0"+
    "\27\16\1\174\11\16\15\0\2\16\1\0\34\16\1\123"+
    "\4\16\15\0\2\16\1\0\15\16\1\65\23\16\15\0"+
    "\2\16\1\0\27\16\1\65\11\16\15\0\2\16\1\0"+
    "\21\16\1\65\17\16\15\0\2\16\1\0\13\16\1\175"+
    "\25\16\15\0\2\16\1\0\34\16\1\176\4\16\32\0"+
    "\1\177\43\0\2\16\1\0\35\16\1\123\3\16\15\0"+
    "\2\16\1\0\40\16\1\65\15\0\2\16\1\0\17\16"+
    "\1\200\21\16\32\0\1\10\43\0\2\16\1\0\21\16"+
    "\1\201\17\16\15\0\2\16\1\0\17\16\1\64\21\16"+
    "\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5929];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\2\11\2\1\2\11\31\1\2\0\2\1\1\0"+
    "\3\1\1\0\30\1\3\0\4\1\2\0\23\1\1\0"+
    "\1\1\1\0\3\1\2\0\13\1\2\0\7\1\1\0"+
    "\3\1\1\0\2\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[129];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return YYEOF;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { System.out.print(yytext());
            }
            // fall through
          case 10: break;
          case 2:
            { /* ignore */
            }
            // fall through
          case 11: break;
          case 3:
            { System.out.println(yytext());
            }
            // fall through
          case 12: break;
          case 4:
            { yybegin(string);System.out.print("T_STRINGLITERAL " + "\"" );
            }
            // fall through
          case 13: break;
          case 5:
            { System.out.println("T_INTLITERAL " + yytext()) ;
            }
            // fall through
          case 14: break;
          case 6:
            { System.out.println("T_ID "+ yytext());
            }
            // fall through
          case 15: break;
          case 7:
            { System.out.print(  yytext() );
            }
            // fall through
          case 16: break;
          case 8:
            { System.out.println("T_DOUBLELITERAL " + yytext()) ;
            }
            // fall through
          case 17: break;
          case 9:
            { System.out.println("T_BOOLEANLITERAL " + yytext());
            }
            // fall through
          case 18: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String[] argv) {
    if (argv.length == 0) {
      System.out.println("Usage : java Scanner [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          // Side-effect: is encodingName valid?
          java.nio.charset.Charset.forName(encodingName);
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        Scanner scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new Scanner(reader);
          while ( !scanner.zzAtEOF ) scanner.yylex();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
