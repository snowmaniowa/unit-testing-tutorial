package mockito;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private Map<String, String> wordMap = new HashMap<>();

    public void add(final String word, final String meaning) {
        wordMap.put(word, meaning);
    }

    public String getMeaning(final String word) {
        return wordMap.get(word);
    }
}
