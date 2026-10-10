package session_ten_topics.class_problems;

import java.util.*;

/**
 * Session 10 - Class Problem 1: Bank Account Withdrawal System
 * 
 * Demonstrates:
 * - Account hierarchy with Savings and Current account rules.
 * - Savings account maintains a minimum balance of 1000 after withdrawal.
 * - Current account allows overdraft up to 5000 (balance down to -5000).
 */
public class BankAccountWithdrawalSystem {

    public static abstract class Account {
        protected String id;
        protected int balance;

        public Account(String id, int initialBalance) {
            this.id = id;
            this.balance = initialBalance;
        }

        public String getId() {
            return id;
        }

        public int getBalance() {
            return balance;
        }

        public abstract void withdraw(int amount);
    }

    public static class SavingsAccount extends Account {
        public SavingsAccount(String id, int initialBalance) {
            super(id, initialBalance);
        }

        @Override
        public void withdraw(int amount) {
            if (this.balance - amount < 1000) {
                System.out.printf("%s rejected: minimum balance 1000%n", id);
            } else {
                this.balance -= amount;
                System.out.printf("%s balance %d%n", id, balance);
            }
        }
    }

    public static class CurrentAccount extends Account {
        public CurrentAccount(String id, int initialBalance) {
            super(id, initialBalance);
        }

        @Override
        public void withdraw(int amount) {
            if (this.balance - amount < -5000) {
                System.out.printf("%s rejected: overdraft limit 5000%n", id);
            } else {
                this.balance -= amount;
                System.out.printf("%s balance %d%n", id, balance);
            }
        }
    }

    public static class Bank {
        private Map<String, Account> accounts = new HashMap<>();

        public void processOperation(String line) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length == 0 || tokens[0].isEmpty()) return;

            String command = tokens[0];
            if (command.equalsIgnoreCase("Savings")) {
                String id = tokens[1];
                int initial = Integer.parseInt(tokens[2]);
                accounts.put(id, new SavingsAccount(id, initial));
            } else if (command.equalsIgnoreCase("Current")) {
                String id = tokens[1];
                int initial = Integer.parseInt(tokens[2]);
                accounts.put(id, new CurrentAccount(id, initial));
            } else if (command.equalsIgnoreCase("WITHDRAW")) {
                String id = tokens[1];
                int amount = Integer.parseInt(tokens[2]);
                Account acc = accounts.get(id);
                if (acc == null) {
                    System.out.println("Account not found");
                } else {
                    acc.withdraw(amount);
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] operations = {
            "Savings S1 5000",
            "WITHDRAW S1 4500",
            "Current C1 2000",
            "WITHDRAW C1 6000",
            "WITHDRAW S1 3000",
            "WITHDRAW C1 2000"
        };

        Bank bank = new Bank();
        for (String op : operations) {
            bank.processOperation(op);
        }
    }
}
