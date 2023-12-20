package vokabeltrainer.common;

import java.util.List;

import vokabeltrainer.editing.GermanLetter;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.LetterType;

public enum LetterForSaving
      implements
      Letter
{
   A,
   B,
   C,
   D,
   E,
   F,
   G,
   H,
   I,
   J,
   K,
   L,
   M,
   N,
   O,
   P,
   Q,
   R,
   S,
   T,
   U,
   V,
   W,
   X,
   Y,
   Z,
   OTHER;

   public static LetterForSaving getLetter(String german)
   {
      List<String> codeList = LetterHelper.findLetterCodes(german);
      vokabeltrainer.editing.Letter first = LetterHelper
            .getLetterFromCode(codeList.get(0), LetterType.GERMAN);
      if (first == null || !(first instanceof GermanLetter))
      {
         return LetterForSaving.OTHER;
      }
      switch ((GermanLetter) first)
      {
      case A:
         return LetterForSaving.A;
      case AE:
         return LetterForSaving.A;
      case B:
         return LetterForSaving.B;
      case C:
         return LetterForSaving.C;
      case D:
         return LetterForSaving.D;
      case E:
         return LetterForSaving.E;
      case F:
         return LetterForSaving.F;
      case G:
         return LetterForSaving.G;
      case H:
         return LetterForSaving.H;
      case I:
         return LetterForSaving.I;
      case J:
         return LetterForSaving.J;
      case K:
         return LetterForSaving.K;
      case L:
         return LetterForSaving.L;
      case M:
         return LetterForSaving.M;
      case N:
         return LetterForSaving.N;
      case O:
         return LetterForSaving.O;
      case OE:
         return LetterForSaving.O;
      case P:
         return LetterForSaving.P;
      case Q:
         return LetterForSaving.Q;
      case R:
         return LetterForSaving.R;
      case S:
         return LetterForSaving.S;
      case SPACE:
         return LetterForSaving.OTHER;
      case T:
         return LetterForSaving.T;
      case U:
         return LetterForSaving.U;
      case UE:
         return LetterForSaving.U;
      case V:
         return LetterForSaving.V;
      case W:
         return LetterForSaving.W;
      case X:
         return LetterForSaving.X;
      case Y:
         return LetterForSaving.Y;
      case Z:
         return LetterForSaving.Z;
      case a:
         return LetterForSaving.A;
      case ae:
         return LetterForSaving.A;
      case b:
         return LetterForSaving.B;
      case c:
         return LetterForSaving.C;
      case d:
         return LetterForSaving.D;
      case e:
         return LetterForSaving.E;
      case f:
         return LetterForSaving.F;
      case g:
         return LetterForSaving.G;
      case h:
         return LetterForSaving.H;
      case i:
         return LetterForSaving.I;
      case j:
         return LetterForSaving.J;
      case k:
         return LetterForSaving.K;
      case l:
         return LetterForSaving.L;
      case m:
         return LetterForSaving.M;
      case n:
         return LetterForSaving.N;
      case o:
         return LetterForSaving.O;
      case oe:
         return LetterForSaving.O;
      case p:
         return LetterForSaving.P;
      case q:
         return LetterForSaving.Q;
      case r:
         return LetterForSaving.R;
      case s:
         return LetterForSaving.S;
      case sz:
         return LetterForSaving.OTHER;
      case t:
         return LetterForSaving.T;
      case u:
         return LetterForSaving.U;
      case ue:
         return LetterForSaving.U;
      case v:
         return LetterForSaving.V;
      case w:
         return LetterForSaving.W;
      case x:
         return LetterForSaving.X;
      case y:
         return LetterForSaving.Y;
      case z:
         return LetterForSaving.Z;
      default:
         return LetterForSaving.OTHER;
      }
   }
}
