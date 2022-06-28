package lpoo.estudio.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lpoo.estudiodanca.modelo.vo.Estudante;
import lpoo.estudiodanca.principal.Main;

public class EstudanteListController implements Initializable {

	@FXML
	private TableView<Estudante> tableViewEstudantes;
	
	@FXML
	private TableColumn<Estudante, Long> tableColomnId;
	@FXML
	private TableColumn<Estudante, String> tableColomnName;
	@FXML
	private Button btnNew;
	
	@FXML
	public void onBtnNewAction() {
		System.out.println("Botão ta on BB!!");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColomnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColomnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewEstudantes.prefHeightProperty().bind(stage.heightProperty());
	}

}
