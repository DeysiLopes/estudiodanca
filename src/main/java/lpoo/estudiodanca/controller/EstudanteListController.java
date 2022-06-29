package lpoo.estudiodanca.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lpoo.estudiodanca.modelo.services.EstudanteService;
import lpoo.estudiodanca.modelo.vo.Estudante;
import lpoo.estudiodanca.principal.Main;

public class EstudanteListController implements Initializable {

	private EstudanteService service;
	
	@FXML
	private TableView<Estudante> tableViewEstudantes;
	
	@FXML
	private TableColumn<Estudante, Long> tableColomnId;
	@FXML
	private TableColumn<Estudante, String> tableColomnName;
	@FXML
	private Button btnNew;
	
	private ObservableList<Estudante> obsList;
	
	@FXML
	public void onBtnNewAction() {
		System.out.println("Botão ta on BB!!");
	}
	
	public void setEstudanteService(EstudanteService service) {
		this.service = service;
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
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("SERVICE WAS NULL");
		}
		List <Estudante> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewEstudantes.setItems(obsList);
	}

}
