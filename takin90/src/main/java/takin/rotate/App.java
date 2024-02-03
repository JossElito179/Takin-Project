package takin.rotate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import takin.rotate.models.EventCTRL;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
       stage.setTitle("Takin rotation");

        GridPane gridPane = new GridPane();

        Button[] listBtn = {new Button("A"), new Button("B"), new Button("C")};
        Button[] listBtn1 = {new Button("D"), new Button("E"), new Button("F")};
        Button[] listBtn2 = {new Button("G"), new Button("H"), new Button("I")};

        Button[] listBtnp = {new Button("A"), new Button("B"), new Button("C")};
        Button[] listBtn1p = {new Button("D"), new Button("E"), new Button("F")};
        Button[] listBtn2p = {new Button("G"), new Button("H"), new Button("I")};

        Button[][] ListBtns = {listBtn, listBtn1, listBtn2};
        Button[][] ListBtns2 = {listBtnp, listBtn1p, listBtn2p};



        for (int i = 0; i < ListBtns2.length; i++) {
            for (int j = 0; j < ListBtns2[i].length; j++) {
                gridPane.add(ListBtns2[i][j], j, i);
            }
        }
        Button rotate90=new Button("Rotation 90");
        rotate90.setOnAction(e -> new EventCTRL().rotate90(ListBtns, ListBtns2));

        Button rotateM90=new Button("Rotation -90");
        rotateM90.setOnAction(e -> new EventCTRL().rotateM90(ListBtns, ListBtns2));

        GridPane gpane = new GridPane();
        Button [] ctrlButtons={rotate90,rotateM90,new Button("Resoudre"),new Button("Embrouiller")};

        for (int i = 0; i < ctrlButtons.length; i++) {
            gpane.add(ctrlButtons[i], i, 1);
        }

        Scene scene = new Scene(new BorderPane(gpane, null, null, gridPane, null), 400, 300);

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}