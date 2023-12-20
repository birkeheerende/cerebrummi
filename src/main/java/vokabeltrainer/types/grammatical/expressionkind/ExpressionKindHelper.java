package vokabeltrainer.types.grammatical.expressionkind;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import vokabeltrainer.ExpressionKindComparator;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;

public class ExpressionKindHelper
{
   public static GrammaticalParentEnum[] UNKNOWN_ENUMS = {};

   public static GrammaticalParentEnum[] ADJECTIVE_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS };

   public static GrammaticalParentEnum[] ADVERB_ENUMS = {};
   
   public static GrammaticalParentEnum[] ADVERB_PLUS_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS,
         GrammaticalParentEnum.GRAMMATICAL_PERSON,
         GrammaticalParentEnum.VERB_TIMES };

   public static GrammaticalParentEnum[] ALTERSANGABE_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS };

   public static GrammaticalParentEnum[] AUSRUF_ENUMS = {};

   public static GrammaticalParentEnum[] BERUF_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS };

   public static GrammaticalParentEnum[] BINDEWORT_ENUMS = {};

   public static GrammaticalParentEnum[] DEMONSTRATIVPRONOM_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS };

   public static GrammaticalParentEnum[] EIGENNAME_ENUMS = {
         GrammaticalParentEnum.GENDER };

   public static GrammaticalParentEnum[] FARBE_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS };

   public static GrammaticalParentEnum[] FRAGEWORT_ENUMS = {};

   public static GrammaticalParentEnum[] PRONOM_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS };

   public static GrammaticalParentEnum[] GLUECKWUNSCH_ENUMS = {};
   
   public static GrammaticalParentEnum[] HOEFLICHKEITSFORMEL_ENUMS = {};

   public static GrammaticalParentEnum[] JAHRESZEIT_ENUMS = {
         GrammaticalParentEnum.GENDER };

   public static GrammaticalParentEnum[] KONSTRUKT_ENUMS = {};

   public static GrammaticalParentEnum[] MODALVERB_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS,
         GrammaticalParentEnum.GRAMMATICAL_PERSON, GrammaticalParentEnum.BINJAN,
         GrammaticalParentEnum.VERB_TIMES };

   public static GrammaticalParentEnum[] ORDNUNGSZAHL_ENUMS = {
         GrammaticalParentEnum.GENDER };

   public static GrammaticalParentEnum[] PARTIKEL_ENUMS = {};

   public static GrammaticalParentEnum[] PERSONALPRAEFIX_ENUMS = {};

   public static GrammaticalParentEnum[] PERSONALPRONOM_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS,
         GrammaticalParentEnum.GRAMMATICAL_PERSON };

   public static GrammaticalParentEnum[] PERSONALSUFFIX_ENUMS = {};

   public static GrammaticalParentEnum[] POSSESSIVPRONOM_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS };

   public static GrammaticalParentEnum[] PRAEPOSITION_ENUMS = {};

   public static GrammaticalParentEnum[] REDEWENDUNG_ENUMS = {};
   
   public static GrammaticalParentEnum[] RELATIVPRONOM_ENUMS = {};

   public static GrammaticalParentEnum[] SUBSTANTIV_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS };

   public static GrammaticalParentEnum[] TEXT_ENUMS = {};
   
   public static GrammaticalParentEnum[] UHRZEIT_ENUMS = {};

   public static GrammaticalParentEnum[] UMGANGSSPRACHE_ENUMS = {};

   public static GrammaticalParentEnum[] VERB_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS,
         GrammaticalParentEnum.GRAMMATICAL_PERSON, GrammaticalParentEnum.BINJAN,
         GrammaticalParentEnum.VERB_TIMES };

   public static GrammaticalParentEnum[] WOCHENTAG_ENUMS = {
         GrammaticalParentEnum.GENDER, GrammaticalParentEnum.NUMERUS };

   public static GrammaticalParentEnum[] ZAHL_ENUMS = {
         GrammaticalParentEnum.GENDER };

   public static GrammaticalParentEnum[] ARTIKEL_ENUMS = {
         GrammaticalParentEnum.GENDER };

   public static List<ExpressionKindItem> getAllExpressionKindItems()
   {
      return getAllExpressionKinds()
            .stream()
            .map(kind -> new ExpressionKindItem(kind))
            .collect(Collectors.toList());
   }

   public static List<ExpressionKind> getAllExpressionKinds()
   {
      List<ExpressionKind> sortedKinds = Arrays
            .stream(ExpressionKind.values())
            .collect(Collectors.toList());
      Collections.sort(sortedKinds, new ExpressionKindComparator());
      return sortedKinds;
   }
}
