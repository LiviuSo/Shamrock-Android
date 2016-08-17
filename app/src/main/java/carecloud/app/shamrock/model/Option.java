
package carecloud.app.shamrock.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Option {

    @SerializedName("languageId")
    @Expose
    private Integer languageId;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("iconId")
    @Expose
    private Object iconId;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("child")
    @Expose
    private List<Object> child = new ArrayList<Object>();
    @SerializedName("isDefault")
    @Expose
    private Boolean isDefault;
    @SerializedName("skip")
    @Expose
    private List<Object> skip = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The languageId
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * 
     * @param languageId
     *     The languageId
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * 
     * @return
     *     The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The iconId
     */
    public Object getIconId() {
        return iconId;
    }

    /**
     * 
     * @param iconId
     *     The iconId
     */
    public void setIconId(Object iconId) {
        this.iconId = iconId;
    }

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The child
     */
    public List<Object> getChild() {
        return child;
    }

    /**
     * 
     * @param child
     *     The child
     */
    public void setChild(List<Object> child) {
        this.child = child;
    }

    /**
     * 
     * @return
     *     The isDefault
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * 
     * @param isDefault
     *     The isDefault
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 
     * @return
     *     The skip
     */
    public List<Object> getSkip() {
        return skip;
    }

    /**
     * 
     * @param skip
     *     The skip
     */
    public void setSkip(List<Object> skip) {
        this.skip = skip;
    }

}
