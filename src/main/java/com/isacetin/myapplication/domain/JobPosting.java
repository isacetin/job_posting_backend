package com.isacetin.myapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isacetin.myapplication.domain.enumeration.JobStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "job_posting")
@Schema(description = "İş İlanı entity")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Benzersiz ID")
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "title", length = 100, nullable = false)
    @Schema(description = "İş ilanı başlığı", required = true)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false)
    @Schema(description = "İş tanımı", required = true)
    private String description;

    @Column(name = "requirements")
    @Schema(description = "İş gereksinimleri")
    private String requirements;

    @NotNull
    @Column(name = "company_name", nullable = false)
    @Schema(description = "Şirket adı", required = true)
    private String companyName;

    @Column(name = "location")
    @Schema(description = "İş lokasyonu")
    private String location;

    @Column(name = "salary_range")
    @Schema(description = "Maaş aralığı")
    private String salaryRange;

    @Column(name = "created_date", nullable = false)
    @Schema(description = "Oluşturulma tarihi")
    private Instant createdDate;

    @Column(name = "expiry_date")
    @Schema(description = "Son başvuru tarihi")
    private Instant expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "password", "email" })
    @Schema(description = "İlanı oluşturan kullanıcı")
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Schema(description = "İlan durumu")
    private JobStatus status = JobStatus.ACTIVE;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}
