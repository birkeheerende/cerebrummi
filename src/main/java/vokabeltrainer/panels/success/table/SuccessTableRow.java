package vokabeltrainer.panels.success.table;

import vokabeltrainer.types.Expression;

public class SuccessTableRow
{
   private Expression expression;

   public SuccessTableRow(Expression expression)
   {
      this.expression = expression;
   }

   public Expression getExpression()
   {
      return expression;
   }
}
