package nl.fa5t.test.app.Model.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ian on 5-10-16.
 */
public class Album implements Parcelable {

    public int id;
    public int gallery_category_id;
    public String title;
    public String description;
    public Date created;
    public boolean status;
    public ArrayList<Image> images;

    protected Album(Parcel in) {
        id = in.readInt();
        gallery_category_id = in.readInt();
        title = in.readString();
        description = in.readString();
        long tmpCreated = in.readLong();
        created = tmpCreated != -1 ? new Date(tmpCreated) : null;
        status = in.readByte() != 0x00;
        if (in.readByte() == 0x01) {
            images = new ArrayList<Image>();
            in.readList(images, Image.class.getClassLoader());
        } else {
            images = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(gallery_category_id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeLong(created != null ? created.getTime() : -1L);
        dest.writeByte((byte) (status ? 0x01 : 0x00));
        if (images == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(images);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}
