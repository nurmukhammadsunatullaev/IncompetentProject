package org.undp.incompetent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.undp.incompetent.excels.ExcelView;
import org.undp.incompetent.models.CaseTypeEntity;
import org.undp.incompetent.models.CountryEntity;
import org.undp.incompetent.models.IncompetentEntity;
import org.undp.incompetent.models.UserEntity;
import org.undp.incompetent.service.repositories.CaseTypeRepository;
import org.undp.incompetent.service.repositories.CountryRepository;
import org.undp.incompetent.service.repositories.IncompetentRepository;
import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Controller
@RequestMapping("/incompetent/")
@SessionAttributes({"region_list", "caseTypes"})
public class IncompetentController {

    private final IncompetentRepository incompetentRepository;

    private final CountryRepository countryRepository;

    private final CaseTypeRepository caseTypeRepository;

    @Autowired
    public IncompetentController(IncompetentRepository incompetentRepository, CountryRepository countryRepository, CaseTypeRepository caseTypeRepository) {
        this.incompetentRepository = incompetentRepository;
        this.countryRepository = countryRepository;
        this.caseTypeRepository = caseTypeRepository;
    }

    @ModelAttribute("region_list")
    public Iterable<CountryEntity> setUpRegion(){
        return countryRepository.findAll();
    }

    @ModelAttribute("caseTypes")
    public Iterable<CaseTypeEntity> setUpCaseTypes(){
        return caseTypeRepository.findAll();
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    private String addIncompetentInformationGet(Model model){
        model.addAttribute("incompetent",new IncompetentEntity());
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    private String addIncompetentInformationPost(@ModelAttribute("incompetent")
                                                 @Valid IncompetentEntity incompetent,
                                                 BindingResult errors, Model model){

        if(errors.hasErrors()){
            return "add";
        }

        Long time_period=Math.abs(ChronoUnit.YEARS.between(LocalDate.now(),incompetent.getIncompetentBirthday().toLocalDate()));
        Boolean passportType=time_period>=16;
        if(passportType){
            String passportInformation=incompetent.getIncompetentPassport();
            String rule="[A-Z]{2}[0-9]{7}";
            if(passportInformation.length() != 9){
                errors.rejectValue("incompetentPassport","error.incompetentPassport","Паспорт маълумотини тўғри киритинг! (Паспорт маълумотининг киритилиш шакли: XXYYYYYYY)");
                return "add";
            }

            if(!passportInformation.matches(rule)){
                errors.rejectValue("incompetentPassport","error.incompetentPassport","Паспорт маълумотини тўғри киритинг! (Паспорт маълумотининг киритилиш шакли: XXYYYYYYY)");
                return "add";
            }
        }

        incompetent.setIncompetentPassportType(passportType);
        if(incompetent.getIncompetentId() == null){
            incompetent.setCourtId(getActiveUser().getCourtEntity().getId());
        }

        if(getActiveUser().getCourtEntity().getId().equals(incompetent.getCourtId())){
            incompetentRepository.save(incompetent);
        }

        return "redirect:/incompetent/list";
    }

    @RequestMapping("/edit/{incompetentId}")
    public String editIncompetentInformationGet(@PathVariable("incompetentId") Long incompetentId, Model model){
        IncompetentEntity incompetent=incompetentRepository.findByIncompetentId(incompetentId);
        model.addAttribute("incompetent",incompetent);
        return "add";
    }

    @RequestMapping(value="/delete/{incompetentId}",method = RequestMethod.GET)
    public String deleteIncompetent(@PathVariable("incompetentId") Long incompetentId){
        IncompetentEntity incompetentEntity=incompetentRepository.findByIncompetentId(incompetentId);
        incompetentRepository.delete(incompetentEntity);
        return "redirect:/incompetent/list";
    }

//    @RequestMapping("/report/excel")
//    public ModelAndView exportToExcel(){
//        return new ModelAndView(new ExcelView(),"incompetent_list",incompetentRepository.findAllByCourtIdAndDateOfDataDeleteIsNull(getActiveUser().getCourtEntity().getId()));
//    }


    public UserEntity getActiveUser(){
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
