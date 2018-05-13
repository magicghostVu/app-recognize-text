package translate_api;

/**
 * Created by magic on 12/05/2018.
 */

public enum LanguageForTransApi {
    chi_sim("zh-CN"),
    jpn("ja"),
    eng("en"),
    vie("vi");

    private String code;

    LanguageForTransApi(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
