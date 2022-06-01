package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.DashboardLayout;
import com.mycompany.myapp.repository.DashboardLayoutRepository;
import com.mycompany.myapp.service.dto.DashboardLayoutDTO;
import com.mycompany.myapp.service.mapper.DashboardLayoutMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link DashboardLayoutResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DashboardLayoutResourceIT {

    private static final Integer DEFAULT_MARGIN = 1;
    private static final Integer UPDATED_MARGIN = 2;

    private static final Boolean DEFAULT_OUTER_MARGIN = false;
    private static final Boolean UPDATED_OUTER_MARGIN = true;

    private static final Integer DEFAULT_OUTER_MARGIN_TOP = 1;
    private static final Integer UPDATED_OUTER_MARGIN_TOP = 2;

    private static final Boolean DEFAULT_USE_TRANSFORM_POSITIONING = false;
    private static final Boolean UPDATED_USE_TRANSFORM_POSITIONING = true;

    private static final Integer DEFAULT_MOBILE_BREAKPOINT = 1;
    private static final Integer UPDATED_MOBILE_BREAKPOINT = 2;

    private static final Boolean DEFAULT_USE_BODY_FOR_BREAKPOINT = false;
    private static final Boolean UPDATED_USE_BODY_FOR_BREAKPOINT = true;

    private static final Integer DEFAULT_MIN_COLS = 1;
    private static final Integer UPDATED_MIN_COLS = 2;

    private static final Integer DEFAULT_MAX_COLS = 1;
    private static final Integer UPDATED_MAX_COLS = 2;

    private static final Integer DEFAULT_MIN_ROWS = 1;
    private static final Integer UPDATED_MIN_ROWS = 2;

    private static final Integer DEFAULT_MAX_ROWS = 1;
    private static final Integer UPDATED_MAX_ROWS = 2;

    private static final Integer DEFAULT_MAX_ITEM_COLS = 1;
    private static final Integer UPDATED_MAX_ITEM_COLS = 2;

    private static final Integer DEFAULT_MIN_ITEM_COLS = 1;
    private static final Integer UPDATED_MIN_ITEM_COLS = 2;

    private static final Integer DEFAULT_MAX_ITEM_ROWS = 1;
    private static final Integer UPDATED_MAX_ITEM_ROWS = 2;

    private static final Integer DEFAULT_MIN_ITEM_ROWS = 1;
    private static final Integer UPDATED_MIN_ITEM_ROWS = 2;

    private static final Integer DEFAULT_MAX_ITEM_AREA = 1;
    private static final Integer UPDATED_MAX_ITEM_AREA = 2;

    private static final Integer DEFAULT_MIN_ITEM_AREA = 1;
    private static final Integer UPDATED_MIN_ITEM_AREA = 2;

    private static final Integer DEFAULT_DEFAULT_ITEM_COLS = 1;
    private static final Integer UPDATED_DEFAULT_ITEM_COLS = 2;

    private static final Integer DEFAULT_DEFAULT_ITEM_ROWS = 1;
    private static final Integer UPDATED_DEFAULT_ITEM_ROWS = 2;

    private static final Boolean DEFAULT_IGNORE_MARGIN_IN_ROW = false;
    private static final Boolean UPDATED_IGNORE_MARGIN_IN_ROW = true;

    private static final Boolean DEFAULT_DRAGGABLE = false;
    private static final Boolean UPDATED_DRAGGABLE = true;

    private static final Boolean DEFAULT_RESIZABLE = false;
    private static final Boolean UPDATED_RESIZABLE = true;

    private static final String ENTITY_API_URL = "/api/dashboard-layouts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DashboardLayoutRepository dashboardLayoutRepository;

    @Autowired
    private DashboardLayoutMapper dashboardLayoutMapper;

    @Autowired
    private MockMvc restDashboardLayoutMockMvc;

    private DashboardLayout dashboardLayout;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DashboardLayout createEntity() {
        DashboardLayout dashboardLayout = new DashboardLayout()
            .margin(DEFAULT_MARGIN)
            .outerMargin(DEFAULT_OUTER_MARGIN)
            .outerMarginTop(DEFAULT_OUTER_MARGIN_TOP)
            .useTransformPositioning(DEFAULT_USE_TRANSFORM_POSITIONING)
            .mobileBreakpoint(DEFAULT_MOBILE_BREAKPOINT)
            .useBodyForBreakpoint(DEFAULT_USE_BODY_FOR_BREAKPOINT)
            .minCols(DEFAULT_MIN_COLS)
            .maxCols(DEFAULT_MAX_COLS)
            .minRows(DEFAULT_MIN_ROWS)
            .maxRows(DEFAULT_MAX_ROWS)
            .maxItemCols(DEFAULT_MAX_ITEM_COLS)
            .minItemCols(DEFAULT_MIN_ITEM_COLS)
            .maxItemRows(DEFAULT_MAX_ITEM_ROWS)
            .minItemRows(DEFAULT_MIN_ITEM_ROWS)
            .maxItemArea(DEFAULT_MAX_ITEM_AREA)
            .minItemArea(DEFAULT_MIN_ITEM_AREA)
            .defaultItemCols(DEFAULT_DEFAULT_ITEM_COLS)
            .defaultItemRows(DEFAULT_DEFAULT_ITEM_ROWS)
            .ignoreMarginInRow(DEFAULT_IGNORE_MARGIN_IN_ROW)
            .draggable(DEFAULT_DRAGGABLE)
            .resizable(DEFAULT_RESIZABLE);
        return dashboardLayout;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DashboardLayout createUpdatedEntity() {
        DashboardLayout dashboardLayout = new DashboardLayout()
            .margin(UPDATED_MARGIN)
            .outerMargin(UPDATED_OUTER_MARGIN)
            .outerMarginTop(UPDATED_OUTER_MARGIN_TOP)
            .useTransformPositioning(UPDATED_USE_TRANSFORM_POSITIONING)
            .mobileBreakpoint(UPDATED_MOBILE_BREAKPOINT)
            .useBodyForBreakpoint(UPDATED_USE_BODY_FOR_BREAKPOINT)
            .minCols(UPDATED_MIN_COLS)
            .maxCols(UPDATED_MAX_COLS)
            .minRows(UPDATED_MIN_ROWS)
            .maxRows(UPDATED_MAX_ROWS)
            .maxItemCols(UPDATED_MAX_ITEM_COLS)
            .minItemCols(UPDATED_MIN_ITEM_COLS)
            .maxItemRows(UPDATED_MAX_ITEM_ROWS)
            .minItemRows(UPDATED_MIN_ITEM_ROWS)
            .maxItemArea(UPDATED_MAX_ITEM_AREA)
            .minItemArea(UPDATED_MIN_ITEM_AREA)
            .defaultItemCols(UPDATED_DEFAULT_ITEM_COLS)
            .defaultItemRows(UPDATED_DEFAULT_ITEM_ROWS)
            .ignoreMarginInRow(UPDATED_IGNORE_MARGIN_IN_ROW)
            .draggable(UPDATED_DRAGGABLE)
            .resizable(UPDATED_RESIZABLE);
        return dashboardLayout;
    }

    @BeforeEach
    public void initTest() {
        dashboardLayoutRepository.deleteAll();
        dashboardLayout = createEntity();
    }

    @Test
    void createDashboardLayout() throws Exception {
        int databaseSizeBeforeCreate = dashboardLayoutRepository.findAll().size();
        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);
        restDashboardLayoutMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeCreate + 1);
        DashboardLayout testDashboardLayout = dashboardLayoutList.get(dashboardLayoutList.size() - 1);
        assertThat(testDashboardLayout.getMargin()).isEqualTo(DEFAULT_MARGIN);
        assertThat(testDashboardLayout.getOuterMargin()).isEqualTo(DEFAULT_OUTER_MARGIN);
        assertThat(testDashboardLayout.getOuterMarginTop()).isEqualTo(DEFAULT_OUTER_MARGIN_TOP);
        assertThat(testDashboardLayout.getUseTransformPositioning()).isEqualTo(DEFAULT_USE_TRANSFORM_POSITIONING);
        assertThat(testDashboardLayout.getMobileBreakpoint()).isEqualTo(DEFAULT_MOBILE_BREAKPOINT);
        assertThat(testDashboardLayout.getUseBodyForBreakpoint()).isEqualTo(DEFAULT_USE_BODY_FOR_BREAKPOINT);
        assertThat(testDashboardLayout.getMinCols()).isEqualTo(DEFAULT_MIN_COLS);
        assertThat(testDashboardLayout.getMaxCols()).isEqualTo(DEFAULT_MAX_COLS);
        assertThat(testDashboardLayout.getMinRows()).isEqualTo(DEFAULT_MIN_ROWS);
        assertThat(testDashboardLayout.getMaxRows()).isEqualTo(DEFAULT_MAX_ROWS);
        assertThat(testDashboardLayout.getMaxItemCols()).isEqualTo(DEFAULT_MAX_ITEM_COLS);
        assertThat(testDashboardLayout.getMinItemCols()).isEqualTo(DEFAULT_MIN_ITEM_COLS);
        assertThat(testDashboardLayout.getMaxItemRows()).isEqualTo(DEFAULT_MAX_ITEM_ROWS);
        assertThat(testDashboardLayout.getMinItemRows()).isEqualTo(DEFAULT_MIN_ITEM_ROWS);
        assertThat(testDashboardLayout.getMaxItemArea()).isEqualTo(DEFAULT_MAX_ITEM_AREA);
        assertThat(testDashboardLayout.getMinItemArea()).isEqualTo(DEFAULT_MIN_ITEM_AREA);
        assertThat(testDashboardLayout.getDefaultItemCols()).isEqualTo(DEFAULT_DEFAULT_ITEM_COLS);
        assertThat(testDashboardLayout.getDefaultItemRows()).isEqualTo(DEFAULT_DEFAULT_ITEM_ROWS);
        assertThat(testDashboardLayout.getIgnoreMarginInRow()).isEqualTo(DEFAULT_IGNORE_MARGIN_IN_ROW);
        assertThat(testDashboardLayout.getDraggable()).isEqualTo(DEFAULT_DRAGGABLE);
        assertThat(testDashboardLayout.getResizable()).isEqualTo(DEFAULT_RESIZABLE);
    }

    @Test
    void createDashboardLayoutWithExistingId() throws Exception {
        // Create the DashboardLayout with an existing ID
        dashboardLayout.setId("existing_id");
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        int databaseSizeBeforeCreate = dashboardLayoutRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDashboardLayoutMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDashboardLayouts() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        // Get all the dashboardLayoutList
        restDashboardLayoutMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dashboardLayout.getId())))
            .andExpect(jsonPath("$.[*].margin").value(hasItem(DEFAULT_MARGIN)))
            .andExpect(jsonPath("$.[*].outerMargin").value(hasItem(DEFAULT_OUTER_MARGIN.booleanValue())))
            .andExpect(jsonPath("$.[*].outerMarginTop").value(hasItem(DEFAULT_OUTER_MARGIN_TOP)))
            .andExpect(jsonPath("$.[*].useTransformPositioning").value(hasItem(DEFAULT_USE_TRANSFORM_POSITIONING.booleanValue())))
            .andExpect(jsonPath("$.[*].mobileBreakpoint").value(hasItem(DEFAULT_MOBILE_BREAKPOINT)))
            .andExpect(jsonPath("$.[*].useBodyForBreakpoint").value(hasItem(DEFAULT_USE_BODY_FOR_BREAKPOINT.booleanValue())))
            .andExpect(jsonPath("$.[*].minCols").value(hasItem(DEFAULT_MIN_COLS)))
            .andExpect(jsonPath("$.[*].maxCols").value(hasItem(DEFAULT_MAX_COLS)))
            .andExpect(jsonPath("$.[*].minRows").value(hasItem(DEFAULT_MIN_ROWS)))
            .andExpect(jsonPath("$.[*].maxRows").value(hasItem(DEFAULT_MAX_ROWS)))
            .andExpect(jsonPath("$.[*].maxItemCols").value(hasItem(DEFAULT_MAX_ITEM_COLS)))
            .andExpect(jsonPath("$.[*].minItemCols").value(hasItem(DEFAULT_MIN_ITEM_COLS)))
            .andExpect(jsonPath("$.[*].maxItemRows").value(hasItem(DEFAULT_MAX_ITEM_ROWS)))
            .andExpect(jsonPath("$.[*].minItemRows").value(hasItem(DEFAULT_MIN_ITEM_ROWS)))
            .andExpect(jsonPath("$.[*].maxItemArea").value(hasItem(DEFAULT_MAX_ITEM_AREA)))
            .andExpect(jsonPath("$.[*].minItemArea").value(hasItem(DEFAULT_MIN_ITEM_AREA)))
            .andExpect(jsonPath("$.[*].defaultItemCols").value(hasItem(DEFAULT_DEFAULT_ITEM_COLS)))
            .andExpect(jsonPath("$.[*].defaultItemRows").value(hasItem(DEFAULT_DEFAULT_ITEM_ROWS)))
            .andExpect(jsonPath("$.[*].ignoreMarginInRow").value(hasItem(DEFAULT_IGNORE_MARGIN_IN_ROW.booleanValue())))
            .andExpect(jsonPath("$.[*].draggable").value(hasItem(DEFAULT_DRAGGABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].resizable").value(hasItem(DEFAULT_RESIZABLE.booleanValue())));
    }

    @Test
    void getDashboardLayout() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        // Get the dashboardLayout
        restDashboardLayoutMockMvc
            .perform(get(ENTITY_API_URL_ID, dashboardLayout.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dashboardLayout.getId()))
            .andExpect(jsonPath("$.margin").value(DEFAULT_MARGIN))
            .andExpect(jsonPath("$.outerMargin").value(DEFAULT_OUTER_MARGIN.booleanValue()))
            .andExpect(jsonPath("$.outerMarginTop").value(DEFAULT_OUTER_MARGIN_TOP))
            .andExpect(jsonPath("$.useTransformPositioning").value(DEFAULT_USE_TRANSFORM_POSITIONING.booleanValue()))
            .andExpect(jsonPath("$.mobileBreakpoint").value(DEFAULT_MOBILE_BREAKPOINT))
            .andExpect(jsonPath("$.useBodyForBreakpoint").value(DEFAULT_USE_BODY_FOR_BREAKPOINT.booleanValue()))
            .andExpect(jsonPath("$.minCols").value(DEFAULT_MIN_COLS))
            .andExpect(jsonPath("$.maxCols").value(DEFAULT_MAX_COLS))
            .andExpect(jsonPath("$.minRows").value(DEFAULT_MIN_ROWS))
            .andExpect(jsonPath("$.maxRows").value(DEFAULT_MAX_ROWS))
            .andExpect(jsonPath("$.maxItemCols").value(DEFAULT_MAX_ITEM_COLS))
            .andExpect(jsonPath("$.minItemCols").value(DEFAULT_MIN_ITEM_COLS))
            .andExpect(jsonPath("$.maxItemRows").value(DEFAULT_MAX_ITEM_ROWS))
            .andExpect(jsonPath("$.minItemRows").value(DEFAULT_MIN_ITEM_ROWS))
            .andExpect(jsonPath("$.maxItemArea").value(DEFAULT_MAX_ITEM_AREA))
            .andExpect(jsonPath("$.minItemArea").value(DEFAULT_MIN_ITEM_AREA))
            .andExpect(jsonPath("$.defaultItemCols").value(DEFAULT_DEFAULT_ITEM_COLS))
            .andExpect(jsonPath("$.defaultItemRows").value(DEFAULT_DEFAULT_ITEM_ROWS))
            .andExpect(jsonPath("$.ignoreMarginInRow").value(DEFAULT_IGNORE_MARGIN_IN_ROW.booleanValue()))
            .andExpect(jsonPath("$.draggable").value(DEFAULT_DRAGGABLE.booleanValue()))
            .andExpect(jsonPath("$.resizable").value(DEFAULT_RESIZABLE.booleanValue()));
    }

    @Test
    void getNonExistingDashboardLayout() throws Exception {
        // Get the dashboardLayout
        restDashboardLayoutMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewDashboardLayout() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();

        // Update the dashboardLayout
        DashboardLayout updatedDashboardLayout = dashboardLayoutRepository.findById(dashboardLayout.getId()).get();
        updatedDashboardLayout
            .margin(UPDATED_MARGIN)
            .outerMargin(UPDATED_OUTER_MARGIN)
            .outerMarginTop(UPDATED_OUTER_MARGIN_TOP)
            .useTransformPositioning(UPDATED_USE_TRANSFORM_POSITIONING)
            .mobileBreakpoint(UPDATED_MOBILE_BREAKPOINT)
            .useBodyForBreakpoint(UPDATED_USE_BODY_FOR_BREAKPOINT)
            .minCols(UPDATED_MIN_COLS)
            .maxCols(UPDATED_MAX_COLS)
            .minRows(UPDATED_MIN_ROWS)
            .maxRows(UPDATED_MAX_ROWS)
            .maxItemCols(UPDATED_MAX_ITEM_COLS)
            .minItemCols(UPDATED_MIN_ITEM_COLS)
            .maxItemRows(UPDATED_MAX_ITEM_ROWS)
            .minItemRows(UPDATED_MIN_ITEM_ROWS)
            .maxItemArea(UPDATED_MAX_ITEM_AREA)
            .minItemArea(UPDATED_MIN_ITEM_AREA)
            .defaultItemCols(UPDATED_DEFAULT_ITEM_COLS)
            .defaultItemRows(UPDATED_DEFAULT_ITEM_ROWS)
            .ignoreMarginInRow(UPDATED_IGNORE_MARGIN_IN_ROW)
            .draggable(UPDATED_DRAGGABLE)
            .resizable(UPDATED_RESIZABLE);
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(updatedDashboardLayout);

        restDashboardLayoutMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dashboardLayoutDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isOk());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
        DashboardLayout testDashboardLayout = dashboardLayoutList.get(dashboardLayoutList.size() - 1);
        assertThat(testDashboardLayout.getMargin()).isEqualTo(UPDATED_MARGIN);
        assertThat(testDashboardLayout.getOuterMargin()).isEqualTo(UPDATED_OUTER_MARGIN);
        assertThat(testDashboardLayout.getOuterMarginTop()).isEqualTo(UPDATED_OUTER_MARGIN_TOP);
        assertThat(testDashboardLayout.getUseTransformPositioning()).isEqualTo(UPDATED_USE_TRANSFORM_POSITIONING);
        assertThat(testDashboardLayout.getMobileBreakpoint()).isEqualTo(UPDATED_MOBILE_BREAKPOINT);
        assertThat(testDashboardLayout.getUseBodyForBreakpoint()).isEqualTo(UPDATED_USE_BODY_FOR_BREAKPOINT);
        assertThat(testDashboardLayout.getMinCols()).isEqualTo(UPDATED_MIN_COLS);
        assertThat(testDashboardLayout.getMaxCols()).isEqualTo(UPDATED_MAX_COLS);
        assertThat(testDashboardLayout.getMinRows()).isEqualTo(UPDATED_MIN_ROWS);
        assertThat(testDashboardLayout.getMaxRows()).isEqualTo(UPDATED_MAX_ROWS);
        assertThat(testDashboardLayout.getMaxItemCols()).isEqualTo(UPDATED_MAX_ITEM_COLS);
        assertThat(testDashboardLayout.getMinItemCols()).isEqualTo(UPDATED_MIN_ITEM_COLS);
        assertThat(testDashboardLayout.getMaxItemRows()).isEqualTo(UPDATED_MAX_ITEM_ROWS);
        assertThat(testDashboardLayout.getMinItemRows()).isEqualTo(UPDATED_MIN_ITEM_ROWS);
        assertThat(testDashboardLayout.getMaxItemArea()).isEqualTo(UPDATED_MAX_ITEM_AREA);
        assertThat(testDashboardLayout.getMinItemArea()).isEqualTo(UPDATED_MIN_ITEM_AREA);
        assertThat(testDashboardLayout.getDefaultItemCols()).isEqualTo(UPDATED_DEFAULT_ITEM_COLS);
        assertThat(testDashboardLayout.getDefaultItemRows()).isEqualTo(UPDATED_DEFAULT_ITEM_ROWS);
        assertThat(testDashboardLayout.getIgnoreMarginInRow()).isEqualTo(UPDATED_IGNORE_MARGIN_IN_ROW);
        assertThat(testDashboardLayout.getDraggable()).isEqualTo(UPDATED_DRAGGABLE);
        assertThat(testDashboardLayout.getResizable()).isEqualTo(UPDATED_RESIZABLE);
    }

    @Test
    void putNonExistingDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dashboardLayoutDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDashboardLayoutWithPatch() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();

        // Update the dashboardLayout using partial update
        DashboardLayout partialUpdatedDashboardLayout = new DashboardLayout();
        partialUpdatedDashboardLayout.setId(dashboardLayout.getId());

        partialUpdatedDashboardLayout
            .margin(UPDATED_MARGIN)
            .outerMargin(UPDATED_OUTER_MARGIN)
            .mobileBreakpoint(UPDATED_MOBILE_BREAKPOINT)
            .maxCols(UPDATED_MAX_COLS)
            .maxRows(UPDATED_MAX_ROWS)
            .minItemCols(UPDATED_MIN_ITEM_COLS)
            .maxItemRows(UPDATED_MAX_ITEM_ROWS)
            .minItemRows(UPDATED_MIN_ITEM_ROWS)
            .maxItemArea(UPDATED_MAX_ITEM_AREA)
            .minItemArea(UPDATED_MIN_ITEM_AREA)
            .draggable(UPDATED_DRAGGABLE)
            .resizable(UPDATED_RESIZABLE);

        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDashboardLayout.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDashboardLayout))
            )
            .andExpect(status().isOk());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
        DashboardLayout testDashboardLayout = dashboardLayoutList.get(dashboardLayoutList.size() - 1);
        assertThat(testDashboardLayout.getMargin()).isEqualTo(UPDATED_MARGIN);
        assertThat(testDashboardLayout.getOuterMargin()).isEqualTo(UPDATED_OUTER_MARGIN);
        assertThat(testDashboardLayout.getOuterMarginTop()).isEqualTo(DEFAULT_OUTER_MARGIN_TOP);
        assertThat(testDashboardLayout.getUseTransformPositioning()).isEqualTo(DEFAULT_USE_TRANSFORM_POSITIONING);
        assertThat(testDashboardLayout.getMobileBreakpoint()).isEqualTo(UPDATED_MOBILE_BREAKPOINT);
        assertThat(testDashboardLayout.getUseBodyForBreakpoint()).isEqualTo(DEFAULT_USE_BODY_FOR_BREAKPOINT);
        assertThat(testDashboardLayout.getMinCols()).isEqualTo(DEFAULT_MIN_COLS);
        assertThat(testDashboardLayout.getMaxCols()).isEqualTo(UPDATED_MAX_COLS);
        assertThat(testDashboardLayout.getMinRows()).isEqualTo(DEFAULT_MIN_ROWS);
        assertThat(testDashboardLayout.getMaxRows()).isEqualTo(UPDATED_MAX_ROWS);
        assertThat(testDashboardLayout.getMaxItemCols()).isEqualTo(DEFAULT_MAX_ITEM_COLS);
        assertThat(testDashboardLayout.getMinItemCols()).isEqualTo(UPDATED_MIN_ITEM_COLS);
        assertThat(testDashboardLayout.getMaxItemRows()).isEqualTo(UPDATED_MAX_ITEM_ROWS);
        assertThat(testDashboardLayout.getMinItemRows()).isEqualTo(UPDATED_MIN_ITEM_ROWS);
        assertThat(testDashboardLayout.getMaxItemArea()).isEqualTo(UPDATED_MAX_ITEM_AREA);
        assertThat(testDashboardLayout.getMinItemArea()).isEqualTo(UPDATED_MIN_ITEM_AREA);
        assertThat(testDashboardLayout.getDefaultItemCols()).isEqualTo(DEFAULT_DEFAULT_ITEM_COLS);
        assertThat(testDashboardLayout.getDefaultItemRows()).isEqualTo(DEFAULT_DEFAULT_ITEM_ROWS);
        assertThat(testDashboardLayout.getIgnoreMarginInRow()).isEqualTo(DEFAULT_IGNORE_MARGIN_IN_ROW);
        assertThat(testDashboardLayout.getDraggable()).isEqualTo(UPDATED_DRAGGABLE);
        assertThat(testDashboardLayout.getResizable()).isEqualTo(UPDATED_RESIZABLE);
    }

    @Test
    void fullUpdateDashboardLayoutWithPatch() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();

        // Update the dashboardLayout using partial update
        DashboardLayout partialUpdatedDashboardLayout = new DashboardLayout();
        partialUpdatedDashboardLayout.setId(dashboardLayout.getId());

        partialUpdatedDashboardLayout
            .margin(UPDATED_MARGIN)
            .outerMargin(UPDATED_OUTER_MARGIN)
            .outerMarginTop(UPDATED_OUTER_MARGIN_TOP)
            .useTransformPositioning(UPDATED_USE_TRANSFORM_POSITIONING)
            .mobileBreakpoint(UPDATED_MOBILE_BREAKPOINT)
            .useBodyForBreakpoint(UPDATED_USE_BODY_FOR_BREAKPOINT)
            .minCols(UPDATED_MIN_COLS)
            .maxCols(UPDATED_MAX_COLS)
            .minRows(UPDATED_MIN_ROWS)
            .maxRows(UPDATED_MAX_ROWS)
            .maxItemCols(UPDATED_MAX_ITEM_COLS)
            .minItemCols(UPDATED_MIN_ITEM_COLS)
            .maxItemRows(UPDATED_MAX_ITEM_ROWS)
            .minItemRows(UPDATED_MIN_ITEM_ROWS)
            .maxItemArea(UPDATED_MAX_ITEM_AREA)
            .minItemArea(UPDATED_MIN_ITEM_AREA)
            .defaultItemCols(UPDATED_DEFAULT_ITEM_COLS)
            .defaultItemRows(UPDATED_DEFAULT_ITEM_ROWS)
            .ignoreMarginInRow(UPDATED_IGNORE_MARGIN_IN_ROW)
            .draggable(UPDATED_DRAGGABLE)
            .resizable(UPDATED_RESIZABLE);

        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDashboardLayout.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDashboardLayout))
            )
            .andExpect(status().isOk());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
        DashboardLayout testDashboardLayout = dashboardLayoutList.get(dashboardLayoutList.size() - 1);
        assertThat(testDashboardLayout.getMargin()).isEqualTo(UPDATED_MARGIN);
        assertThat(testDashboardLayout.getOuterMargin()).isEqualTo(UPDATED_OUTER_MARGIN);
        assertThat(testDashboardLayout.getOuterMarginTop()).isEqualTo(UPDATED_OUTER_MARGIN_TOP);
        assertThat(testDashboardLayout.getUseTransformPositioning()).isEqualTo(UPDATED_USE_TRANSFORM_POSITIONING);
        assertThat(testDashboardLayout.getMobileBreakpoint()).isEqualTo(UPDATED_MOBILE_BREAKPOINT);
        assertThat(testDashboardLayout.getUseBodyForBreakpoint()).isEqualTo(UPDATED_USE_BODY_FOR_BREAKPOINT);
        assertThat(testDashboardLayout.getMinCols()).isEqualTo(UPDATED_MIN_COLS);
        assertThat(testDashboardLayout.getMaxCols()).isEqualTo(UPDATED_MAX_COLS);
        assertThat(testDashboardLayout.getMinRows()).isEqualTo(UPDATED_MIN_ROWS);
        assertThat(testDashboardLayout.getMaxRows()).isEqualTo(UPDATED_MAX_ROWS);
        assertThat(testDashboardLayout.getMaxItemCols()).isEqualTo(UPDATED_MAX_ITEM_COLS);
        assertThat(testDashboardLayout.getMinItemCols()).isEqualTo(UPDATED_MIN_ITEM_COLS);
        assertThat(testDashboardLayout.getMaxItemRows()).isEqualTo(UPDATED_MAX_ITEM_ROWS);
        assertThat(testDashboardLayout.getMinItemRows()).isEqualTo(UPDATED_MIN_ITEM_ROWS);
        assertThat(testDashboardLayout.getMaxItemArea()).isEqualTo(UPDATED_MAX_ITEM_AREA);
        assertThat(testDashboardLayout.getMinItemArea()).isEqualTo(UPDATED_MIN_ITEM_AREA);
        assertThat(testDashboardLayout.getDefaultItemCols()).isEqualTo(UPDATED_DEFAULT_ITEM_COLS);
        assertThat(testDashboardLayout.getDefaultItemRows()).isEqualTo(UPDATED_DEFAULT_ITEM_ROWS);
        assertThat(testDashboardLayout.getIgnoreMarginInRow()).isEqualTo(UPDATED_IGNORE_MARGIN_IN_ROW);
        assertThat(testDashboardLayout.getDraggable()).isEqualTo(UPDATED_DRAGGABLE);
        assertThat(testDashboardLayout.getResizable()).isEqualTo(UPDATED_RESIZABLE);
    }

    @Test
    void patchNonExistingDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dashboardLayoutDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDashboardLayout() throws Exception {
        int databaseSizeBeforeUpdate = dashboardLayoutRepository.findAll().size();
        dashboardLayout.setId(UUID.randomUUID().toString());

        // Create the DashboardLayout
        DashboardLayoutDTO dashboardLayoutDTO = dashboardLayoutMapper.toDto(dashboardLayout);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDashboardLayoutMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dashboardLayoutDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DashboardLayout in the database
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDashboardLayout() throws Exception {
        // Initialize the database
        dashboardLayout.setId(UUID.randomUUID().toString());
        dashboardLayoutRepository.save(dashboardLayout);

        int databaseSizeBeforeDelete = dashboardLayoutRepository.findAll().size();

        // Delete the dashboardLayout
        restDashboardLayoutMockMvc
            .perform(delete(ENTITY_API_URL_ID, dashboardLayout.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DashboardLayout> dashboardLayoutList = dashboardLayoutRepository.findAll();
        assertThat(dashboardLayoutList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
