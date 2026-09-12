package session_five_topics.class_problems;

import java.io.Serializable;

public class MovieBookingProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private boolean confirmed;
    @SuppressWarnings("unused")
    private String otp;

    public MovieBookingProfile() {
        this.name = "";
        this.confirmed = false;
        this.otp = null;
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public static void main(String[] args) {
        // Test 1: Convenience constructor and getName
        MovieBookingProfile profile1 = new MovieBookingProfile("Rahul Dev");
        System.out.println(profile1.getName()); // "Rahul Dev"

        // Test 2: JavaBean setters and boolean getter isConfirmed()
        MovieBookingProfile profile2 = new MovieBookingProfile("Rahul Dev");
        profile2.setConfirmed(true);
        System.out.println(profile2.isConfirmed()); // true

        // Test 3: Write-only OTP
        profile2.setOtp("4471");
        System.out.println("OTP set successfully (no getter exists to retrieve it)");
    }
}