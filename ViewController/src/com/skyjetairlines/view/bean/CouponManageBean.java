package com.skyjetairlines.view.bean;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.event.DialogEvent;

import com.skyjetairlines.view.common.CommonViewUtil;

import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.AttributeContext;
import oracle.binding.RowContext;
import oracle.jbo.Row;

public class CouponManageBean {
    public CouponManageBean() {
    }

    public void deleteCommitCouponPlace(DialogEvent dialogEvent) {
        CommonViewUtil.executeOperation("DeletePlace");
        CommonViewUtil.executeOperation("Commit");
    }

    public void createAndInsertCouponPlace(PopupFetchEvent popupFetchEvent) {
        CommonViewUtil.executeOperation("CreateInsertPlace");
    }

    public boolean isTransactionDirty() {
        return false;
    }

    public void rollbackTransaction() {
    }

    public void commitTransaction() {
    }

    public boolean setAttributeValue(AttributeContext p0, Object p1) {
        return false;
    }

    public Object createRowData(RowContext p0) {
        return null;
    }

    public Object registerDataProvider(RowContext p0) {
        return null;
    }

    public boolean removeRowData(RowContext p0) {
        return false;
    }

    public void validate() {
    }

    public void commitCouponPlace(DialogEvent dialogEvent) {
        CommonViewUtil.executeOperation("Commit");
    }

    public void couponPlaceRollBack(PopupCanceledEvent popupCanceledEvent) {
        CommonViewUtil.executeOperation("Rollback");
    }

    public void testMethod(ActionEvent actionEvent) {
        Row couponSelected = CommonViewUtil.getIterator("CouponsTimeInstanceIterator").getCurrentRow();
        couponSelected.setAttribute("Dpt", couponSelected.getAttribute("StartDate").toString());
        couponSelected.setAttribute("Arv",couponSelected.getAttribute("EndDate").toString());
        CommonViewUtil.executeOperation("Commit");
    }

    public void createAndInsertCouponTime(PopupFetchEvent popupFetchEvent) {
        CommonViewUtil.executeOperation("CreateInsertTime");
    }

    public void commitCouponTime(DialogEvent dialogEvent) {
        Row couponSelected = CommonViewUtil.getIterator("CouponsTimeInstanceIterator").getCurrentRow();
        couponSelected.setAttribute("Dpt", couponSelected.getAttribute("StartDate").toString());
        couponSelected.setAttribute("Arv",couponSelected.getAttribute("EndDate").toString());
        CommonViewUtil.executeOperation("Commit");
    }

    public void deleteCommitCouponTime(DialogEvent dialogEvent) {
        CommonViewUtil.executeOperation("DeleteTime");
        CommonViewUtil.executeOperation("Commit");
    }

    public void commitTime(PopupCanceledEvent popupCanceledEvent) {
        CommonViewUtil.executeOperation("Rollback");
    }

    public void createInsertCouponRange(PopupFetchEvent popupFetchEvent) {
        CommonViewUtil.executeOperation("CreateInsertRange");
    }

    public void deleteCommitCouponRange(DialogEvent dialogEvent) {
        CommonViewUtil.executeOperation("DeleteRange");
        CommonViewUtil.executeOperation("Commit");
    }

    public void commitCouponRange(DialogEvent dialogEvent) {
        CommonViewUtil.executeOperation("Commit");
    }
}
