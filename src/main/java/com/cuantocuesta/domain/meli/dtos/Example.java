
package com.cuantocuesta.domain.meli.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {
    public String site_id;
    public String query;
    public Paging paging;
    public List<Result> results = new ArrayList<Result>();
    public List<Object> secondary_results = new ArrayList<Object>();
    public List<Object> related_results = new ArrayList<Object>();
    public Sort sort;
    public List<Available_sort> available_sorts = new ArrayList<Available_sort>();
    public List<Object> filters = new ArrayList<Object>();
    public List<Available_filter> available_filters = new ArrayList<Available_filter>();
    public Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
