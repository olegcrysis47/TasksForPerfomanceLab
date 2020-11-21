package task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            List<Integer> list = new ArrayList<Integer>();

            while (reader.ready()) {
                list.add(Integer.parseInt(reader.readLine()));
            }

            Collections.sort(list);

            System.out.printf("%.2f\n", percentile90(list));
            System.out.printf("%.2f\n", mediana(list));
            System.out.printf("%.2f\n", maximum(list));
            System.out.printf("%.2f\n", minimum(list));
            System.out.printf("%.2f\n", average(list));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    //--------------------Calculation of percentile--------------------
    public static double percentile90(List<Integer> list) {
        double rank = 0.9 * (list.size() - 1) + 1;
        int rankInt = (int) rank;
        double rankDouble = rank - rankInt;
        double percentile = (double)list.get(rankInt-1) + rankDouble * ((double)list.get(rankInt) - (double)list.get(rankInt-1));
        return percentile;
    }
    //--------------------Сalculation of the median--------------------
    public static double mediana(List<Integer> list) {
        double mediana = 0;
        if (list.size() % 2 == 0) {
            mediana = (double)(list.get(list.size()/2-1) + list.get(list.size()/2))/2;
        } else mediana = (double)list.get(list.size()/2);
        return mediana;
    }
    //--------------------Сalculation of the maximum value--------------
    public static double maximum(List<Integer> list) {
        int max = list.get(0);
        for (Integer element : list) {
            if (element > max) max = element;
        }
        return max;
    }
    //--------------------Сalculation of the minimum value--------------
    public static double minimum(List<Integer> list) {
        int min = list.get(0);
        for (Integer element : list) {
            if (element < min) min = element;
        }
        return min;
    }
    //--------------------Calculating the average value----------------
    public static double average(List<Integer> list) {
        int sum = 0;
        for (Integer element : list) {
            sum += element;
        }
        return (double)sum/ list.size();
    }
}
