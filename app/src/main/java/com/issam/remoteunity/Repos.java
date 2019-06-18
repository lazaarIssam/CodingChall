package com.issam.remoteunity;

public class Repos {
    private String reposName,reposDescription,starsNbr,userName,avatarURL;

    public Repos() {
    }

    public Repos(String reposName, String reposDescription, String starsNbr, String userName, String avatarURL) {
        this.reposName = reposName;
        this.reposDescription = reposDescription;
        this.starsNbr = starsNbr;
        this.userName = userName;
        this.avatarURL = avatarURL;
    }

    public String getReposName() {
        return reposName;
    }

    public void setReposName(String reposName) {
        this.reposName = reposName;
    }

    public String getReposDescription() {
        return reposDescription;
    }

    public void setReposDescription(String reposDescription) {
        this.reposDescription = reposDescription;
    }

    public String getStarsNbr() {
        return starsNbr;
    }

    public void setStarsNbr(String starsNbr) {
        this.starsNbr = starsNbr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
