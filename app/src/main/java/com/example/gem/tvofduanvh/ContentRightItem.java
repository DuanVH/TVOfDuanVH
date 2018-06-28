package com.example.gem.tvofduanvh;

/**
 * Created by gem on 6/28/18.
 */

public class ContentRightItem {

  private String imageUrl;
  private String videoUrl;
  private String title;
  private String description;

  public ContentRightItem(String title, String description, String imageUrl, String videoUrl) {
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
    this.videoUrl = videoUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }
}
