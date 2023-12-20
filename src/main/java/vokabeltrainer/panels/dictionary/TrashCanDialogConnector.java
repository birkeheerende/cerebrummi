package vokabeltrainer.panels.dictionary;

import vokabeltrainer.types.Language;

public interface TrashCanDialogConnector
{

   boolean isRestore();

   void tableValidateRepaint();

   Language getSelectedLanguage();

   boolean isTableNotNull();

   void setRestore(boolean restore);

   void selectAllExpressionsInTable();

   void unselectAllExpressionsInTable();

}
