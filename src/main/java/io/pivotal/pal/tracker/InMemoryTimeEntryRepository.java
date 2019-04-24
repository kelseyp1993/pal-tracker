package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    public Map<Long,TimeEntry> timeEntryMap = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = generateId();
        TimeEntry newTimeEntry = new TimeEntry(id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry. getDate(),
                timeEntry.getHours());
        timeEntryMap.put(id,newTimeEntry);
        return newTimeEntry;
    }

    private Long generateId() {
        return idCounter++;
    }

    @Override
    public TimeEntry find(Long timeEntryId) {
        return timeEntryMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>(timeEntryMap.values());
        return list;
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry. getDate(),
                timeEntry.getHours());
        if(timeEntryMap.get(id) != null) {
            timeEntryMap.put(id, newTimeEntry);
            return newTimeEntry;
        }else{
            return null;
        }

    }

    @Override
    public void delete(Long timeEntryId) {
        timeEntryMap.remove(timeEntryId);
    }


}
