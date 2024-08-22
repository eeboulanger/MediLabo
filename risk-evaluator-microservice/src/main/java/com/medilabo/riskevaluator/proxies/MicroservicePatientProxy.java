package com.medilabo.riskevaluator.proxies;

import com.medilabo.riskevaluator.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-microservice")
public interface MicroservicePatientProxy {

    @GetMapping("/patients/{id}")
    PatientBean getPatientById(@PathVariable("id") long id);
}
