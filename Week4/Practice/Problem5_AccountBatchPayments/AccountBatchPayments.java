class FeeAccount {
    double balance;

    public FeeAccount(double balance) {
        this.balance = balance;
    }
}

class HostelFeeAccount extends FeeAccount {
    public HostelFeeAccount(double balance) {
        super(balance);
    }
}

public class AccountBatchPayments {
    private static int hostelCount = 0;
    private static int dayScholarCount = 0;

    public static void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            System.out.println("Paid in two installments (hostel account)");
            hostelCount++;
        } else if (account instanceof FeeAccount) {
            System.out.println("Paid in one go (day-scholar account)");
            dayScholarCount++;
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(60000),
            new HostelFeeAccount(60000),
            new FeeAccount(60000),
            new FeeAccount(60000)
        };

        for (FeeAccount acc : accounts) {
            processPayment(acc, 60000);
        }

        System.out.printf("Hostel accounts processed: %d | Day-scholar accounts processed: %d%n", hostelCount, dayScholarCount);
    }
}
