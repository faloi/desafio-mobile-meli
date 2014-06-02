
package com.cuantocuesta.domain.meli.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

public class Example {

    private String site_id;
    private String query;
    private Paging paging;
    private List<Result> results = new ArrayList<Result>();
    private List<Object> secondary_results = new ArrayList<Object>();
    private List<Object> related_results = new ArrayList<Object>();
    private Sort sort;
    private List<Available_sort> available_sorts = new ArrayList<Available_sort>();
    private List<Object> filters = new ArrayList<Object>();
    private List<Available_filter> available_filters = new ArrayList<Available_filter>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Object> getSecondary_results() {
        return secondary_results;
    }

    public void setSecondary_results(List<Object> secondary_results) {
        this.secondary_results = secondary_results;
    }

    public List<Object> getRelated_results() {
        return related_results;
    }

    public void setRelated_results(List<Object> related_results) {
        this.related_results = related_results;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public List<Available_sort> getAvailable_sorts() {
        return available_sorts;
    }

    public void setAvailable_sorts(List<Available_sort> available_sorts) {
        this.available_sorts = available_sorts;
    }

    public List<Object> getFilters() {
        return filters;
    }

    public void setFilters(List<Object> filters) {
        this.filters = filters;
    }

    public List<Available_filter> getAvailable_filters() {
        return available_filters;
    }

    public void setAvailable_filters(List<Available_filter> available_filters) {
        this.available_filters = available_filters;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
