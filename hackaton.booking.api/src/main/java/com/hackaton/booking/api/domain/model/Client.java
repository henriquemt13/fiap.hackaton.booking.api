package com.hackaton.booking.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @SequenceGenerator(name = "add_on_seq",
            sequenceName = "add_on_seq", allocationSize = 1)
    @GeneratedValue(generator = "add_on_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "cpf should not be null")
    @NotEmpty(message = "cpf should not be empty")
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "passport")
    private String passport;
    @NotNull(message = "fullName should not be null")
    @NotEmpty(message = "fullName should not be empty")
    @Column(name = "fullName")
    private String fullName;
    @NotNull(message = "birthdayDate should not be null")
    @Column(name = "birthday_date")
    private LocalDate birthdayDate;
    @NotNull(message = "nationality should not be null")
    @NotEmpty(message = "nationality should not be empty")
    @Column(name = "nationality")
    private String nationality;
    @NotNull(message = "homeCountryAddress should not be null")
    @NotEmpty(message = "homeCountryAddress should not be empty")
    @Column(name = "home_country_address")
    private String homeCountryAddress;
    @NotNull(message = "phone should not be null")
    @NotEmpty(message = "phone should not be empty")
    @Column(name = "phone")
    private String phone;
    @NotNull(message = "email should not be null")
    @NotEmpty(message = "email should not be empty")
    @Column(name = "email", unique = true)
    private String email;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
