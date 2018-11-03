package com.unsw.Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Post {
    private int postId;
    private String content;
    private Timestamp postime;
    private String imageNum;
    private Integer commentNum;
    private Integer likeNum;
    private Integer unlikeNum;
    //X*
    private int uid;
    @Basic
    @Column(name="poster_uid", nullable=false)
    public int getUid(){
        return uid;
    }
    public void setUid(int Uid){
        this.uid=Uid;
    }
    //*X
    @Id
    @Column(name = "post_id", nullable = false)
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 256)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "postime", nullable = true)
    public Timestamp getPostime() {
        return postime;
    }

    public void setPostime(Timestamp postime) {
        this.postime = postime;
    }

    @Basic
    @Column(name = "image_num", nullable = true)
    public String getImageNum() {
        return imageNum;
    }

    public void setImageNum(String imageNum) {
        this.imageNum = imageNum;
    }

    @Basic
    @Column(name = "comment_num", nullable = true)
    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    @Basic
    @Column(name = "like_num", nullable = true)
    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    @Basic
    @Column(name = "unlike_num", nullable = true)
    public Integer getUnlikeNum() {
        return unlikeNum;
    }

    public void setUnlikeNum(Integer unlikeNum) {
        this.unlikeNum = unlikeNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (postId != post.postId) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        if (postime != null ? !postime.equals(post.postime) : post.postime != null) return false;
        if (imageNum != null ? !imageNum.equals(post.imageNum) : post.imageNum != null) return false;
        if (commentNum != null ? !commentNum.equals(post.commentNum) : post.commentNum != null) return false;
        if (likeNum != null ? !likeNum.equals(post.likeNum) : post.likeNum != null) return false;
        if (unlikeNum != null ? !unlikeNum.equals(post.unlikeNum) : post.unlikeNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (postime != null ? postime.hashCode() : 0);
        result = 31 * result + (imageNum != null ? imageNum.hashCode() : 0);
        result = 31 * result + (commentNum != null ? commentNum.hashCode() : 0);
        result = 31 * result + (likeNum != null ? likeNum.hashCode() : 0);
        result = 31 * result + (unlikeNum != null ? unlikeNum.hashCode() : 0);
        return result;
    }
}
