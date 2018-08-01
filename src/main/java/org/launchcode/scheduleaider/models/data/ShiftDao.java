package org.launchcode.scheduleaider.models.data;

import org.launchcode.scheduleaider.models.Shift;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface ShiftDao extends CrudRepository<Shift, Integer> {
    Iterable<Shift> findByDate(Date date);
}
