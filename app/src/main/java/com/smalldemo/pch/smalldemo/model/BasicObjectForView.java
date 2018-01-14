package com.smalldemo.pch.smalldemo.model;

/**
 * Used with the ListViewImageAdapter to add the possibility to switch between the thumbnail and the fullsize image.
 */
public class BasicObjectForView extends BasicOject {
    private boolean isTumbnail = true;

    public BasicObjectForView() {
    }

    public BasicObjectForView(BasicOject basicOject) {
        super(basicOject.albumId, basicOject.id, basicOject.getTitle(), basicOject.url, basicOject.thumbnailUrl);
    }

    @Override
    public boolean isThumbnailToShow() {
        return isTumbnail;
    }

    @Override
    public void switchImageToShow() {
        isTumbnail = !isTumbnail;
    }


}
