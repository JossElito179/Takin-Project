// package takin.rotate.models;

// import javafx.application.Application;
// import javafx.embed.swing.SwingFXUtils;
// import javafx.event.ActionEvent;
// import javafx.geometry.Insets;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.stage.FileChooser;
// import javafx.stage.Stage;

// import javax.imageio.ImageIO;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;

// public class ImagePuzzle extends Application {

//     private FileChooser fileChooser;
//     private ImageView imageView;
//     private TextField rowsField;
//     private TextField colsField;
//     private GridPane puzzlePane;

//     @Override
//     public void start(Stage primaryStage) {
//         primaryStage.setTitle("Image Puzzle");

//         // File chooser for selecting image
//         fileChooser = new FileChooser();
//         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

//         // Image view to display selected image
//         imageView = new ImageView();
//         imageView.setFitWidth(400);
//         imageView.setFitHeight(400);

//         // Text fields for specifying rows and columns
//         Label rowsLabel = new Label("Rows:");
//         rowsField = new TextField();
//         rowsField.setPrefColumnCount(3);

//         Label colsLabel = new Label("Columns:");
//         colsField = new TextField();
//         colsField.setPrefColumnCount(3);

//         HBox inputBox = new HBox(10, rowsLabel, rowsField, colsLabel, colsField);
//         inputBox.setPadding(new Insets(10));

//         // Button for selecting image
//         Button selectImageButton = new Button("Select Image");
//         selectImageButton.setOnAction(this::selectImage);

//         // Button for creating puzzle
//         Button createPuzzleButton = new Button("Create Puzzle");
//         createPuzzleButton.setOnAction(this::createPuzzle);

//         HBox buttonBox = new HBox(10, selectImageButton, createPuzzleButton);
//         buttonBox.setPadding(new Insets(10));

//         // GridPane to display puzzle pieces
//         puzzlePane = new GridPane();
//         puzzlePane.setPadding(new Insets(10));

//         VBox root = new VBox(10, imageView, inputBox, buttonBox, puzzlePane);
//         root.setPadding(new Insets(10));

//         primaryStage.setScene(new Scene(root, 600, 600));
//         primaryStage.show();
//     }

//     private void selectImage(ActionEvent event) {
//         File file = fileChooser.showOpenDialog(null);
//         if (file != null) {
//             Image image = new Image(file.toURI().toString());
//             imageView.setImage(image);
//         }
//     }

//     private void createPuzzle(ActionEvent event) {
//         int rows = Integer.parseInt(rowsField.getText());
//         int cols = Integer.parseInt(colsField.getText());

//         if (imageView.getImage() != null && rows > 0 && cols > 0) {
//             BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
//             int pieceWidth = bufferedImage.getWidth() / cols;
//             int pieceHeight = bufferedImage.getHeight() / rows;

//             puzzlePane.getChildren().clear();

//             for (int i = 0; i < rows; i++) {
//                 for (int j = 0; j < cols; j++) {
//                     BufferedImage piece = bufferedImage.getSubimage(j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight);
//                     Image pieceImage = SwingFXUtils.toFXImage(piece, null);
//                     ImageView pieceView = new ImageView(pieceImage);
//                     puzzlePane.add(pieceView, j, i);
//                 }
//             }
//         }
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
