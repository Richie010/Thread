package Controller.cnrtl;

import Controller.dto.Country;
import Controller.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CountryCntrl {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/countrylist")
    public String showCountries(Model model) {
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries",countries);
        return "countrylists";
    }
}
