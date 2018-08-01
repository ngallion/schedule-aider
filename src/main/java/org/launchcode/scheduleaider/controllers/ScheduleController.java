package org.launchcode.scheduleaider.controllers;

import org.launchcode.scheduleaider.forms.AddNewScheduleForm;
import org.launchcode.scheduleaider.forms.AddShiftForm;
import org.launchcode.scheduleaider.models.Schedule;
import org.launchcode.scheduleaider.models.Shift;
import org.launchcode.scheduleaider.models.data.ScheduleDao;
import org.launchcode.scheduleaider.models.data.ShiftDao;
import org.launchcode.scheduleaider.utilities.WeekGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

@Controller
@RequestMapping(value = "schedule")
public class ScheduleController {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private ShiftDao shiftDao;

    private ArrayList<String> getFutureWeeks(){

        ArrayList<String> viewableWeeks = new ArrayList<>();

        WeekGenerator thisWeek = new WeekGenerator(Locale.FRANCE);

        for (int i = 0; i < 6; i++){
            String dateOfMonday = thisWeek.getFirstDay().plusWeeks(i).getMonth().getValue() + "/" +
                    thisWeek.getFirstDay().plusWeeks(i).getDayOfMonth();

            String dateOfSunday = thisWeek.getLastDay().plusWeeks(i).getMonth().getValue() + "/" +
                    thisWeek.getLastDay().plusWeeks(i).getDayOfMonth();

            viewableWeeks.add(dateOfMonday + "-" + dateOfSunday);
        }

        return viewableWeeks;
    }

    public String getHumanReadableDate(LocalDate date) {

        String humanReadableDate = date.getDayOfWeek().toString().substring(0,3) + ", " +
                date.getMonth().toString() + " " +
                date.getDayOfMonth();

        return humanReadableDate;

    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String displayAddNewScheduleForm(Model model){

        ArrayList<String> futureWeeks = getFutureWeeks(); //gets list of possible schedule dates

        model.addAttribute("futureWeeks", futureWeeks);
        model.addAttribute("title", "Create New Schedule");
        model.addAttribute("schedule", new AddNewScheduleForm());

        return "schedule/new";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String processAddNewScheduleForm(Model model, @ModelAttribute AddNewScheduleForm schedule, Errors errors){

        Schedule newSchedule = new Schedule();
        newSchedule.setName(schedule.getName());

        WeekGenerator week = new WeekGenerator(Locale.FRANCE);
        Date startDate = Date.valueOf(week.getFirstDay().plusWeeks(schedule.getWeeksAhead()));

        newSchedule.setStartDate(startDate);

        scheduleDao.save(newSchedule);

        return "redirect:build/" + newSchedule.getId();
//        return "schedule/build/" + newSchedule.getId();
    }

    @RequestMapping(value = "build/{scheduleId}", method = RequestMethod.GET)
    public String displayAddShiftsForm(Model model, @PathVariable("scheduleId") int scheduleId) {

        Schedule schedule = scheduleDao.findOne(scheduleId);

        ArrayList<String> dateTitles = new ArrayList<>();
        ArrayList<LocalDate> dateValues = new ArrayList<>();
        ArrayList<Date> dateSQLValues = new ArrayList<>();
        LocalDate startDate = schedule.getStartDate().toLocalDate();
        for (int i = 0; i < 7; i++){
            dateTitles.add(getHumanReadableDate(startDate.plusDays(i)));
            dateValues.add(startDate.plusDays(i));
            dateSQLValues.add(Date.valueOf(startDate.plusDays(i)));
        }

        ArrayList<Iterable<Shift>> currentSchedule = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            currentSchedule.add(shiftDao.findByDate(dateSQLValues.get(i)));
        }

        model.addAttribute("title", "Add shifts to " + schedule.getName());
        model.addAttribute("dateTitles", dateTitles);
        model.addAttribute("dateValues", dateValues);
        model.addAttribute("currentSchedule", currentSchedule);
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute("shiftForm", new AddShiftForm(scheduleId));

        return "schedule/build";

    }

    @RequestMapping(value = "add-shift", method = RequestMethod.GET)
    public String displayAddShiftForm(Model model){
        model.addAttribute("title", "Add Shift");
        model.addAttribute("form", new AddShiftForm());

        return "schedule/add-shift";
    }

    @RequestMapping(value = "build/add-shift", method = RequestMethod.POST)
    public String processAddShiftForm(Model model, @ModelAttribute AddShiftForm form,
                                      @RequestParam("scheduleId") int scheduleId,
                                      @RequestParam("date") String date, Errors errors){

        Shift newShift = new Shift();

        form.setDate(date);
        newShift.setDate(form.getDateOfTypeDate());
        newShift.setStartTime(form.getStartTimeOfTypeTime());
        newShift.setEndTime(form.getEndTimeOfTypeTime());
        newShift.setSchedule(scheduleDao.findOne(scheduleId));
        newShift.setEmployeeId(1);

        shiftDao.save(newShift);

        return "redirect:" + scheduleId;
    }

}
