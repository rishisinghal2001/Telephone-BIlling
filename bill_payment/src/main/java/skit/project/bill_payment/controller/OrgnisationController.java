package skit.project.bill_payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import skit.project.bill_payment.DTO.OrgnisationDTO;
import skit.project.bill_payment.common.DuplicateEntryException;
import skit.project.bill_payment.entity.OrgnisationEntity;
import skit.project.bill_payment.helper.JwtUtil;
import skit.project.bill_payment.model.JwtRequest;
import skit.project.bill_payment.serviceImpl.OrgnisationDetailService;

@RestController
@CrossOrigin(origins="*")
public class OrgnisationController {

    @Autowired
    private JwtUtil jwtUtil;

    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired 
    @Qualifier("orgnisationdetailservice")
    OrgnisationDetailService orgnisationDetailService;

    
    @PostMapping("/orgnisationlogin")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<?> genrateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getName(), jwtRequest.getPassword()));

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        } catch (Exception e) {
            throw new Exception("Invalid User");
        }

        UserDetails userDetails = this.orgnisationDetailService.loadUserByUsername(jwtRequest.getName());

        String token = this.jwtUtil.generateToken(userDetails);

        System.out.println("JWT Token " + token);


        OrgnisationDTO orgnisation = new OrgnisationDTO();
        orgnisation = orgnisationDetailService.getOrgnisationById( jwtRequest.getName());
        
        orgnisation.setOrgntoken(token);
        
        return new ResponseEntity<OrgnisationDTO>(orgnisation, HttpStatus.OK);
    }

    @GetMapping("/getorgnisation")
    public ResponseEntity<OrgnisationDTO> getorgnisation(@RequestParam("email") String email) {
       OrgnisationDTO orgnisation = orgnisationDetailService.getOrgnisationById(email);
       return new ResponseEntity<OrgnisationDTO>(orgnisation, HttpStatus.OK);
    }
    
    
    @GetMapping("/getorgnisations")
    public Page<OrgnisationDTO> findallorgnisations(@RequestParam("start")int start,@RequestParam("pageSize") int pageSize){
        return orgnisationDetailService.getAllOrgnisations(start,pageSize);
    }

    @PostMapping("/saveorgnisation")
    public ResponseEntity<OrgnisationEntity> saveOrgnisation(@RequestBody OrgnisationDTO orgnisation) throws DuplicateEntryException {
        OrgnisationEntity orgnisationEntity = new OrgnisationEntity();
        try {
                System.out.println(orgnisationDetailService.orgnisationValidation(orgnisation.getEmail()));
                orgnisationEntity = orgnisationDetailService.saveOrgisation(orgnisation);
        }
        catch (DuplicateEntryException e) {
            System.out.println(e);
            throw e;
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<OrgnisationEntity>(orgnisationEntity,HttpStatus.OK) ; 
        
        
    }
    
   
    @DeleteMapping("/deleteorgnisation")
    public String deleteCustomer(@RequestParam("email") String email) {
           orgnisationDetailService.deleteOrgnisation(email);
           return "Deleted Succesfully";
    }
    
    @PutMapping("/updateorgnisation")
    public ResponseEntity<OrgnisationEntity> updateOrgnisation(@RequestParam("email") String email,@RequestParam("orgnName") String orgnName,@RequestParam("orgnPass") String orgnPass){
        OrgnisationEntity orgnisationEntity = new OrgnisationEntity();
        try {
                orgnisationEntity = orgnisationDetailService.updateOrgnisation(email,orgnName,orgnPass);
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<OrgnisationEntity>(orgnisationEntity,HttpStatus.OK) ;    
    }
    
    @PutMapping("/changeorgnisationpassword")
    public ResponseEntity<OrgnisationEntity> updatePassword(@RequestParam("String") String email,@RequestParam("password") String password){
        OrgnisationEntity orgnisationEntity = new OrgnisationEntity();
        try {
                orgnisationEntity = orgnisationDetailService.changeorgnisationPassword(email,password);
        }
        catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
        }
        return new ResponseEntity<OrgnisationEntity>(orgnisationEntity,HttpStatus.OK) ;    
    }   
}
