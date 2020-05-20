package library.bean;

public class BookDTO {
    private int seq;
    private String name;
    private String author;
    private String publisher;
    private String genre;
    private int check;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCheck() {
        return check == 0 ? "대여가능" : "대여불가";
    }

    public void setCheck(int check) {
        this.check = check;
    }

}