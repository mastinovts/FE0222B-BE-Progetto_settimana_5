package servizi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dati.Auto;
import servizi.ConnectionFactory;


public class AutoDAO {
	private static final Logger logger = LoggerFactory.getLogger(AutoDAO.class);
	
	public boolean inserisciAuto (Auto auto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		int i = 0;
		String q = "INSERT INTO multe.auto (targa, marca, modello) VALUES (?,?,?)";
		
		try {
			ps = conn.prepareStatement(q);
			
			ps.setString(1, auto.getTarga());
			ps.setString(2, auto.getMarca());
			ps.setString(3, auto.getModello());
			i = ps.executeUpdate();
			logger.info("Inserimento auto avvenuto");
		}catch(SQLException ex) {
			logger.error("errore nell inserimento", ex);
		}
		
		try {conn.close();} catch(Exception e) {}
		
		if(i>0)
			return true;
		else
			return false;
	}
	
	public ArrayList getAllAuto() {
		Connection conn = ConnectionFactory.getConnection();
		Statement st = null;
		ResultSet rs = null;
		ArrayList <Auto> listaAuto = null;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * from multe.auto");
					
			listaAuto = new ArrayList<Auto>();
			
			while(rs.next()) {
				Auto auto = new Auto();
				auto.setId(rs.getInt("id"));
				auto.setTarga(rs.getString("targa"));
				auto.setMarca(rs.getString("marca"));
				auto.setModello(rs.getString("modello"));
				
				listaAuto.add(auto);
			}
			logger.info("ricerca effettuata");
		}catch(SQLException ex) {
			logger.error("errore nella ricerca");
			ex.printStackTrace();
		}
		
		try {rs.close();}catch(Exception e) {}
		try {conn.close();}catch(Exception e) {}
		
		return listaAuto;
	}
	
	public Auto cercaAuto(String targa) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Auto auto = null;
		String q = "SELECT * from multe.auto where targa=?";
		
		try {
			ps = conn.prepareStatement(q);
			ps.setString(1, targa);
			rs = ps.executeQuery();	
			
			if(rs.next()) {
				auto = new Auto();
				auto.setId(rs.getInt("id"));
				auto.setTarga(rs.getString("targa"));
				auto.setMarca(rs.getString("marca"));
				auto.setModello(rs.getString("modello"));
				logger.info("auto aggiunta con successo");
				
			}
		}catch(SQLException ex) {
			logger.error("errore nell inserimento dell auto");
			ex.printStackTrace();
		}
		
		try {ps.close();}catch(Exception e) {}
		try {rs.close();}catch(Exception e) {}
		try {conn.close();}catch(Exception e) {}
		
		return auto;
	}
}
