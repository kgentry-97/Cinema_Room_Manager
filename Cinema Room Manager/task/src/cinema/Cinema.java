package cinema;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Cinema {

    static int totalticketsSold = 0;
    static int requestedRows = 0;
    static int requestedSeats = 0;
    static int totalIncome = 0;
    static int totalSeats = 0;
    static int rows =0 ;
    static int seats = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean exit = false;

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        String[][] seating = new String[rows + 1][seats + 1];
        fillSeating(seating);
        totalSeats = rows * seats;

        do {
            System.out.println("");
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    showTheSeats(seating);
                    break;
                case 2:
                    buyTheTicket(seating);
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invaild input");

            }

        } while (!exit);

    }

    private static void buyTheTicket(String[][] seating) {
        boolean repeat = false;

        do {
            repeat = false;
            System.out.println("Enter a row number:");
            requestedRows = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            requestedSeats = scanner.nextInt();

            if (requestedRows <= rows && requestedSeats <= seats) {

                if (seating[requestedRows][requestedSeats] != "B ") {
                    seating[requestedRows][requestedSeats] = "B ";
                    totalticketsSold += 1;

                    System.out.print("Ticket price: ");
                    if (totalSeats < 60 || rows / 2 >= requestedRows) {
                        System.out.print("$10");
                        totalIncome += 10;
                    } else {
                        System.out.println("$8");
                        totalIncome += 8;
                    }

                } else {
                    System.out.println("That ticket has already been purchased!");
                    repeat = true;
                }

            } else {
                System.out.println("Wrong input!");
                repeat = true;

            }
        }while (repeat);
    }

    private static void showTheSeats(String[][] seating) {
        System.out.println("Cinema:");
        for (String[] row : seating) {
            for (String seat : row) {
                System.out.print(seat);
            }
            System.out.println("");
        }
    }

    private static void fillSeating(String[][] seating) {
        for (int i = 0; i < seating.length; i++) {
            for (int j = 0; j < seating[i].length; j++) {
                if (j == 0 && i == 0) {
                    seating[i][j] = " ";
                } else if (j == 0) {
                    seating[i][j] = i + " ";
                } else if (i == 0) {
                    seating[i][j] = " " + j;
                } else {
                    seating[i][j] = "S ";
                }
            }
        }
    }

    private static void statistics() {
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        double soldPercent = (double) totalticketsSold/totalSeats;
        System.out.println("Number of purchased tickets: " + totalticketsSold);
        System.out.println("Percentage: " +  format.format(soldPercent));
        System.out.println("Current income: $" + totalIncome);
        System.out.println("Total income: $" + theaterIncome()  );

    }

    private static int theaterIncome(){
        int profit = 0;
        if( totalSeats < 60){
            profit = totalSeats * 10;
        }
        else {
            int frontHalf = rows / 2;
            profit = (frontHalf * seats) * 10 +( (rows - frontHalf) *seats) * 8;
        }
        return profit;
    }
}