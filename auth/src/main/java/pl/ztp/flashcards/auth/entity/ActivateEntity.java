package pl.ztp.flashcards.auth.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import pl.ztp.flashcards.auth.enums.ActivateStatus;
import pl.ztp.flashcards.common.Constants;

import java.time.LocalDateTime;

@Data
@Table("activate")
public class ActivateEntity {
    @Id
    @Column("activate_id")
    private Long id;

    @Column("is_activated")
    private Boolean isActivated;

    @Column("code")
    private String code;

    @Column("status")
    private ActivateStatus status;

    @Column("expired_at")
    private LocalDateTime expiredAt;

    public ActivateEntity activate() {
        setStatus(ActivateStatus.ACTIVE);
        setIsActivated(Boolean.TRUE);
        setCode(Constants.EMPTY_STRING);
        return this;
    }
}
