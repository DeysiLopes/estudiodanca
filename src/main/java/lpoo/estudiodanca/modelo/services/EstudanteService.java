package lpoo.estudiodanca.modelo.services;

import java.util.ArrayList;
import java.util.List;

import lpoo.estudiodanca.modelo.vo.Estudante;

public class EstudanteService {
	
	public List<Estudante> findAll(){
			List<Estudante> list = new ArrayList<>();
			list.add(new Estudante(1, "Deysi"));
			list.add(new Estudante(2, "Debora"));
			list.add(new Estudante(3, "Luana"));
			return list;
	}
}
