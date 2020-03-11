package ec.com.nashira.callcenter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class AppProperties {

  @Value("${logfile.path}")
  private String logFilePath;

  @Value("${angular.client.id}")
  private String angularClientId;

  @Value("${angular.client.p}")
  private String angularClientP;

  @Value("${access.token.validity.seconds}")
  private int accessTokenValiditySeconds;

  @Value("${refresh.token.validity.seconds}")
  private int refreshTokenValiditySeconds;

  @Value("${allowed.app.domains}")
  private String allowedAppDomains;

  @Value("${images.path}")
  private String imagesPath;

  public String getLogFilePath() {
    return logFilePath;
  }

  public String getAngularClientId() {
    return angularClientId;
  }

  public String getAngularClientP() {
    return angularClientP;
  }

  public int getAccessTokenValiditySeconds() {
    return accessTokenValiditySeconds;
  }

  public int getRefreshTokenValiditySeconds() {
    return refreshTokenValiditySeconds;
  }

  public String getAllowedAppDomains() {
    return allowedAppDomains;
  }

  public String getImagesPath() {
    return imagesPath;
  }

}
