package com.hackaton.booking.api.builder;

import com.hackaton.booking.api.domain.dto.response.ClientResponseDTO;
import com.hackaton.booking.api.domain.dto.response.LocationResponseDTO;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.math.MathContext;
import java.math.RoundingMode;

public class HTMLBuilder {

    private HTMLBuilder() {
    }

    public static String getEmailMessage(LocationResponseDTO responseDTO, ClientResponseDTO clientResponseDTO) {
        return createTemplateEngine().process("template.html", loadHTMLVariables(responseDTO, clientResponseDTO));
    }

    private static Context loadHTMLVariables(LocationResponseDTO responseDTO, ClientResponseDTO clientResponseDTO){
        Context context = new Context();
        context.setVariable("clientName", clientResponseDTO.getFullName());
        context.setVariable("startDate", responseDTO.getBooking().getStartDate());
        context.setVariable("endDate", responseDTO.getBooking().getEndDate());
        context.setVariable("locationName", responseDTO.getName());
        context.setVariable("locationStreet", responseDTO.getStreet());
        context.setVariable("locationCep", responseDTO.getCep());
        context.setVariable("locationCity", responseDTO.getCity());
        context.setVariable("locationState", responseDTO.getState());
        context.setVariable("buildingName", responseDTO.getBuildings().get(0).getName());
        context.setVariable("roomType", responseDTO.getBuildings().get(0).getRooms().get(0).getType().name());
        context.setVariable("roomMaxCapacity", responseDTO.getBuildings().get(0).getRooms().get(0).getMaxCapacity());
        context.setVariable("roomTotalBeds", responseDTO.getBuildings().get(0).getRooms().get(0).getTotalBeds());
        context.setVariable("roomTotalDailyValue", responseDTO.getBuildings().get(0).getRooms().get(0).getTotalDailyValue().setScale(2, RoundingMode.HALF_UP).toString().replace(".", ","));
        context.setVariable("amenities", responseDTO.getAmenities());
        context.setVariable("furnitures", responseDTO.getBuildings().get(0).getRooms().get(0).getFurniture());
        context.setVariable("bathroomType", responseDTO.getBuildings().get(0).getRooms().get(0).getBathroom().getType().name());
        context.setVariable("bathroomDescription", responseDTO.getBuildings().get(0).getRooms().get(0).getBathroom().getDescription());
        context.setVariable("addOns", responseDTO.getAddOns());
        context.setVariable("totalValue", responseDTO.getBooking().getTotalValue().setScale(2, RoundingMode.HALF_UP).toString().replace(".", ","));


        return context;

    }

    @Bean
    private static SpringTemplateEngine createTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getHTMLBuilder());
        return templateEngine;
    }

    public static ClassLoaderTemplateResolver getHTMLBuilder() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        setHTMLBuilderSettings(templateResolver);
        return templateResolver;
    }

    private static void setHTMLBuilderSettings(ClassLoaderTemplateResolver templateResolver) {
        templateResolver.setPrefix("template/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setForceTemplateMode(true);
    }

}