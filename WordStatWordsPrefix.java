import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;


public class WordStatWordsPrefix {
    static Map<String, Integer> wordStat = new TreeMap<>();

    private static boolean isDashPunctuation(char smb) {
        return Character.getType(smb) == Character.DASH_PUNCTUATION;
    }

    private static boolean isLetterOrDash(char smb) {
        smb = Character.toLowerCase(smb);
        return (Character.isLetter(smb)) || smb == '\'' || isDashPunctuation(smb);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("bad parameters");
            return;
        }

        try (Reader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            int symb;
            wordStat.clear();
            StringBuilder word = new StringBuilder();
            while ((symb = reader.read()) != -1) {
                char symbol = (char) symb;
                if (isLetterOrDash(symbol)) {
                    word.append(symbol);
                } else if (word.length() > 0) {
                    String keyWord = word.toString().toLowerCase();
                    if (keyWord.length() > 3) {
                        keyWord = keyWord.substring(0, 3);
                    }
                    wordStat.put(keyWord, wordStat.getOrDefault(keyWord, 0) + 1);
                    word = new StringBuilder();
                }
            }
            if (word.length() > 0) {
                String keyWord = word.toString().toLowerCase();
                if (keyWord.length() > 3) {
                    keyWord = keyWord.substring(0, 3);
                }
                wordStat.put(keyWord, wordStat.getOrDefault(keyWord, 0) + 1);
            }

            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1]),
                StandardCharsets.UTF_8
            ))) {
                for (Map.Entry<String, Integer> pair : wordStat.entrySet()) {
                    writer.write(pair.getKey() + " " + pair.getValue().toString() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
