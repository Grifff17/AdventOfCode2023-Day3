import java.io.*;
import java.util.Scanner;


public class solution {
        public static void main(String []args) throws Exception {
            solvepart1();
        }

        private static void solvepart1() throws Exception {
            int sum = 0;
            int totalprints = 0;
            String[] engine = fileRead("input.txt");

            /*do for each row*/
            for(int currentRow = 0; currentRow < engine.length; currentRow++) {
                String row = engine[currentRow];
                /*do for each column*/
                for(int currentColumn = 0; currentColumn < row.length(); currentColumn++) {
                    char part = row.charAt(currentColumn);
                    if (Character.isDigit(part)) {
                        boolean going = true;
                        int i = currentColumn;
                        int partID = 0;
                        /*get length and value of part*/
                        while(going) {
                            partID = partID * 10;
                            partID = partID + Character.getNumericValue(row.charAt(i));
                            if(i-currentColumn < 2 && i < row.length()-1 && Character.isDigit(row.charAt(i+1))) {
                                i++;
                            } else {
                                going = false;
                            }
                        }
                        int partLen = String.valueOf(partID).length();
                        boolean isPart = false;
                        int j;
                        /*check if part is actually a part*/
                        boolean hasTop = (currentRow != 0);
                        boolean hasBottom = (currentRow != engine.length-1);
                        boolean hasLeft = (currentColumn != 0);
                        boolean hasRight = ((currentColumn + partLen) != row.length());
                        if(hasTop) {
                            for(j = currentColumn; j < partLen + currentColumn; j++) {
                                if (!Character.isDigit(engine[currentRow-1].charAt(j)) && engine[currentRow-1].charAt(j) != '.' ) {
                                    isPart = true;
                                }
                            }
                        }
                        if(hasBottom) {
                            for(j = currentColumn; j < partLen + currentColumn; j++) {
                                if (!Character.isDigit(engine[currentRow+1].charAt(j)) && engine[currentRow+1].charAt(j) != '.' ) {
                                    isPart = true;
                                }
                            }
                        }
                        if(hasLeft) {
                            for(j = currentRow - ((hasTop) ? 1 : 0); j < currentRow + 1 + ((hasBottom) ? 1 : 0); j++) {
                                if (!Character.isDigit(engine[j].charAt(currentColumn-1)) && engine[j].charAt(currentColumn-1) != '.' ) {
                                    isPart = true;
                                }
                            }
                        }
                        if(hasRight) {
                            for(j = currentRow - ((hasTop) ? 1 : 0); j < currentRow + 1 + ((hasBottom) ? 1 : 0); j++) {
                                if (!Character.isDigit(engine[j].charAt(currentColumn+partLen)) && engine[j].charAt(currentColumn+partLen) != '.' ) {
                                    isPart = true;
                                }
                            }
                        }
                        if(isPart) {
                            sum = sum + partID;
                            if(totalprints < 100) {
                                totalprints++;
                                System.out.println(partID + " row:" + currentRow + "column:" + currentColumn);
                            }
                        }
                        currentColumn += partLen;
                    }
                }
            }
            System.out.println(sum);
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