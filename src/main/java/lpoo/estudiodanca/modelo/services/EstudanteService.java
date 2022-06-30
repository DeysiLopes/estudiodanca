package lpoo.estudiodanca.modelo.services;

import java.util.ArrayList;
import java.util.List;

import lpoo.estudiodanca.modelo.vo.Estudante;

public class EstudanteService<T> {
	
	public List<Estudante> findAll(){
		List<Estudante> list = new ArrayList<>();
		list.add(new Estudante(1, "Deysi", null));
		list.add(new Estudante(2, "Debora", null));
		list.add(new Estudante(3, "Luana", null));
		return list;
}
}
