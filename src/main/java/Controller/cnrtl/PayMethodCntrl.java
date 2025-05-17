package Controller.cnrtl;

import Controller.repo.PaymethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PayMethodCntrl {
    @Autowired
    PaymethodRepository paymethodrepository;

        @RequestMapping(value = "/paymethod", method = RequestMethod.GET)
    public List<Object> getPaymethods(@RequestParam("countryId") int countryId, Model model) {
        List<Object> paymethod= paymethodrepository.getPaymethodsByCountryId(countryId);
        model.addAttribute("paymethod",paymethod);
        return paymethod;
    }
}
