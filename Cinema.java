package cinema;
import java.util.Scanner;

public class Cinema {

    public static void showSeats(char[][] arr, int rows, int seats) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0 && j > 0 && j < seats) {
                    System.out.print(j + " ");
                } else if (i == 0 && j == seats) {
                    System.out.println(j);
                } else if (i > 0 && j == 0) {
                    System.out.print(i + " ");
                } else if (i > 0 && j > 0 && j < seats) {
                    System.out.print(arr[i][j] + " ");
                } else if (i > 0 && j == seats) {
                    System.out.println(arr[i][j]);
                }
            }
        }
    }

    public static void buyTicket(char[][] arr, int rows, int seats, int[] statistics) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int buyTicketRow;
        int buyTicketSeat;
        while(true) {
            System.out.println("Enter a row number:");
            buyTicketRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            buyTicketSeat = scanner.nextInt();
            if (buyTicketRow < 1 || buyTicketRow > rows || buyTicketSeat < 1 || buyTicketSeat > seats) {
                System.out.println("\nWrong input!\n");
            } else if (arr[buyTicketRow][buyTicketSeat] == 'B') {
                System.out.println("\nThat ticket has already been purchased!\n");
            } else {
                break;
            }
        }
        arr[buyTicketRow][buyTicketSeat] = 'B';
        statistics[0] += 1;
        if (rows * seats <= 60 || buyTicketRow <= rows / 2) {
            statistics[1] += 10;
            System.out.println("Ticket price: $10");
        } else {
            statistics[1] += 8;
            System.out.println("Ticket price: $8");
        }
    }

    public static void showStatistics(int[] statistics, int rows, int seats) {
        System.out.println();
        System.out.println("Number of purchased tickets: " + statistics[0]);
        System.out.printf("Percentage: %.2f%%%n", (statistics[0] / (rows * seats / 100.00)));
        System.out.println("Current income: $" + statistics[1]);
        int totalIncome;
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            totalIncome = (rows / 2 * seats * 10) + ((rows - rows / 2) * seats * 8);
        }
        System.out.println("Total income: $" + totalIncome);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        char[][] arr = new char[rows + 1][seats + 1];
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i > 0 && j > 0 ) {
                    arr[i][j] = 'S';
                }
            }
        }
        int[] statistics = {0, 0};
        while (true) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            if (choice == 1) {
                showSeats(arr, rows, seats);
            } else if (choice == 2) {
                buyTicket(arr, rows, seats, statistics);
            } else if (choice == 3) {
                showStatistics(statistics, rows, seats);
            } else if (choice == 0) {
                break;
            }
        }
    }
}