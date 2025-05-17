package Controller.cnrtl;

import Controller.Service.AccountservicesvcImpl;
import Controller.Service.DashBoardServiceImpl;
import Controller.dto.AccountDto;
import Controller.dto.AccountsDto;
import Controller.dto.SessionDto;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class DashboardCntrl {

    @Autowired
    private AccountservicesvcImpl accountserviceSvc;

    @Autowired
    private SessionDto sessionDto;

    @Autowired
    private DashBoardServiceImpl dashBoardSvc;




    @RequestMapping(value = "/getSessionInfo", method = RequestMethod.GET)
    public ModelAndView getSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        if (session.getAttribute("startTime") == null) {
            session.setAttribute("startTime", LocalDateTime.now());
        }

        LocalDateTime startTime = (LocalDateTime) session.getAttribute("startTime");
        SessionDto sessionDto = new SessionDto(ipAddress, startTime);
        ModelAndView mav = new ModelAndView("sessionInfo");
        mav.addObject("ipAddress", ipAddress);
        mav.addObject("startTime", startTime);

        return mav;
    }

    @GetMapping("/accountDetails")
    public ModelAndView getAccountDetails(HttpServletRequest request) {
        HttpSession session = request.getSession();
        AccountsDto dummyDto = new AccountsDto();
        List<AccountsDto> accountList = accountserviceSvc.getAccountBalance(dummyDto);

        System.out.println("Size"+accountList.size());

        session.setAttribute("accountLoadedTime", LocalDateTime.now());
        ModelAndView mav = new ModelAndView("accountDetails");
        mav.addObject("accounts", accountList);

        return mav;
    }

//    @RequestMapping(value = "/downloadChequeReportXLS", method = RequestMethod.POST)
//    public void downloadChequeReportXLS(@RequestParam("accNO") String accNO, HttpServletResponse response, Locale locale) throws Exception {
//        AccountDto accountList = new AccountDto();
//        accountList.setAccno(accNO);
//       boolean checkChequeAccountEntitlement=true;
//        if (checkChequeAccountEntitlement=true) {
//
//                    int offset = 0;
//                    int pageSize=100000;
//                    int fileCount=1;
//                    String baseFile = "ChequeReport " + accNO+" - "+fileCount+".xlsx";
//         while(true){
//             List<AccountDto> accountDTOList = dashBoardSvc.getChequeDetailsOnAccountnumber(accNO,offset);
//             if(accountDTOList==null || accountDTOList.isEmpty()){
//                 break;
//             }
//             XSSFWorkbook wb = dashBoardSvc.generateChequeReportExcelChunks(accountDTOList, locale);
//             String fileName=baseFile+ "-Part"+ fileCount+ ".xlsx";
//
//
//             if(wb!=null){
//                 try{
////                     OutputStream out = response.getOutputStream();
////                     response.setHeader("Content-Disposition","inline; fileName=\""+fileName+"\"");
////                     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
////                     response.setHeader("Content-Transfer-Encoding","binary");
////
////                     wb.write(out);
////                     out.flush();
//
//                     String filePath = "C:\\Users\\RICHIE\\Documents\\excel\\" + fileName;
//                     try (FileOutputStream fos = new FileOutputStream(filePath)) {
//                         wb.write(fos);
//                         fos.flush();
//                         System.out.println("File saved: " + filePath);
//                     } catch (IOException e) {
//                         e.printStackTrace();
//                     }
//
//                 }catch(Exception e){
//                     e.printStackTrace();
//                 }
//             }
//
//             offset+=pageSize;
//             fileCount++;
//
//             if(accountDTOList.size() < pageSize){
//                 break;
//             }
//         }
//
//
//
//        }
//    }

    @RequestMapping(value = "/downloadChequeReportXLS", method = RequestMethod.POST)
    public void downloadChequeReportXLS(@RequestParam("accNO") String accNO, HttpServletResponse response, Locale locale) throws Exception {
        boolean checkChequeAccountEntitlement = true;

        if (checkChequeAccountEntitlement) {
            int offset = 0;
            int pageSize = 30000;
            int fileCount = 1;

            List<File> tempExcelFiles = new ArrayList<>();
            File tempDir = Files.createTempDirectory("chequeExport").toFile();

            while (true) {
               List<AccountDto> accountDTOList = dashBoardSvc.getChequeDetailsOnAccountnumber(accNO, offset);
//                 List<AccountDto> accountDTOList = dashBoardSvc.getChequeDetailsOnAccount(accNO);

                if (accountDTOList == null || accountDTOList.isEmpty()) {
                    break;
                }

                // Use SXSSFWorkbook for better performance
                SXSSFWorkbook wb = dashBoardSvc.generateChequeReportExcelChunksStreaming(accountDTOList, locale);
                String fileName = "ChequeReport-" + accNO + "-Part" + fileCount + ".xlsx";
                File tempFile = new File(tempDir, fileName);

                System.out.println("fileName "+ fileName +" count "+ fileCount);


                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    wb.write(fos);
                    wb.dispose();
                    tempExcelFiles.add(tempFile);
                }

                offset += pageSize;
                fileCount++;

                if (accountDTOList.size() < pageSize) {
                    break;
                }
            }

            // Now zip all the files and return one download
            String zipFileName = "ChequeReport_" + accNO + ".zip";
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + zipFileName + "\"");

            try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
                for (File excelFile : tempExcelFiles) {
                    try (FileInputStream fis = new FileInputStream(excelFile)) {
                        zos.putNextEntry(new ZipEntry(excelFile.getName()));
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }
                        zos.closeEntry();
                    }
                }
                zos.finish();
            }
            for (File file : tempExcelFiles) file.delete();
            tempDir.delete();

        }
    }

    @GetMapping("/chequeReport")
    public String showChequeReportPage() {
        return "downloadChequeReport"; // resolves to /WEB-INF/views/chequeReport.jsp
    }





}
