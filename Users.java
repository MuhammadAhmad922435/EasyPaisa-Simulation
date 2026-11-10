class Users{
    static class Node{
    private Account user;
    public String Account_Id;
    Node next;
    Node prev;
    public String getAccountId(){
        return user.getAccountNo();
    }
    public Account getUser(){
        return user;
    }
    public Node(Account user ,String Account_Id){
        this.user = user;
        this.Account_Id = Account_Id;
        this.next = null;
        this.prev = null;
    }

}
static Node Head;
static Node tail;

    public static void push(Account user, String Account_Id){
        Node n = new Node(user, Account_Id);
        if(isEmpty()){
            Head = n;
            tail = n;
            return;
        }
        tail.next = n;
        tail = tail.next;
    }
    public static  String pop(String  Account_Id){
        Node curr = Head;
        Node back;
        String id;
        if(isEmpty()){
            return "No account Exists in Application";
        }
        while(curr != null){
        if(curr.getAccountId().equals(Account_Id)){
            if(curr == Head){
                if(Head.next != null){
                    id = Head.getAccountId();
                    Head= Head.next;
                    return id;
                }
            }
            else if(Head == tail){
                if(tail.prev != null){
                    id = tail.getAccountId();
                    tail = tail.prev;
                    return id;
                }
            }
            else{
                back = curr.prev;
                back.next = curr.next;
                curr.next.prev = back;
                curr.next = null;
                curr.prev = null;
                return curr.getAccountId();
            }

        }
            curr = curr.next;
    }
    return "No Account Found With These Credentials";
}
public static boolean isEmpty(){
    return Head == null;
}
public static void display(){
Node curr = Head;
    while(curr !=null){
        System.out.println(curr.user);
        curr = curr.next;
    }
}
public static void withdraw(String AccountId ,double balance){
Node curr = Head;
while(curr != null){
if(curr.getAccountId().equals(AccountId)){
    if(curr.getUser().withdraw(balance)){
        System.out.println("withdraw Successfully");
        return;
    }
    else{
        System.out.println("Invalid Balance");
    }
}
    curr = curr.next;
}
}
public static void deposit(String AccountId ,double balance){
Node curr = Head;
if(balance <= 0){
     System.out.println("Invalid balance");
     return;
}
while(curr != null){
if(curr.getAccountId().equals(AccountId)){
    curr.getUser().deposit(balance);
    System.out.println("deposited Successfully");    
    }
    curr = curr.next;
}
}

}