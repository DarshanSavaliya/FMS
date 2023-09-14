package com.skyjetairlines.view.bean;

import com.skyjetairlines.view.common.CommonViewUtil;

import oracle.adf.view.rich.event.DialogEvent;

public class ManageFlightBean {
    public ManageFlightBean() {
    }

    public void deleteAndCommit(DialogEvent dialogEvent) {
        CommonViewUtil.executeOperation("DeleteFlight");
        CommonViewUtil.executeOperation("Commit");
    }
}
