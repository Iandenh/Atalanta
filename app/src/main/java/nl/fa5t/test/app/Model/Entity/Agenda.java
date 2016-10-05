package nl.fa5t.test.app.Model.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ian on 14-9-16.
 * http://www.parcelabler.com/
 */
public class Agenda implements Parcelable {
    public int id;
    public Party party;
    public Date date;
    public String desc;
    public String body;
    public double price;
    public double price_height;
    public String photo;
    public String photo_dir;



    protected Agenda(Parcel in) {
        id = in.readInt();
        party = (Party) in.readValue(Party.class.getClassLoader());
        long tmpDate = in.readLong();
        date = tmpDate != -1 ? new Date(tmpDate) : null;
        desc = in.readString();
        body = in.readString();
        price = in.readDouble();
        price_height = in.readDouble();
        photo = in.readString();
        photo_dir = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeValue(party);
        dest.writeLong(date != null ? date.getTime() : -1L);
        dest.writeString(desc);
        dest.writeString(body);
        dest.writeDouble(price);
        dest.writeDouble(price_height);
        dest.writeString(photo);
        dest.writeString(photo_dir);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Agenda> CREATOR = new Parcelable.Creator<Agenda>() {
        @Override
        public Agenda createFromParcel(Parcel in) {
            return new Agenda(in);
        }

        @Override
        public Agenda[] newArray(int size) {
            return new Agenda[size];
        }
    };
}
