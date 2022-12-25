package pl.lssystems.adventofcode.v2022.day25;

public class SNAFU {

    public static long decode(String s) {
        long sum = 0L;
        for (int i = 0; i < s.length(); i++) {
            long powerOf5 = (long) Math.pow(5, s.length() - i - 1);
            long number = 0;
            switch (s.charAt(i)) {
                case '=': number = -2; break;
                case '-': number = -1; break;
                case '1': number = 1; break;
                case '2': number = 2; break;
            }
            sum += powerOf5 * number;
        }
        return sum;
    }

    public static String encode(long l) {
        StringBuilder sb = new StringBuilder((int) Math.log10(l) * 2);
        long sum = l;

        while (sum != 0) {
            int mod = (int) (sum % 5);
            switch (mod) {
                case 0: sb.append('0'); break;
                case 1: sb.append('1'); sum -= 1; break;
                case 2: sb.append('2'); sum -= 2; break;
                case 3: sb.append('='); sum += 2; break;
                case 4: sb.append('-'); sum += 1; break;
            }
            sum /= 5;
        }

        return sb.reverse().toString();
    }

}
