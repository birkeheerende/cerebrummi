package vokabeltrainer.table.list.editor;

import java.util.Set;

import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;

public interface ExpressionEditorViewConnector
{
   public void showGrammaticalParentEnums(Set<GrammaticalParentEnum> grammaticalEnumsToShow);
}
