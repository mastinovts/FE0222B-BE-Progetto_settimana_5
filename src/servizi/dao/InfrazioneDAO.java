package servizi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dati.Auto;
import dati.Infrazione;
import servizi.ConnectionFactory;


public class InfrazioneDAO {
	private static final Logger logger = LoggerFactory.getLogger(InfrazioneDAO.class);
	
	public boolean inserisciInfrazione(Infrazione infrazione) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		int i = 0;
		String q = "INSERT INTO multe.infrazione (data, tipo, importo, id_auto) VALUES (?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(q);
			
			ps.setDate(1, infrazione.getData());
			ps.setString(2, infrazione.getTipo());
			ps.setDouble(3, infrazione.getImporto());
			ps.setInt(4, infrazione.getId_auto());
			
			i = ps.executeUpdate();
			logger.info("sono state inserite " + i + " righe");
		}catch(SQLException ex) {
			logger.error("errore nell inserimento", ex);
		}
		
		try {conn.close();} catch(Exception e) {}
		
		if(i>0)
			return true;
		else
			return false;
	}
	
	public void stampaDatiInfrazioneAutoDaTarga(String targa) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "select a.id auto_id, a.targa , a.marca, a.modello, i.id infr_id, i.tipo, i.importo, i.data"
				+ "  from multe.infrazione i"
				+ "    join multe.auto a"
				+ "    on a.id = i.id_auto"
				+ "    where a.targa = ?;";
		
		try {
			ps = conn.prepareStatement(q);
			ps.setString(1, targa);
			rs = ps.executeQuery();	
			
			while(rs.next()) {
				Infrazione i = new Infrazione(rs.getInt("infr_id"),
						rs.getDate("data"), 
						rs.getString("tipo"), 
						rs.getDouble("importo"), 
						rs.getInt("auto_id") );
				Auto a = new Auto(rs.getInt("auto_id"),
						rs.getString("targa"), 
						rs.getString("marca"), 
						rs.getString("modello"));
				logger.info("{} -> {}", i, a);
			}
			
		}catch(Exception e) {
			logger.error("errore nella ricerca dei dati infrazione");
			
		}
		
		try {ps.close();}catch(Exception e) {}
		try {rs.close();}catch(Exception e) {}
		try {conn.close();}catch(Exception e) {}
		
	}
	
	public boolean eliminaInfrazione(int id) {
		Connection conn = ConnectionFactory.getConnection();
		Statement st = null;
		int i=0;
		
		String q = "DELETE FROM infrazione WHERE id= " + id;
		
		try {
			st = conn.prepareStatement(q);
			
			i = st.executeUpdate(q);
			System.out.println("eliminazione avvenuta con successo");
		}
		catch(SQLException ex) {
			System.out.println("eliminazione fallita");
			ex.printStackTrace();
		}
		
		try {conn.close();} catch(Exception e) {}
		
		if(i>0)
			return true;
		else
			return false;
	}

}
