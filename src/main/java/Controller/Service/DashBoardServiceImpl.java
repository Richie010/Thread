package Controller.Service;

import Controller.dto.AccountDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DashBoardServiceImpl implements Dashboardservice {

    @Autowired
    private MessageSource messagesource;

    @Autowired
    private JdbcTemplate jdbctemplate;


    private QueryConstant queryConstant;

    @Override
    public List<AccountDto> getChequeDetailsOnAccountnumber(String accNo, int offset) {

        List<AccountDto> accList = new ArrayList<>();
        int pagesize=30000;
        try{
            accList=jdbctemplate.query(QueryConstant.GET_CHEQUE_DETAILS,new Object[]{accNo,offset,pagesize},new
                    BeanPropertyRowMapper<AccountDto>(AccountDto.class));

        }catch (Exception e){
            e.printStackTrace();
        }

        return accList;
    }
    public List<AccountDto> getChequeDetailsOnAccount(String accNo) {

        List<AccountDto> accList = new ArrayList<>();
        int pagesize=30000;
        try{
            accList=jdbctemplate.query(QueryConstant.GET_CHEQUE_DETAILS_WITHOUTOFF,new Object[]{accNo},new
                    BeanPropertyRowMapper<AccountDto>(AccountDto.class));

        }catch (Exception e){
            e.printStackTrace();
        }

        return accList;
    }

    @Override
    public XSSFWorkbook generateChequeReportExcelChunks(List<AccountDto> accountDto, Locale locale) {

            XSSFWorkbook wb  = new XSSFWorkbook();
            int counter=1;
        XSSFSheet sh = (XSSFSheet) wb.createSheet("cheque");
        try{
            CellStyle style = wb.createCellStyle();
            CellStyle headerStyle = wb.createCellStyle();
            CellStyle footerStyle = wb.createCellStyle();



            Font font = wb.createFont();
            XSSFRow accHeader= sh.createRow(0);

            accHeader.createCell(0).setCellValue("AccNo");
            accHeader.createCell(1).setCellValue("BankName");
            accHeader.createCell(2).setCellValue("BnFcryBankbranch");
            accHeader.createCell(3).setCellValue("bnfcryName");
            accHeader.createCell(4).setCellValue("chequeId");
            accHeader.createCell(5).setCellValue("ChequeAmmount");
            accHeader.createCell(6).setCellValue("Currency");
            accHeader.createCell(7).setCellValue("chequeDate");
            accHeader.createCell(8).setCellValue("AccNoSettlement Date");
            accHeader.createCell(9).setCellValue("benAcc Number");
            accHeader.createCell(10).setCellValue("cheque draweaccno");
            accHeader.createCell(11).setCellValue("status");


            short height=200;
            style=wb.createCellStyle();
            headerStyle=wb.createCellStyle();
            footerStyle=wb.createCellStyle();

            font=wb.createFont();
            font.setBold(true);
            font.setFontName("Arial");
            font.setFontHeight(height);
            font.setColor(IndexedColors.BLACK.getIndex());
            style.setFont(font);
            headerStyle.setFont(font);
            footerStyle.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setAlignment(HorizontalAlignment.LEFT);
            style.setAlignment(HorizontalAlignment.LEFT);
            for(int i=0;i<accHeader.getLastCellNum();i++){
                accHeader.getCell(i).setCellStyle(headerStyle);
            }
            sh.setColumnWidth(0,5000);
            sh.setColumnWidth(1,7000);
            sh.setColumnWidth(2,6000);
            sh.setColumnWidth(3,7000);
            sh.setColumnWidth(4,5000);
            sh.setColumnWidth(5,5000);
            sh.setColumnWidth(6,5000);
            sh.setColumnWidth(7,5000);
            sh.setColumnWidth(8,5000);
            sh.setColumnWidth(9,5500);
            sh.setColumnWidth(10,6000);
            sh.setColumnWidth(11,5000);

            CellStyle rightAligned = wb.createCellStyle();
            rightAligned.setAlignment(HorizontalAlignment.RIGHT);
            CellStyle border = wb.createCellStyle();


            font = wb.createFont();
            font.setFontName("Arial");
            font.setFontHeight(height);
            border.setFont(font);
            border.setWrapText(true);
            border.setVerticalAlignment(VerticalAlignment.CENTER);
            Row row = sh.createRow(1);

            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyymmdd");

            if(accountDto !=null && accountDto.size()>0){
                for(int i=0;i<accountDto.size();i++){
                    row= sh.createRow(counter++);
                    setCellBorder(row.createCell(0),border).setCellValue(accountDto.get(i).getAccno());
                    setCellBorder(row.createCell(1),border).setCellValue(accountDto.get(i).getBankname());
                    setCellBorder(row.createCell(2),border).setCellValue(accountDto.get(i).getBranchname());
                    setCellBorder(row.createCell(3),border).setCellValue(accountDto.get(i).getBnfcryname());
                    setCellBorder(row.createCell(4),border).setCellValue(accountDto.get(i).getChqno());
                    setCellBorder(row.createCell(5),border).setCellValue(accountDto.get(i).getChqno());
                    setCellBorder(row.createCell(6),border).setCellValue(accountDto.get(i).getCurrency());
//                    Date chequeDate=null;
//                    if(accountDto.get(i).getChqdate()!=null){
//                        chequeDate=dateFormat.parse(accountDto.get(i).getChqdate());
//                    }

                    setCellBorder(row.createCell(7),border).setCellValue(accountDto.get(i).getChqdate());
                    setCellBorder(row.createCell(8),border).setCellValue(accountDto.get(i).getSettlementDate());
                    setCellBorder(row.createCell(9),border).setCellValue(accountDto.get(i).getBnfcryAccno());
                    setCellBorder(row.createCell(10),border).setCellValue(accountDto.get(i).getDraweeAccno());
                    setCellBorder(row.createCell(11),border).setCellValue(accountDto.get(i).getStatus());

                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return wb;
    }

    Cell setCellBorder(Cell cell,CellStyle style){
        cell.setCellStyle(style);
        return cell;
    }










//    import org.apache.poi.xssf.streaming.SXSSFWorkbook;

    public SXSSFWorkbook generateChequeReportExcelChunksStreaming(List<AccountDto> accountDto, Locale locale) {
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory
        Sheet sh = wb.createSheet("cheque");
        int counter=1;
        try{
            CellStyle style = wb.createCellStyle();
            CellStyle headerStyle = wb.createCellStyle();
            CellStyle footerStyle = wb.createCellStyle();



            Font font = wb.createFont();
            Row accHeader = sh.createRow(0);


            accHeader.createCell(0).setCellValue("AccNo");
            accHeader.createCell(1).setCellValue("BankName");
            accHeader.createCell(2).setCellValue("BnFcryBankbranch");
            accHeader.createCell(3).setCellValue("bnfcryName");
            accHeader.createCell(4).setCellValue("chequeId");
            accHeader.createCell(5).setCellValue("ChequeAmmount");
            accHeader.createCell(6).setCellValue("Currency");
            accHeader.createCell(7).setCellValue("chequeDate");
            accHeader.createCell(8).setCellValue("AccNoSettlement Date");
            accHeader.createCell(9).setCellValue("benAcc Number");
            accHeader.createCell(10).setCellValue("cheque draweaccno");
            accHeader.createCell(11).setCellValue("status");


            short height=200;
            style=wb.createCellStyle();
            headerStyle=wb.createCellStyle();
            footerStyle=wb.createCellStyle();

            font=wb.createFont();
            font.setBold(true);
            font.setFontName("Arial");
            font.setFontHeight(height);
            font.setColor(IndexedColors.BLACK.getIndex());
            style.setFont(font);
            headerStyle.setFont(font);
            footerStyle.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setAlignment(HorizontalAlignment.LEFT);
            style.setAlignment(HorizontalAlignment.LEFT);
            for(int i=0;i<accHeader.getLastCellNum();i++){
                accHeader.getCell(i).setCellStyle(headerStyle);
            }
            sh.setColumnWidth(0,5000);
            sh.setColumnWidth(1,7000);
            sh.setColumnWidth(2,6000);
            sh.setColumnWidth(3,7000);
            sh.setColumnWidth(4,5000);
            sh.setColumnWidth(5,5000);
            sh.setColumnWidth(6,5000);
            sh.setColumnWidth(7,5000);
            sh.setColumnWidth(8,5000);
            sh.setColumnWidth(9,5500);
            sh.setColumnWidth(10,6000);
            sh.setColumnWidth(11,5000);

            CellStyle rightAligned = wb.createCellStyle();
            rightAligned.setAlignment(HorizontalAlignment.RIGHT);
            CellStyle border = wb.createCellStyle();


            font = wb.createFont();
            font.setFontName("Arial");
            font.setFontHeight(height);
            border.setFont(font);
            border.setWrapText(true);
            border.setVerticalAlignment(VerticalAlignment.CENTER);
            Row row = sh.createRow(1);

            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyymmdd");

            if(accountDto !=null && accountDto.size()>0){
                for(int i=0;i<accountDto.size();i++){
                    row= sh.createRow(counter++);
                    setCellBorder(row.createCell(0),border).setCellValue(accountDto.get(i).getAccno());
                    setCellBorder(row.createCell(1),border).setCellValue(accountDto.get(i).getBankname());
                    setCellBorder(row.createCell(2),border).setCellValue(accountDto.get(i).getBranchname());
                    setCellBorder(row.createCell(3),border).setCellValue(accountDto.get(i).getBnfcryname());
                    setCellBorder(row.createCell(4),border).setCellValue(accountDto.get(i).getChqno());
                    setCellBorder(row.createCell(5),border).setCellValue(accountDto.get(i).getChqno());
                    setCellBorder(row.createCell(6),border).setCellValue(accountDto.get(i).getCurrency());
//                    Date chequeDate=null;
//                    if(accountDto.get(i).getChqdate()!=null){
//                        chequeDate=dateFormat.parse(accountDto.get(i).getChqdate());
//                    }

                    setCellBorder(row.createCell(7),border).setCellValue(accountDto.get(i).getChqdate());
                    setCellBorder(row.createCell(8),border).setCellValue(accountDto.get(i).getSettlementDate());
                    setCellBorder(row.createCell(9),border).setCellValue(accountDto.get(i).getBnfcryAccno());
                    setCellBorder(row.createCell(10),border).setCellValue(accountDto.get(i).getDraweeAccno());
                    setCellBorder(row.createCell(11),border).setCellValue(accountDto.get(i).getStatus());

                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return wb;
    }









}
