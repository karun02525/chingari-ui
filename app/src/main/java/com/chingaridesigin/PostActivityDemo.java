package com.chingaridesigin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.chingaridesigin.model.HashTagsModel;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class PostActivityDemo extends AppCompatActivity {


    private HashTagsAdapter mSuggestionListAdapter;
    private ArrayList<HashTagsModel> suggestionList=new ArrayList<>();
    private EditText edit_container, edit_search_suggest;
    private TextView tvCountShow;
    private RecyclerView rv_hashtags;
    private ImageView iv_search_clear;
    private ProgressBar progress_search_circular;
    private SuggestionViewModel searchViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        searchViewModel = new ViewModelProvider(this).get(SuggestionViewModel.class);



        edit_container = findViewById(R.id.edit_caption);
        edit_search_suggest = findViewById(R.id.edit_search_suggest);
        rv_hashtags = findViewById(R.id.rv_hashtags);
        tvCountShow = findViewById(R.id.tvCountShow);
        progress_search_circular = findViewById(R.id.progress_search_circular);
        iv_search_clear = findViewById(R.id.iv_search_clear);




        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.CENTER);
        rv_hashtags.setLayoutManager(layoutManager);


        setupViewModel();
        initTextCount();
        initSearchSuggestionQuery();
    }

    private void initTextCount() {
        edit_container.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvCountShow.setText(s.toString().length() + "/100");

            }
        });
    }


    private void notifyAdapter(){
        mSuggestionListAdapter = new HashTagsAdapter(this, suggestionList);
        rv_hashtags.setAdapter(mSuggestionListAdapter);
        mSuggestionListAdapter.notifyDataSetChanged();
    }




    void addTags(String tags) {
        if (!edit_container.getText().toString().contains(tags)) {
            edit_container.append(tags + " ");
        } else {
            Toast.makeText(this, tags + " already added", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupViewModel(){
        searchViewModel.getSuggestions().observe(this, new Observer<List<HashTagsModel>>() {
            @Override
            public void onChanged(List<HashTagsModel> data) {
                if (data != null) {
                    PostActivityDemo.this.progressHideShow(false);
                    suggestionList = (ArrayList<HashTagsModel>) data;
                    notifyAdapter();
                }
            }
        });


        searchViewModel.getSuggestionsError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String data) {
                PostActivityDemo.this.progressHideShow(false);

            }
        });


        iv_search_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_search_suggest.getText().clear();
                closeSuggestion();
            }
        });
    }

    void closeSuggestion() {
        suggestionList.clear();
        mSuggestionListAdapter.notifyDataSetChanged();
        iv_search_clear.setVisibility(View.GONE);
        progressHideShow(false);
    }

    private void initSearchSuggestionQuery() {
        edit_search_suggest.requestFocus();
        edit_search_suggest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>2) {
                    searchViewModel.setSuggestions(s.toString().trim());
                    iv_search_clear.setVisibility(View.VISIBLE);
                    progressHideShow(true);

                }else{
                    closeSuggestion();
                    searchViewModel.setSuggestionsStatic();
                }
            }
        });
    }

    void progressHideShow(boolean flag) {
        if (flag) {
            progress_search_circular.setVisibility(View.VISIBLE);
        } else {
            progress_search_circular.setVisibility(View.GONE);
        }
    }
}
