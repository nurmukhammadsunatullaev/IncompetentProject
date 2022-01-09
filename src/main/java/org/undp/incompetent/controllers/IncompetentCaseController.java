package org.undp.incompetent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.undp.incompetent.models.CaseTypeEntity;
import org.undp.incompetent.models.IncompetentCaseEntity;
import org.undp.incompetent.models.IncompetentEntity;
import org.undp.incompetent.models.UserEntity;
import org.undp.incompetent.service.repositories.CaseTypeRepository;
import org.undp.incompetent.service.repositories.IncompetentCaseRepository;
import org.undp.incompetent.service.repositories.IncompetentRepository;
import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/incompetent/case")
public class IncompetentCaseController {

    private final IncompetentRepository incompetentRepository;
    private final IncompetentCaseRepository incompetentCaseRepository;
    private final CaseTypeRepository caseTypeRepository;

    @Autowired
    public IncompetentCaseController(IncompetentRepository incompetentRepository, IncompetentCaseRepository incompetentCaseRepository, CaseTypeRepository caseTypeRepository) {
        this.incompetentRepository = incompetentRepository;
        this.incompetentCaseRepository = incompetentCaseRepository;
        this.caseTypeRepository = caseTypeRepository;
    }

    @RequestMapping(value = "add/{personId}.html", method = RequestMethod.GET)
    private String addIncompetentCase(@PathVariable("personId") Long personId,
                                      @RequestParam("caseTypeId") Integer caseTypeId,
                                      Model model){
            IncompetentEntity incompetentEntity =incompetentRepository.findByIncompetentId(personId);
            Optional<CaseTypeEntity> caseTypeEntity=caseTypeRepository.findById(caseTypeId);
            IncompetentCaseEntity caseModel=new IncompetentCaseEntity(incompetentEntity,caseTypeEntity.orElse(new CaseTypeEntity()));
            caseModel.setJudmentCourt(incompetentEntity.getCourtEntity());
            model.addAttribute("caseModel",caseModel);
            return "case";
    }




    @RequestMapping(value = "add", method = RequestMethod.POST)
    private String addIncompetentCasePost(@ModelAttribute("caseModel")
                                          @Valid IncompetentCaseEntity caseModel,
                                          BindingResult errors, Model model){

        IncompetentEntity incompetentEntity=incompetentRepository.findByIncompetentId(caseModel.getIncompetentId());
        if(incompetentEntity.getCourtId().equals(getActiveUser().getCourtEntity().getId())){
            Optional<CaseTypeEntity> caseTypeEntity=caseTypeRepository.findById(caseModel.getIncompetentCaseType());
            caseModel.setCaseTypeEntity(caseTypeEntity.orElse(new CaseTypeEntity()));
            caseModel.setIncompetent(incompetentEntity);
            if (errors.hasErrors()){
                return "case";
            }
            if(caseModel.getIncompetentCaseId() != null) {
                caseModel.setDateOfDataUpdate(Date.valueOf(LocalDate.now()));
            }
            incompetentCaseRepository.save(caseModel);
        }

        return "redirect:/incompetent/list";
    }

    @RequestMapping(value = "edit/{incompetentCaseId}", method = RequestMethod.GET)
    private String editIncompetentCasePost(@PathVariable("incompetentCaseId") Long incompetentCaseId, Model model){
        IncompetentCaseEntity caseEntity=incompetentCaseRepository.findByIncompetentCaseId(incompetentCaseId);
        model.addAttribute("caseModel",caseEntity);
        return "case";
    }

    @RequestMapping(value="delete/{caseId}",method = RequestMethod.GET)
    public String deleteCase(@PathVariable("caseId") Long caseId){
        IncompetentCaseEntity caseModel=incompetentCaseRepository.findByIncompetentCaseId(caseId);
        IncompetentEntity incompetentEntity =caseModel.getIncompetent();
        if(incompetentEntity.getCourtId().equals(getActiveUser().getCourtEntity().getId())){
            incompetentCaseRepository.delete(caseModel);
        }
        return "redirect:/incompetent/list";
    }


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
