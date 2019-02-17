package no.eidsa.presenter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "presenter")
public class PresenterProperties {

    private String temp = "temp";
    private Boolean cleanTemp = true;

}
