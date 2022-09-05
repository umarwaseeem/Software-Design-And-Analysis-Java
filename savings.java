import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class savings extends Account {

    // deduction record

    savings(){
        super();
        this.accountNumber = "";
        this.balance = 0.0;
        this.dateCreated = "";
        this.deductionRecord = "";
    }

    savings(double balance){
        this.accountNumber = UUID.randomUUID().toString(); // generate random account number - unique number
        this.balance = balance;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.dateCreated = dtf.format(now);
        this.deductionRecord = "";
    }


    // -------------------------------  methods being implemented from abstract class --------------------------------------- //
    @Override
    void makeDeposit(double amount) {
        System.out.println("Deposited " + amount + " successfully in your savings account");
        this.balance += amount;
    }

    @Override
    void withdraw(double amount) {
        // ? can only withdraw amount equal or less than balance
        if (amount > this.balance){
            System.out.println("Insufficient funds in your savings account");
        } else {
            this.balance -= amount;
            System.out.println("Withdrawal of " +  amount  +  " successful from your savings account");
        }
    }

    @Override
    double checkBalance() {
        return this.balance;
        // todo display name
    }

    @Override
    double calculateZakat() {
        double zakat = 0.0;
        if(this.balance > 20000){
            zakat = (this.balance * 2.5) / 100;
            this.balance -= zakat;
            System.out.println("Zakat amount " + zakat + " is deducted from your account");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            deductionRecord += "Zakat Amount: " + zakat + " deducted on " + dtf.format(now) + "\n";
        }
        else{
            System.out.println("You are not eligible for Zakat");
        }
        return zakat;
    }

    @Override
    void printStatement() {
        System.out.println("Savings Account Number: " + this.accountNumber + "\nBalance: " + this.balance + "\nDate Created: " + this.dateCreated + "\n");
        // todo print customer information
    }

    @Override
    void transferAmount(double amount) {
        if (amount > this.balance){
            System.out.println("Insufficient funds");
        } else {
            this.balance -= amount;
            System.out.println("Transfer of " +  amount  +  " successful");
        }
        // todo transfer to specific account
    }

    // -------------------------------  class own methods ------------------------------- //
    
    String displayAllDeductions(){
        return this.deductionRecord;
    }
    // todo void calculateInterest(){}
}
