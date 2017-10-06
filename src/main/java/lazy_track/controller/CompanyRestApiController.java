package lazy_track.controller;

import lazy_track.model.Company;
import lazy_track.service.CompanyService;
import lazy_track.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyRestApiController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyRestApiController.class);

    private CompanyService companyService; //Service which will do all data retrieval/manipulation work

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
// -------------------Retrieve All Companies ---------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> listAllCompanies() {
        List<Company> companies = companyService.findAll();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    // -------------------Retrieve Single Company ----------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCompany(@PathVariable("id") long id) {
        logger.info("Fetching Company with id {}", id);
        Company company = companyService.findById(id);
        if (company == null) {
            logger.error("Company with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Company with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    // -------------------Create a User --------------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createCompany(@RequestBody Company company, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Company : {}", company);

        if (companyService.isExist(company)) {
            logger.error("Unable to create. A Company with name {} already exist", company.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Company with name " +
                    company.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        companyService.save(company);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/company/{id}").buildAndExpand(company.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Company ----------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCompany(@PathVariable("id") long id, @RequestBody Company company) {
        logger.info("Updating Company with id {}", id);

        Company currentCompany = companyService.findById(id);

        if (currentCompany == null) {
            logger.error("Unable to update. Company with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Company with id "
                    + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentCompany.setName(company.getName());
        currentCompany.setDescription(company.getDescription());

        companyService.update(currentCompany);
        return new ResponseEntity<>(currentCompany, HttpStatus.OK);
    }

    // ------------------- Delete a User -------------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCompany(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Company with id {}", id);

        Company company = companyService.findById(id);
        if (company == null) {
            logger.error("Unable to delete. Company with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Company with id "
                    + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        companyService.deleteById(id);
        return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
    }
}