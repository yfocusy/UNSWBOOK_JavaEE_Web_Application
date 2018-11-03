package com.unsw.Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Album {
    private int albumId;
    private String type;

    //X*
    private int uid;
    @Basic
    @Column(name="uid", nullable=false)
    public int getUid(){
        return uid;
    }
    public void setUid(int Uid){
        this.uid=Uid;
    }
    //*X

    @Id
    @Column(name = "album_id", nullable = false)
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 128)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (albumId != album.albumId) return false;
        if (type != null ? !type.equals(album.type) : album.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = albumId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
