package carecloud.app.shamrock;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Utils
 */
public class Utility {

    private static final String SHARED_PREF_SEL_LANG_KEY = "sel_lang";

    public static void saveSelectedLanguage(Activity activity, String selLang) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SHARED_PREF_SEL_LANG_KEY, selLang);
        editor.apply();
    }

    public String retrieveSelectedLanguage(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(SHARED_PREF_SEL_LANG_KEY, "English");
    }
}