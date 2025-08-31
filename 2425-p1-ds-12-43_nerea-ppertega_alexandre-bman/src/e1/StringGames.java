package e1;

import javax.lang.model.type.NullType;

public class StringGames {

    public static String bestCharacters(String str0, String str1) {
        if (str0.length() != str1.length()) {
            throw new IllegalArgumentException();
        }

        int[] counts0 = new int[3]; // upper, lower, digit
        int[] counts1 = new int[3]; // upper, lower, digit

        // Compare char and count
        for (int i = 0; i < str0.length(); i++) {
            char char0 = str0.charAt(i);
            char char1 = str1.charAt(i);

            updateCounts(char0, counts0);
            updateCounts(char1, counts1);
        }

        // Compare wins and count
        int wins0 = 0, wins1 = 0;
        for (int i = 0; i < 3; i++) {
            if (whenWins(counts0[i], counts1[i]) == 0) {
                wins0++;
            } else {
                wins1++;
            }
        }

        // if 0 return srt0, if 1 return str1
        return whenWins(wins0, wins1) == 0 ? str0 : str1;
    }

    public static int crossingWords(String str0, String str1) {
        int n = 0;

        for (int i = 0; i < str0.length(); i++) {
            for (int j = 0; j < str1.length(); j++) {
                if (str0.charAt(i) == str1.charAt(j)) {
                    n++;
                }
            }
        }
        return n;
    }

    public static String wackyAlphabet(String str, String alphabet) {
        if (!isValidAlphabet(alphabet) || !isAllMinus(str)) {
            throw new IllegalArgumentException();
        }
        StringBuilder output = new StringBuilder();
        StringBuilder input = new StringBuilder(str);

        for (char c : alphabet.toCharArray()) {
            for (int i = 0; i < input.length(); i++) {
                char d = input.charAt(i);
                if (c == d) {
                    output.append(d);
                    input.deleteCharAt(i);
                    i--;
                }
            }
        }
        return output.toString();
    }

    private static void updateCounts(char c, int[] counts) {
        // For Update counts on bestCharacters
        if (Character.isUpperCase(c)) {
            counts[0]++;
        } else if (Character.isLowerCase(c)) {
            counts[1]++;
        } else if (Character.isDigit(c)) {
            counts[2]++;
        }
    }

    private static int whenWins(int n, int m) {
        if (n == m) {
            return 0;
        } else if (n > m) {
            return 0;
        } else // m > n
            return 1;
    }

    private static boolean isValidAlphabet(String alphabet) {
        if (alphabet.length() != 26) {
            return false;
        }
        boolean[] seen = new boolean[26];

        for (char c : alphabet.toCharArray()) {
            // char < a or char > z or the alphabetical position is already seen
            if (c < 'a' || c > 'z' || seen[c - 'a']) {
                return false;
            }
            // Mark as seen
            seen[c - 'a'] = true;
        }
        return true;
    }

    private static boolean isAllMinus(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }
}
