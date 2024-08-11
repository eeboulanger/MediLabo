package com.medilabo.riskevaluator.proxies;

import com.medilabo.riskevaluator.beans.MedicalRecordBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "medical-records")
public interface MicroserviceMedicalRecordProxy {

    @GetMapping("/medicalrecords/{id}")
    List<MedicalRecordBean> getMedicalRecords(@PathVariable int id);
}
