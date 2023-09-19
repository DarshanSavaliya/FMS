package com.skyjetairlines.view.bean;

import com.skyjetairlines.view.common.CommonViewUtil;

import oracle.adf.controller.ControllerContext;

import oracle.adf.share.ADFContext;

import oracle.dss.dataView.Controller;

import oracle.jbo.Row;
import oracle.jbo.SessionContext;

public class UserRegisterationBean {
    public UserRegisterationBean() {
    }

    public void assignUserToGroup() {
        CommonViewUtil.executeOperation("CreateGroupMembership");
        Row currentUser = CommonViewUtil.getIterator("GroupMembersInstanceForUsersIterator").getCurrentRow();
        if (ADFContext.getCurrent()
                      .getSecurityContext()
                      .isUserInRole("admin")) {
            currentUser.setAttribute("Name", "admin");
        } else {
            currentUser.setAttribute("Name", "customer");
        }
        CommonViewUtil.executeOperation("CreateCustomer");
        CommonViewUtil.setAttributeInIterator("CustomersInstanceIterator", "Username",
                                              currentUser.getAttribute("Member"));
    }
}
