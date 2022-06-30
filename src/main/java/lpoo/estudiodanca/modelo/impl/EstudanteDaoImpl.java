package lpoo.estudiodanca.modelo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lpoo.estudiodanca.modelo.dao.EstudanteDao;
import lpoo.estudiodanca.modelo.db.DB;
import lpoo.estudiodanca.modelo.db.DbException;
import lpoo.estudiodanca.modelo.vo.Estudante;
import lpoo.estudiodanca.modelo.vo.Turma;

public class EstudanteDaoImpl implements EstudanteDao {
	
	private Connection conn;

//	public Connection getConnection() {
//		return conn;
//	}
	
	public EstudanteDaoImpl(Connection conn) {
		this.conn = conn;
	}

	public void insert(Estudante obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tb_estudante " + "(nome) " + "VALUES " + "(?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());

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

	public void update(Estudante obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tb_estudante " + "SET nome = ?" + "WHERE Id = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("DELETE FROM tb_estudante WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	public Estudante findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT tb_turma.*,tb_funcionario.nome as TurFuncionario " 
							+ "FROM tb_turma INNER JOIN tb_funcionario "
							+ "ON tb_turma.tb_funcionarioId = tb_funcionario.Id " 
							+ "WHERE tb_turma.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Turma tur = instantiateTurma(rs);
				Estudante obj = instantiateEstudante(rs, tur);
				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Estudante instantiateEstudante(ResultSet rs, Turma tur) throws SQLException {
		Estudante obj = new Estudante();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Nome"));
		obj.setTurma(tur);
		return obj;
	}

	private Turma instantiateTurma(ResultSet rs) throws SQLException {
		Turma tur = new Turma();
		tur.setId(rs.getInt("TurmaId"));
		tur.setNome(rs.getString("TurNome"));
		return tur;
	}

	public List<Estudante> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT tb_estudante.*,tb_turma.nome as EstTurma " 
							+ "FROM tb_estudante INNER JOIN tb_turma "
							+ "ON tb_estudante.TurmaId = tb_turma.Id " 
							+ "ORDER BY nome");

			rs = st.executeQuery();

			List<Estudante> list = new ArrayList<>();
			Map<Integer, Turma> map = new HashMap<>();

			while (rs.next()) {
				Turma tur = map.get(rs.getInt("TurmaId"));

				if (tur == null) {
					tur = instantiateTurma(rs);
					map.put(rs.getInt("TurmaId"), tur);
				}
				Estudante obj = instantiateEstudante(rs, tur);
				list.add(obj);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Estudante> findByTurma(Turma turma) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT tb_estudante.*,tb_turma.Name as EstTurma " 
							+ "FROM tb_estudante INNER JOIN tb_turma "
							+ "ON tb_estudante.TurmaId = tb_turma.Id " 
							+ "WHERE TurmaId = ? " 
							+ "ORDER BY nome");

			st.setInt(1, turma.getId());
			rs = st.executeQuery();

			List<Estudante> list = new ArrayList<>();
			Map<Integer, Turma> map = new HashMap<>();

			while (rs.next()) {
				Turma tur = map.get(rs.getInt("TurmaId"));

				if (tur == null) {
					tur = instantiateTurma(rs);
					map.put(rs.getInt("TurmaId"), tur);
				}
				Estudante obj = instantiateEstudante(rs, tur);
				list.add(obj);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
