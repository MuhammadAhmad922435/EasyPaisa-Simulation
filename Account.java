class Account{
    private String AccountNo;
    private String Name;
    private double Account_Balance;
    public Account(String AccountNo,String Name,double balance){
        this.AccountNo = AccountNo;
        this.Name = Name;
        this.Account_Balance = balance;
    }
    public String getPersonName(){
        return Name;
    }
    public void ChangePersonName(String Name){
        this.Name = Name;
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
        if(balance>0){
        this.Account_Balance = Account_Balance + balance;
        return;
        }        
    }
    public boolean withdraw(double balance){
        if(Account_Balance > balance && Account_Balance > 0){
            this.Account_Balance =  Account_Balance - balance;
            return true;
        }
        return false;
    }
    public String toString(){
        return "Account No :"+ AccountNo+"\n" +
               "Account Holder Name :"+ Name+ "\n" + 
               "Account Balance :"+ Account_Balance;  
    }

}