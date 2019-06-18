package com.elfefe.mynews.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Search implements Parcelable {

    private String search;
    private String dateBegin;
    private String dateEnd;
    private List<String> sections;
    private boolean[] checked;

    protected Search(Parcel in) {
        search = in.readString();
        dateBegin = in.readString();
        dateEnd = in.readString();
        sections = in.createStringArrayList();
        checked = in.createBooleanArray();
    }

    public Search(String search,String dateBegin,String dateEnd,List<String> sections,boolean[] checked){

        this.search = search;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.sections = sections;
        this.checked = checked;
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
        dest.writeStringList(sections);
        dest.writeBooleanArray(checked);
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

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<String> getSections() {
        return sections;
    }

    public void setSections(List<String> sections) {
        this.sections = sections;
    }

    public boolean[] getChecked() {
        return checked;
    }

    public void setChecked(boolean[] checked) {
        this.checked = checked;
    }
}
