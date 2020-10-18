package pl.dziuk.springboogellerypictures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.dziuk.springboogellerypictures.repo.AppUserRepo;
@Service

public class UsersDetailsServiceImple implements UserDetailsService {

 private AppUserRepo appUserRepo;
@Autowired
    public UsersDetailsServiceImple(AppUserRepo appUserRepo) {
this.appUserRepo = appUserRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(s);
    }

    }

