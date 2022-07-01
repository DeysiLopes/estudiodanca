package lpoo.estudiodanca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lpoo.estudiodanca.modelo.db.DbException;
import lpoo.estudiodanca.modelo.services.TurmaService;
import lpoo.estudiodanca.modelo.vo.Turma;
import lpoo.estudiodanca.visao.gui.util.Constraints;
import lpoo.estudiodanca.visao.gui.util.Utils;

public class TurmaFormController implements Initializable {

	private Turma  entity;
	
	private TurmaService service;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtHorario;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Button btnCancel;
	
	public void setTurma(Turma entity) {
		this.entity = entity;
	}
	
	public void setTurmaService(TurmaService service) {
		this.service = service;
	}
	
	@FXML
	public void onBtnSaveAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("SErvice was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			Utils.currentStage(event).close();
		}catch (DbException e) {
			System.out.println("Erro salvando objeto " + e);
		}
	}
	
	private Turma getFormData() {
		Turma obj = new Turma();
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setNome(txtName.getText());
		obj.setHorario(txtHorario.getText());
		
		return obj;
	}

	@FXML
	public void onBtnCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
		Constraints.setTextFieldMaxLength(txtHorario, 30);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getNome());
		txtHorario.setText(entity.getHorario());
	}

}
