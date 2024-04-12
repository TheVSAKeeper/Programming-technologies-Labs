package console;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

public class ExplanatoryDictionary
{
    private final TreeMap<String, LinkedHashSet<String>> dictionary = new TreeMap<>();

    void add(String word, String interpretation)
    {
        if (dictionary.containsKey(word))
        {
            dictionary.get(word).add(interpretation);
            return;
        }

        LinkedHashSet<String> interpretations = new LinkedHashSet<>();
        interpretations.add(interpretation);
        dictionary.put(word, interpretations);

        //dictionary.computeIfAbsent(word, k -> new LinkedHashSet<>()).add(interpretation);
    }

    public LinkedHashSet<String> getInterpretations(String word)
    {
        return dictionary.getOrDefault(word, new LinkedHashSet<>());
    }

    void showDictionary()
    {
        System.out.println("Толковый словарь");

        for (Map.Entry<String, LinkedHashSet<String>> interpretations : dictionary.entrySet())
        {
            System.out.println(interpretations.getKey() + ": " + interpretations.getValue());
        }
    }
}