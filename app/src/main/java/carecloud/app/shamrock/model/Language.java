
package carecloud.app.shamrock.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("fieldId")
    @Expose
    private Integer fieldId;
    @SerializedName("required")
    @Expose
    private Boolean required;
    @SerializedName("fieldType")
    @Expose
    private Integer fieldType;
    @SerializedName("fieldCategory")
    @Expose
    private Integer fieldCategory;
    @SerializedName("iconId")
    @Expose
    private Object iconId;
    @SerializedName("label")
    @Expose
    private Object label;
    @SerializedName("lookup")
    @Expose
    private Object lookup;
    @SerializedName("options")
    @Expose
    private List<Option> options = new ArrayList<Option>();

    /**
     * 
     * @return
     *     The fieldId
     */
    public Integer getFieldId() {
        return fieldId;
    }

    /**
     * 
     * @param fieldId
     *     The fieldId
     */
    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * 
     * @return
     *     The required
     */
    public Boolean getRequired() {
        return required;
    }

    /**
     * 
     * @param required
     *     The required
     */
    public void setRequired(Boolean required) {
        this.required = required;
    }

    /**
     * 
     * @return
     *     The fieldType
     */
    public Integer getFieldType() {
        return fieldType;
    }

    /**
     * 
     * @param fieldType
     *     The fieldType
     */
    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * 
     * @return
     *     The fieldCategory
     */
    public Integer getFieldCategory() {
        return fieldCategory;
    }

    /**
     * 
     * @param fieldCategory
     *     The fieldCategory
     */
    public void setFieldCategory(Integer fieldCategory) {
        this.fieldCategory = fieldCategory;
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
     *     The label
     */
    public Object getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    public void setLabel(Object label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The lookup
     */
    public Object getLookup() {
        return lookup;
    }

    /**
     * 
     * @param lookup
     *     The lookup
     */
    public void setLookup(Object lookup) {
        this.lookup = lookup;
    }

    /**
     * 
     * @return
     *     The options
     */
    public List<Option> getOptions() {
        return options;
    }

    /**
     * 
     * @param options
     *     The options
     */
    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
