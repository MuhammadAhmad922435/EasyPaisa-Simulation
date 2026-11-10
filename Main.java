class Maintest{
    public static void main(String[] args) {
        Account a1 = new Account("A13e", "Muhammad Ahmad", 20000);
        Account a2 = new Account("A13E", "Muhammad Ahmad", 20000);
        Users acc = new Users();
        acc.push(a1, a1.getAccountNo());
        acc.push(a2, a2.getAccountNo());
        acc.withdraw("A13E", 4);
        acc.deposit("A13E", 1000000);
        acc.display();
    
        


    }
}