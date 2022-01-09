package org.undp.incompetent.controllers.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.undp.incompetent.models.IncompetentEntity;
import org.undp.incompetent.models.UserFullNameRequestEntity;
import org.undp.incompetent.service.repositories.IncompetentRepository;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IncompetentRestController {

    @Autowired
    private IncompetentRepository incompetentRepository;
    @PostMapping(value = "/test/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") @Pattern(regexp = "[A-Z]{2}[0-9]{7}") String passportId){


        if(passportId.length() != 9 ||!passportId.matches("[A-Z]{2}[0-9]{7}")){
          return new ResponseEntity(null  , HttpStatus.NO_CONTENT);
        }

        List<IncompetentEntity> incompetentEntityList=incompetentRepository.findByIncompetentPassport(passportId);

        if(incompetentEntityList.isEmpty()){
            return new ResponseEntity(null  , HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(incompetentEntityList,HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/name",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> getByName(@RequestBody UserFullNameRequestEntity requestEntity){

        List<IncompetentEntity> incompetentEntityList=incompetentRepository.findByIncompetentFirstnameAndIncompetentSurnameAndIncompetentPatronymic(requestEntity.getIncompetentFirstname(),requestEntity.getIncompetentSurname(),requestEntity.getIncompetentPatronymic());
        if(incompetentEntityList.isEmpty()){
            return new ResponseEntity(null  , HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(incompetentEntityList,HttpStatus.OK);
    }
}