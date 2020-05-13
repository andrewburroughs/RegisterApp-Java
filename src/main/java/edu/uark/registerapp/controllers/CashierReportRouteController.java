package edu.uark.registerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.employees.SortedEmployeesQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Employee;

@Controller
@RequestMapping(value = "/cashierReport")
public class CashierReportRouteController {
    @RequestMapping(value = "/")
    public ModelAndView showDescendingQuantity(){
        ModelAndView modelAndView = 
            new ModelAndView(ViewNames.CASHIER_REPORT.getViewName());
        try{
            modelAndView.addObject(
                ViewModelNames.EMPLOYEES.getValue(),
                this.sortedEmployeesQuery.execute());
        } catch (final Exception e){
            modelAndView.addObject(
                ViewModelNames.ERROR_MESSAGE.getValue(),
                e.getMessage());
                modelAndView.addObject(
                    ViewModelNames.EMPLOYEE.getValue(),
                    (new Employee[0]));
        }
        
        return modelAndView;
    }

    @RequestMapping(value = "/{config}")
    public ModelAndView show(@PathVariable final String config){
        ModelAndView modelAndView = 
            new ModelAndView(ViewNames.CASHIER_REPORT.getViewName());
        try{
            modelAndView.addObject(
                ViewModelNames.EMPLOYEES.getValue(),
                this.sortedEmployeesQuery.execute(config));
        } catch (final Exception e){
            modelAndView.addObject(
                ViewModelNames.ERROR_MESSAGE.getValue(),
                e.getMessage());
                modelAndView.addObject(
                    ViewModelNames.EMPLOYEE.getValue(),
                    (new Employee[0]));
        }
        
        return modelAndView;
    }

    @Autowired
    private SortedEmployeesQuery sortedEmployeesQuery;
}