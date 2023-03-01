public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        for (String argument : args) {
            String[] ar = argument.split("\\p{javaWhitespace}");
            for (String arg : ar) {
                if (!arg.equals("")) {
                    sum += Integer.parseInt(arg);
                }
            }
        }
        System.out.println(sum);
    }
}