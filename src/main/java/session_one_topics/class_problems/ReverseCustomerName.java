package session_one_topics.class_problems;

public class ReverseCustomerName {

    public static String reverseCustomerName(String customerName) {
        if (customerName == null) return null;
        char[] chars = customerName.toCharArray();
        char[] reversed = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            reversed[i] = chars[chars.length - 1 - i];
        }
        return new String(reversed);
    }

    public static void main(String[] args) {
        String name1 = "Sunil";
        String rev1 = reverseCustomerName(name1);
        System.out.println("Original Name: " + name1);
        System.out.println("Reversed Name: " + rev1);

        System.out.println();
        String name2 = "Ananya";
        String rev2 = reverseCustomerName(name2);
        System.out.println("Original Name: " + name2);
        System.out.println("Reversed Name: " + rev2);
    }
}