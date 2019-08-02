package ru.bocharova.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bocharova.tm.model.dto.ProjectDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity
@Cacheable
@NoArgsConstructor
@Table(name = "app_project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project extends BaseEntity implements Serializable {

    @Nullable
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public ProjectDTO getDTO() {
        @NotNull final ProjectDTO dto = new ProjectDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setDateBegin(dateBegin);
        dto.setDateEnd(dateEnd);
        dto.setStatus(status);
        dto.setUserId(user.getId());
        return dto;
    }
}
