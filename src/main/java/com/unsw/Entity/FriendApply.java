package com.unsw.Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class FriendApply {
    private int friendApplyId;
    private int senderUid;
    private int receiverUid;
    private Date time;

    @Id
    @Column(name = "friendApply_id", nullable = false)
    public int getFriendApplyId() {
        return friendApplyId;
    }

    public void setFriendApplyId(int friendApplyId) {
        this.friendApplyId = friendApplyId;
    }

    @Basic
    @Column(name = "sender_uid", nullable = false)
    public int getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(int senderUid) {
        this.senderUid = senderUid;
    }

    @Basic
    @Column(name = "receiver_uid", nullable = false)
    public int getReceiverUid() {
        return receiverUid;
    }

    public void setReceiverUid(int receiverUid) {
        this.receiverUid = receiverUid;
    }


    @Basic
    @Column(name = "time", nullable = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendApply that = (FriendApply) o;

        if (friendApplyId != that.friendApplyId) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = friendApplyId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
