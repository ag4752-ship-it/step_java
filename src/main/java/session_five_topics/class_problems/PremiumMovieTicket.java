package session_five_topics.class_problems;

public class PremiumMovieTicket extends MovieTicket {
    private double discountRate;

    public PremiumMovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle, double discountRate) {
        super(seatNumber, screenId, ticketPrice, movieTitle);
        this.discountRate = discountRate;
    }

    public double getDiscountedPrice() {
        // Subclass can access protected ticketPrice directly
        return this.ticketPrice * (1.0 - discountRate);
    }
}