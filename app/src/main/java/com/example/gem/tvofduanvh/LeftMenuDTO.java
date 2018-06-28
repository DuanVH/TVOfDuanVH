package com.example.gem.tvofduanvh;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gem on 6/26/18.
 */

public class LeftMenuDTO implements Parcelable {

  private int id;
  private String name;

  public static final Parcelable.Creator<LeftMenuDTO> CREATOR = new Parcelable.Creator<LeftMenuDTO>() {
    public LeftMenuDTO createFromParcel(Parcel in) {
      return new LeftMenuDTO(in);
    }

    @Override
    public LeftMenuDTO[] newArray(int size) {
      return new LeftMenuDTO[size];
    }
  };

  private LeftMenuDTO(Parcel in) {
    id = in.readInt();
    name = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeString(name);
  }
}
