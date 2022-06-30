package lpoo.estudiodanca.modelo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import lpoo.estudiodanca.modelo.dao.TurmaDao;
import lpoo.estudiodanca.modelo.db.DB;
import lpoo.estudiodanca.modelo.db.DbException;
import lpoo.estudiodanca.modelo.vo.Turma;

public class TurmaDaoImpl implements TurmaDao {
	
	private Connection conn;

	public Connection getConnection() {
		return conn;
	}

	@Override
	public void insert(Turma obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO tb_turma " 
					+ "(nome, horario) " 
					+ "VALUES " 
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setDate(2, new java.sql.Date(obj.getHorario().getTime()));

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro insperado!! Nenhuma linha afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Turma obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Turma findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Turma> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
