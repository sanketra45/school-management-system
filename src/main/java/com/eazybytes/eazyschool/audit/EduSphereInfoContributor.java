package com.eazybytes.eazyschool.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class EduSphereInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder)
    {
        Map<String, String> eduMap = new HashMap<String, String>();
        eduMap.put("App Name", "Edusphere");
        eduMap.put("App Description", "EduSphere School Web Application for Students and Admin");
        eduMap.put("App Version", "1.0.0");
        eduMap.put("Contact Email", "info@edusphere.com");
        eduMap.put("Contact Number", "+1(21) 673 4587");
        builder.withDetail("eazyschool-info", eduMap);
    }

}
