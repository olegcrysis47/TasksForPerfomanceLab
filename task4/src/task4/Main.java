package task4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            SimpleDateFormat simple = new SimpleDateFormat("HH:mm");

            Map<Date, Integer> enterTime = new HashMap<Date, Integer>();
            Map<Date, Integer> exitTime = new HashMap<Date, Integer>();

            while (reader.ready()) {
                String str = reader.readLine();
                String[] strings = str.split(" ");
                Date date0 = simple.parse(strings[0]);
                Date date1 = simple.parse(strings[1]);

                if (enterTime.containsKey(date0)) {
                    int i = enterTime.get(date0) + 1;
                    enterTime.put(date0, i);
                }
                else enterTime.put(date0, 1);

                if (exitTime.containsKey(date1)) {
                    int i = exitTime.get(date1) + 1;
                    exitTime.put(date1, i);
                }
                else exitTime.put(date1, 1);
            }

            int count = 0;//количество посетителей в текущуй момент времени
            int max = 0;
            List<Date> enterList = new ArrayList<>();
            List<Date> exitList = new ArrayList<>();

            for (int i = 8; i < 20; i++) {
                for (int j = 0; j <= 59; j++) {
                    String str = i + ":" + j;
                    Date date = simple.parse(str);//время в течении дня
                    if (enterTime.containsKey(date)) count += enterTime.get(date);
                    if (exitTime.containsKey(date)) count -= exitTime.get(date);

                    if (count > max) {
                        max = count;
                        enterList.clear();
                        exitList.clear();
                        enterList.add(date);
                    } else if (count < max && enterList.size() > exitList.size()) {
                        exitList.add(date);
                    } else if (count == max && enterList.size() == exitList.size()) {
                        enterList.add(date);
                    }
                }
            }

            for (int i = 0; i < enterList.size(); i++) {
                System.out.println(simple.format(enterList.get(i)) + " " + simple.format(exitList.get(i))); //
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
