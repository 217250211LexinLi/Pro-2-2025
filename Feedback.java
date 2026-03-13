public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;
public Feedback(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
        this.email = email;
    }
public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, String sent3, String sent4, String sent5) {
if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            StringBuilder sb = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
            completeFeedback = sb.toString();
        }
longFeedback = checkFeedbackLength(completeFeedback);
        reviewID = createReviewID(firstName, lastName, completeFeedback);
    }
private String feedbackUsingConcatenation(String s1, String s2, String s3, String s4, String s5) {
        String concatenatedFeedback = s1 + s2 + s3 + s4 + s5;
        return concatenatedFeedback;
    }
private StringBuilder feedbackUsingStringBuilder(String s1, String s2, String s3, String s4, String s5) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        sb.append(s3);
        sb.append(s4);
        sb.append(s5);
        return sb.toString();
    }
private boolean checkFeedbackLength(String feedback) {
        return feedback.length() > 500;
    }
private String createReviewID(String firstName, String lastName, String completeFeedback) {
        String namePart = (firstName + lastName).substring(2, 6).toUpperCase();
        String feedbackPart = completeFeedback.substring(10, 15).toLowerCase();
        int len = completeFeedback.length();
        String lengthPart = len + "_";
        String timestamp = "" + System.currentTimeMillis();
        String reviewId = namePart + feedbackPart + lengthPart + timestamp;
        reviewId = reviewId.replace("_", "");
        return reviewId;
    }
public String toString() {
        return "Feedback{" +"firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' +", email='" + email + '\'' +", completeFeedback='" + completeFeedback + '\'' +", longFeedback=" + longFeedback +", reviewID='" + reviewID + '\'' +'}';
    }
