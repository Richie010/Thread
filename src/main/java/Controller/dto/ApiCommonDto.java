package Controller.dto;

import java.math.BigDecimal;

public class ApiCommonDto {

    private String accNo;        // Account number
    private String accId;        // Account ID
    private String cifId;        // Customer Information File ID
    private BigDecimal balance;  // Account balance
    private String branchName;   // Branch name

    // Default constructor
    public ApiCommonDto() {}

    // Parameterized constructor
    public ApiCommonDto(String accNo, String accId, String cifId, BigDecimal balance, String branchName) {
        this.accNo = accNo;
        this.accId = accId;
        this.cifId = cifId;
        this.balance = balance;
        this.branchName = branchName;
    }

    // Getters and Setters

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getCifId() {
        return cifId;
    }

    public void setCifId(String cifId) {
        this.cifId = cifId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public String toString() {
        return "ApiCommonDto{" +
                "accNo='" + accNo + '\'' +
                ", accId='" + accId + '\'' +
                ", cifId='" + cifId + '\'' +
                ", balance=" + balance +
                ", branchName='" + branchName + '\'' +
                '}';
    }
}
