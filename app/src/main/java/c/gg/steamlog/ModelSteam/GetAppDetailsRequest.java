package c.gg.steamlog.ModelSteam;

public class GetAppDetailsRequest {

    private long appid;
    private String name;
    private String developer;
    private String publisher;
    private int score_rank;
    private long positive;
    private long negative;
    private int userscore;
    private String owners;
    private int average_forever;
    private int average_2weeks;
    private int median_forever;
    private int median_2weeks;
    private String price;
    private String initialprice;
    private String discount;
    private String languages;
    private String genre;
    private int ccu;

    public GetAppDetailsRequest(long appid, String name, String developer, String publisher, int scoreRank, long positiveReview, long negativeReview, int userscore, String owners, int avgForever, int avg2Weeks, int medianForever, int median2Weeks, String price, String initialPrice, String discount, String languages, String genre, int concurrentUsers) {
        this.appid = appid;
        this.name = name;
        this.developer = developer;
        this.publisher = publisher;
        this.score_rank = scoreRank;
        this.positive = positiveReview;
        this.negative = negativeReview;
        this.userscore = userscore;
        this.owners = owners;
        this.average_forever = avgForever;
        this.average_2weeks = avg2Weeks;
        this.median_forever = medianForever;
        this.median_2weeks = median2Weeks;
        this.price = price;
        this.initialprice = initialPrice;
        this.discount = discount;
        this.languages = languages;
        this.genre = genre;
        this.ccu = concurrentUsers;
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
        return score_rank;
    }

    public void setScoreRank(int scoreRank) {
        this.score_rank = scoreRank;
    }

    public long getPositiveReview() {
        return positive;
    }

    public void setPositiveReview(long positiveReview) {
        this.positive = positiveReview;
    }

    public long getNegativeReview() {
        return negative;
    }

    public void setNegativeReview(long negativeReview) {
        this.negative = negativeReview;
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
        return average_forever;
    }

    public void setAvgForever(int avgForever) {
        this.average_forever = avgForever;
    }

    public int getAvg2Weeks() {
        return average_2weeks;
    }

    public void setAvg2Weeks(int avg2Weeks) {
        this.average_2weeks = avg2Weeks;
    }

    public int getMedianForever() {
        return median_forever;
    }

    public void setMedianForever(int medianForever) {
        this.median_forever = medianForever;
    }

    public int getMedian2Weeks() {
        return median_2weeks;
    }

    public void setMedian2Weeks(int median2Weeks) {
        this.median_2weeks = median2Weeks;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInitialPrice() {
        return initialprice;
    }

    public void setInitialPrice(String initialPrice) {
        this.initialprice = initialPrice;
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
        return ccu;
    }

    public void setConcurrentUsers(int concurrentUsers) {
        this.ccu = concurrentUsers;
    }
}
