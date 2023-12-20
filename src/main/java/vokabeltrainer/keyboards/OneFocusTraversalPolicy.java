package vokabeltrainer.keyboards;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;

public class OneFocusTraversalPolicy extends FocusTraversalPolicy
{
   Component component;
   
   public OneFocusTraversalPolicy(Component component)
   {
      this.component = component;
   }

   @Override
   public Component getComponentAfter(Container aContainer,
         Component aComponent)
   {
      return component;
   }

   @Override
   public Component getComponentBefore(Container aContainer,
         Component aComponent)
   {
      return component;
   }

   @Override
   public Component getFirstComponent(Container aContainer)
   {
      return component;
   }

   @Override
   public Component getLastComponent(Container aContainer)
   {
      return component;
   }

   @Override
   public Component getDefaultComponent(Container aContainer)
   {
      return component;
   }

}
