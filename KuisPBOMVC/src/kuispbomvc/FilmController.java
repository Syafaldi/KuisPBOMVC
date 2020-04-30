
package kuispbomvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class FilmController {
    FilmModel filmmodel;
    FilmView filmview;
    FilmDAO filmdao;
    static String genreTable,statusTable,judulTable,tipeTable,idTable,genre,status,judul,tipe;
    static int episodeTable,ratingTable,episode,rating;
    
    public FilmController(FilmModel filmmodel,FilmView filmview, FilmDAO filmdao){
        this.filmmodel=filmmodel;
        this.filmview =filmview;
        this.filmdao=filmdao;
        
        if(filmdao.getJmldata()!=0){
            tampilkan();
        }else{
            JOptionPane.showMessageDialog(null,"Data tidak ada");
        }
        
        filmview.btnInput.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                judul = filmview.txtJudul.getText();
                tipe = filmview.txtTipe.getText();
                String episodestr = filmview.txtEpisode.getText();
                episode = Integer.parseInt(episodestr);
                String ratingstr = filmview.txtRating.getText();
                rating = Integer.parseInt(ratingstr);
                genre = filmview.txtGenre.getText();
                status = filmview.comboStatus.getSelectedItem().toString();
                if(judul.isEmpty()||tipe.isEmpty()||episodestr.isEmpty()||ratingstr.isEmpty()||genre.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Harap isi semua field");
                }else{
                    filmmodel.SetFilmModel(null,judul,tipe,genre,status,episode,rating);
                    filmdao.Insert(filmmodel);
                    tampilkan();
                    FilmView.txtJudul.setText(null);
                    FilmView.txtTipe.setText(null);
                    FilmView.txtEpisode.setText(null);
                    FilmView.txtGenre.setText(null);
                    FilmView.txtRating.setText(null);
                    FilmView.comboStatus.setSelectedItem("Selesai");
                }    
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Episode dan Rating hanya bisa diisi angka");
                }
            }
        });
        
        filmview.btnUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                judul = filmview.txtJudul.getText();
                tipe = filmview.txtTipe.getText();
                String episodestr = filmview.txtEpisode.getText();
                episode = Integer.parseInt(episodestr);
                String ratingstr = filmview.txtRating.getText();
                rating = Integer.parseInt(ratingstr);
                genre = filmview.txtGenre.getText();
                status = filmview.comboStatus.getSelectedItem().toString();
                if(judul.isEmpty()||tipe.isEmpty()||episodestr.isEmpty()||ratingstr.isEmpty()||genre.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Harap isi semua field");
                }else{
                    filmmodel.SetFilmModel(idTable,judul,tipe,genre,status,episode,rating);
                    filmdao.Update(filmmodel);
                    tampilkan();
                    FilmView.txtJudul.setText(null);
                    FilmView.txtTipe.setText(null);
                    FilmView.txtEpisode.setText(null);
                    FilmView.txtGenre.setText(null);
                    FilmView.txtRating.setText(null);
                    FilmView.comboStatus.setSelectedItem("Selesai");
                }    
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Episode dan Rating hanya bisa diisi angka");
                }
            }
        });
        
        FilmView.tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                 int baris = FilmView.tabel.rowAtPoint(evt.getPoint());
                 idTable = FilmView.tabel.getValueAt(baris,1).toString();
                 judulTable = FilmView.tabel.getValueAt(baris,2).toString();
                 tipeTable = FilmView.tabel.getValueAt(baris,3).toString();
                 episodeTable =Integer.parseInt(FilmView.tabel.getValueAt(baris,4).toString());
                 genreTable = FilmView.tabel.getValueAt(baris,5).toString();
                 statusTable = FilmView.tabel.getValueAt(baris,6).toString();
                 ratingTable = Integer.parseInt(FilmView.tabel.getValueAt(baris,7).toString());
                 
                 FilmView.txtJudul.setText(judulTable);
                 FilmView.txtTipe.setText(tipeTable);
                 String episodeTablestr = Integer.toString(episodeTable);
                 FilmView.txtEpisode.setText(episodeTablestr);
                 FilmView.txtGenre.setText(genreTable);
                 String ratingTablestr = Integer.toString(ratingTable);
                 FilmView.txtRating.setText(ratingTablestr);
                 FilmView.comboStatus.setSelectedItem(statusTable);
            }
        });
        
         FilmView.btnDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                filmmodel.SetFilmModel(idTable,null,null,null,null,0,0);
                filmdao.Delete(filmmodel);
                tampilkan();
                FilmView.txtJudul.setText(null);
                FilmView.txtTipe.setText(null);
                FilmView.txtEpisode.setText(null);
                FilmView.txtGenre.setText(null);
                FilmView.txtRating.setText(null);
                FilmView.comboStatus.setSelectedItem("Selesai");
            }
        });
         
         FilmView.btnSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String judulsearch = FilmView.txtSearch.getText();
                filmmodel.SetFilmModel(null,judulsearch,null,null,null,0,0);
                
                String dataMahasiswa[][] = filmdao.searchFilm(filmmodel);
                filmview.tabel.setModel((new JTable(dataMahasiswa,filmview.namaKolom)).getModel());
                filmview.tabel.getColumnModel().getColumn(0).setPreferredWidth(20);
                filmview.tabel.getColumnModel().getColumn(1).setPreferredWidth(30);
                filmview.tabel.getColumnModel().getColumn(2).setPreferredWidth(150);
                filmview.tabel.getColumnModel().getColumn(4).setPreferredWidth(60);
                filmview.tabel.getColumnModel().getColumn(5).setPreferredWidth(100);
                filmview.tabel.getColumnModel().getColumn(6).setPreferredWidth(100);

                
            }
        });
         FilmView.btnSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String judulsearch = FilmView.txtSearch.getText();
                filmmodel.SetFilmModel(null,judulsearch,null,null,null,0,0);
                
                String dataMahasiswa[][] = filmdao.searchFilm(filmmodel);
                filmview.tabel.setModel((new JTable(dataMahasiswa,filmview.namaKolom)).getModel());
                filmview.tabel.getColumnModel().getColumn(0).setPreferredWidth(20);
                filmview.tabel.getColumnModel().getColumn(1).setPreferredWidth(30);
                filmview.tabel.getColumnModel().getColumn(2).setPreferredWidth(150);
                filmview.tabel.getColumnModel().getColumn(4).setPreferredWidth(60);
                filmview.tabel.getColumnModel().getColumn(5).setPreferredWidth(100);
                filmview.tabel.getColumnModel().getColumn(6).setPreferredWidth(100);

                
            }
        });
         
        FilmView.btnExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                filmview.dispose();
            }
        });
         
        FilmView.btnRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               tampilkan();
               FilmView.txtJudul.setText(null);
               FilmView.txtTipe.setText(null);
               FilmView.txtEpisode.setText(null);
               FilmView.txtGenre.setText(null);
               FilmView.txtRating.setText(null);
               FilmView.comboStatus.setSelectedItem("Selesai");
               FilmView.txtSearch.setText(null);
            }
        });
    }
    
    public void tampilkan(){
            String dataMahasiswa[][] = filmdao.readFilm();
            filmview.tabel.setModel((new JTable(dataMahasiswa,filmview.namaKolom)).getModel());
            filmview.tabel.getColumnModel().getColumn(0).setPreferredWidth(20);
            filmview.tabel.getColumnModel().getColumn(1).setPreferredWidth(30);
            filmview.tabel.getColumnModel().getColumn(2).setPreferredWidth(150);
            filmview.tabel.getColumnModel().getColumn(4).setPreferredWidth(60);
            filmview.tabel.getColumnModel().getColumn(5).setPreferredWidth(100);
            filmview.tabel.getColumnModel().getColumn(6).setPreferredWidth(100);
    }
}
