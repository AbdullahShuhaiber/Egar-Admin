package com.example.egar_admin.interfaces;

import com.example.egar_admin.Model.Category;

import java.util.List;

public interface OnCategoryFetchListener {
    void onFetchSuccess(Category category);
    void onFetchAllSuccess(List<Category> categories);
    void onFetchFailure(String errorMessage);
}

