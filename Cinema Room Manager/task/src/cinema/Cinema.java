package cinema;
import java.util.Scanner;
public class Cinema {
    /*
    fillSeats(seats);
        printSeats(seats);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNum = scanner.nextInt();
        System.out.println(); 
        int price = getTicketPrice(seats, row, seatNum);
        System.out.println("Ticket price: $" + price);
        System.out.println(); 
        setSeats(seats, row, seatNum);
        printSeats(seats);   
    
    */
 
       /*
        printSeats(seats);
        fillSeats(seats);
        int income = calculateIncome(rows, columns);        
        System.out.println("Total income:");
        System.out.println("$" + income);
       */
    
    public static void main(String[] args) {
        // Write your code here 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int columns = scanner.nextInt();
        System.out.println(); 
        char[][] seats = new char[rows + 1 ][columns + 1];  
        fillSeats(seats);
        int num_of_purchased_tickets = 0;
        int currentIncome = 0;
        int option;
        do{
            showMenu();
            option = scanner.nextInt();
            System.out.println();
            switch (option) {
                case 1:                    
                    printSeats(seats);
                    break;
                case 2:
                    int row;
                    int seatNum;
                    do {
                        System.out.println("Enter a row number:");
                        row = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatNum = scanner.nextInt();
                    } while(!isValidSeat(seats,row,seatNum));
                    System.out.println();                     
                    buyTicket(seats, row, seatNum);
                    num_of_purchased_tickets++;
                    currentIncome += getTicketPrice(seats,row,seatNum);
                    break;
                case 3:
                    getStatistics(rows,columns,num_of_purchased_tickets,currentIncome);
                    break;

            }
        } while (option != 0);

    }

    static boolean isValidSeat(char[][] seats, int row, int seatNum) {
        if (row < 1 || row > seats.length - 1 || seatNum < 1 || seatNum > seats[0].length - 1){
            System.out.println();
            System.out.println("Wrong input!");
            System.out.println();
            return false;
        } else if (seats[row][seatNum] == 'B'){
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            return false;
        }
        return true;
    }
    static void buyTicket(char[][] seats, int row, int seatNum){
        int price = getTicketPrice(seats, row, seatNum);
        System.out.println("Ticket price: $" + price);
        System.out.println(); 
        setSeats(seats, row, seatNum);        
    }
    
    static void showMenu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    //create cinema
    static void fillSeats(char[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                seats[i][j] = 'S'; 
                if (i == 0) {
                    seats[i][j] = (char)(j +'0'); 
                } 
                if (j == 0) {
                    seats[i][j] = (char)(i +'0');                    
                }
                seats[0][0] = ' ';   
            }
        }        
    }
    
    static void setSeats(char[][] seats, int row, int seatNum){
        seats[row][seatNum] = 'B';        
    }
    static void printSeats(char[][] seats) {
        System.out.println("Cinema:");
         for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j] + " ");                               
            } 
            System.out.println();                
         } 
                     
        System.out.println(); 
    }

    public static void getStatistics(int rows, int columns, int num_of_purchased_tickets, int currentIncome){
        System.out.println("Number of purchased tickets: " + num_of_purchased_tickets);
        int totalSeats = rows * columns;
        float percentage;
        if (num_of_purchased_tickets > 0 ){
            percentage = (float) num_of_purchased_tickets / totalSeats * 100;
           // System.out.println(String.format("Percentage: %.2f%%%n", percentage));
        } else {
            percentage = 0;
        }
       // float percentage = num_of_purchased_tickets == 0 ? 0 : num_of_purchased_tickets / totalSeats;
       // System.out.printf("Percentage: %.2f%%%n", percentage);
        //System.out.println(String.format("Percentage: %.2f%%", percentage));
        System.out.printf("Percentage: %.2f%%%n", percentage);

        //current income
        System.out.println("Current income: $" + currentIncome);
        //total income
        int totalIncome = calculateIncome(rows,columns);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();

    }
    
    public static int getTicketPrice(char[][] seats, int row, int seatNum){
        int totalSeats = (seats.length-1) * (seats[0].length-1);
        if (totalSeats <= 60) {
            return 10;            
        } else {
            if (row <= (seats.length -1 ) / 2){
                return 10;
            } else {
                return 8; 
            }
            
        }
        
    }
    
    public static int calculateIncome(int rows, int columns){
        int totalSeats = rows * columns;
        int income = 0;
        if(totalSeats <= 60) {
            income = totalSeats *10;
        }
        else{
           income = (rows / 2) * columns * 10 + (rows - (rows / 2)) * columns * 8;
        }        
        return income; 
        
    }
}