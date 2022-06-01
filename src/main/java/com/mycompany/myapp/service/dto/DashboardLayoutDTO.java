package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DashboardLayout} entity.
 */
public class DashboardLayoutDTO implements Serializable {

    private String id;

    private Integer margin;

    private Boolean outerMargin;

    private Integer outerMarginTop;

    private Boolean useTransformPositioning;

    private Integer mobileBreakpoint;

    private Boolean useBodyForBreakpoint;

    private Integer minCols;

    private Integer maxCols;

    private Integer minRows;

    private Integer maxRows;

    private Integer maxItemCols;

    private Integer minItemCols;

    private Integer maxItemRows;

    private Integer minItemRows;

    private Integer maxItemArea;

    private Integer minItemArea;

    private Integer defaultItemCols;

    private Integer defaultItemRows;

    private Boolean ignoreMarginInRow;

    private Boolean draggable;

    private Boolean resizable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Boolean getOuterMargin() {
        return outerMargin;
    }

    public void setOuterMargin(Boolean outerMargin) {
        this.outerMargin = outerMargin;
    }

    public Integer getOuterMarginTop() {
        return outerMarginTop;
    }

    public void setOuterMarginTop(Integer outerMarginTop) {
        this.outerMarginTop = outerMarginTop;
    }

    public Boolean getUseTransformPositioning() {
        return useTransformPositioning;
    }

    public void setUseTransformPositioning(Boolean useTransformPositioning) {
        this.useTransformPositioning = useTransformPositioning;
    }

    public Integer getMobileBreakpoint() {
        return mobileBreakpoint;
    }

    public void setMobileBreakpoint(Integer mobileBreakpoint) {
        this.mobileBreakpoint = mobileBreakpoint;
    }

    public Boolean getUseBodyForBreakpoint() {
        return useBodyForBreakpoint;
    }

    public void setUseBodyForBreakpoint(Boolean useBodyForBreakpoint) {
        this.useBodyForBreakpoint = useBodyForBreakpoint;
    }

    public Integer getMinCols() {
        return minCols;
    }

    public void setMinCols(Integer minCols) {
        this.minCols = minCols;
    }

    public Integer getMaxCols() {
        return maxCols;
    }

    public void setMaxCols(Integer maxCols) {
        this.maxCols = maxCols;
    }

    public Integer getMinRows() {
        return minRows;
    }

    public void setMinRows(Integer minRows) {
        this.minRows = minRows;
    }

    public Integer getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }

    public Integer getMaxItemCols() {
        return maxItemCols;
    }

    public void setMaxItemCols(Integer maxItemCols) {
        this.maxItemCols = maxItemCols;
    }

    public Integer getMinItemCols() {
        return minItemCols;
    }

    public void setMinItemCols(Integer minItemCols) {
        this.minItemCols = minItemCols;
    }

    public Integer getMaxItemRows() {
        return maxItemRows;
    }

    public void setMaxItemRows(Integer maxItemRows) {
        this.maxItemRows = maxItemRows;
    }

    public Integer getMinItemRows() {
        return minItemRows;
    }

    public void setMinItemRows(Integer minItemRows) {
        this.minItemRows = minItemRows;
    }

    public Integer getMaxItemArea() {
        return maxItemArea;
    }

    public void setMaxItemArea(Integer maxItemArea) {
        this.maxItemArea = maxItemArea;
    }

    public Integer getMinItemArea() {
        return minItemArea;
    }

    public void setMinItemArea(Integer minItemArea) {
        this.minItemArea = minItemArea;
    }

    public Integer getDefaultItemCols() {
        return defaultItemCols;
    }

    public void setDefaultItemCols(Integer defaultItemCols) {
        this.defaultItemCols = defaultItemCols;
    }

    public Integer getDefaultItemRows() {
        return defaultItemRows;
    }

    public void setDefaultItemRows(Integer defaultItemRows) {
        this.defaultItemRows = defaultItemRows;
    }

    public Boolean getIgnoreMarginInRow() {
        return ignoreMarginInRow;
    }

    public void setIgnoreMarginInRow(Boolean ignoreMarginInRow) {
        this.ignoreMarginInRow = ignoreMarginInRow;
    }

    public Boolean getDraggable() {
        return draggable;
    }

    public void setDraggable(Boolean draggable) {
        this.draggable = draggable;
    }

    public Boolean getResizable() {
        return resizable;
    }

    public void setResizable(Boolean resizable) {
        this.resizable = resizable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardLayoutDTO)) {
            return false;
        }

        DashboardLayoutDTO dashboardLayoutDTO = (DashboardLayoutDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dashboardLayoutDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardLayoutDTO{" +
            "id='" + getId() + "'" +
            ", margin=" + getMargin() +
            ", outerMargin='" + getOuterMargin() + "'" +
            ", outerMarginTop=" + getOuterMarginTop() +
            ", useTransformPositioning='" + getUseTransformPositioning() + "'" +
            ", mobileBreakpoint=" + getMobileBreakpoint() +
            ", useBodyForBreakpoint='" + getUseBodyForBreakpoint() + "'" +
            ", minCols=" + getMinCols() +
            ", maxCols=" + getMaxCols() +
            ", minRows=" + getMinRows() +
            ", maxRows=" + getMaxRows() +
            ", maxItemCols=" + getMaxItemCols() +
            ", minItemCols=" + getMinItemCols() +
            ", maxItemRows=" + getMaxItemRows() +
            ", minItemRows=" + getMinItemRows() +
            ", maxItemArea=" + getMaxItemArea() +
            ", minItemArea=" + getMinItemArea() +
            ", defaultItemCols=" + getDefaultItemCols() +
            ", defaultItemRows=" + getDefaultItemRows() +
            ", ignoreMarginInRow='" + getIgnoreMarginInRow() + "'" +
            ", draggable='" + getDraggable() + "'" +
            ", resizable='" + getResizable() + "'" +
            "}";
    }
}
