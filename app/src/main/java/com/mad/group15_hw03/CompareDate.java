/*
Assignment : HomeWork 03
File Name : Group15_HW03.zip
Full Names : Manideep Reddy Nukala, Sai Charan Reddy Vallapureddy
 */
package com.mad.group15_hw03;
import java.util.Comparator;

public class CompareDate<T> implements Comparator<Album>{
    @Override
    public int compare(Album o1, Album o2) {
       return o1.releaseDate.compareTo(o2.releaseDate);
    }
}