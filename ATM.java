package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class BankAccount {
    private String userId;
    String userPin;
    private double balance;
    private ArrayList<Transaction> transactions;

    public BankAccount(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
        System.out.println("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void transfer(BankAccount receiver, double amount) {
        if (balance >= amount) {
            balance -= amount;
            receiver.deposit(amount);
            transactions.add(new Transaction("Transfer", amount));
            System.out.println("Transferred: $" + amount + " to User ID: " + receiver.getUserId());
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println("- " + transaction.getType() + ": $" + transaction.getAmount());
        }
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankAccount userAccount = new BankAccount("userID123", "1234", 1000.0);

        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (enteredUserId.equals(userAccount.getUserId()) && enteredPin.equals(userAccount.userPin)) {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Check Balance");
                System.out.println("6. Quit");
                System.out.print("Enter your choice: ");

                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1:
                        userAccount.showTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        userAccount.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        userAccount.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter receiver's User ID: ");
                        String receiverUserId = scanner.next();
                        System.out.print("Enter amount to transfer: $");
                        double transferAmount = scanner.nextDouble();
                        BankAccount receiverAccount = new BankAccount(receiverUserId, "", 0);
                        userAccount.transfer(receiverAccount, transferAmount);
                        break;
                    case 5:
                        System.out.println("Current Balance: $" + userAccount.getBalance());
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
    }
}
