package httpfactory;

import utils.Config;

public class URLFactory {

    private URLFactory() {

    }

    public static String createObject() {
        return Config.getLocalisedMandatoryPropValue("create.object");
    }

    public static String getObject(String objectId) {
        return Config.getLocalisedMandatoryPropValue("get.object") + objectId;
    }

    public static String listAllObjects() {
        return Config.getLocalisedMandatoryPropValue("list.all.objects");
    }

    public static String deleteObject(String objectId) {
        return Config.getLocalisedMandatoryPropValue("delete.object") + objectId;
    }
}