package com.chingaridesigin.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HashTagsModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("totalNumberOfPostUsed")
    @Expose
    private Integer totalNumberOfPostUsed;
    @SerializedName("weightage")
    @Expose
    private Integer weightage;
    @SerializedName("_id")
    @Expose
    private String id;



    @SerializedName("hashtag")
    @Expose
    private String hashtag;

    public HashTagsModel(String hashtag) {
        this.hashtag = hashtag;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotalNumberOfPostUsed() {
        return totalNumberOfPostUsed;
    }

    public void setTotalNumberOfPostUsed(Integer totalNumberOfPostUsed) {
        this.totalNumberOfPostUsed = totalNumberOfPostUsed;
    }

    public Integer getWeightage() {
        return weightage;
    }

    public void setWeightage(Integer weightage) {
        this.weightage = weightage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

}
