package vokabeltrainer.types.grammatical.expressionkind;

public class ExpressionKindItem
{
   private ExpressionKind kind;
   private boolean selected;
   
   public ExpressionKindItem(ExpressionKind kind)
   {
      this.kind = kind;
   }
   
   public ExpressionKindItem(ExpressionKind kind, boolean selected)
   {
      this.kind = kind;
      this.selected = selected;
   }

   public boolean isSelected()
   {
      return selected;
   }

   public void setSelected(boolean selected)
   {
      this.selected = selected;
   }

   public void toggleSelected()
   {
      this.selected = !this.selected;
   }

   public ExpressionKind getKind()
   {
      return kind;
   }
}
