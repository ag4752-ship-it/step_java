package session_five_topics.assignment_problems;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LibraryMember implements Serializable {
    private static final long serialVersionUID = 1L;

    private String membershipId;
    private String name;
    private boolean premiumMember;
    @SuppressWarnings("unused")
    private String securityAnswerHash;

    public LibraryMember() {
        this.membershipId = null;
        this.name = "";
        this.premiumMember = false;
        this.securityAnswerHash = null;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        // Write-once behavior: only sets on the first call
        if (this.membershipId == null) {
            this.membershipId = membershipId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void setSecurityAnswer(String answer) {
        if (answer == null) {
            this.securityAnswerHash = null;
            return;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(answer.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            this.securityAnswerHash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            this.securityAnswerHash = Integer.toHexString(answer.hashCode());
        }
    }

    public static void main(String[] args) {
        // Test 1: Basic JavaBean property setting
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println("Membership ID: " + m.getMembershipId()); // "LIB-8841"

        // Test 2: Write-once protection on membershipId
        m.setMembershipId("FAKE-0000");
        System.out.println("Membership ID after overwrite attempt: " + m.getMembershipId()); // "LIB-8841"

        // Test 3: Boolean getter isPremiumMember
        System.out.println("Is premium member: " + m.isPremiumMember()); // true

        // Test 4: Write-only securityAnswer
        m.setSecurityAnswer("BlueMountain");
        System.out.println("Security answer stored securely (no getter available)");
    }
}