package c.gg.steamlog.ModelSteam;

//STEAMSPY

public class GetAppDetailsRequest {

    private long appid;
    private String name;
    private String developer;
    private String publisher;
    private int scoreRank;
    private long positiveReview;
    private long negativeReview;
    private int userscore;
    private String owners;
    private int avgForever;
    private int avg2Weeks;
    private int medianForever;
    private int median2Weeks;
    private String price;
    private String initialPrice;
    private String discount;
    private String languages;
    private String genre;
    private int concurrentUsers;

    public GetAppDetailsRequest(long appid, String name, String developer, String publisher, int scoreRank, long positiveReview, long negativeReview, int userscore, String owners, int avgForever, int avg2Weeks, int medianForever, int median2Weeks, String price, String initialPrice, String discount, String languages, String genre, int concurrentUsers) {
        this.appid = appid;
        this.name = name;
        this.developer = developer;
        this.publisher = publisher;
        this.scoreRank = scoreRank;
        this.positiveReview = positiveReview;
        this.negativeReview = negativeReview;
        this.userscore = userscore;
        this.owners = owners;
        this.avgForever = avgForever;
        this.avg2Weeks = avg2Weeks;
        this.medianForever = medianForever;
        this.median2Weeks = median2Weeks;
        this.price = price;
        this.initialPrice = initialPrice;
        this.discount = discount;
        this.languages = languages;
        this.genre = genre;
        this.concurrentUsers = concurrentUsers;
    }

    public long getAppid() {
        return appid;
    }

    public void setAppid(long appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getScoreRank() {
        return scoreRank;
    }

    public void setScoreRank(int scoreRank) {
        this.scoreRank = scoreRank;
    }

    public long getPositiveReview() {
        return positiveReview;
    }

    public void setPositiveReview(long positiveReview) {
        this.positiveReview = positiveReview;
    }

    public long getNegativeReview() {
        return negativeReview;
    }

    public void setNegativeReview(long negativeReview) {
        this.negativeReview = negativeReview;
    }

    public int getUserscore() {
        return userscore;
    }

    public void setUserscore(int userscore) {
        this.userscore = userscore;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public int getAvgForever() {
        return avgForever;
    }

    public void setAvgForever(int avgForever) {
        this.avgForever = avgForever;
    }

    public int getAvg2Weeks() {
        return avg2Weeks;
    }

    public void setAvg2Weeks(int avg2Weeks) {
        this.avg2Weeks = avg2Weeks;
    }

    public int getMedianForever() {
        return medianForever;
    }

    public void setMedianForever(int medianForever) {
        this.medianForever = medianForever;
    }

    public int getMedian2Weeks() {
        return median2Weeks;
    }

    public void setMedian2Weeks(int median2Weeks) {
        this.median2Weeks = median2Weeks;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(String initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getConcurrentUsers() {
        return concurrentUsers;
    }

    public void setConcurrentUsers(int concurrentUsers) {
        this.concurrentUsers = concurrentUsers;
    }
}
