import java.io.*;
import java.util.Scanner;


public class solution {
        public static void main(String []args) throws Exception {
            solvepart1();
        }

        private static void solvepart1() throws Exception {
            
        }

        private static void solvepart2() throws Exception {
            
        }

        private static String[] fileRead(String name) throws Exception {
            File file = new File(name);
            Scanner scanner = new Scanner(file);
            String data = scanner.nextLine();
            while (scanner.hasNextLine()) {
                data = data + "\n" + scanner.nextLine();
            }
            scanner.close();
            String[] splitdata = data.split(System.lineSeparator());
            return splitdata;
        }

}