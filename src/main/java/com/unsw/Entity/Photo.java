package com.unsw.Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Photo {
    private int photoId;
    //X*
    private int albumId;
    private int postId;
    @Basic
    @Column(name="album_id", nullable=false)
    public int getAlbumId(){
        return albumId;
    }
    public void setAlbumId(int albumId){
        this.albumId=albumId;
    }

    @Basic
    @Column(name="post_id", nullable = false)
    public int getPostId(){
        return postId;
    }
    public void setPostId(int postId){this.postId=postId;}
    //*X

    @Id
    @Column(name = "photo_id", nullable = false)
    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (photoId != photo.photoId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return photoId;
    }
}
