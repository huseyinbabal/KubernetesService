package com.huseyin.kubernetes.service;

import com.huseyin.kubernetes.dto.ScaleResponseDto;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final KubernetesClient kubernetesClient;

    public ApplicationService(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    public ScaleResponseDto scale(String name, Integer replicas) {
        kubernetesClient.extensions()
                .deployments()
                .inNamespace("default")
                .withName(name)
                .scale(replicas, true);
        return new ScaleResponseDto("Scale operation initiated");
    }
}
