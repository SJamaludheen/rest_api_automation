package utils;

public class ObjectId {

    private ObjectId() {

    }

    private static String objectId;

    public static void setObjectId(String objectId) {
        ObjectId.objectId = objectId;
    }

    public static String getObjectId() {
        return objectId;
    }
}