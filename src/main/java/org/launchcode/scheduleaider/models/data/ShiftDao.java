package org.launchcode.scheduleaider.models.data;

import org.launchcode.scheduleaider.models.Shift;
import org.springframework.data.repository.CrudRepository;

public interface ShiftDao extends CrudRepository<Shift, Integer> {
}
