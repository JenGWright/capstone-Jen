package org.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString

public class EmployeeLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;

    @NotNull(message = "Name must not be null")
    @Column(name = "name")
    public String name;

    @Column(name = "manager_name")
    public String managerName;

    @NotNull(message = "Please provide a start date")
    @Column (name = "from_date")
    private Date fromDate;

    @NotNull(message = "Please provide an end date")
    @Column(name ="to_date")
    private Date toDate;

    @NotEmpty(message = "Please select type of leave")
    @Column(name = "leave_type")
    private String leaveType;

    @Column(name = "duration")
    private int duration;

    @Column(name = "accept_reject_flag")
    private boolean acceptRejectFlag;

    @Column(name = "active")
    private boolean active;




}
