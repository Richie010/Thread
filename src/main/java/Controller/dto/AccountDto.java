package Controller.dto;

import java.math.BigDecimal;
import java.util.Date;


public class AccountDto {

    private String accno;
    private String bankname;
    private String branchname;
    private String bnfcryname;
    private String chqno;
    private BigDecimal chqammount;
    private String currency;
    private Date chqdate;
    private Date settlementDate;
    private String bnfcryAccno;
    private String draweeAccno;
    private String status;

    // Getters and Setters

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getBnfcryname() {
        return bnfcryname;
    }

    public void setBnfcryname(String bnfcryname) {
        this.bnfcryname = bnfcryname;
    }

    public String getChqno() {
        return chqno;
    }

    public void setChqno(String chqno) {
        this.chqno = chqno;
    }

    public BigDecimal getChqammount() {
        return chqammount;
    }

    public void setChqammount(BigDecimal chqammount) {
        this.chqammount = chqammount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getChqdate() {
        return chqdate;
    }

    public void setChqdate(Date chqdate) {
        this.chqdate = chqdate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getBnfcryAccno() {
        return bnfcryAccno;
    }

    public void setBnfcryAccno(String bnfcryAccno) {
        this.bnfcryAccno = bnfcryAccno;
    }

    public String getDraweeAccno() {
        return draweeAccno;
    }

    public void setDraweeAccno(String draweeAccno) {
        this.draweeAccno = draweeAccno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Optional: toString method

    @Override
    public String toString() {
        return "AccountDto{" +
                "accno='" + accno + '\'' +
                ", bankname='" + bankname + '\'' +
                ", branchname='" + branchname + '\'' +
                ", bnfcryname='" + bnfcryname + '\'' +
                ", chqno='" + chqno + '\'' +
                ", chqammount=" + chqammount +
                ", currency='" + currency + '\'' +
                ", chqdate=" + chqdate +
                ", settlementDate=" + settlementDate +
                ", bnfcryAccno='" + bnfcryAccno + '\'' +
                ", draweeAccno='" + draweeAccno + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
