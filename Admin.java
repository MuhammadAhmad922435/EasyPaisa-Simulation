class Admin{
public static void ProcessTransction(){
                Transctions.Node process = Transctions.Dequeue();
                if(process == null){
                    System.out.println("There is no Transction At this time");
                    return;
                }
                if(process.type.equalsIgnoreCase("withdraw")){
                    process.target = null;
                    double balance = Users.withdraw(process.source, process.amount);
                    if(balance > 0){
                        process.status = "Successfull";
                    }
                    else if(balance == 0){
                        process.status = "Failed Due to Low Balance";
                    }
                    else if(balance == -1){
                        process.status = "Canceled . Account doesnt Exists";
                    }
                }
                else if(process.type.equalsIgnoreCase("deposit")){
                    process.target = null;
                    boolean result = Users.deposit(process.source, process.amount);
                    if(result){
                        process.status = " Successfull";
                    }
                    else{
                        process.status = "Failed due to Invalid balance or Account not found";
                    }
                }
                else if(process.type.equalsIgnoreCase("transfer")){
                    boolean result = false;
                    double transferMoney = Users.withdraw(process.source, process.amount);
                    if(transferMoney < 0){
                        process.status = "Canceled . Account doesnt Exists";
                    }
                    else if(transferMoney == 0){
                        process.status = "Failed due to low balance";
                        
                    }
                    else{
                        result = Users.deposit(process.target, transferMoney);
                    }
                    
                    if(result){
                        process.status = "Successfull";
                    }
                    else{
                        Users.deposit(process.source, transferMoney);
                        process.status = "Failed . Account Doesnt Exists";
                    }
                }
                else{
                    System.out.println("please Choose Correct Option");
                }



                System.out.println("Transction Id : "+process.getTransctionId());
                System.out.println("Source : "+ process.getSource());
                System.out.println("Target : "+ process.getTarget());
                System.out.println("Type : "+ process.getType());
                System.out.println("Amount : "+ process.getAmount());
                System.out.println("Status : "+ process.getStatus());
            }
}
