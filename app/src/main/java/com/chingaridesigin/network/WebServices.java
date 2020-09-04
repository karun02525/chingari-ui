package com.chingaridesigin.network;

import com.chingaridesigin.model.HashTagsSuggestModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface WebServices {

    @FormUrlEncoded
    @POST("/post/v2/searchHashtag")
    Observable<HashTagsSuggestModel> setSuggestions(@FieldMap Map<String, String> params);

}
