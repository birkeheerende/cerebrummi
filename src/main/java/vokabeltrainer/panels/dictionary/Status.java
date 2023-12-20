package vokabeltrainer.panels.dictionary;

import java.util.ArrayDeque;

public enum Status
{
   OPENED_PAGE,
   TAB_CHAPTER,
   TAB_EXPRESSIONKIND,
   TAB_SEARCH,
   TAB_SELECTED_EXPRESSIONS,
   CHAPTER_WHICH,
   EXPRESSIONKIND_WHICH,
   SEARCH_WHICH_GERMAN,
   SEARCH_WHICH_HEBREW;
   
   private static ArrayDeque<Status> status = new ArrayDeque<>();
   
   protected static void push(Status status)
   {
      Status.status.push(status);
   }
   
   protected static Status pop()
   {
      return Status.status.pollLast();
   }
   
   protected static Status peek()
   {
      return Status.status.peekLast();
   }
}
