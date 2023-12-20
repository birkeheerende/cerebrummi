package vokabeltrainer.types.grammatical.expressionkind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import vokabeltrainer.common.Common;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableModel;
import vokabeltrainer.table.list.editor.expressionkindtable.multiselect.ExpressionKindTableRow;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableModel2;
import vokabeltrainer.table.list.editor.expressionkindtable.singleselect.ExpressionKindTableRow2;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;

public enum ExpressionKind {
	EXPRESSIONKIND_UNKNOWN(Translation.UNBEKANNT, ExpressionKindHelper.UNKNOWN_ENUMS),
	ADJEKTIV(Translation.ADJEKTIV_EIGENSCHAFTSWORT, ExpressionKindHelper.ADJECTIVE_ENUMS),
	ADVERB(Translation.ADVERB, ExpressionKindHelper.ADVERB_ENUMS),
	ADVERB_PLUS(Translation.ADVERB_MIT_ZUSATZ, ExpressionKindHelper.ADVERB_PLUS_ENUMS),
	ALTERSANGABE(Translation.ALTERSANGABE, ExpressionKindHelper.ALTERSANGABE_ENUMS),
	AUSRUF(Translation.INTERJEKTION_AUSRUF, ExpressionKindHelper.AUSRUF_ENUMS),
	ARTIKEL(Translation.ARTIKEL, ExpressionKindHelper.ARTIKEL_ENUMS),
	BERUF(Translation.BERUF, ExpressionKindHelper.BERUF_ENUMS),
	BINDEWORT(Translation.KONJUGATION_BINDEWORT, ExpressionKindHelper.BINDEWORT_ENUMS),
	DEMONSTRATIVPRONOM(Translation.DEMONSTRATIVPRONOM, ExpressionKindHelper.DEMONSTRATIVPRONOM_ENUMS),
	EIGENNAME(Translation.EIGENNAME, ExpressionKindHelper.EIGENNAME_ENUMS),
	FARBE(Translation.FARBE, ExpressionKindHelper.FARBE_ENUMS),
	FRAGEWORT(Translation.FRAGEWORT, ExpressionKindHelper.FRAGEWORT_ENUMS),
	PRONOM(Translation.PRONOM_FUERWORT, ExpressionKindHelper.PRONOM_ENUMS),
	GLUECKWUNSCH(Translation.GLUECKWUNSCH_GRUSS, ExpressionKindHelper.GLUECKWUNSCH_ENUMS),
	HOEFLICHKEITSFORMEL(Translation.HOEFLICHKEITSFORMEL, ExpressionKindHelper.HOEFLICHKEITSFORMEL_ENUMS),
	JAHRESZEIT(Translation.JAHRESZEIT, ExpressionKindHelper.JAHRESZEIT_ENUMS),
	KONSTRUKT(Translation.KONSTRUKT_SSMICHUT, ExpressionKindHelper.KONSTRUKT_ENUMS),
	MODALVERB(Translation.MODALVERB, ExpressionKindHelper.MODALVERB_ENUMS),
	ORDNUNGSZAHL(Translation.ORDNUNGSZAHL, ExpressionKindHelper.ORDNUNGSZAHL_ENUMS),
	PARTIKEL(Translation.PARTIKEL, ExpressionKindHelper.PARTIKEL_ENUMS),
	PERSONALPRAEFIX(Translation.PERSONALPRAEFIX, ExpressionKindHelper.PERSONALPRAEFIX_ENUMS),
	PERSONALPRONOM(Translation.PERSONALPRONOM, ExpressionKindHelper.PERSONALPRONOM_ENUMS),
	PERSONALSUFFIX(Translation.PERSONALSUFFIX, ExpressionKindHelper.PERSONALSUFFIX_ENUMS),
	POSSESSIVPRONOM(Translation.POSSESSIVPRONOM, ExpressionKindHelper.POSSESSIVPRONOM_ENUMS),
	PRAEPOSITION(Translation.PRAEPOSITION, ExpressionKindHelper.PRAEPOSITION_ENUMS),
	REDEWENDUNG(Translation.REDEWENDUNG, ExpressionKindHelper.REDEWENDUNG_ENUMS),
	RELATIVPRONOM(Translation.REATIVPRONOM, ExpressionKindHelper.RELATIVPRONOM_ENUMS),
	SUBSTANTIV(Translation.SUBSTANTIV_HAUPTWORT, ExpressionKindHelper.SUBSTANTIV_ENUMS),
	TEXT(Translation.TEXT, ExpressionKindHelper.TEXT_ENUMS),
	UHRZEIT(Translation.UHRZEIT, ExpressionKindHelper.UHRZEIT_ENUMS),
	UMGANGSPRACHE(Translation.UMGANGSSPRACHE, ExpressionKindHelper.UMGANGSSPRACHE_ENUMS),
	VERB(Translation.VERB_TUWORT, ExpressionKindHelper.VERB_ENUMS),
	WOCHENTAG(Translation.WOCHENTAG, ExpressionKindHelper.WOCHENTAG_ENUMS),
	ZAHL(Translation.ZAHL, ExpressionKindHelper.ZAHL_ENUMS);

