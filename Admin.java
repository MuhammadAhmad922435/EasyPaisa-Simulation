
class Admin {

    public static void ProcessTransction() {

        Transctions.Node process = Transctions.Dequeue();

        if (process == null) {
            System.out.println("There is no Transaction at this time.");
            return;
        }

        if (process.type.equalsIgnoreCase("withdraw")) {

            double balance = Users.withdraw(
                    process.source,
                    process.amount
            );

            if (balance > 0) {
                process.status = "Successful";
            } else if (balance == 0) {
                process.status = "Failed: Insufficient balance or invalid amount";
            } else {
                process.status = "Failed: Source account does not exist";
            }
        }

        else if (process.type.equalsIgnoreCase("deposit")) {

            boolean result = Users.deposit(
                    process.source,
                    process.amount
            );

            if (result) {
                process.status = "Successful";
            } else {
                process.status = "Failed: Invalid amount or account not found";
            }
        }

        else if (process.type.equalsIgnoreCase("transfer")) {

            boolean result = Users.transfer(
                    process.source,
                    process.target,
                    process.amount
            );

            if (result) {
                process.status = "Successful";
            } else {
                process.status = "Failed: Transfer could not be completed";
            }
        }

        else {
            process.status = "Failed: Invalid transaction type";
        }

        System.out.println("\n------ Transaction Result ------");
        System.out.println("Transaction ID : " + process.getTransctionId());
        System.out.println("Source         : " + process.getSource());
        System.out.println("Target         : " + process.getTarget());
        System.out.println("Type           : " + process.getType());
        System.out.println("Amount         : " + process.getAmount());
        System.out.println("Status         : " + process.getStatus());
        System.out.println("--------------------------------");

        TransactionHistory.store(process);
    }
}
