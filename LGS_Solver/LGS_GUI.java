import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LGS_GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX 2VarLGS");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        //grid.setPadding(new Insets(25, 25, 25, 25));

        Label x1 = new Label("x");
        Label x2 = new Label("x");
        Label y1 = new Label("y");
        Label y2 = new Label("y");

        TextField x1_input = new TextField();
        x1_input.setPrefWidth(30);
        TextField x2_input = new TextField();
        x2_input.setPrefWidth(30);
        TextField rhs1 = new TextField();
        rhs1.setPrefWidth(30);

        TextField y1_input = new TextField();
        y1_input.setPrefWidth(30);
        TextField y2_input = new TextField();
        y2_input.setPrefWidth(30);
        TextField rhs2 = new TextField();
        rhs1.setPrefWidth(30);
        
        // 1. Gleichung
        grid.add(x1_input, 0, 0);
        grid.add(x1, 1, 0);
        grid.add(y1_input, 2, 0);
        grid.add(y1, 3, 0);
        grid.add(new Label(" = "), 4, 0);
        grid.add(rhs1, 5, 0);

        // 2. Gleichung
        grid.add(x2_input, 0, 1);
        grid.add(x2, 1, 1);
        grid.add(y2_input, 2, 1);
        grid.add(y2, 3, 1);
        grid.add(new Label(" = "), 4, 1);
        grid.add(rhs2, 5, 1);

        Button btn = new Button("Solve!");
        //n.setAlignment(Pos.BOTTOM_RIGHT);
        //hbBtn.getChildren().add(btn);
        grid.add(btn, 0, 3);

        final Text result = new Text();
        grid.add(result, 0, 4);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                double[][] mat = {
                    { Double.parseDouble(x1_input.getText()), Double.parseDouble(y1_input.getText()), Double.parseDouble(rhs1.getText()) },
                    { Double.parseDouble(x2_input.getText()), Double.parseDouble(y2_input.getText()), Double.parseDouble(rhs2.getText()) }
                };
                try {
                    ZweiVarLGS.printArray2D(mat); // Debug
                    double[] lsg = ZweiVarLGS.solve2VarMatrix(mat);
                    result.setFill(Color.BLUE);
                    result.setText("x: " + lsg[0] + ", y: " + lsg[1]);
                } catch (ArithmeticException ae) {
                    result.setText("Result undefined");
                }
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
