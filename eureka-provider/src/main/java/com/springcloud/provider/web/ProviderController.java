package com.springcloud.provider.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xufeng on 2017/11/24
 */
@RestController
public class ProviderController {

    @Autowired
    private Registration registration;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("provider")
    public String provider() {
        ServiceInstance instance = getServiceInstance();

        if (instance == null) {
            return "no instance provider";
        }
        return "hello,Provider" + instance.getHost();
    }

    public ServiceInstance getServiceInstance() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }
}
