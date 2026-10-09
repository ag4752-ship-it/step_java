package session_eight_topics.class_problems;

import java.util.*;

/**
 * Session 8 - Class Problem 5: Payment Processing for a Shopping System
 * 
 * Demonstrates:
 * - PaymentMethod interface with polymorphic implementations (CreditCardPayment, PayPalPayment, BankTransferPayment).
 * - Shopping cart and order state management (Pending, Paid).
 * - Empty order validation and handling of transaction outcomes.
 */
public class PaymentProcessingShoppingSystem {

    public interface PaymentMethod {
        boolean processPayment(double amount);
        String getMethodName();
    }

    public static class CreditCardPayment implements PaymentMethod {
        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public boolean processPayment(double amount) {
            // Simulated payment verification
            return true;
        }

        @Override
        public String getMethodName() {
            return "Credit Card";
        }
    }

    public static class PayPalPayment implements PaymentMethod {
        private String email;
        private boolean shouldSucceed;

        public PayPalPayment(String email, boolean shouldSucceed) {
            this.email = email;
            this.shouldSucceed = shouldSucceed;
        }

        @Override
        public boolean processPayment(double amount) {
            return shouldSucceed;
        }

        @Override
        public String getMethodName() {
            return "PayPal";
        }
    }

    public static class BankTransferPayment implements PaymentMethod {
        private String bankAccount;

        public BankTransferPayment(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        @Override
        public boolean processPayment(double amount) {
            return true;
        }

        @Override
        public String getMethodName() {
            return "Bank Transfer";
        }
    }

    public static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    public static class OrderItem {
        private Product product;
        private int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public double getSubtotal() {
            return product.getPrice() * quantity;
        }
    }

    public static class Customer {
        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum OrderStatus {
        PENDING,
        PAID
    }

    public static class Order {
        private String orderId;
        private Customer customer;
        private List<OrderItem> items;
        private OrderStatus status;

        public Order(String orderId, Customer customer) {
            this.orderId = orderId;
            this.customer = customer;
            this.items = new ArrayList<>();
            this.status = OrderStatus.PENDING;
            System.out.printf("Order created for %s.%n", customer.getName());
        }

        public void addItem(Product product, int quantity) {
            this.items.add(new OrderItem(product, quantity));
        }

        public double getTotalAmount() {
            double total = 0;
            for (OrderItem item : items) {
                total += item.getSubtotal();
            }
            return total;
        }

        public boolean processPayment(PaymentMethod paymentMethod) {
            if (items.isEmpty()) {
                System.out.println("Cannot process payment for an empty order.");
                return false;
            }

            System.out.printf("Payment initiated via %s for Order %s.%n",
                    paymentMethod.getMethodName(), customer.getName().replace("Customer ", ""));

            boolean success = paymentMethod.processPayment(getTotalAmount());
            if (success) {
                this.status = OrderStatus.PAID;
                System.out.printf("Payment for Order %s successful. Order status: %s.%n",
                        customer.getName().replace("Customer ", ""), getStatusString());
            } else {
                System.out.printf("Payment for Order %s failed. Order status: %s.%n",
                        customer.getName().replace("Customer ", ""), getStatusString());
            }
            return success;
        }

        public String getStatusString() {
            return status == OrderStatus.PAID ? "Paid" : "Pending";
        }
    }

    public static void main(String[] args) {
        Product prodA = new Product("Product A", 50.0);
        Product prodB = new Product("Product B", 30.0);
        Product prodC = new Product("Product C", 40.0);

        Customer custX = new Customer("Customer X");
        Customer custY = new Customer("Customer Y");
        Customer custZ = new Customer("Customer Z");

        // Customer X creates an order with Product A (qty 2) and Product B (qty 1)
        Order orderX = new Order("ORD-1", custX);
        orderX.addItem(prodA, 2);
        orderX.addItem(prodB, 1);
        orderX.processPayment(new CreditCardPayment("1234-5678-9012-3456"));

        // Customer Y creates an empty order and attempts payment
        Order orderY = new Order("ORD-2", custY);
        orderY.processPayment(new CreditCardPayment("9876-5432-1098-7654"));

        // Customer Z creates an order with Product C (qty 1) and attempts PayPal (fails)
        Order orderZ = new Order("ORD-3", custZ);
        orderZ.addItem(prodC, 1);
        orderZ.processPayment(new PayPalPayment("z@example.com", false));
    }
}
