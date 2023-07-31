import java.util.Scanner;

public class TimerStopwatch {
    private static boolean isRunning = false;
    private static long startTime = 0;
    private static long elapsedTime = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Timer\n2. Stopwatch");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter the timer duration in seconds: ");
            int duration = scanner.nextInt();
            timer(duration);
        } else if (choice == 2) {
            System.out.println("Press Enter to start the stopwatch.");
            scanner.nextLine(); // Consume the newline character left by previous nextInt() call
            stopwatch();
        } else {
            System.out.println("Invalid choice!");
        }

        scanner.close();
    }

    private static void timer(int duration) {
        try {
            System.out.println("Timer started. Duration: " + duration + " seconds.");
            long endTime = System.currentTimeMillis() + (duration * 1000);

            while (System.currentTimeMillis() < endTime) {
                Thread.sleep(100);
            }

            System.out.println("Timer finished!");
        } catch (InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }

    private static void stopwatch() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("[S] Start  [P] Pause  [R] Reset  [Enter] Exit");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "S":
                    startStopwatch();
                    break;
                case "P":
                    pauseStopwatch();
                    break;
                case "R":
                    resetStopwatch();
                    break;
                default:
                    System.out.println("Stopwatch stopped. Final Elapsed Time: " + (elapsedTime / 1000) + " seconds.");
                    scanner.close();
                    return;
            }
        }
    }

    private static void startStopwatch() {
        if (!isRunning) {
            System.out.println("Stopwatch started.");
            startTime = System.currentTimeMillis() - elapsedTime;
            isRunning = true;
        }
    }

    private static void pauseStopwatch() {
        if (isRunning) {
            System.out.println("Stopwatch paused.");
            elapsedTime = System.currentTimeMillis() - startTime;
            isRunning = false;
        }
    }

    private static void resetStopwatch() {
        System.out.println("Stopwatch reset.");
        isRunning = false;
        elapsedTime = 0;
    }
}
