package lpoo.estudiodanca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import lpoo.estudiodanca.modelo.services.EstudanteService;
import lpoo.estudiodanca.principal.Main;

public class CadastrarEstudanteController implements Initializable{

	private static final long serialVersionUID = 1L;
	
	@FXML
	private MenuItem menuItemEstudante;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemEstudanteAction() {
		loadView2("/lpoo/estudiodanca/visao/gui/EstudanteList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/lpoo/estudiodanca/visao/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}
	
	private void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
		} catch (IOException e) {
			throw new Error(e.getMessage());
		}
	}
	
	private void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			EstudanteListController controller = loader.getController();
			controller.setEstudanteService(new EstudanteService());
			controller.updateTableView();
			
		} catch (IOException e) {
			throw new Error(e.getMessage());
		}
	}
	
}
