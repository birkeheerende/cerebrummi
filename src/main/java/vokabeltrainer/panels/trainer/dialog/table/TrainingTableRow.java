package vokabeltrainer.panels.trainer.dialog.table;

import java.util.List;
import java.util.Set;

import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.FieldOfTraining;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class TrainingTableRow
{
   private String field;
   private int toBeRepeatedWords;
   private int notStudiedWords;
   private int amountOfNewWords;
   private boolean fieldDone;
   private FieldOfTraining fieldOfTraining;
   private Chapter chapter;
   private ExpressionKind kind;
   private List<Expression> expressionListNewWords;
   private Set<Expression> expressionListOldWords;
   private boolean started;

   public String getField()
   {
      return field;
   }

   public void setField(String field)
   {
      this.field = field;
   }

   public int getToBeRepeatedWords()
   {
      return toBeRepeatedWords;
   }

   public void setToBeRepeatedWords(int totalWords)
   {
      this.toBeRepeatedWords = totalWords;
   }

   public int getNotStudiedWords()
   {
      return notStudiedWords;
   }

   public void setNotStudiedWords(int notStudiedWords)
   {
      this.notStudiedWords = notStudiedWords;
   }

   public int getAmountOfNewWords()
   {
      return amountOfNewWords;
   }

   public void setAmountOfNewWords(int amountOfNewWords)
   {
      this.amountOfNewWords = amountOfNewWords;
   }

   public boolean isFieldDone()
   {
      return fieldDone;
   }

   public void setFieldDone(boolean fieldDone)
   {
      this.fieldDone = fieldDone;
   }

   public FieldOfTraining getFieldOfTraining()
   {
      return fieldOfTraining;
   }

   public void setFieldOfTraining(FieldOfTraining fieldOfTraining)
   {
      this.fieldOfTraining = fieldOfTraining;
   }

   public Chapter getChapter()
   {
      return chapter;
   }

   public void setChapter(Chapter chapter)
   {
      this.chapter = chapter;
   }

   public ExpressionKind getKind()
   {
      return kind;
   }

   public void setKind(ExpressionKind kind)
   {
      this.kind = kind;
   }

   public List<Expression> getExpressionListNewWords()
   {
      return expressionListNewWords;
   }

   public void setExpressionListNewWords(List<Expression> listSelected)
   {
      this.expressionListNewWords = listSelected;
   }

   public Set<Expression> getExpressionListOldWords()
   {
      return expressionListOldWords;
   }

   public void setExpressionListOldWords(Set<Expression> expressionListOldWords)
   {
      this.expressionListOldWords = expressionListOldWords;
   }

   public boolean isStarted()
   {
      return started;
   }

   public void setStarted(boolean started)
   {
      this.started = started;
   }
}
