package com.cecd.help.document.domain.entity;

import com.cecd.help.workspace.domain.entity.Workspace;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "director_file")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "file_name")
    private String fileName;

    @Column(nullable = false, name = "file_url")
    private String fileUrl;

    @Column(nullable = false, name = "create_at")
    private LocalDate createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "directory_id")
    private Directory directory;




    @Builder
    public Store(String fileName, String fileUrl, Directory directory) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.directory = directory;
        this.createAt = LocalDate.now();
    }

    public void updateFileName(String name) {
        this.fileName = name;
    }
}
