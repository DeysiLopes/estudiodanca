package lpoo.estudiodanca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import lpoo.estudiodanca.modelo.services.EstudanteService;
import lpoo.estudiodanca.modelo.services.TurmaService;
import lpoo.estudiodanca.principal.Main;

public class MainViewController implements Initializable{

	private static final long serialVersionUID = 1L;
	
	@FXML
	private MenuItem menuItemEstudante;
	
	@FXML
	private MenuItem menuItemFuncionario;
	
	@FXML
	private MenuItem menuItemTurma;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemEstudanteAction() {
		loadView("/lpoo/estudiodanca/visao/gui/EstudanteList.fxml", (EstudanteListController controller)-> {
			controller.setEstudanteService(new EstudanteService());
			controller.updateTableView();
		});
	}
	@FXML
	public void onMenuItemFuncionarioAction() {
		loadView("/lpoo/estudiodanca/visao/gui/FuncionarioList.fxml", x -> {});
	}
	
	@FXML
	public void onMenuItemTurmaAction() {
		loadView("/lpoo/estudiodanca/visao/gui/TurmaList.fxml",  x -> {});
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/lpoo/estudiodanca/visao/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			
		} catch (IOException e) {
			throw new Error(e.getMessage());
		}
	}
	
	
}
