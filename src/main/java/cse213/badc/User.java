package cse213.badc;

public class User {
    /* User-7: BADC Job Applicant (U7) */
    public static String applicantId;
    /* User-8: Development Partner */
    public static String partnerId;

    public static String fullName;
    public static String email;
    public static String userType;

    public static void clear() {
        applicantId = null;
        partnerId = null;
        fullName = null;
        email = null;
        userType = null;
    }
}
