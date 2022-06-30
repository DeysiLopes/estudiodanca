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

import lpoo.estudiodanca.modelo.dao.TurmaDao;
import lpoo.estudiodanca.modelo.db.DB;
import lpoo.estudiodanca.modelo.db.DbException;
import lpoo.estudiodanca.modelo.vo.Estudante;
import lpoo.estudiodanca.modelo.vo.Funcionario;
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

	@Override
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

	@Override
	public Turma findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT tb_estudante.*,tb_turma.nome as EstTurma " 
							+ "FROM tb_estudante INNER JOIN tb_turma "
							+ "ON tb_estudante.tb_turmaId = tb_turma.Id " 
							+ "WHERE tb_estudante.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Funcionario fun = instantiateFuncionario(rs);
				Turma obj = instantiateTurma(rs, fun);
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



	private Turma instantiateTurma(ResultSet rs, Funcionario fun) throws SQLException {
		Turma obj = new Turma();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Nome"));
		obj.setFun(fun);
		return obj;
	}

	private Funcionario instantiateFuncionario(ResultSet rs) throws SQLException {
		Funcionario tur = new Funcionario();
		tur.setId(rs.getInt("FuncionarioId"));
		tur.setNome(rs.getString("FunNome"));
		return tur;
	}

	@Override
	public List<Turma> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT tb_turma.*,tb_funcionario.nome as TurFuncionario " 
							+ "FROM tb_turma INNER JOIN tb_funcionario "
							+ "ON tb_turma.FuncionarioId = tb_funcionario.Id " 
							+ "ORDER BY nome");

			rs = st.executeQuery();

			List<Turma> list = new ArrayList<>();
			Map<Integer,Funcionario> map = new HashMap<>();

			while (rs.next()) {
				Funcionario fun = map.get(rs.getInt("FuncionarioId"));

				if (fun == null) {
					fun = instantiateFuncionario(rs);
					map.put(rs.getInt("FuncionarioId"), fun);
				}
				Turma obj = instantiateTurma(rs, fun);
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
