package com.conf;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
@Configuration
public class DroolsConfig {

    private static KieSession kieSession;
    private static final String RULES_PATH = "rules/";

    @Bean
    public static KieSession getKieSession() throws IOException {
            KieServices kieServices = KieServices.Factory.get();
            KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
            for (org.springframework.core.io.Resource file : new PathMatchingResourcePatternResolver().getResources("classpath*:" + RULES_PATH + "**/*.*")) {
                kieFileSystem.write(org.kie.internal.io.ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
            }
            final KieRepository kieRepository = KieServices.Factory.get().getRepository();
            kieRepository.addKieModule(() -> {
                return kieRepository.getDefaultReleaseId();
            });
            KieBuilder kieBuilder = KieServices.Factory.get().newKieBuilder(kieFileSystem);
            kieBuilder.buildAll();
            kieSession = KieServices.Factory.get().newKieContainer(kieRepository.getDefaultReleaseId()).newKieSession().getKieBase().newKieSession();
            return kieSession;
    }
}