
import java.util.UUID;

class Transctions {

    static class Node {

        public String transctionId;
        public String source;
        public String target;
        public double amount;
        public String type;
        public String status;

        Node next;
        Node prev;

        public Node(String tId, String source, String target,
                    double amount, String type) {

            this.transctionId = tId;
            this.source = source;
            this.target = target;
            this.amount = amount;
            this.type = type;
            this.status = "Pending";

            this.next = null;
            this.prev = null;
        }

        public String getTransctionId() {
            return transctionId;
        }

        public String getSource() {
            return source;
        }

        public String getTarget() {
            return target;
        }

        public double getAmount() {
            return amount;
        }

        public String getType() {
            return type;
        }

        public String getStatus() {
            return status;
        }
    }

    static Node Front;
    static Node Rear;

    public void Enqueue(String tId, String source,
                        String target, String type, double amount) {

        Node n = new Node(tId, source, target, amount, type);

        if (isEmpty()) {
            Front = n;
            Rear = n;
            return;
        }

        Rear.next = n;
        n.prev = Rear;
        Rear = n;
    }

    public static Node Dequeue() {

        if (isEmpty()) {
            return null;
        }

        Node removed = Front;

        Front = Front.next;

        if (Front == null) {
            Rear = null;
        } else {
            Front.prev = null;
        }

        removed.next = null;
        removed.prev = null;

        return removed;
    }

    public static boolean cancelTransction(String tid) {

        if (tid == null) {
            return false;
        }

        Node curr = Front;

        while (curr != null) {

            if (curr.transctionId.equals(tid)
                    && curr.status.equalsIgnoreCase("Pending")) {

                if (curr.prev == null) {
                    Front = curr.next;
                } else {
                    curr.prev.next = curr.next;
                }

                if (curr.next == null) {
                    Rear = curr.prev;
                } else {
                    curr.next.prev = curr.prev;
                }

                curr.status = "Canceled";

                curr.next = null;
                curr.prev = null;

                TransactionHistory.store(curr);

                return true;
            }

            curr = curr.next;
        }

        return false;
    }

    public static boolean isEmpty() {
        return Front == null;
    }

    public static void StartTransctions() {
        Admin.ProcessTransction();
    }

    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    public static void display() {

        Node curr = Front;

        if (curr == null) {
            System.out.println("No Pending Transactions");
            return;
        }

        while (curr != null) {

            System.out.println("Transaction Id : " + curr.transctionId);
            System.out.println("Source : " + curr.source);
            System.out.println("Target : " + curr.target);
            System.out.println("Type : " + curr.type);
            System.out.println("Amount : " + curr.amount);
            System.out.println("Status : " + curr.status);

            System.out.println("---------------------------");

            curr = curr.next;
        }
    }
}
