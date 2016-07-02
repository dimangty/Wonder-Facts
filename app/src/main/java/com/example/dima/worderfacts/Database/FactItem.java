package com.example.dima.worderfacts.Database;



/**
 * Created by dima on 04.06.16.
 */
public class FactItem {


    private int mFactId;
    private int mUserId;
    private String mFirstName;
    private String mLastName;
    private String mCurrentAvatar;


    private String mFactDateStr;
    private int mFactDateInt;
    private int mFactRating;
    private String mFactBody;
    private int mFactCount;

    private String mCurrentTitul;
    private int mCurrentTotalRating;
    private int mFactStar;

    private String mCategory;
    private int mCategoryId;

    public int getFactId() {
        return mFactId;
    }

    public void setFactId(int factId) {
        this.mFactId = factId;
    }

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

    public String getCurrentAvatar() {
        return mCurrentAvatar;
    }

    public void setCurrentAvatar(String currentAvatar) {
        this.mCurrentAvatar = currentAvatar;
    }

    public String getFactDateStr() {
        return mFactDateStr;
    }

    public void setFactDateStr(String factDateStr) {
        this.mFactDateStr = factDateStr;
    }

    public int getFactDateInt() {
        return mFactDateInt;
    }

    public void setFactDateInt(int factDateInt) {
        this.mFactDateInt = factDateInt;
    }

    public int getFactRating() {
        return mFactRating;
    }

    public void setFactRating(int factRating) {
        this.mFactRating = factRating;
    }

    public String getFactBody() {
        return mFactBody;
    }

    public void setFactBody(String factBody) {
        this.mFactBody = factBody;
    }

    public int getFactCount() {
        return mFactCount;
    }

    public void setFactCount(int factCount) {
        this.mFactCount = factCount;
    }

    public String getCurrentTitul() {
        return mCurrentTitul;
    }

    public void setCurrentTitul(String currentTitul) {
        this.mCurrentTitul = currentTitul;
    }

    public int getCurrentTotalRating() {
        return mCurrentTotalRating;
    }

    public void setCurrentTotalRating(int currentTotalRating) {
        this.mCurrentTotalRating = currentTotalRating;
    }

    public int getFactStar() {
        return mFactStar;
    }

    public void setFactStar(int factStar) {
        this.mFactStar = factStar;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        this.mCategoryId = categoryId;
    }
}
