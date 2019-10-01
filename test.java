import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Map<String, String> glob = new LinkedHashMap<>();
        Map<String, Integer> harga = new LinkedHashMap<>();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String masuk = in.nextLine();
            if (masuk.equalsIgnoreCase("")) {
                break;
            }
            if (masuk.startsWith("how much is ")) {
                String[] tanya = masuk.substring(11).split(" ");
                String kumpul = "";
                String biasa = "";
                for (int i = 0; i < tanya.length; i++) {
                    if (glob.get(tanya[i]) != null) {
                        kumpul = kumpul + glob.get(tanya[i]) + "";
                        biasa = biasa + tanya[i] + " ";
                    }
                }
                System.out.println(biasa.trim() + " is " + convert(kumpul));
            } else if (masuk.endsWith("Credits")) {
                masuk = removeWord(masuk, "Credits");
                masuk = removeWord(masuk, "is");
                String[] tanya = masuk.split(" ");

                int result = 0;
                String kumpul = "";

                for (int i = 0; i < tanya.length; i++) {
                    if (glob.get(tanya[i]) != null) {
                        kumpul = kumpul + glob.get(tanya[i]) + "";
                    }
                }
                result = convert(kumpul);
                harga.put(tanya[tanya.length - 2], Integer.parseInt(tanya[tanya.length - 1]) / result);
            } else if (masuk.startsWith("how many Credits is ")) {
                String[] tanya = masuk.substring(19).split(" ");
                int result = 0;
                String kumpul = "";
                String biasa = "";
                for (int i = 0; i < tanya.length; i++) {
                    if (glob.get(tanya[i]) != null) {
                        kumpul = kumpul + glob.get(tanya[i]) + "";
                        biasa = biasa + tanya[i] + " ";
                    }
                }
                Integer hargaBarang = harga.get(tanya[tanya.length - 2]);
                System.out.println(biasa.trim() + " " + tanya[tanya.length - 2] + " " + hargaBarang * convert(kumpul) + " Credits");
            } else if (masuk.contains(" is ") && masuk.split(" is ").length == 2) {
                glob.put(masuk.split(" is ")[0], masuk.split(" is ")[1]);
            }else{
                System.out.println("I have no idea what you are talking about");
            }
        }
    }

    public static int convert(String str) {
        int len = str.length();

        str = str + " ";
        int result = 0;
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            char next_char = str.charAt(i + 1);

            if (ch == 'M') {
                result += 1000;
            } else if (ch == 'C') {
                if (next_char == 'M') {
                    result += 900;
                    i++;
                } else if (next_char == 'D') {
                    result += 400;
                    i++;
                } else {
                    result += 100;
                }
            } else if (ch == 'D') {
                result += 500;
            } else if (ch == 'X') {
                if (next_char == 'C') {
                    result += 90;
                    i++;
                } else if (next_char == 'L') {
                    result += 40;
                    i++;
                } else {
                    result += 10;
                }
            } else if (ch == 'L') {
                result += 50;
            } else if (ch == 'I') {
                if (next_char == 'X') {
                    result += 9;
                    i++;
                } else if (next_char == 'V') {
                    result += 4;
                    i++;
                } else {
                    result++;
                }
            } else { // if (ch == 'V')
                result += 5;
            }
        }
        return result;
    }

    public static String removeWord(String string, String word) {
        if (string.contains(word)) {

            String tempWord = word + " ";
            string = string.replaceAll(tempWord, "");

            tempWord = " " + word;
            string = string.replaceAll(tempWord, "");
        }

        return string;
    }
}
