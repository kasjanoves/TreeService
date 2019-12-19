package treeservice.repo;

import java.util.List;

import treeservice.domain.CalcRequestResult;

public interface CalcRequestRepository {

    void save(CalcRequestResult result);
    
    List<CalcRequestResult> getAll();
}
