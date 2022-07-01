package lpoo.estudiodanca.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lpoo.estudiodanca.exceptions.ValidationExcpetion;
import lpoo.estudiodanca.modelo.db.DbException;
import lpoo.estudiodanca.modelo.services.TurmaService;
import lpoo.estudiodanca.modelo.vo.Turma;
import lpoo.estudiodanca.visao.gui.listeners.DataChangeListener;
import lpoo.estudiodanca.visao.gui.util.Constraints;
import lpoo.estudiodanca.visao.gui.util.Utils;

public class TurmaFormController implements Initializable {

	private Turma  entity;
	
	private TurmaService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
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
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
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
			notifyDatachageListeners();
			Utils.currentStage(event).close();
		}catch (ValidationExcpetion e) {
			setErrorMessege(e.getErrors());
		}catch (DbException e) {
			System.out.println("Erro salvando objeto " + e);
		}
	}
	
	private void notifyDatachageListeners() {
		for(DataChangeListener listerner : dataChangeListeners) {
			listerner.onDataChanged();
		}
		
	}

	private Turma getFormData() {
		Turma obj = new Turma();
		
		ValidationExcpetion excpetion = new ValidationExcpetion("Validação com erro");
		
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		if(txtName.getText() == null || txtName.getText().trim().equals("")) {
			excpetion.addError("nome", "Este campo não estar vazio!!");
		}
		if(txtHorario.getText() == null || txtHorario.getText().trim().equals("")) {
			excpetion.addError("horario", "Este campo não estar vazio!!");
		}
		
		obj.setNome(txtName.getText());
		obj.setHorario(txtHorario.getText());
		
		if(excpetion.getErrors().size() > 0) {
			throw excpetion;
		}
		
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
	
	private void setErrorMessege(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("nome")) {
			labelErrorName.setText(errors.get("nome"));
		}
		if(fields.contains("horario")) {
			labelErrorName.setText(errors.get("horario"));
		}
	}

}
