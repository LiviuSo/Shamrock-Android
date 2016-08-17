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
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import carecloud.app.shamrock.model.MainResponse;
import carecloud.app.shamrock.model.Option;
import carecloud.app.shamrock.parser.LanguageParser;

/**
 * Fragment for choosing language screen
 */
public class SelectLanguageFragment extends Fragment implements
                                                     AdapterView.OnItemSelectedListener {

    private static final String LOG_TAG               = SelectLanguageFragment.class.getSimpleName();
    public static final  String LANG_OPTIONS          = "lang_options";
    public static final  String SCREEN_TITLE          = "title";

    private String[] mLanguages;
    private String   mSelectedLanguage;
    private TextView tvLabel;
    private String mScreenName = "";
    private LangOptionsBundle mLangOptionsBundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainResponse objMainResponse =   readFileAndGetArray();
        List<Option> langOptions = objMainResponse.getScreens().get(0).getJson().get(0).getLanguage().get(0).getOptions();

        // create the language options holder
        mLangOptionsBundle = new LangOptionsBundle(langOptions.toArray(new Option[langOptions.size()]));

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
        int position = 0;
        if(adapter != null) {
            if(mSelectedLanguage != null) {
                position = adapter.getPosition(mSelectedLanguage);
            }
            spinner.setSelection(position);
        }

        Button button = (Button) view.findViewById(R.id.btn_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save selection
                Utility.saveSelectedLanguage(getActivity(), mSelectedLanguage);

                // test
                Snackbar.make(view, mSelectedLanguage, Snackbar.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private MainResponse readFileAndGetArray() {
        try {
            String json = null;

                InputStream is = getActivity().getAssets().open("deta.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            Log.e(LOG_TAG,json.toString());

            LanguageParser lParser= new LanguageParser();
          return lParser.getParsedObjectFromJSON(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }




    private void setSelectedLanguage() {
        Option languageOption = mLangOptionsBundle.findAfterLanguage(mSelectedLanguage);
        if (tvLabel != null && languageOption != null) {
            tvLabel.setText(languageOption.getLabel());
        }
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

    /**
     * Utility class to play with the available languages
     */
    public static class LangOptionsBundle {

        private Option[] mLangOptions;
        private String[]         mLanguages;

        public LangOptionsBundle(Option[] languageOptions) {
            mLangOptions = languageOptions;
            extractLanguages();
        }

        private void extractLanguages() {
            mLanguages = new String[mLangOptions.length];
            for (int i = 0; i < mLangOptions.length; i++) {
                mLanguages[i] = mLangOptions[i].getValue();
            }
        }

        private Option findAfterLanguage(String language) {
            for (Option languageOption : mLangOptions) {
                if (languageOption.getValue().equals(language)) {
                    return languageOption;
                }
            }
            return null;
        }

        private String findDefaultLanguage() {
            for (Option languageOption : mLangOptions) {
                if (languageOption.getIsDefault()) {
                    return languageOption.getValue();
                }
            }
            return null;
        }

        public String[] getAvailLanguages() {
            return mLanguages;
        }
    }
}
