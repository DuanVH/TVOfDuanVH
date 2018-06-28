package com.example.gem.tvofduanvh;

/**
 * Created by gem on 6/28/18.
 */

public class ContentRight {

  private String image;
  private String title;
  private String description;

  public ContentRight(String title, String description, String image) {
    this.image = image;
    this.title = title;
    this.description = description;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImage() {
    return image;
  }

  public String getTitle() {
    return title;
  }
}
