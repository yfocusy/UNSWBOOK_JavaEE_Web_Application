package com.unsw.Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Friend {
    private int friendshipId;
    private int user1Uid;
    private int user2Uid;
    private Date time;

    @Id
    @Column(name = "friendship_id", nullable = false)
    public int getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }
    @Basic
    @Column(name = "user1_uid", nullable = false)
    public int getUser1Uid() {
        return user1Uid;
    }

    public void setUser1Uid(int user1Uid) {
        this.user1Uid = user1Uid;
    }
    @Basic
    @Column(name = "user2_uid", nullable = false)
    public int getUser2Uid() {
        return user2Uid;
    }

    public void setUser2Uid(int user2Uid) {
        this.user2Uid = user2Uid;
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

        Friend friend = (Friend) o;

        if (friendshipId != friend.friendshipId) return false;
        if (time != null ? !time.equals(friend.time) : friend.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = friendshipId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