	private Translation description;
	private GrammaticalParentEnum[] grammaticalParentEnums;

	ExpressionKind(Translation description, GrammaticalParentEnum[] grammaticalParentEnums) {
		this.description = description;
		this.grammaticalParentEnums = grammaticalParentEnums;
	}

	@Override
	public String toString() {
		Translator translator = Common.getTranslator();
		return translator.realisticTranslate(description);
	}

	public String toDescription() {
		Translator translator = Common.getTranslator();
		switch (this) {
		case ADJEKTIV:
		case ADVERB:
		case ADVERB_PLUS:
		case ALTERSANGABE:
		case AUSRUF:
		case ARTIKEL:
		case BERUF:
		case BINDEWORT:
		case DEMONSTRATIVPRONOM:
		case EIGENNAME:
		case FARBE:
		case FRAGEWORT:
		case GLUECKWUNSCH:
		case HOEFLICHKEITSFORMEL:
		case JAHRESZEIT:
		case KONSTRUKT:
		case MODALVERB:
		case ORDNUNGSZAHL:
		case PARTIKEL:
		case PERSONALPRAEFIX:
		case PERSONALPRONOM:
		case PERSONALSUFFIX:
		case POSSESSIVPRONOM:
		case PRAEPOSITION:
		case PRONOM:
		case REDEWENDUNG:
		case RELATIVPRONOM:
		case SUBSTANTIV:
		case TEXT:
		case UHRZEIT:
		case UMGANGSPRACHE:
		case VERB:
		case WOCHENTAG:
		case ZAHL:
			return translator.realisticTranslate(description);
		case EXPRESSIONKIND_UNKNOWN:
			return translator.realisticTranslate(Translation.WORTART) + " "
					+ translator.realisticTranslate(description);
		default:
			return "";
		}
	}

	public static int getNumberOfValues() {
		return ExpressionKind.values().length;
	}

	public String getDescription() {
		Translator translator = Common.getTranslator();
		return translator.realisticTranslate(description);
	}

	public static ExpressionKindTableModel getModelForMultiselect() {
		Vector<Vector<ExpressionKindTableRow>> data = new Vector<>();
		for (ExpressionKindItem kind : ExpressionKindHelper.getAllExpressionKindItems()) {
			kind.setSelected(false);
			Vector<ExpressionKindTableRow> row = new Vector<>();
			row.add(new ExpressionKindTableRow(kind));
			data.add(row);
		}
		Vector<String> columnNames = new Vector<>();
		columnNames.add("eins");
		return new ExpressionKindTableModel(data, columnNames);
	}

	public static ExpressionKindTableModel2 getModelForSingleselect() {
		Vector<Vector<ExpressionKindTableRow2>> data = new Vector<>();
		for (ExpressionKindItem kind : ExpressionKindHelper.getAllExpressionKindItems()) {
			kind.setSelected(false);
			Vector<ExpressionKindTableRow2> row = new Vector<>();
			row.add(new ExpressionKindTableRow2(kind));
			data.add(row);
		}
		Vector<String> columnNames = new Vector<>();
		columnNames.add("eins");
		return new ExpressionKindTableModel2(data, columnNames);
	}

	public static ExpressionKindTableModel getModelForMultiselect(Set<ExpressionKind> expressionKinds) {
		Set<ExpressionKindItem> expressionKindItems = new HashSet<>();
		Vector<Vector<ExpressionKindTableRow>> data = new Vector<>();
		for (ExpressionKind kind : ExpressionKindHelper.getAllExpressionKinds()) {
			ExpressionKindItem item = new ExpressionKindItem(kind);
			if (expressionKinds.contains(kind)) {
				item.setSelected(true);
				expressionKindItems.add(item);
			} else {
				item.setSelected(false);
				expressionKindItems.add(item);
			}
			Vector<ExpressionKindTableRow> row = new Vector<>();
			row.add(new ExpressionKindTableRow(item));
			data.add(row);
		}
		Vector<String> columnNames = new Vector<>();
		columnNames.add("eins");
		return new ExpressionKindTableModel(data, columnNames);
	}

	public static Set<GrammaticalParentEnum> getSetOfGrammaticalParentEnums(List<ExpressionKindTableRow> rows) {
		return rows.stream().map(row -> Arrays.stream(row.getExpressionKindItem().getKind().getGrammaticalParentEnums())
				.collect(Collectors.toSet())).flatMap(Set::stream).collect(Collectors.toSet());
	}

	public static Set<GrammaticalParentEnum> getSetOfGrammaticalParentEnums(Set<ExpressionKind> kinds) {
		return kinds.stream().map(kind -> Arrays.stream(kind.getGrammaticalParentEnums()).collect(Collectors.toSet()))
				.flatMap(Set::stream).collect(Collectors.toSet());
	}

	public GrammaticalParentEnum[] getGrammaticalParentEnums() {
		return grammaticalParentEnums;
	}
}
