package carecloud.app.shamrock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import carecloud.app.shamrock.model.LanguageOption;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            // parse the json
            ParseHelper parseHelper = new ParseHelper();
            LanguageOption[] langOptions = parseHelper.getLangOptions();
            String screenName = parseHelper.getSelLangScreenTitle();

            // create the fragment and pass the lang options and the title
            SelectLanguageFragment fragment = new SelectLanguageFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArray(SelectLanguageFragment.LANG_OPTIONS, langOptions);
            bundle.putString(SelectLanguageFragment.SCREEN_TITLE, screenName);
            fragment.setArguments(bundle);

            // add select language fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_holder, fragment, null)
                    .commit();
        }
    }

    /**
     *
     */
    public static class ParseHelper {

        private LanguageOption[] mLangOptions;
        private String mSelLangScreen;

        public ParseHelper() {
            parseJson();
        }

        private void parseJson() {
            try {
                JSONObject root = new JSONObject(json);
                JSONArray screensArray = root.getJSONArray("screens");
                JSONObject screenObject = screensArray.getJSONObject(0);
                JSONArray jsonArray = screenObject.getJSONArray("json");
                JSONObject jsonScreen = jsonArray.getJSONObject(0);
                mSelLangScreen = jsonScreen.getString("screen name");
                JSONArray languageArray = jsonScreen.getJSONArray("language");
                JSONObject languageObject = languageArray.getJSONObject(0);
                JSONArray languageOptions = languageObject.getJSONArray("options");
                mLangOptions = new LanguageOption[languageOptions.length()];
                for (int i = 0; i < languageOptions.length(); i++) {
                    JSONObject jsonLanguageOption = languageOptions.getJSONObject(i);
                    LanguageOption languageOption = new LanguageOption();
                    languageOption.languageId = jsonLanguageOption.getInt("languageId");
                    languageOption.label = jsonLanguageOption.getString("label");
                    languageOption.iconId = -1;
                    languageOption.value = jsonLanguageOption.getString("value");
                    languageOption.child = null; // todo parse
                    languageOption.isDefault = jsonLanguageOption.getBoolean("isDefault");
                    languageOption.skip = null; // todo parse

                    mLangOptions[i] = languageOption;

                    // test
                    Log.v(LOG_TAG, languageOption.toString());
                }
            } catch (JSONException e) {
                Log.v(LOG_TAG, e.getMessage(), e);
            }
        }

        public LanguageOption[] getLangOptions() {
            return mLangOptions;
        }


        public String getSelLangScreenTitle() {
            return mSelLangScreen;
        }
        /**
         * hard-coded test json
         */
        private static final String json = "{\n" +
                "    \"screens\": [\n" +
                "        {\n" +
                "            \"json\": [\n" +
                "                {\n" +
                "                    \"screen name\": \"Select Language\",\n" +
                "                    \"description\": \"screen for language selection\",\n" +
                "                    \"langId\": 1,\n" +
                "                    \"language\": [\n" +
                "                        {\n" +
                "                            \"fieldId\": 101,\n" +
                "                            \"required\": false,\n" +
                "                            \"fieldType\": 2,\n" +
                "                            \"fieldCategory\": 2,\n" +
                "                            \"iconId\": null,\n" +
                "                            \"label\": null,\n" +
                "                            \"lookup\": null,\n" +
                "                            \"options\": [\n" +
                "                                {\n" +
                "                                    \"languageId\": 201,\n" +
                "                                    \"label\": \"Please Select Language\",\n" +
                "                                    \"iconId\": null,\n" +
                "                                    \"value\": \"English\",\n" +
                "                                    \"child\": [],\n" +
                "                                    \"isDefault\": false,\n" +
                "                                    \"skip\": []\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"languageId\": 202,\n" +
                "                                    \"label\": \"Por favor, seleccione Idioma\",\n" +
                "                                    \"iconId\": null,\n" +
                "                                    \"value\": \"Español\",\n" +
                "                                    \"child\": [],\n" +
                "                                    \"isDefault\": true,\n" +
                "                                    \"skip\": []\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

    }
}
