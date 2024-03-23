package org.example.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.example.Entity.EmployeeLeave;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaveManageSQLRepo {

    @PersistenceContext
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    public List<EmployeeLeave> getAllLeavesOnStatus(StringBuffer whereQuery) {

        Query query = entityManager.createNativeQuery("select * from leave_details where " + whereQuery,
                EmployeeLeave.class);

        return query.getResultList();
    }
}

