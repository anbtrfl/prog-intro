import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {

    private static boolean isDashPunctuation(char smb) {
        return Character.getType(smb) == Character.DASH_PUNCTUATION;
    }

    private static boolean isLetterOrDash(char smb) {
        smb = Character.toLowerCase(smb);
        return (Character.isLetter(smb)) || smb == '\'' || isDashPunctuation(smb);
    }

    private static void wordPrepare(String keyWord, int wordCounter, Map<String, IntList> wordStat) {
        IntList statInfo = wordStat.get(keyWord);
        if(statInfo == null) {
            IntList list = new IntList(2);
            list.addElem(1);
            list.addElem(wordCounter);
            wordStat.put(keyWord, list);
        } else {
            statInfo.set(0, statInfo.get(0) + 1);
            statInfo.addElem(wordCounter);
            wordStat.put(keyWord, statInfo);
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, IntList> wordStat = new LinkedHashMap<>();

        if (args.length != 2) {
            System.out.println("bad parameters");
            return;
        }

        BestScannerEver reader = new BestScannerEver(new FileInputStream(args[0]));

        try {
            StringBuilder word = new StringBuilder();
            int wordCounter = 0;
            while (reader.hasNext()) {
                char symbol = reader.read();
                if (isLetterOrDash(symbol)) {
                    word.append(symbol);
                } else if (word.length() > 0) {
                    String keyWord = word.toString().toLowerCase();
                    wordCounter += 1;
                    wordPrepare(keyWord, wordCounter, wordStat);
                    word = new StringBuilder();
                }
            } if (word.length() > 0) {
                String keyWord = word.toString().toLowerCase();
                wordCounter += 1;
                wordPrepare(keyWord, wordCounter, wordStat);
            }

            Writer writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
            try {
                for (Map.Entry<String, IntList> pair : wordStat.entrySet()) {
                    writer.write(pair.getKey() + " " + pair.getValue().toString() + System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}