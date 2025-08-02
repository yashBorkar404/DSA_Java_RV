import java.util.*;

class Pair {
    String word;
    List<String> path;

    Pair(String word, List<String> path) {
        this.word = word;
        this.path = new ArrayList<>(path);
        this.path.add(word);
    }
}
public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        if (!wordSet.contains(endWord)) return result;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(beginWord, new ArrayList<>()));
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> currentLevelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                Pair currentPair = queue.poll();
                String currentWord = currentPair.word;

                if (currentWord.equals(endWord)) {
                    result.add(currentPair.path);
                    found = true;
                }

                for (int j = 0; j < currentWord.length(); j++) {
                    char[] chars = currentWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String nextWord = new String(chars);

                        if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                            currentLevelVisited.add(nextWord);
                            queue.offer(new Pair(nextWord, currentPair.path));
                        }
                    }
                }
            }
            visited.addAll(currentLevelVisited);
        }
        return result;
    }
}
