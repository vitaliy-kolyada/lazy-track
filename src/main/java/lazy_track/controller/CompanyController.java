package lazy_track.controller;

import lazy_track.model.Company;
import lazy_track.service.CompanyService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {
    private CompanyService companyService;

    @Autowired
    @Qualifier(value = "companyService")
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Company> addCompany(@RequestBody Company company) {
        if (company.getId() == 0) {
            companyService.addCompany(company);
        } else {
            companyService.updateCompany(company);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        try {
            companyService.updateCompany(company);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Company> removeCompany(@PathVariable("id") int id) {
        try {
            companyService.removeCompany(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "company/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Company> getCompanyById(@PathVariable("id") int id) {
        Company company;
        try {
            company = companyService.getCompanyById(id);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "company", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Company>> listCompanies() {
        List<Company> companyList = companyService.listCompanies();
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }
}
