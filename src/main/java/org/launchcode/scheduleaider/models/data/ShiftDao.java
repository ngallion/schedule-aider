package org.launchcode.scheduleaider.models.data;

import org.launchcode.scheduleaider.models.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;

@Repository
@Transactional
public interface ShiftDao extends CrudRepository<Shift, Integer> {
    Iterable<Shift> findByDate(Date date);
    Iterable<Shift> findByDateAndScheduleId(Date date, int id);
    Iterable<Shift> findByScheduleId(int id);
}
