import java.util.Scanner;

public class Kaprekar{
    public static void main(String[] args){
        int initial=0, asc=0, desc=0, ans=0, iteration;

        do{
            initial = readInput();
            if(!checkValidity(initial)){
                System.out.println("Invalid input, must use at least two different digits\n");
            }
            
        }while(!checkValidity(initial));

        //sort input in ascending
        asc = sortAsc(initial);

        //sort input to descending
        desc = sortDesc(initial);

        //output values
        System.out.println("\nInitial Descending Ascending Difference");
        iteration = calcKaprekar(initial, asc, desc);
        System.out.println("_____________________________________________\n");
        System.out.printf("Iteration occured : %d\n", iteration);
    }

    public static int readInput(){
        String init;
        int inti;
        Scanner in = new Scanner(System.in);

        do{
            System.out.println("\nInput a four digit number (using at least two different digits):");
            System.out.print(">>");
            init = in.next();
            if(init.length()!=4){
                System.out.println("Invalid input, must input 4 digits\n");
            }
            
        }while(init.length()!=4);
        
        inti = Integer.parseInt(init);
        in.close();
        return inti;
    }

    public static boolean checkValidity(int i){
        //boolean check;
        int[] invArray = new int[]{0000,1111,2222,3333,4444,5555,6666,7777,8888,9999};
        for(int val:invArray){
            if(i==val){
                return false;
            }
        }
        return true;
    }

    public static int sortDesc(int num) {    
        // our number we will return
        int sortedNumber = 0;
        
        // loop with a maximum number int
        // perform loop in reverse
        for (int i = 9; i >= 0; i--) {
            // set a local number variable
            int tmpNumber = num;
            // while greater than 0
            while (tmpNumber > 0) {
                // get the digit
                int digit = tmpNumber % 10;
                // check for the greatest digit in the given number
                if (digit == i) {
                    // build the sortedNumber value
                    sortedNumber *= 10;
                    sortedNumber += digit;
                }
                // reduce the loop variable
                tmpNumber /= 10;
            }
        }
        // return our sorted number
        //leading zeroes problem
        if(countDig(sortedNumber)==1){
            sortedNumber = sortedNumber*1000;
        }
        else if(countDig(sortedNumber)==2){
            sortedNumber = sortedNumber*100;
        }
        else if(countDig(sortedNumber)==3){
            sortedNumber = sortedNumber*10;
        }
        return sortedNumber;
      }

      // method to find the number of digits present in the number n 
      public static int countDig(int n){
        int count = 0;  
        while(n != 0){
            // removing the last digit of the number n  
            n = n / 10;  
            // increasing count by 1  
            count = count + 1;  
        }
        return count; 
      }

      public static int sortAsc(int num) {    
        // number to return
        int sortedNumber = 0;
        
        // loop with a maximum number int
        // perform loop in reverse
        for (int i = 0; i <= 9; i++) {
            // set a local number variable
            int tmpNumber = num;
            // while greater than 0
            while (tmpNumber > 0) {
                // get the digit
                int digit = tmpNumber % 10;
                // check for the greatest digit in the given number
                if (digit == i) {
                    // build the sortedNumber value
                    sortedNumber *= 10;
                    sortedNumber += digit;
                }
                // reduce the loop variable
                tmpNumber /= 10;
            }
        }
        // return our sorted number
        return sortedNumber;
      }

      public static int calcKaprekar(int i, int a, int d){
          //start
          int dif = d-a, tint = i, count = 0;
          do{
            System.out.printf("%-7d ", tint);
            System.out.printf("%-10d ", d);
            if(countDig(a)==1){
                System.out.printf("000%-6d ", a);
            }
            else if(countDig(a)==2){
                System.out.printf("00%-7d ", a);
            }
            else if(countDig(a)==3){
                System.out.printf("0%-8d ", a);
            }
            else{
                System.out.printf("%-9d ", a);
            }
            System.out.printf("%-4d\n", dif);
            tint = dif;
            d = sortDesc(dif);
            a = sortAsc(dif);
            dif = d-a;
            count++;
          }while(tint!=6174);
          return count;
      }

}