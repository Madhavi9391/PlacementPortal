public void sendOtp(String toEmail, String otp) {

    System.out.println("OTP = " + otp);

    try {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Placement Portal OTP Verification");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

    } catch (Exception e) {
        System.out.println("Email sending failed");
        e.printStackTrace();
    }
}