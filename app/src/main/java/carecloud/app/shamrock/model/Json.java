
package carecloud.app.shamrock.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Json {

    @SerializedName("screen name")
    @Expose
    private String screenName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("langId")
    @Expose
    private Integer langId;
    @SerializedName("language")
    @Expose
    private List<Language> language = new ArrayList<Language>();

    /**
     * 
     * @return
     *     The screenName
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * 
     * @param screenName
     *     The screen name
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The langId
     */
    public Integer getLangId() {
        return langId;
    }

    /**
     * 
     * @param langId
     *     The langId
     */
    public void setLangId(Integer langId) {
        this.langId = langId;
    }

    /**
     * 
     * @return
     *     The language
     */
    public List<Language> getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    public void setLanguage(List<Language> language) {
        this.language = language;
    }

}
