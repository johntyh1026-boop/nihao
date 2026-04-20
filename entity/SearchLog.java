package entity;

public class SearchLog {
    private int id;
    private String keyword;
    private String searchTime;
    private String note;

    public SearchLog(int id, String keyword, String searchTime, String note) {
        this.id = id;
        this.keyword = keyword;
        this.searchTime = searchTime;
        this.note = note;
    }

    public int getId() { return id; }
    public String getKeyword() { return keyword; }
    public String getSearchTime() { return searchTime; }
    public String getNote() { return note; }
}