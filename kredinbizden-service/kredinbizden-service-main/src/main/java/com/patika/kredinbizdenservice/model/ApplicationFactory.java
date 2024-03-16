package com.patika.kredinbizdenservice.model;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ApplicationFactory {

    private static final List<Application> applications = new ArrayList<>();

    public static Application createLoanApplication(Loan loan, User user, LocalDateTime localDateTime) {
        Application application = new Application(loan, user, localDateTime);
        applications.add(application);
        return application;
    }

    public static Application createProductApplication(Product product, User user, LocalDateTime localDateTime) {
        Application application = new Application(product, user, localDateTime);
        applications.add(application);
        return application;
    }


    public static List<Application> getAllApplications() {
        return applications;
    }

    public void findApplicationWithHighestLoanAmount() {  //En çok başvuru yapan kullanıcıyı bulan method
        Optional<Application> highestLoan= applications.stream()
                .filter(app -> app.getLoan() != null && app.getLoan().getAmount() != null)
                .max((app1, app2) -> app1.getLoan().getAmount().compareTo(app2.getLoan().getAmount()));
        if (highestLoan.isPresent()) {
            Application application = highestLoan.get();
                   System.out.println("En yüksek kredi isteyen kullanıcı: " + application.getUser().getName());
                   System.out.println("İstenen tutar: " + application.getLoan().getAmount());
        } else {
            System.out.println("Uygun bir başvuru bulunamadı.");
        }

    }
    public List<Application> listApplicationsFromLastMonth() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minus(Period.ofMonths(1)); // Şu andan bir ay öncesinin tarihini hesapla

        return applications.stream()
                .filter(app -> app.getLocalDateTime() != null && app.getLocalDateTime().isAfter(oneMonthAgo)) // Son bir ay içinde yapılan başvuruları filtrele
                .collect(Collectors.toList()); // Sonuçları bir liste olarak topla
    }

}