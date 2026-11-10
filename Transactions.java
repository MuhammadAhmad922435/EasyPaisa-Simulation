class Transctions{
    static class Node{

    
    public String transctionId;
    public String source;
    public String target;
    public double amount;
    public String type;
    public String status;
    Node next;
    Node prev;
    public Node(String tId,String source,String target,double amount,String type){
        this.transctionId = tId;
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.type = type;
        this.status = "pending";
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
            public static void Enqueue(String tId , String source ,String target , String type ,double amount){
                Node n = new Node(tId, source, target, amount, type);
                if(isEmpty()){
                    Front  = n;
                    Rear = n;
                    return;
                }
                Rear.next = n;
                n.prev = Rear;
                Rear = Rear.next;
            }

            public static Node Dequeue(){
                if(isEmpty()){
                    return null;
                }
                Node Remove = Front;
                if(Front.next == null){
                    Front  = null;
                    Rear = null;
                    return Remove;
                }
                if(Front.next !=null){
                    Front = Front.next;
                    Front.prev = null;
                    return Remove;
                }
                
                
                return Remove;
            }

            public static boolean isEmpty(){
                return Front == null;
            }
            

}