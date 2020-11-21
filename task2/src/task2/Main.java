package task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            List<Point> list = new ArrayList<Point>();
            while (reader.ready()) {
                String str = reader.readLine();
                String[] pointStr = str.split(" ");
                list.add(new Point(Double.parseDouble(pointStr[0]), Double.parseDouble(pointStr[1])));
            }

            Point A = list.get(0);
            Point B = list.get(1);
            Point C = list.get(2);
            Point D = list.get(3);

            reader = new BufferedReader(new FileReader(args[1]));

            while (reader.ready()) {
                String str = reader.readLine();
                String[] pointStr = str.split(" ");
                proverka(A, B, C, D, new Point(Double.parseDouble(pointStr[0]), Double.parseDouble(pointStr[1])));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void proverka(Point A, Point B, Point C, Point D, Point M) {
        if (A.x == M.x && A.y == M.y || B.x == M.x && B.y == M.y ||
                C.x == M.x && C.y == M.y || D.x == M.x && D.y == M.y)
            System.out.println(0);

        else {
            //fAB(P) ≡   (Ax - Bx) (My - By) - (Ay - By) (Mx - Bx)
            double fAB = (A.x - B.x) * (M.y - B.y) - (A.y - B.y) * (M.x - B.x);
            double fBC = (B.x - C.x) * (M.y - C.y) - (B.y - C.y) * (M.x - C.x);
            double fCD = (C.x - D.x) * (M.y - D.y) - (C.y - D.y) * (M.x - D.x);
            double fDA = (D.x - A.x) * (M.y - A.y) - (D.y - A.y) * (M.x - A.x);
            if (fAB == 0 || fBC == 0 || fCD == 0 || fDA == 0) System.out.println(1);
            else if (fAB > 0 && fBC > 0 && fCD > 0 && fDA > 0) System.out.println(2);
            else System.out.println(3);
        }
    }
}
