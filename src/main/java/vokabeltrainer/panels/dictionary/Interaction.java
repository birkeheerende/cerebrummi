package vokabeltrainer.panels.dictionary;

import java.util.HashMap;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.notifications.ErrorNotification;
import vokabeltrainer.panels.translation.Translation;

public class Interaction
{ 
   private static HashMap<Interaction, Command> interactions;
   
   static
   {
      Interaction.interactions = new HashMap<>();
      
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.CHAPTER_WHICH), Command.RESTORE_WHICH_CHAPTER);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.OPENED_PAGE), Command.NO_TABLE);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.SEARCH_WHICH_GERMAN), Command.RESTORE_WHICH_SEARCH_GERMAN);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.SEARCH_WHICH_HEBREW), Command.RESTORE_WHICH_SEARCH_HEBREW);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.TAB_CHAPTER), Command.NO_TABLE);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.TAB_EXPRESSIONKIND), Command.NO_TABLE);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.TAB_SEARCH), Command.NO_TABLE);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      
      interactions.put(new Interaction(Action.GERMAN_TO_HEBREW, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.HEBREW_TO_GERMAN, Status.OPENED_PAGE), Command.NOTHING);
 
      interactions.put(new Interaction(Action.GERMAN_TO_HEBREW, Status.TAB_CHAPTER), Command.NO_TABLE);
      interactions.put(new Interaction(Action.HEBREW_TO_GERMAN, Status.TAB_CHAPTER), Command.NO_TABLE);
      
      interactions.put(new Interaction(Action.GERMAN_TO_HEBREW, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.HEBREW_TO_GERMAN, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      
      interactions.put(new Interaction(Action.GERMAN_TO_HEBREW, Status.TAB_EXPRESSIONKIND), Command.NO_TABLE);
      interactions.put(new Interaction(Action.HEBREW_TO_GERMAN, Status.TAB_EXPRESSIONKIND), Command.NO_TABLE);
      
      interactions.put(new Interaction(Action.GERMAN_TO_HEBREW, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.HEBREW_TO_GERMAN, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      
      interactions.put(new Interaction(Action.GERMAN_TO_HEBREW, Status.TAB_SEARCH), Command.NO_TABLE);
      interactions.put(new Interaction(Action.HEBREW_TO_GERMAN, Status.TAB_SEARCH), Command.NO_TABLE);
      
      interactions.put(new Interaction(Action.GERMAN_TO_HEBREW, Status.SEARCH_WHICH_HEBREW), Command.NO_TABLE);
      interactions.put(new Interaction(Action.GERMAN_TO_HEBREW, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.HEBREW_TO_GERMAN, Status.SEARCH_WHICH_GERMAN), Command.NO_TABLE);
      interactions.put(new Interaction(Action.HEBREW_TO_GERMAN, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.GERMAN_TO_HEBREW, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.HEBREW_TO_GERMAN, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      
      interactions.put(new Interaction(Action.TAB_CHAPTER, Status.OPENED_PAGE), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_CHAPTER, Status.TAB_EXPRESSIONKIND), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_CHAPTER, Status.EXPRESSIONKIND_WHICH), Command.NO_TABLE);    
      interactions.put(new Interaction(Action.TAB_CHAPTER, Status.TAB_SEARCH), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_CHAPTER, Status.SEARCH_WHICH_GERMAN), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_CHAPTER, Status.SEARCH_WHICH_HEBREW), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_CHAPTER, Status.TAB_SELECTED_EXPRESSIONS), Command.NO_TABLE);
      
      interactions.put(new Interaction(Action.TAB_EXPRESSIONKIND, Status.OPENED_PAGE), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_EXPRESSIONKIND, Status.TAB_CHAPTER), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_EXPRESSIONKIND, Status.CHAPTER_WHICH), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_EXPRESSIONKIND, Status.TAB_SEARCH), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_EXPRESSIONKIND, Status.SEARCH_WHICH_GERMAN), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_EXPRESSIONKIND, Status.SEARCH_WHICH_HEBREW), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_EXPRESSIONKIND, Status.TAB_SELECTED_EXPRESSIONS), Command.NO_TABLE);
      
      interactions.put(new Interaction(Action.TAB_SEARCH, Status.OPENED_PAGE), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_SEARCH, Status.TAB_CHAPTER), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_SEARCH, Status.CHAPTER_WHICH), Command.NO_TABLE); 
      interactions.put(new Interaction(Action.TAB_SEARCH, Status.TAB_EXPRESSIONKIND), Command.NO_TABLE);
      interactions.put(new Interaction(Action.TAB_SEARCH, Status.EXPRESSIONKIND_WHICH), Command.NO_TABLE);    
      interactions.put(new Interaction(Action.TAB_SEARCH, Status.TAB_SELECTED_EXPRESSIONS), Command.NO_TABLE);
      
      interactions.put(new Interaction(Action.TAB_SELECTED_EXPRESSIONS, Status.OPENED_PAGE), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.TAB_SELECTED_EXPRESSIONS, Status.TAB_CHAPTER), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.TAB_SELECTED_EXPRESSIONS, Status.CHAPTER_WHICH), Command.TABLE_SELECTED_EXPRESSIONS); 
      interactions.put(new Interaction(Action.TAB_SELECTED_EXPRESSIONS, Status.TAB_EXPRESSIONKIND), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.TAB_SELECTED_EXPRESSIONS, Status.EXPRESSIONKIND_WHICH), Command.TABLE_SELECTED_EXPRESSIONS);  
      interactions.put(new Interaction(Action.TAB_SELECTED_EXPRESSIONS, Status.TAB_SEARCH), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.TAB_SELECTED_EXPRESSIONS, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.TAB_SELECTED_EXPRESSIONS, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SELECTED_EXPRESSIONS);
      
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.CHAPTER_WHICH, Status.TAB_CHAPTER), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.CHAPTER_WHICH, Status.OPENED_PAGE), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.CHAPTER_WHICH, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.EXPRESSIONKIND_WHICH, Status.TAB_EXPRESSIONKIND), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.EXPRESSIONKIND_WHICH, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.EXPRESSIONKIND_WHICH, Status.OPENED_PAGE), Command.TABLE_EXPRESSIONKIND_WHICH);
      
      interactions.put(new Interaction(Action.SEARCH_WHICH_GERMAN, Status.TAB_SEARCH), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.SEARCH_WHICH_GERMAN, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.SEARCH_WHICH_GERMAN, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.SEARCH_WHICH_GERMAN, Status.OPENED_PAGE), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.SEARCH_WHICH_HEBREW, Status.TAB_SEARCH), Command.TABLE_SEARCH_WHICH_HEBREW);
      interactions.put(new Interaction(Action.SEARCH_WHICH_HEBREW, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
      interactions.put(new Interaction(Action.SEARCH_WHICH_HEBREW, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_HEBREW);
      interactions.put(new Interaction(Action.SEARCH_WHICH_HEBREW, Status.OPENED_PAGE), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.COPY, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.COPY, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.COPY, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.COPY, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.COPY, Status.TAB_SELECTED_EXPRESSIONS), Command.NOTHING);
      interactions.put(new Interaction(Action.COPY, Status.CHAPTER_WHICH), Command.NOTHING);
      interactions.put(new Interaction(Action.COPY, Status.EXPRESSIONKIND_WHICH), Command.NOTHING);
      interactions.put(new Interaction(Action.COPY, Status.SEARCH_WHICH_GERMAN), Command.NOTHING);
      interactions.put(new Interaction(Action.COPY, Status.SEARCH_WHICH_HEBREW), Command.NOTHING);
      
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
    
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.CHAPTER_WHICH), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.EXPRESSIONKIND_WHICH), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.SEARCH_WHICH_GERMAN), Command.NOTHING);
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.SEARCH_WHICH_HEBREW), Command.NOTHING);
      
      interactions.put(new Interaction(Action.NEW_EXPRESSION, Status.OPENED_PAGE), Command.NOTHING); // must be nothing, because it is always followed by Action "save"
      interactions.put(new Interaction(Action.NEW_EXPRESSION, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.NEW_EXPRESSION, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.NEW_EXPRESSION, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.NEW_EXPRESSION, Status.TAB_SELECTED_EXPRESSIONS), Command.NOTHING);
      interactions.put(new Interaction(Action.NEW_EXPRESSION, Status.CHAPTER_WHICH), Command.NOTHING);
      interactions.put(new Interaction(Action.NEW_EXPRESSION, Status.EXPRESSIONKIND_WHICH), Command.NOTHING);
      interactions.put(new Interaction(Action.NEW_EXPRESSION, Status.SEARCH_WHICH_GERMAN), Command.NOTHING);
      interactions.put(new Interaction(Action.NEW_EXPRESSION, Status.SEARCH_WHICH_HEBREW), Command.NOTHING);
      
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.OPENED_PAGE), Command.NO_TABLE);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.TAB_CHAPTER), Command.NO_TABLE);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.TAB_EXPRESSIONKIND), Command.NO_TABLE);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.TAB_SEARCH), Command.NO_TABLE);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.CHAPTER_WHICH), Command.RESTORE_WHICH_CHAPTER);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.SAVE, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.SAVE, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.SAVE, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.SAVE, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.SAVE, Status.TAB_SELECTED_EXPRESSIONS), Command.NOTHING);
      interactions.put(new Interaction(Action.SAVE, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.SAVE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.SAVE, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.SAVE, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.WORK_SHREDDER, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.WORK_SHREDDER, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.WORK_SHREDDER, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.WORK_SHREDDER, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.WORK_SHREDDER, Status.TAB_SELECTED_EXPRESSIONS), Command.NOTHING);
      interactions.put(new Interaction(Action.WORK_SHREDDER, Status.CHAPTER_WHICH), Command.NOTHING);
      interactions.put(new Interaction(Action.WORK_SHREDDER, Status.EXPRESSIONKIND_WHICH), Command.NOTHING);
      interactions.put(new Interaction(Action.WORK_SHREDDER, Status.SEARCH_WHICH_GERMAN), Command.NOTHING);
      interactions.put(new Interaction(Action.WORK_SHREDDER, Status.SEARCH_WHICH_HEBREW), Command.NOTHING);
      
      interactions.put(new Interaction(Action.SORT_NOW, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.SORT_NOW, Status.TAB_CHAPTER), Command.NOTHING);
      interactions.put(new Interaction(Action.SORT_NOW, Status.TAB_EXPRESSIONKIND), Command.NOTHING);
      interactions.put(new Interaction(Action.SORT_NOW, Status.TAB_SEARCH), Command.NOTHING);
      interactions.put(new Interaction(Action.SORT_NOW, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.SORT_NOW, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.SORT_NOW, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.SORT_NOW, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.SORT_NOW, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SEARCH_WHICH_HEBREW);
      
      interactions.put(new Interaction(Action.MOVE_TO_CHAPTER, Status.OPENED_PAGE), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_CHAPTER, Status.TAB_CHAPTER), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_CHAPTER, Status.CHAPTER_WHICH), Command.TABLE_SELECTED_EXPRESSIONS); 
      interactions.put(new Interaction(Action.MOVE_TO_CHAPTER, Status.TAB_EXPRESSIONKIND), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_CHAPTER, Status.EXPRESSIONKIND_WHICH), Command.TABLE_SELECTED_EXPRESSIONS);  
      interactions.put(new Interaction(Action.MOVE_TO_CHAPTER, Status.TAB_SEARCH), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_CHAPTER, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_CHAPTER, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_CHAPTER, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SELECTED_EXPRESSIONS);
      
      interactions.put(new Interaction(Action.MOVE_TO_DATABASE, Status.OPENED_PAGE), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_DATABASE, Status.TAB_CHAPTER), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_DATABASE, Status.CHAPTER_WHICH), Command.TABLE_SELECTED_EXPRESSIONS); 
      interactions.put(new Interaction(Action.MOVE_TO_DATABASE, Status.TAB_EXPRESSIONKIND), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_DATABASE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_SELECTED_EXPRESSIONS);  
      interactions.put(new Interaction(Action.MOVE_TO_DATABASE, Status.TAB_SEARCH), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_DATABASE, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_DATABASE, Status.SEARCH_WHICH_GERMAN), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.MOVE_TO_DATABASE, Status.SEARCH_WHICH_HEBREW), Command.TABLE_SELECTED_EXPRESSIONS);
   }
   
   public static Command getCommand(Interaction interaction)
   {
      if(interactions.get(interaction) == null)
      {
         ErrorNotification.display(
               Common.getTranslator()
                     .realisticTranslate(Translation.ENTSCHULDIGUNG),
               interaction.getAction().name(), interaction.getStatus().name(),
               Common.getTranslator().realisticTranslate(Translation.FEEDBACK));
         return Command.ERROR;
      }
      return interactions.get(interaction);
   }
   
   public Interaction(Action action, Status status)
   {
      this.action = action;
      this.status = status;
   }
   
   public Interaction(Status status)
   {
      this.status = status;
   }
   
   private Status status;
   private Action action;

   Status getStatus()
   {
      return status;
   }

   Action getAction()
   {
      return action;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((action == null) ? 0 : action.hashCode());
      result = prime * result + ((status == null) ? 0 : status.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Interaction other = (Interaction) obj;
      if (action != other.action)
         return false;
      if (status != other.status)
         return false;
      return true;
   }


}
