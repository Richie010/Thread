package Controller.Service;

import Controller.dto.AccountsDto;
import Controller.dto.SessionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



@Service
public class AccountservicesvcImpl implements AccountserviceSvc {

    @Autowired
    private ApiServiceSvcImpl apiservice;

    @Autowired
    private SessionDto sessiondto;

    private static final Logger logger = LogManager.getLogger(AccountservicesvcImpl.class);


    @Override
    public List<AccountsDto> getAccountBalance(AccountsDto accountDto) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = now.format(formatter);

        List<AccountsDto> accList = accLists(accountDto);
        List<AccountsDto> responseList = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<List<AccountsDto>>> futures = new ArrayList<>();

        RequestAttributes context = RequestContextHolder.getRequestAttributes();
        String ipAddress = sessiondto.getIpAddress(); // Capture session info here

        try {
            for (AccountsDto accDto : accList) {
                futures.add(executor.submit(() -> {
                    try {
                        RequestContextHolder.setRequestAttributes(context);

                        long apiStartTime = System.currentTimeMillis();
                        System.out.println(timestamp + " acco :: " + accDto.getAccNo());
                        logger.debug("[{}] Started processing account: {}", timestamp, accDto.getAccNo());
                        return apiservice.getCifArrangementDetails(accDto);


                    } finally {
                        RequestContextHolder.resetRequestAttributes();
                    }
                }));
            }

            for (Future<List<AccountsDto>> future : futures) {
                List<AccountsDto> accounts = future.get();
                responseList.addAll(accounts);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            executor.shutdown();
        }

        return responseList;
    }


    public static List<AccountsDto> accLists(AccountsDto accdto) {

        List<AccountsDto> acclistss = Arrays.asList(
                new AccountsDto("ACC00000001", "Customer 1", "CIF0001", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000002", "Customer 2", "CIF0002", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000003", "Customer 3", "CIF0003", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000004", "Customer 4", "CIF0004", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000005", "Customer 5", "CIF0005", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000006", "Customer 6", "CIF0006", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000007", "Customer 7", "CIF0007", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000008", "Customer 8", "CIF0008", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000009", "Customer 9", "CIF0009", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000010", "Customer 10", "CIF0010", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000011", "Customer 11", "CIF0011", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000012", "Customer 12", "CIF0012", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000013", "Customer 13", "CIF0013", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000014", "Customer 14", "CIF0014", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000015", "Customer 15", "CIF0015", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000016", "Customer 16", "CIF0016", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000017", "Customer 17", "CIF0017", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000018", "Customer 18", "CIF0018", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000019", "Customer 19", "CIF0019", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000020", "Customer 20", "CIF0020", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000021", "Customer 21", "CIF0021", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000022", "Customer 22", "CIF0022", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000023", "Customer 23", "CIF0023", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000024", "Customer 24", "CIF0024", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000025", "Customer 25", "CIF0025", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000026", "Customer 26", "CIF0026", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000027", "Customer 27", "CIF0027", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000028", "Customer 28", "CIF0028", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000029", "Customer 29", "CIF0029", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000030", "Customer 30", "CIF0030", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000031", "Customer 31", "CIF0031", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000032", "Customer 32", "CIF0032", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000033", "Customer 33", "CIF0033", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000034", "Customer 34", "CIF0034", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000035", "Customer 35", "CIF0035", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000036", "Customer 36", "CIF0036", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000037", "Customer 37", "CIF0037", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000038", "Customer 38", "CIF0038", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000039", "Customer 39", "CIF0039", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000040", "Customer 40", "CIF0040", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000041", "Customer 41", "CIF0041", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000042", "Customer 42", "CIF0042", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000043", "Customer 43", "CIF0043", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000044", "Customer 44", "CIF0044", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000045", "Customer 45", "CIF0045", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000046", "Customer 46", "CIF0046", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000047", "Customer 47", "CIF0047", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000048", "Customer 48", "CIF0048", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000049", "Customer 49", "CIF0049", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000050", "Customer 50", "CIF0050", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000051", "Customer 51", "CIF0051", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000052", "Customer 52", "CIF0052", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000053", "Customer 53", "CIF0053", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000054", "Customer 54", "CIF0054", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000055", "Customer 55", "CIF0055", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000056", "Customer 56", "CIF0056", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000057", "Customer 57", "CIF0057", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000058", "Customer 58", "CIF0058", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000059", "Customer 59", "CIF0059", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000060", "Customer 60", "CIF0060", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000061", "Customer 61", "CIF0061", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000062", "Customer 62", "CIF0062", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000063", "Customer 63", "CIF0063", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000064", "Customer 64", "CIF0064", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000065", "Customer 65", "CIF0065", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000066", "Customer 66", "CIF0066", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000067", "Customer 67", "CIF0067", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000068", "Customer 68", "CIF0068", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000069", "Customer 69", "CIF0069", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000070", "Customer 70", "CIF0070", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000071", "Customer 71", "CIF0071", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000072", "Customer 72", "CIF0072", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000073", "Customer 73", "CIF0073", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000074", "Customer 74", "CIF0074", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000075", "Customer 75", "CIF0075", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000076", "Customer 76", "CIF0076", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000077", "Customer 77", "CIF0077", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000078", "Customer 78", "CIF0078", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000079", "Customer 79", "CIF0079", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000080", "Customer 80", "CIF0080", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000081", "Customer 81", "CIF0081", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000082", "Customer 82", "CIF0082", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000083", "Customer 83", "CIF0083", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000084", "Customer 84", "CIF0084", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000085", "Customer 85", "CIF0085", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000086", "Customer 86", "CIF0086", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000087", "Customer 87", "CIF0087", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000088", "Customer 88", "CIF0088", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000089", "Customer 89", "CIF0089", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000090", "Customer 90", "CIF0090", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000091", "Customer 91", "CIF0091", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000092", "Customer 92", "CIF0092", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000093", "Customer 93", "CIF0093", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000094", "Customer 94", "CIF0094", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000095", "Customer 95", "CIF0095", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000096", "Customer 96", "CIF0096", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000097", "Customer 97", "CIF0097", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000098", "Customer 98", "CIF0098", 0, 0, "Main Branch", ""),
                new AccountsDto("ACC00000099", "Customer 99", "CIF0099", 0, 0, "City Branch", ""),
                new AccountsDto("ACC00000100", "Customer 100", "CIF0100", 0, 0, "Main Branch", "")
        );


        return acclistss;
    }


}
