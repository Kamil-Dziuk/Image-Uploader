package pl.dziuk.springboogellerypictures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.dziuk.springboogellerypictures.model.AppUser;
import pl.dziuk.springboogellerypictures.repo.AppUserRepo;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UsersDetailsServiceImple usersDetailsService;
    private AppUserRepo appUserRepo;
    @Autowired
    public WebSecurityConfig(UsersDetailsServiceImple usersDetailsService,AppUserRepo appUserRepo) {
        this.usersDetailsService = usersDetailsService;
        this.appUserRepo = appUserRepo;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(usersDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test1").hasRole("USER")
                .antMatchers("/test2").hasRole("ADMIN")
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasRole("USER")

                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();



    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {

        AppUser appUserUser = new AppUser("UserJan", passwordEncoder().encode("UserJan"), "ROLE_USER");
        AppUser appUserAdmin = new AppUser("AdminJan", passwordEncoder().encode("AdminJan"), "ROLE_ADMIN");

        appUserRepo.save(appUserUser);
        appUserRepo.save(appUserAdmin);

    }
}

