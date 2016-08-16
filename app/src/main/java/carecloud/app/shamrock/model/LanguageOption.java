package carecloud.app.shamrock.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for language option
 */
public class LanguageOption implements Parcelable{

    public int      languageId;
    public String   label;
    public int      iconId;
    public String   value;
    public String[] child;
    public boolean  isDefault;
    public String[] skip;

    public LanguageOption(Parcel in) {
        languageId = in.readInt();
        label = in.readString();
        iconId = in.readInt();
        value = in.readString();
        child = in.createStringArray();
        isDefault = in.readByte() != 0;
        skip = in.createStringArray();
    }

    public static final Creator<LanguageOption> CREATOR = new Creator<LanguageOption>() {
        @Override
        public LanguageOption createFromParcel(Parcel in) {
            return new LanguageOption(in);
        }

        @Override
        public LanguageOption[] newArray(int size) {
            return new LanguageOption[size];
        }
    };

    public LanguageOption() {

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(languageId).append(" ")
                .append(label).append(" ")
                .append(value).append(" ")
                .append(isDefault).append(" \n");
        return stringBuilder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(languageId);
        parcel.writeString(label);
        parcel.writeInt(iconId);
        parcel.writeString(value);
        parcel.writeStringArray(child);
        parcel.writeByte((byte) (isDefault ? 1 : 0));
        parcel.writeStringArray(skip);
    }
}
