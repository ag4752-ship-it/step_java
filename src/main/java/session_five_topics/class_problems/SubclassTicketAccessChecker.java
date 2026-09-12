package session_five_topics.class_problems;

class PremiumMovieTicket extends MovieTicket {
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

public class SubclassTicketAccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier.toLowerCase()) {
            case "public":
                return "ALLOWED";

            case "protected":
                switch (accessorContext) {
                    case "SAME_CLASS":
                    case "SAME_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                        return "ALLOWED";
                    case "DIFFERENT_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                    default:
                        return "DENIED";
                }

            case "default":
                switch (accessorContext) {
                    case "SAME_CLASS":
                    case "SAME_PACKAGE":
                        return "ALLOWED";
                    case "DIFFERENT_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                    default:
                        return "DENIED";
                }

            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));    // "ALLOWED"
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")); // "DENIED"
        System.out.println(classifyAccess("private", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));      // "DENIED"
        System.out.println(classifyAccess("default", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));      // "DENIED"

        PremiumMovieTicket premium = new PremiumMovieTicket("VIP-1", "SCR-IMAX", 500.0, "Interstellar", 0.10);
        System.out.println("Discounted Price: " + premium.getDiscountedPrice());
    }
}