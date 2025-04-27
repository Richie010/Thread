package Controller.cnrtl;

import Controller.Service.AccountservicesvcImpl;
import Controller.dto.AccountsDto;
import Controller.dto.SessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DashboardCntrl {

    @Autowired
    private AccountservicesvcImpl accountserviceSvc;

    @Autowired
    private SessionDto sessionDto;


    @RequestMapping("/")
    public ModelAndView getPage(){
        ModelAndView m = new ModelAndView();
        m.setViewName("welcome");
        return m;
    }

    @RequestMapping(value = "/getSessionInfo", method = RequestMethod.GET)
    public ModelAndView getSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            // If not available, fall back to the remote address
            ipAddress = request.getRemoteAddr();
        }
        if (session.getAttribute("startTime") == null) {
            session.setAttribute("startTime", LocalDateTime.now());
        }

        LocalDateTime startTime = (LocalDateTime) session.getAttribute("startTime");
        SessionDto sessionDto = new SessionDto(ipAddress, startTime);
        ModelAndView mav = new ModelAndView("sessionInfo"); // JSP name without .jsp
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
        ModelAndView mav = new ModelAndView("accountDetails"); // Maps to accountDetails.jsp
        mav.addObject("accounts", accountList);

        return mav;
    }




}
