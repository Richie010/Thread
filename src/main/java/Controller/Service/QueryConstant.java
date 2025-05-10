package Controller.Service;

public class QueryConstant {

    public static final String GET_CHEQUE_DETAILS="SELECT accno ,bankname ,branchname ,bnfcryname ,chqno ,chqammount ,currency ,chqdate ,settlementDate ,bnfcryAccno ,draweeAccno ,status FROM vtcmchequeaccdtls WITH (NOLOCK) WHERE ACCNO=? ORDER BY CHQNO OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    public static final String GET_CHEQUE_DETAILS_WITHOUTOFF="SELECT accno ,bankname ,branchname ,bnfcryname ,chqno ,chqammount ,currency ,chqdate ,settlementDate ,bnfcryAccno ,draweeAccno ,status FROM vtcmchequeaccdtls WITH (NOLOCK) WHERE ACCNO=?";

}
