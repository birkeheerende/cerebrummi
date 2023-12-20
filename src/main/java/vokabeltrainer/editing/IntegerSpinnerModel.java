package vokabeltrainer.editing;

import javax.swing.AbstractSpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class IntegerSpinnerModel extends AbstractSpinnerModel
{
   private static final long serialVersionUID = -7145780527051124946L;
   
   int number = 0;
   int minimum = 0;
   int maximum = 0;

   public Object getValue()
   {
      return String.valueOf(number);

   }

   public void setValue(Object value)
   {
      if (value instanceof Integer)
      {
         int input = (int) value;
         if (minimum <= input && maximum >= input)
         {
            this.number = input;
            this.fireChangeEvent();
         }
      }

      if (value instanceof String)
      {
         if(((String) value).isEmpty())
         {
            return;
         }
         try
         {
            int input = Integer.valueOf((String) value);
            if (minimum <= input && maximum >= input)
            {
               this.number = input;
               this.fireChangeEvent();
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   public void setMinimum(int minimum)
   {
      this.minimum = minimum;
   }

   public void setMaximum(int maximum)
   {
      this.maximum = maximum;
   }

   private void fireChangeEvent()
   {
      for (ChangeListener l : super.getChangeListeners())
      {
         l.stateChanged(new ChangeEvent(this));
      }
   }

   public Object getNextValue()
   {
      int next = (number + 1) %(maximum+1);
      if (minimum <= next && maximum >= next)
      {
         this.number = next;
         this.fireChangeEvent();
      }
      
      return String.valueOf(next);
   }

   public Object getPreviousValue()
   {
      int previous = (number+maximum) %(maximum+1);
      if (minimum <= previous && maximum >= previous)
      {
         this.number = previous;
         this.fireChangeEvent();
      }
      return String.valueOf(previous);
   }
}
