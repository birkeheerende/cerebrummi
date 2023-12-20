package vokabeltrainer.panels.trainer.dialog.table;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.types.Expression;
import vokabeltrainer.types.FieldOfTraining;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.Repetition;
import vokabeltrainer.types.TrainingStatus;

public class TrainingTable extends JTable
{
   private static final long serialVersionUID = -1180739124368536646L;

   public TrainingTable(TrainingTableModel model)
   {
      super(model, new TrainingTableColumnModel());

      putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
      setOpaque(false);
      setRowHeight(50);
      setShowHorizontalLines(false);
      setBackground(new Color(0, 0, 0, 0));
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(true);
      this.setCellSelectionEnabled(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

   public List<Expression> findNewExpressions(Language languageDirection, FieldOfTraining fieldOfTraining)
   {
      TrainingTableModel model = (TrainingTableModel) getModel();

      Set<Expression> resultSet = new HashSet<>();
      for (TrainingTableRow[] row : model.getData())
      {
         if (row[0].getAmountOfNewWords() > 0)
         {
            resultSet.addAll(findRandomWords(row[0].getExpressionListNewWords(),
                  row[0].getAmountOfNewWords()));
         }
      }
      if(FieldOfTraining.AREA_SELECTED_TEMPORARY == fieldOfTraining)
      {
         return new ArrayList<>(resultSet);
      }
      return initTrainingStatus(resultSet, languageDirection);
   }

   public List<Expression> findOldToBeRepeatedExpressions()
   {
      TrainingTableModel model = (TrainingTableModel) getModel();

      Set<Expression> resultSet = new HashSet<>();
      for (TrainingTableRow[] row : model.getData())
      {
         if(row[0].getExpressionListOldWords() != null)
         {
            resultSet.addAll(row[0].getExpressionListOldWords());
         }
      }
      return new ArrayList<Expression>(resultSet);
   }

   private List<Expression> initTrainingStatus(Set<Expression> resultSet,
         Language languageDirection)
   {
      List<Expression> list = new ArrayList<>(resultSet.size());
      switch (languageDirection)
      {
      case GERMAN_TO_HEBREW:
         for (Expression expression : resultSet)
         {
            if (!expression.getTrainingStatusDToH().isTrainingStarted())
            {
               expression
                     .setTrainingStatusDToH(new TrainingStatus(Repetition.NOW));
            }

            list.add(expression);
         }
         break;
      case HEBREW_TO_GERMAN:
         for (Expression expression : resultSet)
         {
            if (!expression.getTrainingStatusHToD().isTrainingStarted())
            {
               expression
                     .setTrainingStatusHToD(new TrainingStatus(Repetition.NOW));
            }
            list.add(expression);
         }
      }
      return list;
   }

   private List<Expression> findRandomWords(Collection<Expression> listSelected,
         int amountOfNewWords)
   {
      List<Expression> list = new ArrayList<>(listSelected);
      Collections.shuffle(list);
      List<Expression> selected = new ArrayList<>();
      for (int i = 0; i < amountOfNewWords; i++)
      {
         selected.add(list.get(i));
      }
      return selected;
   }
}
