package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DashboardDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Dashboard}.
 */
public interface DashboardService {
    /**
     * Save a dashboard.
     *
     * @param dashboardDTO the entity to save.
     * @return the persisted entity.
     */
    DashboardDTO save(DashboardDTO dashboardDTO);

    /**
     * Updates a dashboard.
     *
     * @param dashboardDTO the entity to update.
     * @return the persisted entity.
     */
    DashboardDTO update(DashboardDTO dashboardDTO);

    /**
     * Partially updates a dashboard.
     *
     * @param dashboardDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DashboardDTO> partialUpdate(DashboardDTO dashboardDTO);

    /**
     * Get all the dashboards.
     *
     * @return the list of entities.
     */
    List<DashboardDTO> findAll();

    /**
     * Get the "id" dashboard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DashboardDTO> findOne(String id);

    /**
     * Delete the "id" dashboard.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
