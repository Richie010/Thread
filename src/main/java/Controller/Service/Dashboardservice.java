package Controller.Service;

import Controller.dto.AccountDto;
import Controller.dto.AccountsDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Locale;

public interface Dashboardservice {

    public List<AccountDto> getChequeDetailsOnAccountnumber(String accNo,int offset);

    public XSSFWorkbook generateChequeReportExcelChunks(List<AccountDto> accountDto, Locale locale);
}
