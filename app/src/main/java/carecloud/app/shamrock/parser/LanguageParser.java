package carecloud.app.shamrock.parser;

import android.util.Log;
import com.google.gson.Gson;
import org.json.JSONException;
import carecloud.app.shamrock.model.MainResponse;


public class LanguageParser {

    public MainResponse getParsedObjectFromJSON(String rootJsonObjectStr) throws JSONException {

            Gson gson = new Gson();
            MainResponse mainResponse = gson.fromJson(rootJsonObjectStr, MainResponse.class);
            mainResponse.getScreens().size();
            Log.e("arrLanguageOptions", mainResponse.getScreens().size()+"");

        return mainResponse;
    }
}
