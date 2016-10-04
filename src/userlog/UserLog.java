/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userlog;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserLog extends Application {

    private TableView table;
    public TextField ListingInput;
    private Text actionStatus;
    private static double xOffset = 0;
    private static double yOffset = 0;
    private Stage eventWindow;

    @Override
    public void start(Stage primaryStage) {
        eventWindow = new Stage();
        eventWindow.initStyle(StageStyle.UNDECORATED);
        eventWindow.getIcons().add(new Image("/userlog/NCP.PNG"));
        eventWindow.setTitle("Unit Table");

        Label label = new Label("User Log");
        label.setTextFill(Color.LIGHTGRAY);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        label.setTranslateX(55);
        label.setTranslateY(-147);

        Image image1 = new Image("/userlog/NCP.PNG");
        ImageView TIcon = new ImageView();
        TIcon.setImage(image1);
        TIcon.setFitWidth(45);
        TIcon.setPreserveRatio(true);
        TIcon.setSmooth(true);
        TIcon.setCache(true);
        TIcon.setTranslateX(5);
        TIcon.setTranslateY(-110);

        ImageView Min = new ImageView("/userlog/minimizeButton1.PNG");
        Min.getStyleClass().add("ImageView");
        Min.setFitHeight(18);
        Min.setFitWidth(18);
        Min.setTranslateX(360);
        Min.setTranslateY(-94);

        Min.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                eventWindow.setIconified(true);
            }
        });

        table = new TableView<>();

        //table.setItems(getRemarkList());

        TableColumn timeColumn = new TableColumn("User Time Tracker");
   
        timeColumn.setCellValueFactory(new PropertyValueFactory("gettime"));
        timeColumn.prefWidthProperty().bind(table.widthProperty().multiply(1.0));

        table.getColumns().setAll(timeColumn);
        table.setPrefWidth(10);
        table.setPrefHeight(500);
        table.setTranslateX(0);
        table.setTranslateY(-130);

        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        Image image = new Image("/userlog/logBackG.jpg");
        VBox vbox = new VBox(0);
        vbox.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        vbox.setPadding(new Insets(100, 20, -70, 20));
        vbox.getChildren().addAll(Min, TIcon, actionStatus, label, table);
        Scene scene = new Scene(vbox, 400, 400); 

        eventWindow.setScene(scene);
        eventWindow.show();

        //Positioning the window on the screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        eventWindow.setX((primScreenBounds.getWidth() - eventWindow.getWidth()) / 100);
        eventWindow.setY((primScreenBounds.getHeight() - eventWindow.getHeight()) / 100);

        eventWindow.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = eventWindow.getX() - event.getScreenX();
                yOffset = eventWindow.getY() - event.getScreenY();
            }
        });

        eventWindow.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                eventWindow.setX(event.getScreenX() + xOffset);
                eventWindow.setY(event.getScreenY() + yOffset);
            }
        });
    }

    //mouse clicked from placed marker should get the string 
  
    public void addMouseClicked() {
        //System.out.println(ListingInput.getText());
        getTime timeList = new getTime();
        timeList.setListing(ListingInput.getText());//change listinInput to your marker method
        table.getItems().add(timeList);
        //ListingInput.clear();
    }

   

  /* public ObservableList<RemarkList> getRemarkList() {
        ObservableList<RemarkList> remarks = FXCollections.observableArrayList();
        remarks.add(new RemarkList(""));
        return remarks;
    }*/
   
    public static void main(String[] args) {
        launch(args);
    }

}
