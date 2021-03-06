package pl.dziuk.springboogellerypictures.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dziuk.springboogellerypictures.ImageUpader;


@Route("upload")
public class UploadGui extends VerticalLayout {

    private ImageUpader imageUpader;

    @Autowired
    public UploadGui(ImageUpader imageUpader) {
        this.imageUpader = imageUpader;

        Label label = new Label();
        TextField textField = new TextField();
        Button button = new Button("upload");
        button.addClickListener(clickEvent ->
        {
            String uploadedImage = imageUpader.uploadFileAndSaveToDb(textField.getValue());
            Image image = new Image(uploadedImage, "There is no picture");
            label.setText("Successful picture upload");
            add(label);
            add(image);
        });
        add(textField);
        add(button);

    }
}