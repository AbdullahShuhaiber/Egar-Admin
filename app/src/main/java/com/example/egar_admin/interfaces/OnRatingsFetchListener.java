package com.example.egar_admin.interfaces;





import com.example.egar_admin.Model.Rating;

import java.util.List;

public interface OnRatingsFetchListener {
    void onRatingsFetched(List<Rating> ratings);
    void onFailure(String errorMessage);

    void onRatingsFetchFailure(String message);

}
