package ec.com.nashira.callcenter.auth.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import ec.com.nashira.callcenter.AppProperties;
import ec.com.nashira.callcenter.auth.AdditionalUserInfo;
import ec.com.nashira.callcenter.auth.constants.JwtConstants;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AppProperties properties;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  @Qualifier("authenticationManager")
  private AuthenticationManager authenticationManager;

  @Autowired
  private AdditionalUserInfo additionalUserInfo;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient(properties.getAngularClientId())
        .secret(passwordEncoder.encode(properties.getAngularClientP())).scopes("read", "write")
        .authorizedGrantTypes(JwtConstants.GRANT_TYPE_SECRET, "refresh_token")
        .accessTokenValiditySeconds(properties.getAccessTokenValiditySeconds())
        .refreshTokenValiditySeconds(properties.getRefreshTokenValiditySeconds());
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(additionalUserInfo, accessTokenConverter()));
    endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
        .accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
  }

  @Bean
  public JwtTokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    jwtAccessTokenConverter.setSigningKey(JwtConstants.RSA_PRIVATE_KEY);
    jwtAccessTokenConverter.setVerifierKey(JwtConstants.RSA_PUBLIC_KEY);
    return jwtAccessTokenConverter;
  }

}
