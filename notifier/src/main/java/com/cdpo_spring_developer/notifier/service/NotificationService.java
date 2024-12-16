package com.cdpo_spring_developer.notifier.service;


import com.cdpo_spring_developer.notifier.dto.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cdpo_spring_developer.notifier.client.UserClient;

import lombok.RequiredArgsConstructor;

// Simple Mail Transfer Protocol (smtp) — простой протокол передачи писем. Используется для рассылки писем.
// SMTP использует порт 25 или 587. А его защищенная версия SMTPS слушает порт 465.

// Post Office Protocol v3 (POP3) — почтовый протокол. Используется для чтения писем.
// POP3 при выкачивании письма на клиентский компьютер удаляет письмо с почтового сервера.
// При просмотре письма на одном клиентском компьютере это письмо уже нельзя будет посмотреть с другого устройства.
// POP3 слушает порт 110. А его защищенная версия POP3S слушает порт 995.

// Internet Message Access Protocol (IMAP) — протокол доступа к электронной почте. Альтернатива протоколу POP3.
// IMAP подгружает на клиент только мета-информацию письма, а остальные данные предоставляет по требованию.
// IMAP слушает порт 143. А его защищенная версия IMAPS слушает порт 993.

// Multipurpose Internet Mail Extensions (MIME) — многоцелевые расширения интернет-почты.
// MIME используется, для того чтобы обозначить тип передаваемого контента

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final UserClient userClient;
    private final JavaMailSender javaMailSender;

    public void sendReservationStatusNotification(ReservationNotificationDTO notificationDTO) {
        UserDto userDto = userClient.getUserDataById(notificationDTO.userId());
        String userEmail = userDto.email();
        String userName = userDto.name();

        String message = userName + ", your reservation got new status" + notificationDTO.statusType().toString() ;
        javaMailSender.send(getSimpleMessage(message,
                "Уведомление",
                new String[]{userEmail}));
    }

    public void sendExcuseDiscount(DiscountNotificationDTO notificationDTO) {
        UserDto userDto = userClient.getUserDataById(notificationDTO.userId());
        String userEmail = userDto.email();
        String userName = userDto.name();
        String message = userName + ", unfortunately, your reservation has to be cancelled. " +
                "We offer you a " + notificationDTO.discount()*100 + "discount.";
        javaMailSender.send(getSimpleMessage(message, "Отмена записи", new String[]{userEmail}));

    }

    public void sendNotification(AdvNotification advNotification) {
        String[] usersEmails = userClient.getUsersData()
                .stream()
                .map(UserDto::email)
                .toArray(String[]::new);

        javaMailSender.send(getSimpleMessage(advNotification.messageText(),
                "Рассылка",
                usersEmails));
    }

    private SimpleMailMessage getSimpleMessage(String message, String subject, String[] setTo){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("admin@yandex.ru");
        mailMessage.setTo(setTo); // кому
        mailMessage.setSubject(subject); // тема письма
        mailMessage.setText(message); // текст письма
        return mailMessage;
    }

/*    private void example(){
        // письмо с вложениями
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom("admin@yandex.ru");
        // ...
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        FileSystemResource resource = new FileSystemResource("путь к файлу");
        try {
            helper.addAttachment("filename_in_mail", resource);
        } catch (MessagingException e) {

        }
        javaMailSender.send(mimeMessage); // отправка
    }*/
}
