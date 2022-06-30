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

import lpoo.estudiodanca.modelo.dao.FuncionarioDao;
import lpoo.estudiodanca.modelo.db.DB;
import lpoo.estudiodanca.modelo.db.DbException;
import lpoo.estudiodanca.modelo.vo.Estudante;
import lpoo.estudiodanca.modelo.vo.Funcionario;
import lpoo.estudiodanca.modelo.vo.Turma;

public class FuncionarioDaoImpl implements FuncionarioDao {
	
	private Connection conn;

	public FuncionarioDaoImpl(Connection conn) {
		this.conn = conn;
	}

//	public Connection getConnection() {
//		return conn;
//	}

	@Override
	public void insert(Funcionario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO tb_funcionario " 
							+ "(nome, cpf, turma) " 
							+ "VALUES " 
							+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getCpf());
			st.setInt(3, obj.getTurma().getId());

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
	public void update(Funcionario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE tb_funcionario " 
					+ "SET nome = ?, cpf = ?, turma = ?" 
					+ "WHERE Id = ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getCpf());
			st.setInt(3, obj.getTurma().getId());
			st.setInt(4, obj.getId());

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

			st = conn.prepareStatement("DELETE FROM tb_funcionario WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Funcionario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT tb_funcionario.*,tb_turma.nome as FunTurma "
					+ "FROM tb_funcionario INNER JOIN tb_turma "
					+ "ON tb_funcionario.tb_turmaId = tb_turma.Id " 
					+ "WHERE tb_funcionario.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Turma tur = instantiateTurma(rs);
				Funcionario obj = instantiateFuncionario(rs, tur);
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

	private Funcionario instantiateFuncionario(ResultSet rs, Turma tur) throws SQLException {
		Funcionario obj = new Funcionario();
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

	@Override
	public List<Funcionario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT tb_estudante.*,tb_turma.nome as EstTurma "
							+ "FROM tb_estudante INNER JOIN tb_turmat "
							+ "ON tb_estudante.TurmaId = tb_turma.Id "
							+ "ORDER BY nome");

			rs = st.executeQuery();

			List<Funcionario> list = new ArrayList<>();
			Map<Integer, Turma> map = new HashMap<>();

			while (rs.next()) {
				Turma tur = map.get(rs.getInt("TurmaId"));

				if (tur == null) {
					tur = instantiateTurma(rs);
					map.put(rs.getInt("TurmaId"), tur);
				}
				Funcionario obj = instantiateFuncionario(rs, tur);
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
	public List<Funcionario> findByTurma(Turma turma) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT tb_funcionario.*,tb_turma.Name as FunTurma " 
							+ "FROM tb_funcionario INNER JOIN tb_turma "
							+ "ON tb_funcionario.TurmaId = tb_turma.Id " 
							+ "WHERE TurmaId = ? " 
							+ "ORDER BY nome");

			st.setInt(1, turma.getId());
			rs = st.executeQuery();

			List<Funcionario> list = new ArrayList<>();
			Map<Integer, Turma> map = new HashMap<>();

			while (rs.next()) {
				Turma tur = map.get(rs.getInt("TurmaId"));

				if (tur == null) {
					tur = instantiateTurma(rs);
					map.put(rs.getInt("TurmaId"), tur);
				}
				Funcionario obj = instantiateFuncionario(rs, tur);
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
