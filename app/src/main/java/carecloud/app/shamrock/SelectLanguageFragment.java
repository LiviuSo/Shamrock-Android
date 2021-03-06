package carecloud.app.shamrock;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment for choosing language screen
 */
public class SelectLanguageFragment extends Fragment implements
                                                     AdapterView.OnItemSelectedListener {

    private static final String LOG_TAG = SelectLanguageFragment.class.getSimpleName();
    private List<LanguageOption> mLangOptions;
    private String[]             mLanguages;
    private String               mSelectedLanguage;
    private TextView             tvLabel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseJson(json);
        extractLanguages();
        mSelectedLanguage = findDefaultLanguage();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_language, container, false);

        tvLabel = (TextView) view.findViewById(R.id.tv_message);

        Spinner spinner = (Spinner) view.findViewById(R.id.languages_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> adapter = null;
        if (mLanguages != null) {
            adapter = new ArrayAdapter(getActivity(),
                                       R.layout.support_simple_spinner_dropdown_item, mLanguages);
        }
        // Specify the layout to use when the list of choices appears
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        // set spinner to the default
        spinner.setSelection(adapter != null ? adapter.getPosition(mSelectedLanguage) : 0);

        Button button = (Button) view.findViewById(R.id.btn_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo change the Locale?

                // test
                Log.v(LOG_TAG, "language set to :" + mSelectedLanguage);
            }
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSelectedLanguage = (String) adapterView.getAdapter().getItem(i);
        setSelectedLanguage();

        // test
        Log.v(LOG_TAG, "selected language: " + mSelectedLanguage);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private LanguageOption[] parseJson(String json) {
        mLangOptions = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(json);
            JSONArray screensArray = root.getJSONArray("screens");
            JSONObject screenObject = screensArray.getJSONObject(0);
            JSONArray jsonArray = screenObject.getJSONArray("json");
            JSONArray languageArray = jsonArray.getJSONObject(0).getJSONArray("language");
            JSONObject languageObject = languageArray.getJSONObject(0);
            JSONArray languageOptions = languageObject.getJSONArray("options");
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

                mLangOptions.add(languageOption);
                // test
                Log.v(LOG_TAG, languageOption.toString());
            }

        } catch (JSONException e) {
            Log.v(LOG_TAG, e.getMessage(), e);
        }
        return null;
    }

    private void extractLanguages() {
        mLanguages = new String[mLangOptions.size()];
        for (int i = 0; i < mLangOptions.size(); i++) {
            mLanguages[i] = mLangOptions.get(i).value;
        }
    }

    private LanguageOption findAfterLanguage(String language) {
        for (LanguageOption languageOption : mLangOptions) {
            if (languageOption.value.equals(language)) {
                return languageOption;
            }
        }
        return null;
    }

    private String findDefaultLanguage() {
        for (LanguageOption languageOption : mLangOptions) {
            if (languageOption.isDefault) {
                return languageOption.value;
            }
        }
        return null;
    }

    private void setSelectedLanguage() {
        LanguageOption languageOption = findAfterLanguage(mSelectedLanguage);
        if(tvLabel != null && languageOption != null) {
            tvLabel.setText(languageOption.label);
        }
    }

    /**
     * Model for language option
     */
    public static class LanguageOption {

        public int      languageId;
        public String   label;
        public int      iconId;
        public String   value;
        public String[] child;
        public boolean  isDefault;
        public String[] skip;

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(languageId).append(" ")
                    .append(label).append(" ")
                    .append(value).append(" ")
                    .append(isDefault).append(" \n");
            return stringBuilder.toString();
        }
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
