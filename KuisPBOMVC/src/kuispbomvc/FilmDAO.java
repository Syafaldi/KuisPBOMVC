package kuispbomvc;

import java.sql.*;
import javax.swing.JOptionPane;

public class FilmDAO {
    private Statement statement;
    private Connection koneksi;
    String judulsearch;
    public FilmDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kuis_pbo";
            koneksi = DriverManager.getConnection(url,"root","");
            statement = koneksi.createStatement();
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Class Not found :"+ex);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"SQL Exception :"+ex);
        }
    }
    
     public void Insert(FilmModel Model){
        try{
            String query ="INSERT INTO film VALUES (null,'"+Model.getJudul()+"','"+Model.getTipe()+"','"+Model.getEpisode()+"','"+Model.getGenre()+"','"+Model.getStatus()+"','"+Model.getRating()+"')";
            
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data disimpan");
        }catch (Exception sql){
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
     
    public void Delete(FilmModel Model){
        try{
            String query = "DELETE FROM film WHERE id_film='"+Model.getId()+"'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Berhasil dihapus");
        }catch(SQLException sql){
            System.out.println(sql.getMessage());
        }
    }
    
    public void Update(FilmModel Model){
        try{
            String query = "UPDATE film SET judul ='"+Model.getJudul()+"',tipe = '"+Model.getTipe()+"',episode = '"+Model.getEpisode()+"',genre = '"+Model.getGenre()+"',status = '"+Model.getStatus()+"',rating = '"+Model.getRating()+"' WHERE id_film = '"+Model.getId()+"'";
            System.out.println(query);
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Berhasil di update");
        }catch(SQLException sql){
            System.out.println(sql.getMessage());
        }
    }
    
     
     public int getJmldata(){
        int jmlData = 0;
        try{
            String query = "SELECT * FROM film";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                jmlData++;
            }
            return jmlData;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
     public int getJmldataSearch(){
        int jmlData = 0;
        try{
            String query = "SELECT * FROM film WHERE judul LIKE '%"+judulsearch+"%'";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                jmlData++;
            }
            return jmlData;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
     public String[][] readFilm(){
        try{
            int jmlData = 0;
            String data[][] = new String[getJmldata()][8];
            String query = "SELECT * FROM film";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[jmlData][0] = Integer.toString(jmlData+1);
                data[jmlData][1] = resultSet.getString("id_film");
                data[jmlData][2] = resultSet.getString("judul");
                data[jmlData][3] = resultSet.getString("tipe");
                data[jmlData][4] = resultSet.getString("episode");
                data[jmlData][5] = resultSet.getString("genre");
                data[jmlData][6] = resultSet.getString("status");
                data[jmlData][7] = resultSet.getString("rating");
                
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }
     
     public String[][] searchFilm(FilmModel Model){
        try{
            int jmlData = 0;
            String query = "SELECT * FROM film WHERE judul LIKE '%"+Model.getJudul()+"%'";
            judulsearch=Model.getJudul();
            String data[][] = new String[getJmldataSearch()][8];
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[jmlData][0] = Integer.toString(jmlData+1);
                data[jmlData][1] = resultSet.getString("id_film");
                data[jmlData][2] = resultSet.getString("judul");
                data[jmlData][3] = resultSet.getString("tipe");
                data[jmlData][4] = resultSet.getString("episode");
                data[jmlData][5] = resultSet.getString("genre");
                data[jmlData][6] = resultSet.getString("status");
                data[jmlData][7] = resultSet.getString("rating");
                
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
     }
}
