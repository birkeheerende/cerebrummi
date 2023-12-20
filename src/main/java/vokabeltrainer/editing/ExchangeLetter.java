package vokabeltrainer.editing;

public enum ExchangeLetter
{
   SSIN(
         "\uFb2B",
         " Fb2B");
   
   private String unicode;
   private String code;
   
   ExchangeLetter (String unicode, String code)
   {
      this.unicode = unicode;
      this.code = code;
   }

   public String getUnicode()
   {
      return unicode;
   }

   public void setUnicode(String unicode)
   {
      this.unicode = unicode;
   }

   public String getCode()
   {
      return code;
   }

   public void setCode(String code)
   {
      this.code = code;
   }
}
