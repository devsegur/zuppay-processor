package com.zup.processor.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class BaseEntity implements Serializable {

  @CreatedDate private LocalDateTime createdDate;
  @LastModifiedDate private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;
}
