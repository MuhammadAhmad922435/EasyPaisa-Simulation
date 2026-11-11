
class TransactionHistory {

    static class List {

        Transctions.Node Head;
        Transctions.Node Tail;
        int count = 0;

        public void push(Transctions.Node process) {

            process.next = null;
            process.prev = null;

            if (Head == null) {
                Head = process;
                Tail = process;
            } else {
                Tail.next = process;
                process.prev = Tail;
                Tail = process;
            }

            count++;
        }

        public void display() {

            Transctions.Node curr = Head;

            if (curr == null) {
                System.out.println("No Transactions Found");
                return;
            }

            while (curr != null) {

                System.out.println("Transaction ID: " + curr.transctionId);
                System.out.println("Source: " + curr.source);
                System.out.println("Target: " + curr.target);
                System.out.println("Type: " + curr.type);
                System.out.println("Amount: " + curr.amount);
                System.out.println("Status: " + curr.status);
                System.out.println("---------------------------");

                curr = curr.next;
            }
        }
    }

    static List successful = new List();
    static List failed = new List();
    static List canceled = new List();

    public static void store(Transctions.Node process) {

        if (process == null) {
            return;
        }

        if (process.status.equalsIgnoreCase("Successful")) {
            successful.push(process);
        } else if (process.status.equalsIgnoreCase("Canceled")) {
            canceled.push(process);
        } else {
            failed.push(process);
        }
    }

    public static void generateReport() {

        System.out.println("\n===== BANK TRANSACTION REPORT =====");

        System.out.println("\nSuccessful Transactions: " + successful.count);
        successful.display();

        System.out.println("\nFailed Transactions: " + failed.count);
        failed.display();

        System.out.println("\nCancelled Transactions: " + canceled.count);
        canceled.display();

        int total = successful.count + failed.count + canceled.count;

        System.out.println("\nTotal Transactions: " + total);
        System.out.println("==================================");
    }
}
