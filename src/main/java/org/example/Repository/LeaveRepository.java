package org.example.Repository;

import org.example.Entity.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "leaveRepository")
public interface LeaveRepository  extends JpaRepository<EmployeeLeave, Long> {

    List<EmployeeLeave> findByName(String name);

    @Query(nativeQuery = true, value = "select array_to_json(array_agg(row_to_json(t))) from (select name||' on leave' as title,to_char(from_date,'YYYY-MM-DD') as start,to_char(to_date,'YYYY-MM-DD') as end from leave_details) as t;")
    public Object getAllLeavesAsJsonArray();

    @Query(nativeQuery = true, value = "select * from leave_details where active=true")
    public List<EmployeeLeave> getAllActiveLeaves();

    @Query(nativeQuery = true, value = "select * from leave_details where name=? order by id desc")
    public List<EmployeeLeave> getAllLeavesOfUser(String username);


}
