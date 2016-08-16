package carecloud.app.shamrock;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import java.util.Arrays;
import java.util.List;

import carecloud.app.shamrock.model.LanguageOption;

/**
 * Fragment for choosing language screen
 */
public class SelectLanguageFragment extends Fragment implements
                                                     AdapterView.OnItemSelectedListener {

    private static final String LOG_TAG     = SelectLanguageFragment.class.getSimpleName();
    public static final String LANG_OPTIONS = "lang_options";
    public static final String SCREEN_TITLE = "title";

    private String[]          mLanguages;
    private String            mSelectedLanguage;
    private TextView          tvLabel;
    private String            mScreenName = "";
    private LangOptionsBundle mLangOptionsBundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        LanguageOption[] langOptions = null;
        if(bundle != null) {
            langOptions = (LanguageOption[]) bundle.getParcelableArray(LANG_OPTIONS);
            mScreenName = bundle.getString(SCREEN_TITLE);
        }

        // create the language options holder
        mLangOptionsBundle = new LangOptionsBundle(langOptions);

        // get all languages available and the default one
        mLanguages = mLangOptionsBundle.getAvailLanguages();
        mSelectedLanguage = mLangOptionsBundle.findDefaultLanguage();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_language, container, false);

        tvLabel = (TextView) view.findViewById(R.id.tv_message);
        getActivity().setTitle(mScreenName);

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
                Snackbar.make(view, mSelectedLanguage, Snackbar.LENGTH_SHORT).show();
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

    private void setSelectedLanguage() {
        LanguageOption languageOption = mLangOptionsBundle.findAfterLanguage(mSelectedLanguage);
        if (tvLabel != null && languageOption != null) {
            tvLabel.setText(languageOption.label);
        }
    }

    /**
     * Utility class to play with the available languages
     */
    public static class LangOptionsBundle {

        private LanguageOption[] mLangOptions;
        private String[]             mLanguages;

        public LangOptionsBundle(LanguageOption[] languageOptions) {
            mLangOptions = languageOptions;
            extractLanguages();
        }

        private void extractLanguages() {
            mLanguages = new String[mLangOptions.length];
            for (int i = 0; i < mLangOptions.length; i++) {
                mLanguages[i] = mLangOptions[i].value;
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

        public String[] getAvailLanguages() {
            return mLanguages;
        }
    }
}
