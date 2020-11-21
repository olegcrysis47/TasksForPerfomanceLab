package task3;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Main {
    public static double[] massive = new double[16];

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[args.length];
        for (int i = 0; i < args.length; i++) {
            threads[i] = new CashThread(new File(args[i]));
            threads[i].start();
            threads[i].join();
        }

        double max = massive[0];
        int interval = 1;

        for (int i = 1; i < massive.length; i++) {
            if (massive[i] > max) {
                max = massive[i];
                interval = i + 1;
            }
        }

        System.out.println(interval);
    }

    public static class CashThread extends Thread {
        File file;

        public CashThread(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                synchronized (massive) {
                    for (int i = 0; i < massive.length; i++) {
                        massive[i] = massive[i] + Double.parseDouble(reader.readLine());
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
