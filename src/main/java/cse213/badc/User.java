package cse213.badc;

public class User {
    /* User-3: Irrigation Equipment Supplier */
    public static String supplierId;
    /* User-4: BADC Field Officer */
    public static String officerId;
    /* User-7: BADC Job Applicant */
    public static String applicantId;
    /* User-8: Development Partner */
    public static String partnerId;

    public static String fullName;
    public static String email;
    public static String userType;

    public static void clear() {
        supplierId = null;
        officerId = null;
        applicantId = null;
        partnerId = null;
        fullName = null;
        email = null;
        userType = null;
    }
}
