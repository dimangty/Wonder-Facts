package com.example.dima.worderfacts.Database;

/**
 * Created by dima on 11.06.16.
 */
public class UserItem {
    private int mUserId;

    private String mFirstName;
    private String mLastName;

    private int mFactCount;
    private String mTitul;
    private int mTotalRating;
    private int mStarCount;
    private String mAvatarUrl;
    private int mSocialType;

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        this.mUserId = userId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public int getFactCount() {
        return mFactCount;
    }

    public void setFactCount(int factCount) {
        this.mFactCount = factCount;
    }

    public String getTitul() {
        return mTitul;
    }

    public void setTitul(String titul) {
        this.mTitul = titul;
    }

    public int getTotalRating() {
        return mTotalRating;
    }

    public void setTotalRating(int totalRating) {
        this.mTotalRating = totalRating;
    }

    public int getStarCount() {
        return mStarCount;
    }

    public void setStarCount(int starCount) {
        this.mStarCount = starCount;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.mAvatarUrl = avatarUrl;
    }

    public int getSocialType() {
        return mSocialType;
    }

    public void setSocialType(int socialType) {
        this.mSocialType = socialType;
    }
}
