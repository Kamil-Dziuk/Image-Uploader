package pl.dziuk.springboogellerypictures.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dziuk.springboogellerypictures.model.AppUser;
import pl.dziuk.springboogellerypictures.model.Image;

public interface ImageRepo extends JpaRepository<Image,Long> {
    AppUser faindByUsername(String username);
}

