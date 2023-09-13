package com.skyjetairlines.view.common;

import oracle.adf.view.rich.event.DialogEvent;

public class ManageFlightBean {
    public ManageFlightBean() {
    }

    public void deleteAndCommit(DialogEvent dialogEvent) {
        CommonViewUtil.executeOperation("DeleteFlight");
        CommonViewUtil.executeOperation("Commit");
    }
}
