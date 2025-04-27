package Controller.Service;

import Controller.dto.AccountsDto;
import Controller.dto.SessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Array;
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


    @Override
    public List<AccountsDto> getAccountBalance(AccountsDto accountDto) {
        List<AccountsDto> accList = accLists(accountDto);
        List<AccountsDto> responseList = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<List<AccountsDto>>> futures = new ArrayList<>();

        // Capture the parent thread's context
        RequestAttributes context = RequestContextHolder.getRequestAttributes();

        try {
            for (AccountsDto accDto : accList) {
                futures.add(executor.submit(() -> {
                    try {
                        RequestContextHolder.setRequestAttributes(context); // Set context to child thread

                        // ✅ Access session inside thread
                        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                        HttpSession session = request.getSession(false);

                        if (session != null) {
                            SessionDto sessionDto = (SessionDto) session.getAttribute("sessionInfo");
                            if (sessionDto != null) {
                                System.out.println("Inside thread, IP from session: " + sessionDto.getIpAddress());
                            } else {
                                System.out.println("SessionDto not found in session.");
                            }
                        } else {
                            System.out.println("Session not available.");
                        }

                        return apiservice.getCifArrangementDetails(accDto);

                    } finally {
                        RequestContextHolder.resetRequestAttributes(); // Clean threadlocal memory
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
                new AccountsDto("1234567890", "John Doe", "CIF1001", 0, 0, "Main Branch",""),
                new AccountsDto("9876543210", "Jane Smith", "CIF1002", 0, 0, "City Branch","")
        );

        return acclistss;
    }


}
