package org.launchcode.scheduleaider.controllers;

import org.launchcode.scheduleaider.forms.AddNewScheduleForm;
import org.launchcode.scheduleaider.forms.AddShiftForm;
import org.launchcode.scheduleaider.models.Schedule;
import org.launchcode.scheduleaider.models.Shift;
import org.launchcode.scheduleaider.utilities.WeekGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

@Controller
@RequestMapping(value = "schedule")
public class ScheduleController {

    ArrayList<Shift> allShifts = new ArrayList<>();

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

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String displayAddNewScheduleForm(Model model){

        ArrayList<String> futureWeeks = getFutureWeeks();

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

        model.addAttribute("title", "Add to Schedule");
        model.addAttribute("schedule", newSchedule);

        return "schedule/add-shifts";
    }

    @RequestMapping(value = "add-shift", method = RequestMethod.GET)
    public String displayAddShiftForm(Model model){
        model.addAttribute("title", "Add Shift");
        model.addAttribute("form", new AddShiftForm());
        model.addAttribute("allShifts", allShifts);

        return "schedule/add-shift";
    }

    @RequestMapping(value = "add-shift", method = RequestMethod.POST)
    public String processAddShiftForm(Model model, @ModelAttribute AddShiftForm form, Errors errors){

        Shift newShift = new Shift();

        newShift.setDate(form.getDateOfTypeDate());
        newShift.setStartTime(form.getStartTimeOfTypeTime());
        newShift.setEndTime(form.getEndTimeOfTypeTime());
        newShift.setEmployeeId(1);

        allShifts.add(newShift);

        return "redirect:add-shift";
    }

}
