package lazy_track.controller;

import lazy_track.model.Company;
import lazy_track.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompanyController {
    private CompanyService companyService;

    @Autowired
    @Qualifier(value = "companyService")
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }


    @RequestMapping(value = "companies", method = RequestMethod.GET)
    public String listCompanies(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("listCompanies", companyService.list());
        return "companies";
    }

    @RequestMapping(value = "/company/add", method = RequestMethod.POST)
    public String addCompany(@ModelAttribute("company") Company company) {
        if (company.getId() == 0) {
            companyService.add(company);
        } else {
            companyService.update(company);
        }
        return "redirect:/company";
    }

    @RequestMapping("/remove/{id}")
    public String removeCompany(@PathVariable("id") int id) {
        companyService.remove(id);
        return "redirect:/companies";
    }

    @RequestMapping("edit/{id}")
    public String editCompany(@PathVariable("id") int id, Model model) {
        model.addAttribute("company", companyService.get(id));
        model.addAttribute("listCompanies", companyService.list());
        return "companies";
    }

    @RequestMapping("company/{id}")
    public String companyData(@PathVariable("id") int id, Model model) {
        model.addAttribute("company", companyService.get(id));
        return "company";
    }
}
