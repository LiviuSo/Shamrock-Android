
package carecloud.app.shamrock.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainResponse {

    @SerializedName("screens")
    @Expose
    private List<Screen> screens = new ArrayList<Screen>();

    /**
     * 
     * @return
     *     The screens
     */
    public List<Screen> getScreens() {
        return screens;
    }

    /**
     * 
     * @param screens
     *     The screens
     */
    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

}
