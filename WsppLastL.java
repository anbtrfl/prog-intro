import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WsppLastL {

    private static boolean isDashPunctuation(char smb) {
        return Character.getType(smb) == Character.DASH_PUNCTUATION;
    }

    private static boolean isLetterOrDash(char smb) {
        smb = Character.toLowerCase(smb);
        return (Character.isLetter(smb)) || smb == '\'' || isDashPunctuation(smb);
    }

    private static void wordPrepare(String keyWord, int wordCounter, int lineCounter, Map<String, IntList> wordStat) {
        IntList statInfo = wordStat.get(keyWord);
        if (statInfo == null) {
            IntList list = new IntList(2);
            list.addElem(1);
            list.addElem(lineCounter);
            list.addElem(wordCounter);
            wordStat.put(keyWord, list);
        } else {
            statInfo.set(0, statInfo.get(0) + 1);
            statInfo.addElem(lineCounter);
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
            int lineCounter = 1;
            while (reader.hasNext()) {
                char symbol = reader.read();
                if (isLetterOrDash(symbol)) {
                    word.append(symbol);
                } else if (word.length() > 0) {
                    String keyWord = word.toString().toLowerCase();
                    wordCounter += 1;
                    wordPrepare(keyWord, wordCounter, lineCounter, wordStat);
                    word = new StringBuilder();
                }
                if (symbol == System.lineSeparator().charAt(0)) {
                    wordCounter = 0;
                    lineCounter++;
                }
            }
            if (word.length() > 0) {
                String keyWord = word.toString().toLowerCase();
                wordCounter += 1;
                wordPrepare(keyWord, wordCounter, lineCounter, wordStat);
            }


            Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
            try {
                for (Map.Entry<String, IntList> pair : wordStat.entrySet()) {
                    writer.write(pair.getKey() + " " + pair.getValue().toStringLastL() + System.lineSeparator());
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