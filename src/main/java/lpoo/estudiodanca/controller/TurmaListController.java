package lpoo.estudiodanca.controller;

import java.net.URL;
import java.sql.Time;
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
import lpoo.estudiodanca.modelo.services.TurmaService;
import lpoo.estudiodanca.modelo.vo.Turma;
import lpoo.estudiodanca.principal.Main;

public class TurmaListController implements Initializable{
	
	private TurmaService service;
	
	@FXML
	private TableView<Turma> tableViewTurma;
	@FXML
	private TableColumn<Turma, Long> tableColumnId;
	@FXML
	private TableColumn<Turma, Long> tableColumnName;
	@FXML
	private TableColumn<Turma, Time> tableColumnHorario;
	
	@FXML
	private Button btnNew;
	
	private ObservableList<Turma> obsList;
	
	@FXML
	public void onBtnNewAction() {
		System.out.println("Ta on!!");
	}
	
	public void setTurmaService(TurmaService service) {
		this.service = service;
	}	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewTurma.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Turma> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewTurma.setItems(obsList);
	}
	
}
