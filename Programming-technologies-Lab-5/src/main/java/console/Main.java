package console;

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        String[] words = {"массив",
                "список",
                "запись",
                "массив",
                "множество",
                "массив",
                "список",
                "множество",
                "множество",
                "множество"};

        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words)
        {
            wordCount.merge(word, 1, Integer::sum);
        }

        System.out.println("Уникальные слова: " + wordCount.keySet());
        System.out.println("Количество повторений слов: " + wordCount.entrySet());
        System.out.println("\n\n");

        ExplanatoryDictionary explanatoryDictionary = new ExplanatoryDictionary();
        fillDictionary(explanatoryDictionary);

        System.out.println("книга: " + explanatoryDictionary.getInterpretations("книга"));
        System.out.println();
        explanatoryDictionary.showDictionary();
    }

    private static void fillDictionary(ExplanatoryDictionary dictionary)
    {
        dictionary.add("словарь", "1. Собрание слов (обычно в алфавитном порядке), устойчивых выражений с пояснениями, толкованиями или с переводом на другой язык.");
        dictionary.add("словарь", "2. Совокупность слов какого-нибудь языка, а также слов, употреблённых в ка-ком-нибудь одном произведении, в произведениях какого-нибудь писателя или вообще употребляемых кем-нибудь.");

        dictionary.add("книга", "1. Произведение печати (в старину также рукописное) в виде переплетённых листов с каким-нибудь текстом.");
        dictionary.add("книга", "2. Крупное подразделение литературного произведения, состоящее из многих глав.");
        dictionary.add("книга", "3. Сшитые в один переплёт листы бумаги, заполняемые документальными официальными учётными данными.");

        dictionary.add("справочник", "Справочная книга.");

        dictionary.add("справочник1", "Справочная книга1.");
        dictionary.add("справочник1", "Справочная книга2.");
    }
}
