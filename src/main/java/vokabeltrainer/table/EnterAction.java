package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.table.list.editor.NikudExpressionEditorController;
import vokabeltrainer.table.list.editor.NikudExpressionEditorView;
import vokabeltrainer.table.list.editor.TextExpressionEditorView;
import vokabeltrainer.types.Expression;

public class EnterAction extends AbstractAction
{
   private ExpressionTable table;
   private NikudExpressionEditorView editorPunktation;
   private TextExpressionEditorView editorText;
   private TableConnector connector;

   public EnterAction(ExpressionTable table,
         TableConnector connector)
   {
      this.table = table;
      this.connector = connector;
      NikudExpressionEditorController controller = new NikudExpressionEditorController();
      editorPunktation = controller.getNikudExpressionEditorDialog();
      editorText = controller.getTextExpressionEditorDialog();
   }

   private static final long serialVersionUID = 719272853628204094L;

   @Override
   public void actionPerformed(ActionEvent e)
   {
      int selectedRow = table.getSelectedRow();
      if (selectedRow >= 0)
      {
         Expression expression = (Expression) table.getValueAt(selectedRow, 0);
         if(expression.getDefinitions().isExpressionKindText())
         {
        	 showEditorText(expression);
         }
         else
         {
        	 showEditorPunktation(expression);
         }
      }
   }

   private void showEditorText(Expression expression)
   {
		editorText.setFrozen(expression.isDoNotChange());
		editorText.setExpression(expression, false);
		editorText.setLocationRelativeTo(Common.getjFrame());
		editorText.setVisible(true);
		if (editorText.isSave())
	      {
	         connector.save();
	      }
	      editorText.dispose();
   }

private void showEditorPunktation(Expression expression)
   {
      editorPunktation.setFrozen(expression.isDoNotChange());
      editorPunktation.setExpression(expression, false);
      editorPunktation.setLocationRelativeTo(Common.getjFrame());
      editorPunktation.setVisible(true);
      if (editorPunktation.isSave())
      {
         connector.save();
      }
      editorPunktation.dispose();
   }
}
