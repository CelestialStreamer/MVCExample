package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator extends Application {
    private static ScriptEngine engine;

    public static void main(String[] args) {
	engine = new ScriptEngineManager().getEngineByName("JavaScript");
	launch(args);
    }

    @FXML
    private TextField display;

    @FXML
    protected void calc(ActionEvent event) throws ScriptException {
	display.setText(engine.eval(display.getText()).toString());
    }

    @FXML
    protected void clear(ActionEvent event) {
	display.setText("");
    }

    @FXML
    protected void input(ActionEvent event) {
	Button source = (Button) event.getSource();
	display.setText(display.getText() + source.getText());
    }
    
    @FXML
    protected void onEnter(ActionEvent event) throws ScriptException {
	calc(event);
	display.positionCaret(display.getText().length());
    }

    @Override
    public void start(Stage stage) throws IOException {
	Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainView.fxml")));
	scene.getStylesheets().add("resources/application.css");
	stage.setScene(scene);
	stage.setTitle("MVC Calculator");
	stage.getIcons().add(new Image("resources/icon.png"));
	stage.show();
    }
}
