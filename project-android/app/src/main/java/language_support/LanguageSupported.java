package language_support;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by magic on 12/05/2018.
 */

public enum LanguageSupported {


    vie(0, "TIếng Việt"),
    jpn(3, "Tiếng Nhật"),
    chi_sim(2, "Tiếng Trung"),
    eng(1, "Tiếng Anh");

    private int postion;

    private String nameLanguage;

    LanguageSupported(int postion, String nameLanguage) {
        this.postion = postion;
        this.nameLanguage = nameLanguage;
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

    public String getNameLanguage() {
        return nameLanguage;
    }
}
