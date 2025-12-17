package com.eazybytes.eazyschool.controller;


import org.springframework.ui.Model;

import com.eazybytes.eazyschool.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {

    @GetMapping("/holidays")
    public String displayHolidays(@RequestParam(required = false) boolean festival,
                                  @RequestParam(required = false) boolean federal,  Model model){
        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);
        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1", "New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31", "Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24", "Diwali", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25", "Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Aug 15", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sept 5", "Teachers Day", Holiday.Type.FEDERAL),
                new Holiday(" 2 Oct", "Mahatma Gandhi Jayanti", Holiday.Type.FEDERAL),
                new Holiday(" Nov 14", "Children's Day", Holiday.Type.FEDERAL)
        );

        Holiday.Type[] types = Holiday.Type.values();
        for(Holiday.Type type : types){
            model.addAttribute(type.toString(),
                    holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }

        return "holidays";
    }
}
