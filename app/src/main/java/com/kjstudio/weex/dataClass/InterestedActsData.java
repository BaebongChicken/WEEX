package com.kjstudio.weex.dataClass;

import java.io.Serializable;

/**
 * Created by JinHee on 2015-12-14.
 */
public class InterestedActsData implements Serializable {
    public String actsName = null;
    public String actsLevel = null;



    public InterestedActsData(String mActsName, String mActsLevel) {
        this.actsName = mActsName;
        this.actsLevel = mActsLevel;
    }

    public InterestedActsData() {

    }
}
