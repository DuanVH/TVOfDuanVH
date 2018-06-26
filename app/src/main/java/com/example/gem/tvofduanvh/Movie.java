package com.example.gem.tvofduanvh;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by gem on 6/26/18.
 */

public class Movie {

  private int id;
  private String url;
  private String title;
  private String description;

  public Movie(int id, String url, String title, String description) {
    this.id = id;
    this.url = url;
    this.title = title;
    this.description = description;
  }

  public Movie(String title, String description, String url) {
    this.title = title;
    this.description = description;
    this.url = url;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public URI getURI() {
    try {
      return new URI(getUrl());
    } catch (URISyntaxException e) {
      return null;
    }
  }
}
