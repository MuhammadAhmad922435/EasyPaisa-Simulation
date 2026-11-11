
import java.util.Scanner;

class Maintest {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       EASYPAISA SIMULATION");
            System.out.println("====================================");
            System.out.println("1. User Menu");
            System.out.println("2. Admin Menu");
            System.out.println("3. View All Accounts");
            System.out.println("0. Exit");
            System.out.println("====================================");

            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    userMenu();
                    break;

                case 2:
                    adminMenu();
                    break;

                case 3:
                    Users.display();
                    break;

                case 0:
                    System.out.println("Thank you for using EasyPaisa Simulation!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    static void userMenu() {

        int choice;

        do {
            System.out.println("\n========== USER MENU ==========");
            System.out.println("1. Create New Account");
            System.out.println("2. Login to Existing Account");
            System.out.println("3. View All Accounts");
            System.out.println("0. Back to Main Menu");
            System.out.println("===============================");

            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    createAccount();
                    break;

                case 2:
                    userAccountMenu();
                    break;

                case 3:
                    Users.display();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    static void createAccount() {

        System.out.println("\n------ CREATE ACCOUNT ------");

        System.out.print("Enter account number: ");
        String accountNo = input.nextLine().trim();

        if (accountNo.isEmpty() || findAccount(accountNo) != null) {
            System.out.println("Invalid or already existing account number.");
            return;
        }

        System.out.print("Enter account holder name: ");
        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        double balance = readDouble("Enter opening balance: ");

        if (balance < 0) {
            System.out.println("Opening balance cannot be negative.");
            return;
        }

        Account account = new Account(accountNo, name, balance);

        Users.push(account, accountNo);

        System.out.println("Account created successfully!");
        System.out.println(account);
    }

    static void userAccountMenu() {

        System.out.print("\nEnter your account number: ");
        String accountNo = input.nextLine().trim();

        Account account = findAccount(accountNo);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        int choice;

        do {
            System.out.println("\n========== ACCOUNT MENU ==========");
            System.out.println("Account Holder: " + account.getPersonName());
            System.out.println("Account Number: " + account.getAccountNo());
            System.out.println("1. View Account Details");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Cancel Pending Transaction");
            System.out.println("0. Logout");
            System.out.println("==================================");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    System.out.println("\n" + account);
                    break;

                case 2:
                    double depositAmount = readDouble("Enter amount to deposit: ");

                    if (depositAmount > 0) {
                        account.depositOp(depositAmount);
                    } else {
                        System.out.println("Amount must be greater than zero.");
                    }
                    break;

                case 3:
                    double withdrawAmount = readDouble("Enter amount to withdraw: ");

                    if (withdrawAmount > 0) {
                        account.withdrawOp(withdrawAmount);
                    } else {
                        System.out.println("Amount must be greater than zero.");
                    }
                    break;

                case 4:
                    System.out.print("Enter receiver account number: ");
                    String target = input.nextLine().trim();

                    double transferAmount = readDouble("Enter amount to transfer: ");

                    if (transferAmount > 0) {
                        account.transferOp(target, transferAmount);
                    } else {
                        System.out.println("Amount must be greater than zero.");
                    }
                    break;

                case 5:
                    System.out.print("Enter pending transaction ID to cancel: ");
                    String transactionId = input.nextLine().trim();

                    account.CancelTransction(transactionId);
                    break;

                case 0:
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    static void adminMenu() {

        int choice;

        do {
            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. Process Next Transaction");
            System.out.println("2. Process All Pending Transactions");
            System.out.println("3. Generate Transaction Report");
            System.out.println("4. View All Accounts");
            System.out.println("0. Back to Main Menu");
            System.out.println("================================");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    Transctions.StartTransctions();
                    break;

                case 2:
                    if (Transctions.isEmpty()) {
                        System.out.println("There are no pending transactions.");
                    } else {
                        while (!Transctions.isEmpty()) {
                            Transctions.StartTransctions();
                        }
                    }
                    break;

                case 3:
                    TransactionHistory.generateReport();
                    break;

                case 4:
                    Users.display();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    static Account findAccount(String accountNo) {

        Users.Node current = Users.Head;

        while (current != null) {

            if (current.getAccountId().equalsIgnoreCase(accountNo)) {
                return current.getUser();
            }

            current = current.next;
        }

        return null;
    }

    static int readInt(String message) {

        while (true) {

            System.out.print(message);
            String value = input.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    static double readDouble(String message) {

        while (true) {

            System.out.print(message);
            String value = input.nextLine().trim();

            try {
                double amount = Double.parseDouble(value);

                if (Double.isFinite(amount)) {
                    return amount;
                }

                System.out.println("Please enter a valid amount.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
}
