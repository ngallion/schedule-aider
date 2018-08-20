package org.launchcode.scheduleaider.models.data;

import org.launchcode.scheduleaider.models.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@Repository
@Transactional
public interface ShiftDao extends CrudRepository<Shift, Integer> {
    Iterable<Shift> findByDate(LocalDate date);
    Iterable<Shift> findByDateAndScheduleId(LocalDate date, int id);
}
