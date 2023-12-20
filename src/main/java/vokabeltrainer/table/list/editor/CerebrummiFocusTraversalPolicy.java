package vokabeltrainer.table.list.editor;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CerebrummiFocusTraversalPolicy extends FocusTraversalPolicy
{

   int focusNumber = 0;
   private List<Component> focusList;
   private Map<Component, Integer> focusMap;

   public CerebrummiFocusTraversalPolicy(Component[] focusList)
   {
      this.focusList = new ArrayList<Component>(Arrays.asList(focusList));
      this.focusMap = new HashMap<>();
      for(int i=0; i < focusList.length; i++)
      {
         focusMap.put(this.focusList.get(i), i);
      }
   }

   @Override
   public Component getComponentAfter(Container focusCycleRoot,
         Component aComponent)
   {
      Integer componentNumber = focusMap.get(aComponent);
      if(componentNumber != null && componentNumber >= 0 && componentNumber < focusList.size())
      {
         int focusNumber = (componentNumber + 1) % focusList.size();
         return focusList.get(focusNumber);
      }
      return this.getFirstComponent(focusCycleRoot);
   }

   @Override
   public Component getComponentBefore(Container focusCycleRoot,
         Component aComponent)
   {
      Integer componentNumber = focusMap.get(aComponent);
      if(componentNumber != null && componentNumber >= 0 && componentNumber < focusList.size())
      {
         int focusNumber = (focusList.size() + componentNumber - 1) % focusList.size();
         return focusList.get(focusNumber);
      }
      return this.getLastComponent(focusCycleRoot);
   }

   @Override
   public Component getDefaultComponent(Container focusCycleRoot)
   {
      return getFirstComponent(focusCycleRoot);
   }

   @Override
   public Component getLastComponent(Container focusCycleRoot)
   {
      return focusList.get(focusList.size()-1);
   }

   @Override
   public Component getFirstComponent(Container focusCycleRoot)
   {
      return focusList.get(0);
   }
}