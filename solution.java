import java.io.*;
import java.util.*;


public class solution {
        public static void main(String []args) throws Exception {
            solvepart2();
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
                            if(i < row.length()-1 && Character.isDigit(row.charAt(i+1))) {
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
            int sum = 0;
            int totalprints = 0;
            String[] engine = fileRead("input.txt");

            /*do for each row*/
            for(int currentRow = 0; currentRow < engine.length; currentRow++) {
                String row = engine[currentRow];
                /*do for each column*/
                for(int currentColumn = 0; currentColumn < row.length(); currentColumn++) {
                    char gear = row.charAt(currentColumn);
                    if (gear == '*') {
                        List<Integer> adjacentParts = new ArrayList<Integer>();
                        int j;
                        /*collect list of adjacent parts*/
                        boolean hasTop = (currentRow != 0);
                        boolean hasBottom = (currentRow != engine.length-1);
                        boolean hasLeft = (currentColumn != 0);
                        boolean hasRight = ((currentColumn + 1) != row.length());
                        //check left side of gear
                        if(hasLeft && Character.isDigit(row.charAt(currentColumn-1))) {
                            String leftval = scanLeft(row, currentColumn-1);
                            adjacentParts.add(Integer.valueOf(leftval));
                        }
                        //check right side
                        if(hasRight && Character.isDigit(row.charAt(currentColumn+1))) {
                            String rightval = scanRight(row, currentColumn+1);
                            adjacentParts.add(Integer.valueOf(rightval));
                        }
                        //check top side
                        if(hasTop) {
                            String upRow = engine[currentRow-1];
                            //check top center
                            if(Character.isDigit(upRow.charAt(currentColumn))) {
                                String topval = Character.toString(upRow.charAt(currentColumn));
                                if(hasLeft && Character.isDigit(upRow.charAt(currentColumn-1))) {
                                    topval = scanLeft(upRow, currentColumn-1) + topval;
                                }
                                if(hasRight && Character.isDigit(upRow.charAt(currentColumn+1))) {
                                    topval = topval + scanRight(upRow, currentColumn+1);
                                }
                                adjacentParts.add(Integer.valueOf(topval));
                            } else {
                                //check top left
                                if(hasLeft && Character.isDigit(upRow.charAt(currentColumn-1))) {
                                    String topLeftval = scanLeft(upRow, currentColumn-1);
                                    adjacentParts.add(Integer.valueOf(topLeftval));
                                }
                                //check top right
                                if(hasRight && Character.isDigit(upRow.charAt(currentColumn+1))) {
                                    String topRightval = scanRight(upRow, currentColumn+1);
                                    adjacentParts.add(Integer.valueOf(topRightval));
                                }
                            }
                        }
                        //check bottom side
                        if(hasBottom) {
                            String downRow = engine[currentRow+1];
                            //check bottom center
                            if(Character.isDigit(downRow.charAt(currentColumn))) {
                                String topval = Character.toString(downRow.charAt(currentColumn));
                                if(hasLeft && Character.isDigit(downRow.charAt(currentColumn-1))) {
                                    topval = scanLeft(downRow, currentColumn-1) + topval;
                                }
                                if(hasRight && Character.isDigit(downRow.charAt(currentColumn+1))) {
                                    topval = topval + scanRight(downRow, currentColumn+1);
                                }
                                adjacentParts.add(Integer.valueOf(topval));
                            } else {
                                //check bottom left
                                if(hasLeft && Character.isDigit(downRow.charAt(currentColumn-1))) {
                                    String topLeftval = scanLeft(downRow, currentColumn-1);
                                    adjacentParts.add(Integer.valueOf(topLeftval));
                                }
                                //check bottom right
                                if(hasRight && Character.isDigit(downRow.charAt(currentColumn+1))) {
                                    String topRightval = scanRight(downRow, currentColumn+1);
                                    adjacentParts.add(Integer.valueOf(topRightval));
                                }
                            }
                        }
                        currentColumn++;
                        System.out.println(Arrays.toString(adjacentParts.toArray()));
                        if (adjacentParts.size() == 2) {
                            sum = sum + (adjacentParts.get(0) * adjacentParts.get(1)); 
                        }
                    }
                }
            }
            System.out.println(sum);
        }

        private static String scanLeft(String row, int index) {
            boolean searching = true;
            int j = index;
            String leftval = "";
            while(searching) {
                leftval = row.charAt(j) + leftval;
                if(j > 0 && Character.isDigit(row.charAt(j-1))){
                    j--;
                } else {
                    searching = false;
                }
            }
            return leftval;
        }

        private static String scanRight(String row, int index) {
            boolean searching = true;
            int j = index;
            String rightval = "";
            while(searching) {
                rightval = rightval + row.charAt(j);
                if(j < (row.length()-1) && Character.isDigit(row.charAt(j+1))){
                    j++;
                } else {
                    searching = false;
                }
            }
            return rightval;
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