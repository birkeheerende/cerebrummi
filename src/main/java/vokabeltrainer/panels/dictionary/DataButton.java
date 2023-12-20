package vokabeltrainer.panels.dictionary;

import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import vokabeltrainer.types.Expression;

public class DataButton extends JButton
{
   private static final long serialVersionUID = 5406649403725608010L;

   private List<Expression> data = Collections.emptyList();

   private int index = -1;
   private Expression currentExpression;

   public void setData(List<Expression> data)
   {
      this.data = data;
      this.index = 0;
      if(currentExpression == null)
      {
         this.nextExpression();
      }
   }

   public DataButton()
   {

   }

   public DataButton(Icon icon)
   {
      super(icon);

   }

   public DataButton(String text)
   {
      super(text);

   }

   public DataButton(Action a)
   {
      super(a);

   }

   public DataButton(String text, Icon icon)
   {
      super(text, icon);
   }

   public Expression getIndexExpression()
   {
      if (!data.isEmpty())
      {
         return data.get(index);
      }

      return null;
   }

   public Expression getCurrentExpression()
   {
      if (!data.isEmpty())
      {
         return currentExpression;
      }
      return null;
   }

   public void nextIndex()
   {
      if (!data.isEmpty())
      {
         index = (index + 1) % data.size();
      }

   }

   public void nextExpression()
   {
      if (data.isEmpty())
      {
         currentExpression = null;
         return;
      }
      for (int i = 0; i < data.size(); i++)
      {
         if (currentExpression == data.get(i))
         {
            index = i;
            nextIndex();
            currentExpression = data.get(index);
            return;
         }
      }
      if (index < data.size())
      {
         nextIndex();
         currentExpression = data.get(index);
         return;
      }
      currentExpression = data.get(0);
   }
}
