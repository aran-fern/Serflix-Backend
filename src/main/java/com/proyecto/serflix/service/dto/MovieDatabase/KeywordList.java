package com.proyecto.serflix.service.dto.MovieDatabase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javassist.compiler.ast.Keyword;

import java.util.List;

public class KeywordList {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("keywords")
    @Expose
    private List<Keyword> keywords = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "KeywordList{" +
            "id=" + id +
            ", keywords=" + keywords +
            '}';
    }
}
