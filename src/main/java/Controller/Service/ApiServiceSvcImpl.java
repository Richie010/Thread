package Controller.Service;

import Controller.dto.AccountsDto;
import Controller.dto.SessionDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ApiServiceSvcImpl implements  ApiServicesvc{


    @Autowired
    private SessionDto sessiondto;

    @Override
    public List<AccountsDto> getCifArrangementDetails(AccountsDto ap) {
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
           //  account.setAccNo(dataObject.get("accountNumber").getAsString());
           account.setAccNo(ap.getAccNo());
           account.setBalance(dataObject.get("balance").getAsInt());
           account.setAccName("Mock Account");
           account.setCifId("CIF999");
           account.setWorkingBalance(account.getBalance());
           account.setBranchName("Test Branch");

        // ✅ Return a list with one account
        return Collections.singletonList(account);
    }

}
