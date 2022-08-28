import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new Connection().getUrl().openConnection();
        httpURLConnection.setRequestMethod("GET");

        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream()))) {
            result.append(reader.readLine());
        }

        /// Вывод url + цитата
        System.out.println("GET " + new Connection().getUrl());
        System.out.println(result);

        /// Строка слов, массив символов
        String words = result.toString().replaceAll("[()<\\[\\]>.?;,:№#$%'^&*! -]", "").toLowerCase();
        String[] chars = words.split("");

        /// Коллекция уникальных значений массива chars
        Set<String> set = new HashSet<>(Arrays.asList(chars));

        /// Словарь для записи ключ - значение
        HashMap<String, String> hashMap = new HashMap<>();

        /// Счетчик и сумма повторяющихся элементов
        int countRepeatedElements = 0;
        int sumAllRepeat = 0;

        for (String str : set) {
            countRepeatedElements = 0;
            for (int i = 0; i < chars.length; i++) {
                if (str.equals(chars[i])) {
                    countRepeatedElements++;
                }
            }

            /// Переменная, для установки падежа раз(а)
            boolean b = countRepeatedElements % 10 == 2 ||
                    countRepeatedElements % 10 == 3 ||
                    countRepeatedElements % 10 == 4;

            if ((countRepeatedElements < 10 && b) || (countRepeatedElements > 20 && b)) {
                hashMap.put(str, countRepeatedElements + " раза");
            } else {
                hashMap.put(str, countRepeatedElements + " раз");
            }

            /// Сумма всех повторяющихся элементов
            sumAllRepeat += countRepeatedElements;
        }

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        /**
         * Продолжение задания (со звездочкой)
         **/

        // Среднее значение частоты
        double averageValue = 0;

        // Подсчет среднего значения частоты
        averageValue = (double) sumAllRepeat / hashMap.size();

        System.out.printf("Среднее значение частоты %d / %d = %f\n", sumAllRepeat, hashMap.size(), averageValue);

        /// Список из символов значения которых близки к среднему
        List<String> charsEqualsAverage = new ArrayList<>();

        /// Сравнение двух чисел и добавление в список
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            boolean isSuitableNumber = Integer.valueOf(entry.getValue().substring(0, entry.getValue().indexOf(' ')))
                    .equals((int)averageValue);

            if (isSuitableNumber) {
                charsEqualsAverage.add(entry.getKey());
            }
        }

        /// Преобразования списка в массив символов (char[])
        char[] charSymbol= charsEqualsAverage.toString().replaceAll("[\\[\\], ]", "").toCharArray();

        /// Вывод символов и их ключей в соответствии с ASCII
        System.out.printf("Символы, которые соответствуют условию наиболее близкого значения частоты к среднему значанию: ");
        for (int i = 0; i < charSymbol.length - 1; i++) {
            System.out.printf("%c(%d), ", charSymbol[i], (int) charSymbol[i]);
        }
        System.out.printf("%c(%d)", charSymbol[charSymbol.length - 1], (int) charSymbol[charSymbol.length - 1]);
    }
}
