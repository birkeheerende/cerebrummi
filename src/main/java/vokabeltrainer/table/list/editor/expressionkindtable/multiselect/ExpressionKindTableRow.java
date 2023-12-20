package vokabeltrainer.table.list.editor.expressionkindtable.multiselect;

import vokabeltrainer.types.grammatical.expressionkind.ExpressionKindItem;

public class ExpressionKindTableRow
{
   private ExpressionKindItem expressionKindItem;

   public ExpressionKindTableRow(ExpressionKindItem expressionKindItem)
   {
      this.expressionKindItem = expressionKindItem;
   }

   public ExpressionKindItem getExpressionKindItem()
   {
      return expressionKindItem;
   }

}
