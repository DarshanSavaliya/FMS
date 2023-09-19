package com.skyjetairlines.view.bean;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.event.DialogEvent;

import com.skyjetairlines.view.common.CommonViewUtil;

import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.AttributeContext;
import oracle.binding.RowContext;
import oracle.jbo.Row;
import java.text.ParseException;
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

    @SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
    public void commitCouponTime(DialogEvent dialogEvent) {
        Row couponSelected = CommonViewUtil.getIterator("CouponsTimeInstanceIterator").getCurrentRow();
        String dpt = couponSelected.getAttribute("StartDate").toString();
        String arv = couponSelected.getAttribute("EndDate").toString();
        Date dptd,arvd,today=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            dptd=dateFormat.parse(dpt);
            arvd=dateFormat.parse(arv);
            if(dptd.getDate()<today.getDate()) {
                CommonViewUtil.showErrorMessage("You should not select the StartDate of the coupon before Today! Discarding ...");
            }
            else if(arvd.getDate()<dptd.getDate()) {
                CommonViewUtil.showErrorMessage("EndDate should be greater than or equal to Start Date");
            }
            else {
                couponSelected.setAttribute("Dpt", couponSelected.getAttribute("StartDate").toString());
                couponSelected.setAttribute("Arv",couponSelected.getAttribute("EndDate").toString());
                CommonViewUtil.showSuccessfulMessage("Successfully Created New Coupon of Type Date");
                CommonViewUtil.executeOperation("Commit");
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
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
        Row couponSelected = CommonViewUtil.getIterator("CouponsRangeInstanceIterator").getCurrentRow();
        double dpt = Double.parseDouble(couponSelected.getAttribute("Dpt").toString());
        double arv = Double.parseDouble(couponSelected.getAttribute("Arv").toString());
        System.out.println(dpt);
        System.out.println(arv);
        if(dpt>=0 && arv >=0 && arv>=dpt) {
            CommonViewUtil.executeOperation("Commit");
            CommonViewUtil.showSuccessfulMessage("Successfully created New coupon of Type Price Range");
        }
        else {
            if(dpt<0 || arv < 0) {
                CommonViewUtil.showErrorMessage("Both Beginning of the range and Ending of the Range should be positive value! Discarding ...");
            }
            else {
                CommonViewUtil.showErrorMessage("Ending of the range should be greater than or equal to beginning of the range! Discarding ...");
                CommonViewUtil.executeOperation("Rollback");
            }
        }
    }

    public void commitCouponPlace(DialogEvent dialogEvent) {
        Row couponSelected = CommonViewUtil.getIterator("CouponsPlaceInstanceIterator").getCurrentRow();
        String dpt = couponSelected.getAttribute("Dpt").toString();
        String arv = couponSelected.getAttribute("Arv").toString();
        System.out.println(dpt);
        System.out.println(arv);
        if(dpt.equals(arv)) {
            CommonViewUtil.showErrorMessage("Should not create a Coupon having same Departure and Arrival Place! Discarding ...");
        }
        else {
            CommonViewUtil.executeOperation("Commit");
            CommonViewUtil.showSuccessfulMessage("Successfully Created New Coupon of Type Place");
        }
    }
}
