package com.chingaridesigin;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chingaridesigin.model.HashTagsModel;
import com.chingaridesigin.model.HashTagsSuggestModel;
import com.chingaridesigin.network.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SuggestionViewModel extends ViewModel {

    private MutableLiveData<List<HashTagsModel>> list=new MutableLiveData<>();
    private MutableLiveData<String> errorMessage=new MutableLiveData<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public void setSuggestionsStatic() {
        list.setValue(initArray());
    }

    public void setSuggestions(String query) {
        Map<String, String> params = new HashMap<>();
        params.put("hashtag", query);
        RestClient.webServices()
                .setSuggestions(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HashTagsSuggestModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HashTagsSuggestModel res) {
                        Log.d("TAG", "Response : " + res.getMessage());
                        if(res.getCode()==200){
                            list.setValue(res.getData());
                        }else {
                            list.setValue(initArray());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        list.setValue(initArray());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }


    public MutableLiveData<List<HashTagsModel>> getSuggestions() {
        List<HashTagsModel> data = initArray();
        list.setValue(data);
       return list;
    }

    public MutableLiveData<String> getSuggestionsError() {
        errorMessage.setValue("Error");
        return errorMessage;
    }

    private List<HashTagsModel> initArray() {
        List<HashTagsModel> suggestionList = new ArrayList<>();
        suggestionList.add(new HashTagsModel("\uD83E\uDDDC #Dance"));
        suggestionList.add(new HashTagsModel("#Funny"));
        suggestionList.add(new HashTagsModel("#Life"));
        suggestionList.add(new HashTagsModel("\uD83D\uDE03 #Smileys & People"));
        suggestionList.add(new HashTagsModel("#Fail"));
        suggestionList.add(new HashTagsModel("\uD83E\uDD70 #Smiling Face with Hearts"));
        suggestionList.add(new HashTagsModel("#Status"));
        suggestionList.add(new HashTagsModel("\uD83D\uDC4D #Thumbs Up"));
        suggestionList.add(new HashTagsModel("#Devotional"));
        suggestionList.add(new HashTagsModel("#Motivation challenge"));
        suggestionList.add(new HashTagsModel("#Music"));
        suggestionList.add(new HashTagsModel("\uD83C\uDF82 #Birthday"));
        suggestionList.add(new HashTagsModel("#Bollywood"));
        suggestionList.add(new HashTagsModel("\uD83D\uDC95 #Hearts"));
        suggestionList.add(new HashTagsModel("#News"));
        suggestionList.add(new HashTagsModel("\uD83D\uDC98 #Valentine’s Day"));
        return  suggestionList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
