package com.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "users", schema = "wpobase")
public class User {
    @Id
    @GenericGenerator(name = "user-uuid",strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "user_tel")
    private String userTel;
    @Basic
    @Column(name = "user_pwd")
    private String userPwd;
    @Basic
    @Column(name = "user_center")
    private String userCenter;
    @Basic
    @Column(name = "user_active")
    private Boolean userActive;
    @ManyToOne
    @JoinColumn(name = "user_authority_id",insertable =false,updatable = false)

    @JSONField(serialize = false)
    private Authority authority;
    @Basic
    @Column(name = "user_authority_id")
    private int authorityId;

    public User(){

    }
    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }


    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }


    public String getUserCenter() {
        return userCenter;
    }

    public void setUserCenter(String userCenter) {
        this.userCenter = userCenter;
    }


    public Boolean getUserActive() {
        return userActive;
    }

    public void setUserActive(Boolean userActive) {
        this.userActive = userActive;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userTel != null ? !userTel.equals(that.userTel) : that.userTel != null) return false;
        if (userPwd != null ? !userPwd.equals(that.userPwd) : that.userPwd != null) return false;
        if (userCenter != null ? !userCenter.equals(that.userCenter) : that.userCenter != null) return false;
        if (userActive != null ? !userActive.equals(that.userActive) : that.userActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userTel != null ? userTel.hashCode() : 0);
        result = 31 * result + (userPwd != null ? userPwd.hashCode() : 0);
        result = 31 * result + (userCenter != null ? userCenter.hashCode() : 0);
        result = 31 * result + (userActive != null ? userActive.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userCenter='" + userCenter + '\'' +
                ", userActive=" + userActive +
                '}';
    }
}
