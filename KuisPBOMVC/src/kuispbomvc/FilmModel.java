package kuispbomvc;

public class FilmModel {
    private String judul,tipe,genre,status,id;
    private int episode,rating;
    
    public void SetFilmModel(String id,String judul,String tipe,String genre,String status,int episode,int rating){
        this.judul = judul;
        this.tipe = tipe;
        this.genre = genre;
        this.status = status;
        this.episode = episode;
        this.rating = rating;
        this.id = id;
    }
///////////////////SETTER GETTER///////////////////
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getTipe() {
        return tipe;
    }
    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getEpisode() {
        return episode;
    }
    public void setEpisode(int episode) {
        this.episode = episode;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    
}
