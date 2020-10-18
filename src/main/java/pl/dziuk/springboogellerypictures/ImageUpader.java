
package pl.dziuk.springboogellerypictures;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.dziuk.springboogellerypictures.model.Image;
import pl.dziuk.springboogellerypictures.repo.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUpader  {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    @Value("${cloudNameValue}")
    private String cloudNameValue;
    @Value("${apiKeyValue}")
    private String apiKeyValue;
    @Value("${apiSecretValue}")
    private String apiSecretValue;

    @Autowired
    public ImageUpader(ImageRepo imageRepo,
                       @Value("${cloudNameValue}") String cloudNameValue,
                       @Value("${apiKeyValue}") String apiKeyValue,
                       @Value("${apiSecretValue}") String apiSecretValue) {
        this.imageRepo=imageRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dkyerlkbu",
                "api_key", "517458212836277",
                "api_secret", "ieVA4RY9Us1XNI9eqDQzwiy2kRE"));
    }
    public String uploadFileAndSaveToDb(String path) {
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
        }
        return uploadResult.get("url").toString();
    }


}