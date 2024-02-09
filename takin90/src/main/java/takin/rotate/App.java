package takin.rotate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import takin.rotate.models.EventCTRL;
import takin.rotate.models.ImageInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Random random = new Random();
    public ArrayList<Integer> indices=new ArrayList<Integer>();
    public int ligne=3;
    public int column=3;
    public int Cout=0;
    public ImageInfo[][] ListBtns = new ImageInfo[ligne][column];
    public ImageInfo[][] imageInfos = new ImageInfo[ligne][column];
    
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        
        Label imageInfoLabel = new Label("Informations sur l'image:");
        try {
            File csvFile = new File("image_info.csv");
            Scanner scanner = new Scanner(csvFile);
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            String imagePath = parts[0];
            int rows = Integer.parseInt(parts[1]);
            int columns = Integer.parseInt(parts[2]);
            ligne=rows;
            column=columns;
            ListBtns = new ImageInfo[ligne][column];
            imageInfos = new ImageInfo[ligne][column];

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       stage.setTitle("Takin rotation");
        GridPane gridPane = new GridPane();
        initiateTable(imageInfos, ListBtns);

        for (int i = 0; i < imageInfos.length; i++) {
            for (int j = 0; j < imageInfos[i].length; j++) {
                int l=i;
                int c=j;
                imageInfos[i][j].button.setOnAction(e -> this.interchange(l,c));
                gridPane.add(imageInfos[i][j].button, j, i);
            }
        }
        Button rotate90=new Button("Rotation 90");
        rotate90.setOnAction(e -> new EventCTRL().rotate90(ListBtns, imageInfos));

        Button rotateM90=new Button("Rotation -90");
        rotateM90.setOnAction(e -> new EventCTRL().rotateM90(ListBtns, imageInfos));

        Button melanger=new Button("Melanger");
        melanger.setOnAction(e -> this.melanger(imageInfos,ListBtns, gridPane));    

        GridPane gpane = new GridPane();
        Button [] ctrlButtons={rotate90,rotateM90,melanger};

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

    private Button createImageButton(String imageName) {
        try {
        InputStream inputStream = getClass().getResourceAsStream(imageName);
        if (inputStream == null) {
            throw new FileNotFoundException("Image not found: " + imageName);
        }

        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Button button = new Button();
        button.setGraphic(imageView);

        return button;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }

    public void initiateTable(ImageInfo [][] imageInfo,ImageInfo [][] ListBtns){
        int k=1;
        for (int i = 0; i < imageInfo.length; i++) {
            for (int j = 0; j < imageInfo[i].length; j++) {
                ListBtns[i][j]=new ImageInfo();
                ListBtns[i][j].button=createImageButton("pic/"+k+".jpg");
                ListBtns[i][j].name="pic/"+k+".jpg";
                imageInfos[i][j]=new ImageInfo();
                imageInfos[i][j].button=createImageButton("pic/"+k+".jpg");
                imageInfos[i][j].name="pic/"+k+".jpg";
                k++;
            }
        }
    }

    public void interchange(int i,int j){
        if(this.indices.size()==2){
            ImageView img=(ImageView)this.imageInfos[i][j].button.getGraphic();
            String nameC=this.imageInfos[i][j].name;
            this.imageInfos[i][j].button.setGraphic(this.imageInfos[indices.get(0)][indices.get(1)].button.getGraphic());
            this.imageInfos[i][j].name=this.imageInfos[indices.get(0)][indices.get(1)].name;
            this.imageInfos[indices.get(0)][indices.get(1)].button.setGraphic(img);
            this.imageInfos[indices.get(0)][indices.get(1)].name=nameC;
            
            this.ListBtns[i][j].button.setGraphic(this.ListBtns[indices.get(0)][indices.get(1)].button.getGraphic());
            this.ListBtns[i][j].name=this.ListBtns[indices.get(0)][indices.get(1)].name;
            this.ListBtns[indices.get(0)][indices.get(1)].button.setGraphic(img);
            this.ListBtns[indices.get(0)][indices.get(1)].name=nameC;
            Cout++;
            System.out.println(Cout);
            this.indices.remove(0);
            this.indices.remove(0);
            boolean maty=CheckVictoire();
            if (maty) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Felicitation");
                alert.setHeaderText("Puzzle reussi avec "+Cout+" couts");
                alert.setContentText("Cliquez sur OK pour fermer la fenêtre.");
                Cout=0;
                alert.showAndWait();
            }
        }else{
            this.indices.add(i);
            this.indices.add(j);
        }
    }

    public boolean CheckVictoire() {
        int index=1;
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < imageInfos[i].length; j++) {
                if (!imageInfos[i][j].name.equals("pic/"+index+".jpg")) {
                    return false;
                }
                index++;
            }
        }
        return true;
    }
    
    
    
    

    public void melanger(ImageInfo[][] imagenf,ImageInfo[][] ListBtns, GridPane gridPane) {
        int numRows = imagenf.length;
        int numCols = imagenf[0].length;
        List<Integer> indices = new ArrayList<>();
        for (int i = 1; i <= numRows * numCols; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);
        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int randImageIndex = indices.get(index++);
                imagenf[i][j].button.setGraphic(createImageButton("pic/" + randImageIndex + ".jpg").getGraphic());
                imagenf[i][j].name="pic/" + randImageIndex + ".jpg";
                ListBtns[i][j].button.setGraphic(createImageButton("pic/" + randImageIndex + ".jpg").getGraphic());
                ListBtns[i][j].name="pic/" + randImageIndex + ".jpg";
            }
        }
    }

    // public void resoudre(Button[][] ListBtns2,Button[][] ListBtns, GridPane gridPane) {
    //     int numRows = ListBtns2.length;
    //     int numCols = ListBtns2[0].length;
    //     int index = 1;
    //     for (int i = 0; i < numRows; i++) {
    //         for (int j = 0; j < numCols; j++) {
    //             ListBtns2[i][j].setGraphic(createImageButton("pic/" + index + ".jpg").getGraphic());
    //             ListBtns[i][j].setGraphic(createImageButton("pic/" + index + ".jpg").getGraphic());
    //             index++;
    //         }
    //     }
    // }

    public static void main(String[] args) {
        launch();
    }

}