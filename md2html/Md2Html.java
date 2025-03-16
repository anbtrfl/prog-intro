package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("Некорректное количество аргументов");
        }
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new InputStreamReader(
            new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            StringBuilder paragraph = new StringBuilder();
            while (input.ready()) {
                String line = input.readLine();
                if (!line.trim().isEmpty()) {
                    paragraph.append(line).append(System.lineSeparator());
                } else if (!paragraph.toString().trim().isEmpty()) {
                    String block = paragraph.substring(0, paragraph.length() - System.lineSeparator().length());
                    BlockCompiler blockCompiler = new BlockCompiler(block);
                    result.append(blockCompiler.getBlock()).append(System.lineSeparator());
                    paragraph = new StringBuilder();
                }
            }
            if (!paragraph.toString().trim().isEmpty()) {
                String block = paragraph.substring(0, paragraph.length() - System.lineSeparator().length());
                BlockCompiler blockCompiler = new BlockCompiler(block);
                result.append(blockCompiler.getBlock()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения");
        }
        try (Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            out.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи");
        }
    }
}
