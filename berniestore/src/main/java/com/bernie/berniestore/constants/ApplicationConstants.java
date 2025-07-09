package com.bernie.berniestore.constants;

public class ApplicationConstants {
    public ApplicationConstants() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE = "ab6a5a163508341e1751a61db25388fe6e42146a5a89bab25a4f6206d0c17e33";
    public static final String JWT_HEADER = "Authorization";

    public static final String  ORDER_STATUS_CONFIRMED = "CONFIRMED";
    public static final String  ORDER_STATUS_CREATED = "CREATED";
    public static final String  ORDER_STATUS_CANCELLED = "CANCELLED";
}
