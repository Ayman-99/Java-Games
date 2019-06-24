package otherStuff;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class CommonMethods {
    
   /***************************** CommOn METHODS **********************************************/
    public static void alretBox(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label l = new Label();
        l.setText(message);
        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(l, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    //Confirm box
    static boolean yesOrNo;
    public static boolean confirmBox(String title, String message){
        
     Stage window = new Stage();
     window.initModality(Modality.APPLICATION_MODAL);
     window.setTitle(title);
     window.setMinWidth(250);
     Label l = new Label();
     l.setText(message);

     // Two buttons
     Button yesButton = new Button("Yes");
     Button noButton = new Button("No");
     yesButton.setOnAction(e -> {      
         yesOrNo = true;
         window.close();
     });
     noButton.setOnAction(e -> {
         yesOrNo = false;
         window.close();
     });
     VBox layout = new VBox(10);
     layout.getChildren().addAll(l, yesButton, noButton);
     layout.setAlignment(Pos.CENTER);

     Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();

    return yesOrNo;
    }
    public static void askExit(Stage window) {
        if(CommonMethods.confirmBox("Exit confirmation", "Are you sure you want to exit??")){
                window.close();
                System.exit(0);
        }
    }
    public void setTimer(Label timeElapsed, int time, Timer temp){
     temp.scheduleAtFixedRate(new TimerTask() {
         public int time1 = time;
           @Override
            public void run() {
               if(time1 > 0){
                  Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                    timeElapsed.setText("Program will exit after: " + time1);
                  }
                  });
                  time1--;
                  }else{
                    System.exit(0);
                  }
            }
       }, 1500,1500);
    }
    /***************************** End of CommOn METHODS **********************************************/
}