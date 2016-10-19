package nl.fa5t.test.app.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ian on 5-10-16.
 */
public class Image implements Parcelable {

    public int id;
    public int gallery_album_id;
    public String filename_on_disk;
    public String original_file_name;
    public Date created;
    public boolean promoted;

    @Override
    public String toString() {
        return filename_on_disk;
    }

    protected Image(Parcel in) {
        id = in.readInt();
        gallery_album_id = in.readInt();
        filename_on_disk = in.readString();
        original_file_name = in.readString();
        long tmpCreated = in.readLong();
        created = tmpCreated != -1 ? new Date(tmpCreated) : null;
        promoted = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(gallery_album_id);
        dest.writeString(filename_on_disk);
        dest.writeString(original_file_name);
        dest.writeLong(created != null ? created.getTime() : -1L);
        dest.writeByte((byte) (promoted ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
