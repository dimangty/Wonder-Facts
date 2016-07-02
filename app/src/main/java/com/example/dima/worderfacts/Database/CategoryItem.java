package com.example.dima.worderfacts.Database;

/**
 * Created by dima on 05.06.16.
 */
public class CategoryItem {
    private int mCategoryId;
    private String mCategoryName;
    private int mFactsCount;

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.mCategoryId = CategoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.mCategoryName = categoryName;
    }

    public int getFactsCount() {
        return mFactsCount;
    }

    public void setFactsCount(int factsCount) {
        this.mFactsCount = factsCount;
    }
}
