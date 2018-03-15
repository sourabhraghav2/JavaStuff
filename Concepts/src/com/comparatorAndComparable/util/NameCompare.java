package com.comparatorAndComparable.util;

import java.util.Comparator;

import com.comparatorAndComparable.model.Movie;

public class NameCompare implements Comparator<Movie>
{
    public int compare(Movie m1, Movie m2)
    {
        return m1.getName().compareTo(m2.getName());
    }
}