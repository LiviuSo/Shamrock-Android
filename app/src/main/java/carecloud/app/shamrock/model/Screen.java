
package carecloud.app.shamrock.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Screen {

    @SerializedName("json")
    @Expose
    private List<Json> json = new ArrayList<Json>();

    /**
     * 
     * @return
     *     The json
     */
    public List<Json> getJson() {
        return json;
    }

    /**
     * 
     * @param json
     *     The json
     */
    public void setJson(List<Json> json) {
        this.json = json;
    }

}
