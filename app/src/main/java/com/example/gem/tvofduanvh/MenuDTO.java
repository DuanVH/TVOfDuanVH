package com.example.gem.tvofduanvh;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gem on 6/26/18.
 */

public class MenuDTO implements Parcelable {

  private int id;
  private String name;

  public static final Parcelable.Creator<MenuDTO> CREATOR = new Parcelable.Creator<MenuDTO>() {
    public MenuDTO createFromParcel(Parcel in) {
      return new MenuDTO(in);
    }

    @Override
    public MenuDTO[] newArray(int size) {
      return new MenuDTO[size];
    }
  };

  private MenuDTO(Parcel in) {
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
