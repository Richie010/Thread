package Controller.cnrtl;

import Controller.dto.Country;
import Controller.dto.HolidayMaster;
import Controller.dto.Paymethod;
import Controller.repo.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HolidayCntrl {

    @Autowired
    private HolidayRepository holidayrepository;

    //	@GetMapping("/hello")
//	public String hello() {
//		return "First";
    // }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }
    @RequestMapping("/")
    public ModelAndView getPage(){
        ModelAndView m = new ModelAndView();
        m.setViewName("Dashboard");
        return m;
    }



        @GetMapping("/addHoliday")
    public String showAddHolidayForm(Model model) {
        model.addAttribute("holiday", new HolidayMaster());
        model.addAttribute("countries", holidayrepository.getAllCountries());
        return "add-holiday";
    }
    @PostMapping("/addHoliday")
    public String addHoliday(@ModelAttribute("holiday") HolidayMaster holiday) {
        holidayrepository.addHoliday(holiday);
        return "redirect:/viewHolidays";
    }
    @GetMapping("/country/code/{id}")
    @ResponseBody
    public String getCountryCode(@PathVariable("id") Long id) {
        String code= holidayrepository.getCountryCodeById(id);
        return code;
    }

    @GetMapping("/paymethods/{countryId}")
    @ResponseBody
    public List<Paymethod> getPayMethods(@PathVariable("countryId") Long countryId) {
        return holidayrepository.getPayMethodsByCountry(countryId);
    }

    @GetMapping("/viewHolidays")
    public String viewAllHolidays(Model model) {
        List<HolidayMaster> holidays = holidayrepository.getAllHolidays();
        model.addAttribute("holidays", holidays);
        return "view-all";
    }

    @GetMapping("/del")
    public String deleteHoliday(@RequestParam("id") int id) {
        holidayrepository.deleteHolidayBy(id);
        return "redirect:/viewHolidays";
    }
}

