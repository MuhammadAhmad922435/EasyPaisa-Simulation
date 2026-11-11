
class Users {

    static class Node {

        private Account user;
        public String Account_Id;
        Node next;
        Node prev;

        public String getAccountId() {
            return user.getAccountNo();
        }

        public Account getUser() {
            return user;
        }

        public Node(Account user, String Account_Id) {
            this.user = user;
            this.Account_Id = Account_Id;
            this.next = null;
            this.prev = null;
        }
    }

    static Node Head;
    static Node tail;

    public static void push(Account user, String Account_Id) {

        if (user == null) {
            System.out.println("Invalid Account");
            return;
        }

        if (accountExists(Account_Id)) {
            System.out.println("Account Already Exists");
            return;
        }

        Node n = new Node(user, Account_Id);

        if (isEmpty()) {
            Head = n;
            tail = n;
            return;
        }

        tail.next = n;
        n.prev = tail;
        tail = n;
    }

    public static String pop(String Account_Id) {

        Node curr = Head;

        if (isEmpty()) {
            return "No account Exists in Application";
        }

        while (curr != null) {

            if (curr.getAccountId().equals(Account_Id)) {

                if (curr.prev == null) {
                    Head = curr.next;
                } else {
                    curr.prev.next = curr.next;
                }

                if (curr.next == null) {
                    tail = curr.prev;
                } else {
                    curr.next.prev = curr.prev;
                }

                String id = curr.getAccountId();

                curr.next = null;
                curr.prev = null;

                return id;
            }

            curr = curr.next;
        }

        return "No Account Found With These Credentials";
    }

    public static boolean isEmpty() {
        return Head == null;
    }

    public static boolean accountExists(String Account_Id) {

        if (Account_Id == null) {
            return false;
        }

        Node curr = Head;

        while (curr != null) {

            if (curr.getAccountId().equals(Account_Id)) {
                return true;
            }

            curr = curr.next;
        }

        return false;
    }

    public static void display() {

        if (isEmpty()) {
            System.out.println("Nothing to print");
            return;
        }

        Node curr = Head;

        while (curr != null) {
            System.out.println(curr.getUser());
            System.out.println("----------------------");
            curr = curr.next;
        }
    }

    public static double withdraw(String AccountId, double balance) {

        Node curr = Head;

        if (!Double.isFinite(balance) || balance <= 0) {
            System.out.println("Invalid Balance");
            return 0;
        }

        while (curr != null) {

            if (curr.getAccountId().equals(AccountId)) {

                if (curr.getUser().withdraw(balance)) {
                    System.out.println("Withdraw Successfully");
                    return balance;
                } else {
                    System.out.println("Insufficient Balance");
                    return 0;
                }
            }

            curr = curr.next;
        }

        return -1;
    }

    public static boolean deposit(String AccountId, double balance) {

        if (!Double.isFinite(balance) || balance <= 0) {
            System.out.println("Invalid Balance");
            return false;
        }

        Node curr = Head;

        while (curr != null) {

            if (curr.getAccountId().equals(AccountId)) {

                double newBalance =
                        curr.getUser().getAccountBalance() + balance;

                if (!Double.isFinite(newBalance)) {
                    System.out.println("Invalid Deposit Amount");
                    return false;
                }

                curr.getUser().deposit(balance);

                System.out.println("Deposited Successfully");
                return true;
            }

            curr = curr.next;
        }

        return false;
    }

    public static boolean transfer(String sourceId,
                                   String targetId,
                                   double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Invalid Transfer Amount");
            return false;
        }

        Node source = Head;
        Node target = Head;

        while (source != null
                && !source.getAccountId().equals(sourceId)) {
            source = source.next;
        }

        while (target != null
                && !target.getAccountId().equals(targetId)) {
            target = target.next;
        }

        if (source == null) {
            System.out.println("Source Account Not Found");
            return false;
        }

        if (target == null) {
            System.out.println("Target Account Not Found");
            return false;
        }

        if (source == target) {
            System.out.println("Cannot Transfer To The Same Account");
            return false;
        }

        double sourceBalance =
                source.getUser().getAccountBalance();

        double targetBalance =
                target.getUser().getAccountBalance();

        if (sourceBalance < amount) {
            System.out.println("Insufficient Balance");
            return false;
        }

        if (!Double.isFinite(targetBalance + amount)) {
            System.out.println("Invalid Transfer Amount");
            return false;
        }

        source.getUser().withdraw(amount);
        target.getUser().deposit(amount);

        System.out.println("Transfer Successfully");
        return true;
    }

    public static void findById(String Id) {

        Node curr = Head;

        while (curr != null) {

            if (curr.getAccountId().equals(Id)) {
                System.out.println("Found!.....\n" + curr.getUser());
                return;
            }

            curr = curr.next;
        }

        System.out.println("No Data Found With This Id");
    }
}
