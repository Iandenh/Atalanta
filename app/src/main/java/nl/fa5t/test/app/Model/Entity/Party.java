package nl.fa5t.test.app.Model.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ian on 14-9-16.
 * http://www.parcelabler.com/
 */
public class Party implements Parcelable {
    public int id;
    public String title;
    public String desc;

    protected Party(Parcel in) {
        id = in.readInt();
        title = in.readString();
        desc = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(desc);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Party> CREATOR = new Parcelable.Creator<Party>() {
        @Override
        public Party createFromParcel(Parcel in) {
            return new Party(in);
        }

        @Override
        public Party[] newArray(int size) {
            return new Party[size];
        }
    };
}
