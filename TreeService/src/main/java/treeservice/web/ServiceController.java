package treeservice.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import treeservice.domain.CalcRequestResult;
import treeservice.domain.TreeNode;
import treeservice.repo.CalcRequestRepository;
import treeservice.service.CalcService;

@RestController
@RequestMapping("/tree")
public class ServiceController {

    @Autowired
    CalcRequestRepository calcRequestRepository;

    @Autowired
    CalcService calcService;

    @RequestMapping(value = "/calc", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> calc(@RequestBody TreeNode root) {
	Long sum = calcService.calcSum(root);
	CalcRequestResult result = new CalcRequestResult(root, LocalDateTime.now(), sum);
	calcRequestRepository.save(result);
	return new ResponseEntity<Long>(sum, HttpStatus.OK);
    }
}
