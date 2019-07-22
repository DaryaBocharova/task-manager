package ru.bocharova.tm.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bocharova.tm.DTO.ProjectDTO;
import ru.bocharova.tm.api.repository.IUserRepository;
import ru.bocharova.tm.api.service.IProjectService;
import ru.bocharova.tm.entity.User;
import ru.bocharova.tm.api.repository.IProjectRepository;
import ru.bocharova.tm.entity.Project;
import ru.bocharova.tm.exception.DataValidateException;
import ru.bocharova.tm.repository.ProjectRepository;
import ru.bocharova.tm.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public final class ProjectService implements IProjectService {

    @NotNull
    final EntityManagerFactory entityManagerFactory;

    @Override
    public void create(
            @Nullable final ProjectDTO projectDTO)
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @NotNull final Project project = convertDTOtoProject(projectDTO, entityManager);
            projectRepository
                    .persist(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void edit(
            @Nullable final ProjectDTO projectDTO) {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository.findOne(projectDTO.getId());
            if (project == null) throw new DataValidateException("Project not found!");
            project.setName(projectDTO.getName());
            project.setDescription(projectDTO.getDescription());
            project.setStatus(projectDTO.getStatus());
            project.setDateBegin(projectDTO.getDateBegin());
            project.setDateEnd(projectDTO.getDateEnd());
            projectRepository
                    .merge(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ProjectDTO findOne(
            @Nullable final String id,
            @Nullable final String userId)
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository
                    .findOneByUserId(id, getUser(userId, entityManager));
            if (project == null) throw new DataValidateException("Project not found!");
            entityManager.getTransaction().commit();
            return project.getDTO();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void remove(
            @Nullable final String id,
            @Nullable final String userId)
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository
                    .findOneByUserId(id, getUser(userId, entityManager));
            if (project == null) throw new DataValidateException("Project not found!");
            projectRepository
                    .remove(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void clear()
            throws DataValidateException {
        @Nullable final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @Nullable final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository
                    .removeAll();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ProjectDTO findOne(
            @Nullable final String id)
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository
                    .findOne(id);
            if (project == null) throw new DataValidateException("Project not found!");
            entityManager.getTransaction().commit();
            return project.getDTO();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ProjectDTO remove(
            @Nullable final String id)
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Project project = projectRepository
                    .findOne(id);
            if (project == null) throw new DataValidateException("Project not found!");
            projectRepository
                    .remove(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public Collection<ProjectDTO> findAll()
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Collection<Project> projects = projectRepository
                    .findAll();
            if (projects == null) throw new DataValidateException("Projects not found!");
            entityManager.getTransaction().commit();
            return projects
                    .stream()
                    .map(Project::getDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<ProjectDTO> findAllByUserId(
            @Nullable final String id)
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Collection<Project> projects = projectRepository
                    .findAllByUserId(getUser(id, entityManager));
            if (projects == null) throw new DataValidateException("Projects not found!");
            entityManager.getTransaction().commit();
            return projects
                    .stream()
                    .map(Project::getDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeAllByUserId(
            @Nullable final String id)
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository
                    .removeAllByUserID(getUser(id, entityManager));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<ProjectDTO> sortAllByUserId(
            @Nullable final String id,
            @Nullable final String parameter)
            throws DataValidateException {
        if ("order".equals(parameter)) return findAllByUserId(id);
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Collection<Project> projects = projectRepository
                    .sortAllByUserId(getUser(id, entityManager), parameter);
            if (projects == null) throw new DataValidateException("Projects not found!");
            entityManager.getTransaction().commit();
            return projects
                    .stream()
                    .map(Project::getDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<ProjectDTO> findAllByPartOfNameOrDescription(
            @Nullable final String name,
            @Nullable final String description,
            @Nullable final String userId)
            throws DataValidateException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            @Nullable final Collection<Project> projects = projectRepository
                    .findAllByPartOfNameOrDescription(name, description, getUser(userId, entityManager));
            if (projects == null) throw new DataValidateException("Projects not found!");
            entityManager.getTransaction().commit();
            return projects
                    .stream()
                    .map(Project::getDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DataValidateException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    private Project convertDTOtoProject(
            @NotNull final ProjectDTO projectDTO,
            @NotNull final EntityManager entityManager)
            throws DataValidateException {
        @NotNull final Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setDateBegin(projectDTO.getDateBegin());
        project.setDateEnd(projectDTO.getDateEnd());
        project.setUser(getUser(projectDTO.getUserId(), entityManager));
      project.setStatus(projectDTO.getStatus());
        return project;
    }

    private User getUser(@NotNull final String userId, @NotNull final EntityManager em) throws DataValidateException {
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new DataValidateException("User not found!");
        return user;
    }
}