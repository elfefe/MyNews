package com.elfefe.mynews.models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class PageOrganiser extends ViewModel {

    private MutableLiveData<String> orgFacet;

    public static PageOrganiser instance(){
        return new PageOrganiser();
    }

    private PageOrganiser(){
    }


    MutableLiveData<String> getOrgFacet(){
        return this.orgFacet;
    }

    void setOrgFacet(MutableLiveData<String> orgFacet){
        this.orgFacet = orgFacet;
    }
}
