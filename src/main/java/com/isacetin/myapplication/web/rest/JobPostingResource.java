package com.isacetin.myapplication.web.rest;

import com.isacetin.myapplication.domain.JobPosting;
import com.isacetin.myapplication.service.JobPostingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "İş İlanı", description = "İş ilanı yönetim API'si")
public class JobPostingResource {

    private final JobPostingService jobPostingService;

    public JobPostingResource(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @Operation(
        summary = "Yeni iş ilanı oluştur",
        description = "Yeni bir iş ilanı oluşturur",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "İlan başarıyla oluşturuldu",
                content = @Content(schema = @Schema(implementation = JobPosting.class))
            ),
            @ApiResponse(responseCode = "400", description = "Geçersiz istek"),
            @ApiResponse(responseCode = "401", description = "Yetkilendirme hatası"),
        }
    )
    @PostMapping("/job-postings")
    public ResponseEntity<JobPosting> createJobPosting(@Valid @RequestBody JobPosting jobPosting) {
        JobPosting result = jobPostingService.save(jobPosting);
        return ResponseEntity.created(URI.create("/api/job-postings/" + result.getId())).body(result);
    }

    @Operation(
        summary = "Tüm aktif iş ilanlarını getir",
        description = "Sistemdeki tüm aktif iş ilanlarını listeler",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "İş ilanları başarıyla getirildi",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = JobPosting.class)))
            ),
        }
    )
    @GetMapping("/job-postings")
    public List<JobPosting> getAllActiveJobPostings() {
        return jobPostingService.findAllActive();
    }

    @Operation(summary = "Kullanıcının ilanlarını getir", description = "Giriş yapmış kullanıcının kendi iş ilanlarını listeler")
    @GetMapping("/job-postings/my-postings")
    public List<JobPosting> getMyJobPostings() {
        return jobPostingService.findByCurrentUser();
    }

    @Operation(summary = "İş ilanı detayı getir", description = "Belirtilen ID'ye sahip iş ilanının detaylarını getirir")
    @GetMapping("/job-postings/{id}")
    public ResponseEntity<JobPosting> getJobPosting(@Parameter(description = "İş ilanı ID'si") @PathVariable Long id) {
        JobPosting jobPosting = jobPostingService.findOne(id);
        return ResponseEntity.ok(jobPosting);
    }
}
