package Controller.Service;

import Controller.dto.AccountsDto;
import Controller.dto.SessionDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
public class ApiServiceSvcImpl implements  ApiServicesvc{


    @Autowired
    private SessionDto sessiondto;

    private static final Logger logger = LogManager.getLogger(ApiServiceSvcImpl.class);


    @Override
    public List<AccountsDto> getCifArrangementDetails(AccountsDto ap) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = now.format(formatter);
        String jsonResponse = "{ \"status\": \"success\", \"message\": \"Account fetched successfully\", \"data\": { \"accountNumber\": \"123456\", \"balance\": 2000.0 } }";
       try {
           System.out.println("ip address:: " + sessiondto.getIpAddress());
       }catch (Exception e ){
           e.printStackTrace();
       }
           Gson gson = new Gson();
           JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
           JsonObject dataObject = jsonObject.getAsJsonObject("data");

           AccountsDto account = new AccountsDto();
           account.setAccNo(ap.getAccNo());
           account.setBalance(dataObject.get("balance").getAsInt());
           account.setAccName("Mock Account");
           account.setCifId("CIF999");
           account.setWorkingBalance(account.getBalance());
           account.setBranchName("Test Branch");
        long apiEndTime = System.currentTimeMillis();
        logger.debug("[{}] Started processing account: {}", timestamp, account.getAccNo());
        System.out.println(timestamp + " acco After Thread :: " + account.getAccNo());



        return Collections.singletonList(account);
    }

}
