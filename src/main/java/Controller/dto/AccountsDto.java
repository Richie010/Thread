package Controller.dto;

import java.math.BigDecimal;

public class AccountsDto {

    private String accNo;
    private String accName;
    private String cifId;
    private int balance;
    private int workingBalance;
    private String branchName;
    private String additionalInfo; // Some extra information if needed
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Default constructor
    public AccountsDto() {}

    // Parameterized constructor
    public AccountsDto(String accNo, String accName, String cifId, int balance, int workingBalance, String branchName,String status) {
        this.accNo = accNo;
        this.accName = accName;
        this.cifId = cifId;
        this.balance = balance;
        this.workingBalance = workingBalance;
        this.branchName = branchName;
        this.status=status;

    }

    // Getters and Setters

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getCifId() {
        return cifId;
    }

    public void setCifId(String cifId) {
        this.cifId = cifId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getWorkingBalance() {
        return workingBalance;
    }

    public void setWorkingBalance(int workingBalance) {
        this.workingBalance = workingBalance;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "AccountsDto{" +
                "accNo='" + accNo + '\'' +
                ", accName='" + accName + '\'' +
                ", cifId='" + cifId + '\'' +
                ", balance=" + balance +
                ", workingBalance=" + workingBalance +
                ", branchName='" + branchName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
