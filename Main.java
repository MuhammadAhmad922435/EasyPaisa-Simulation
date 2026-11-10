class Maintest{
    public static void main(String[] args) {
        Account a1 = new Account("A13e", "Muhammad Ahmad", 20000);
        Account a2 = new Account("A13E", "Muhammad Ahmad", 20000);
        Users acc = new Users();
        acc.push(a1, a1.getAccountNo());
        acc.display();
       a1.transferOp("A13E", 40000);
       Transctions.StartTransctions();
    
        


    }
}