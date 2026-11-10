class Account{
    private String AccountNo;
    private String Name;
    private double Account_Balance;
    public Account(String AccountNo,String Name,double balance){
        this.AccountNo = AccountNo;
        this.Name = name;
        this.Account_Balance = balance;
    }
    public String getPersonName(){
        return Name;
    }
    public void ChangePersonName(String name){
        this.Name = name;
    }
    public String getAccountNo(){
        return AccountNo;
    }
    public double getAccountBalance(){
        return Account_Balance;
    }
    public void UpdateAccountBalance(double balance){
        this.Account_Balance = balance;
    }
    public void deposit(double balance){
        this.Account_Balance = Account_Balance + balance;
    }
    public boolean withdraw(double balance){
        if(Account_Balance > balance && Account_Balance > 0){
            this.Account_Balance =  Account_Balance - balance;
            return true;
        }
        return false;
    }
    public String toString(){
        return "Account No :"+ AccountNo +
               " Account Holder Name :"+ Name+ 
               "Account Balance :"+ Account_Balance;  
    }

}