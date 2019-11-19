package com.metalheart.model.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class RunningListArchivePK implements Serializable {

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "week", nullable = false)
    private Integer week;
}
