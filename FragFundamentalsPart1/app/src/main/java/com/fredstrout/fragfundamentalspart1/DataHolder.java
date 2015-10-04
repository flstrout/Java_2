package com.fredstrout.fragfundamentalspart1;

import java.io.Serializable;

/**
 * Created by fredstrout on 10/4/15.
 */
public class DataHolder implements Serializable {
    private static final long serialVersionUID = 8736847634070552888L;

    private String mData;
    private int mChanges;

    public DataHolder() {
        mData = "";
        mChanges = 0;
    }

    public void setData(String _data) {
        mData = _data;
        mChanges++;
    }

    public String getData() {
        return mData;
    }

    public int getChanges() {
        return mChanges;
    }
}
