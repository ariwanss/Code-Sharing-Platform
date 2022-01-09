package platform.util;

import java.util.UUID;

public class Utility {
    public static String generateUuidString() {
        return UUID.randomUUID().toString();
    }

    public static UUID generateUuid() {
        return UUID.randomUUID();
    }
}
