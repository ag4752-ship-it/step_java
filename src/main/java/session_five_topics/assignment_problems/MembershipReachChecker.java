package session_five_topics.assignment_problems;

public class MembershipReachChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier.toLowerCase()) {
            case "public":
                return "ALLOWED";
            case "protected":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        int privAllowed = 0, privDenied = 0;
        int defAllowed = 0, defDenied = 0;
        int protAllowed = 0, protDenied = 0;
        int pubAllowed = 0, pubDenied = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String modifier = attempt[0];
                    String context = attempt[1];
                    String result = classifyAccess(modifier, context);
                    boolean isAllowed = "ALLOWED".equals(result);

                    if ("private".equalsIgnoreCase(modifier)) {
                        if (isAllowed) privAllowed++; else privDenied++;
                    } else if ("default".equalsIgnoreCase(modifier)) {
                        if (isAllowed) defAllowed++; else defDenied++;
                    } else if ("protected".equalsIgnoreCase(modifier)) {
                        if (isAllowed) protAllowed++; else protDenied++;
                    } else if ("public".equalsIgnoreCase(modifier)) {
                        if (isAllowed) pubAllowed++; else pubDenied++;
                    }
                }
            }
        }

        return "private: " + privAllowed + " allowed / " + privDenied + " denied | " +
               "default: " + defAllowed + " allowed / " + defDenied + " denied | " +
               "protected: " + protAllowed + " allowed / " + protDenied + " denied | " +
               "public: " + pubAllowed + " allowed / " + pubDenied + " denied";
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));          // "ALLOWED"
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE")); // "DENIED"

        String[][] batch = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(batch));
        // "private: 1 allowed / 1 denied | default: 1 allowed / 1 denied | protected: 2 allowed / 0 denied | public: 1 allowed / 0 denied"
    }
}