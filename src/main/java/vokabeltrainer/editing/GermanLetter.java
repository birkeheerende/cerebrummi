package vokabeltrainer.editing;

public enum GermanLetter
      implements
      Letter
{
   A(
         " 0041",
         "\u0041"),
   B(
         " 0042",
         "\u0042"),
   C(
         " 0043",
         "\u0043"),
   D(
         " 0044",
         "\u0044"),
   E(
         " 0045",
         "\u0045"),
   F(
         " 0046",
         "\u0046"),
   G(
         " 0047",
         "\u0047"),
   H(
         " 0048",
         "\u0048"),
   I(
         " 0049",
         "\u0049"),
   J(
         " 004a",
         "\u004a"),
   K(
         " 004b",
         "\u004b"),
   L(
         " 004c",
         "\u004c"),
   M(
         " 004d",
         "\u004d"),
   N(
         " 004e",
         "\u004e"),
   O(
         " 004f",
         "\u004f"),
   P(
         " 0050",
         "\u0050"),
   Q(
         " 0051",
         "\u0051"),
   R(
         " 0052",
         "\u0052"),
   S(
         " 0053",
         "\u0053"),
   T(
         " 0054",
         "\u0054"),
   U(
         " 0055",
         "\u0055"),
   V(
         " 0056",
         "\u0056"),
   W(
         " 0057",
         "\u0057"),
   X(
         " 0058",
         "\u0058"),
   Y(
         " 0059",
         "\u0059"),
   Z(
         " 005a",
         "\u005a"),
   UE(
         " 00dc",
         "\u00dc"),
   AE(
         " 00c4",
         "\u00c4"),
   OE(
         " 00d6",
         "\u00d6"),
   a(
         " 0061",
         "\u0061"),
   b(
         " 0062",
         "\u0062"),
   c(
         " 0063",
         "\u0063"),
   d(
         " 0064",
         "\u0064"),
   e(
         " 0065",
         "\u0065"),
   f(
         " 0066",
         "\u0066"),
   g(
         " 0067",
         "\u0067"),
   h(
         " 0068",
         "\u0068"),
   i(
         " 0069",
         "\u0069"
   ),
   j(
         " 006a",
         "\u006a"
   ),
   k(
         " 006b",
         "\u006b"
   ),
   l(
         " 006c",
         "\u006c"
   ),
   m(
         " 006d",
         "\u006d"
   ),
   n(
         " 006e",
         "\u006e"
   ),
   o(
         " 006f",
         "\u006f"
   ),
   p(
         " 0070",
         "\u0070"
   ),
   q(
         " 0071",
         "\u0071"
   ),
   r(
         " 0072",
         "\u0072"
   ),
   s(
         " 0073",
         "\u0073"
   ),
   t(
         " 0074",
         "\u0074"
   ),
   u(
         " 0075",
         "\u0075"
   ),
   v(
         " 0076",
         "\u0076"
   ),
   w(
         " 0077",
         "\u0077"
   ),
   x(
         " 0078",
         "\u0078"
   ),
   y(
         " 0079",
         "\u0079"
   ),
   z(
         " 007a",
         "\u007a"
   ),
   ue(
         " 00fc",
         "\u00fc"
   ),
   ae(
         " 00e4",
         "\u00e4"
   ),
   oe(
         " 00f6",
         "\u00f6"
   ),
   sz(
         " 00df",
         "\u00df"
   ),
   SPACE(
         " 0020",
         "\u0020"
   );

   private String code;
   private String unicode;

   GermanLetter(String code, String unicode)
   {
      this.code = code;
      this.unicode = unicode;
   }

   @Override
   public String getCode()
   {
      return code;
   }

   @Override
   public String getUnicode()
   {
      return unicode;
   }

   @Override
   public boolean isNewspace()
   {
      return false;
   }

   @Override
   public boolean isSpace()
   {
      return GermanLetter.SPACE == this;
   }

   @Override
   public LetterType isType()
   {
      return LetterType.GERMAN;
   }

   @Override
   public Letter getNewspace()
   {
      return null;
   }

   @Override
   public int getPixelWidth()
   {
      return -1;
   }

   @Override
   public String getTranscript()
   {
      return name();
   }
}
