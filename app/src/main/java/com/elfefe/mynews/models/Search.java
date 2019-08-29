package com.elfefe.mynews.models;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings({"unused", "CanBeFinal"})
public class Search implements Parcelable {

    private String search;
    private String dateBegin;
    private String dateEnd;
    private String sections;

    private Search(Parcel in) {
        search = in.readString();
        dateBegin = in.readString();
        dateEnd = in.readString();
        sections = in.readString();
    }

    public Search(String search,String dateBegin,String dateEnd,String sections){

        this.search = search;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.sections = sections;
    }

    public static final Creator<Search> CREATOR = new Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel in) {
            return new Search(in);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(search);
        dest.writeString(dateBegin);
        dest.writeString(dateEnd);
        dest.writeString(sections);
    }


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getSections() {
        return sections;
    }
}
