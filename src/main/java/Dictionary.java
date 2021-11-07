import java.util.Arrays;

class Dictionary {
    private final UppercaseTrieSET dictionary;

    Dictionary(String[] words) {
        if (words == null) throw new IllegalArgumentException();
        dictionary = new UppercaseTrieSET();
        Arrays.stream(words).forEachOrdered(dictionary::add);
    }

    boolean isValid(String word) {
        return word.length() >= 3 && dictionary.contains(word);
    }

    boolean existsPrefix(String word) {
        return dictionary.hasAnyKeyWithPrefix(word);
    }
}
