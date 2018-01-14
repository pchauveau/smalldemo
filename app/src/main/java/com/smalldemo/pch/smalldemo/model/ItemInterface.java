package com.smalldemo.pch.smalldemo.model;

/**
 * Used as a contracts.
 * Allow to have multiple Object to use in a List and still have the same functions to use.
 */
public interface ItemInterface {
    String getTitle();
    String getLinkThumbnail();
    String getLinkFullsizeImage();

    boolean isThumbnailToShow();
    void switchImageToShow();
}
