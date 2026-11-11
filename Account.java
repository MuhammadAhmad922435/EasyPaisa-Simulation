
class Account {

    private String AccountNo;
    private String Name;
    private double Account_Balance;

    public Account(String AccountNo, String Name, double balance) {
        this.AccountNo = AccountNo;
        this.Name = Name;
        this.Account_Balance = balance;
    }

    public String getPersonName() {
        return Name;
    }

    public void ChangePersonName(String Name) {
        this.Name = Name;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public double getAccountBalance() {
        return Account_Balance;
    }

    public void UpdateAccountBalance(double balance) {
        if (Double.isFinite(balance) && balance >= 0) {
            this.Account_Balance = balance;
        }
    }

    public void deposit(double balance) {
        if (Double.isFinite(balance) && balance > 0
                && Double.isFinite(Account_Balance + balance)) {
            Account_Balance += balance;
        }
    }

    public boolean withdraw(double balance) {
        if (Double.isFinite(balance) && balance > 0
                && Account_Balance >= balance) {
            Account_Balance -= balance;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account Number: " + AccountNo
                + "\nName: " + Name
                + "\nBalance: " + Account_Balance;
    }

    public void withdrawOp(double balance) {
        String tid = Transctions.generateTransactionId();

        Transctions t = new Transctions();
        t.Enqueue(tid, AccountNo, null, "withdraw", balance);

        System.out.println("Withdrawal Transaction ID: " + tid);
    }

    public void depositOp(double balance) {
        String tid = Transctions.generateTransactionId();

        Transctions t = new Transctions();
        t.Enqueue(tid, AccountNo, null, "deposit", balance);

        System.out.println("Deposit Transaction ID: " + tid);
    }

    public void transferOp(String target, double balance) {
        String tid = Transctions.generateTransactionId();

        Transctions t = new Transctions();
        t.Enqueue(tid, AccountNo, target, "transfer", balance);

        System.out.println("Transfer Transaction ID: " + tid);
    }

    public void CancelTransction(String tid) {
        boolean check = Transctions.cancelTransction(tid);

        if (check) {
            System.out.println("Transaction Cancelled Successfully.");
        } else {
            System.out.println("Transaction not found or already processed.");
        }
    }
}
