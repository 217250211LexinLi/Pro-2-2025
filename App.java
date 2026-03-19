public static void main(String[] args) {
        String s1 = "I was very satisfied with the service.";
        String s2 = "The e-Bike is quite comfortable to ride.";
    String s3 = "The battery life of the e-Bike is impressive.";
        String s4 = "The customer support was helpful and responsive.";
        String s5 = "I would recommend this e-Bike to my friends and family.";
Feedback feedback = new Feedback("Lexin", "Li", "2184807894@qq.com");
    feedback.analyseFeedback(false, s1, s2, s3, s4, s5);
        System.out.println(feedback);
    }
}