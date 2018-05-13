package language_support;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by magic on 12/05/2018.
 */

public enum LanguageSupported {


    vie(0),
    jpn(3),
    chi_sim(2),
    eng(1);

    private int postion;

    LanguageSupported(int postion) {
        this.postion = postion;
    }


    private static Map<Integer, LanguageSupported> mapCache;

    private static void initMap() {
        mapCache = new HashMap<>();
        for (LanguageSupported l : LanguageSupported.values()) {
            mapCache.put(l.postion, l);
        }
    }


    public static LanguageSupported fromPosition(int postion) {
        if (mapCache == null) {
            initMap();
        }
        return mapCache.get(postion);
    }
}
