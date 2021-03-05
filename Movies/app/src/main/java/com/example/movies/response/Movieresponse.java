package com.example.movies.response;

import com.example.movies.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//class is for requesting a single movie
public class Movieresponse {
    //1 finding the movie object

    @SerializedName("results")
    @Expose
    private MovieModel movie;
    public MovieModel getMovie(){
        return  movie;

    }

    @Override
    public String toString() {
        return "Movieresponse{" +
                "movie=" + movie +
                '}';
    }
}
