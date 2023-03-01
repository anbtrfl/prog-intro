public class SumOctal {
    public static void main(String[] args) {
        int sum = 0;
        for (String argument : args) {
            String[] ar = argument.split("\\p{javaWhitespace}");
            for (String arg : ar) {
                if (arg.endsWith("o") || arg.endsWith("O")){
                    arg = arg.substring(0, arg.length() - 1);
                    sum += Long.parseLong(arg, 8);
                } else if (!arg.equals("")) {
                    sum += Integer.parseInt(arg);
                }
            }
        }
        System.out.println(sum);
    }
}
