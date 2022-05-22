package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Contact;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ContactService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	DepartmentRepository deptRepo;
	
	@Autowired
	ProjectRepository projRepo;
	
	@RequestMapping(path = {"/login","/"})
    public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("user", new User());
        return mav;
    }
    
    @RequestMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());       
        return "register";
    }
    
    @RequestMapping(value = "/process_register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute("user") User user) {
    	User u = userService.validateUsername(user.getUsername());
    	if(Objects.nonNull(u))
    		return "redirect:/register/";
    	userService.save(user);
        return "register_success";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user) {
    	User oauthUser = userService.validateUser(user.getUsername(), user.getPassword());
    	if(Objects.nonNull(oauthUser))
    		return "redirect:/index";
    	else
    		return "user_not_found";
    }
    
    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logout(HttpServletRequest request,HttpServletResponse response)
    {
        return "redirect:/login";
    }
    
    @RequestMapping("/index")
    public String showIndex(Model model) {     
        return "index";
    }
    
    @RequestMapping(path = {"/management","/search","/searchbysalary","/searchbydepartment"})
    public String viewEmployeeHomePage(Model model, String name, String designation, Long salary, Long deptId) {  	
    	return findPaginated(1, model,name,designation,salary,deptId,"employeeId","asc");
    }
    
    @RequestMapping(path = {"/departments","/searchbydeptname"})
    public String viewDepartments(Model model, String deptName) {
    	if(deptName!=null) {
    		List<Department> listDept = deptRepo.findByDeptName(deptName);
    		if(listDept.size()== 0)
    			listDept = deptRepo.findAll();
        	model.addAttribute("listDept", listDept);
        	return "dept";
    	}
    	List<Department> listDept = deptRepo.findAll();
    	model.addAttribute("listDept", listDept);
    	return "dept";
    }
    
    @RequestMapping(path = {"/projects","/searchbyprojectname"})
    public String viewProjects(Model model, String projectName) {
    	if(projectName!=null) {
    		List<Project> listProj = projRepo.findByProjectName(projectName);
    		if(listProj.size()==0)
    			listProj = projRepo.findAll();
    		model.addAttribute("listProj", listProj);
        	return "project";
    	}
    	List<Project> listProj = projRepo.findAll();
    	model.addAttribute("listProj", listProj);
    	return "project";
    }
    
	@RequestMapping("/management/add")
    public String addEmployee(Model model){
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
    }
	
	@RequestMapping("/management/addcontact")
    public String addEmployeeContact(Model model){
		Contact contact = new Contact();
		model.addAttribute("contact",contact);
		return "new_contact";
    }
	
	@RequestMapping(value = "/management/save", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
	    service.save(employee);	     
	    return "redirect:/management/addcontact";
	}
	
	@RequestMapping(value = "/management/saveUpdate", method = RequestMethod.POST)
	public String saveEmployeeUpdate(@ModelAttribute("employee") Employee employee) {
	    service.save(employee);	     
	    return "redirect:/management/";
	}
	
	@RequestMapping(value = "/management/savecontact", method = RequestMethod.POST)
	public String saveEmployeeContact(@ModelAttribute("contact") Contact contact) {
		contactService.save(contact);
		return "redirect:/management/";
	}
	
	/*@RequestMapping("/management/contact/{empId}")
	public ModelAndView viewEmployeeContact(@PathVariable(name = "empId") long empId) {
		ModelAndView mav = new ModelAndView("view_contact");
		ResponseEntity<Contact> responseEntity = new RestTemplate().getForEntity("http://localhost:8092/contact/employee/{employeeId}", Contact.class, empId);
		Contact contact = responseEntity.getBody();
	    mav.addObject("contact",contact);
	    return mav;
	}*/
	@RequestMapping("/management/contact/{empId}")
	public ModelAndView viewEmployeeContact(@PathVariable(name = "empId") long empId) {
		ModelAndView mav = new ModelAndView("view_contact");
		Contact contact = contactService.getContactByEmployeeId(empId);
		 mav.addObject("contact",contact);
		    return mav;
		}
	
	
	@RequestMapping("/management/view/{empId}")
	public ModelAndView viewEmployee(@PathVariable(name = "empId") long empId) {
		ModelAndView mav = new ModelAndView("view_employee");
	    Employee employee = service.getEmployeeById(empId);
	    mav.addObject("employee",employee);	     
	    return mav;
	}
	
	@RequestMapping("/management/update/{empId}")
	public ModelAndView showEditEmployeePage(@PathVariable(name = "empId") long empId) {
	    ModelAndView mav = new ModelAndView("update_employee");
	    Employee employee = service.getEmployeeById(empId);
	    mav.addObject("employee",employee);     
	    return mav;
	}
	
	@RequestMapping("/management/delete/{empId}")
	public String deleteEmployee(@PathVariable(name = "empId") long empId) {
	    service.deleteEmployee(empId);
	    return "redirect:/management/";
	}
	
	@RequestMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable("pageNo") int pageNo, Model model, 
			String name, String designation, Long salary, Long deptId, @RequestParam(value = "sortField", defaultValue="employeeId") String sortField,
	        @RequestParam(value = "sortDir", defaultValue="asc") String sortDir) {
		int pageSize=3;
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, 
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
		Page<Employee> page;
		if(name!=null && (designation==null || designation.equals("")) && salary==null && deptId==null) {
    		page = empRepo.findByName(name,pageable);
    	}
    	else if((name==null || name.equals("")) && designation!=null && salary==null && deptId==null) {
    		page = empRepo.findByDesignation(designation,pageable);
    	}
    	else if(name!=null && designation!=null && salary==null && deptId==null) {
    		page = empRepo.findByNameAndDesignation(name, designation,pageable);
    	}
    	else if(name==null && designation==null && salary!=null && deptId==null) {
    		page = empRepo.findBySalary(salary,pageable);
    	}
    	else if(name==null && designation==null && salary==null && deptId!=null) {
    		page = empRepo.findByDeptId(deptId,pageable);
    	}
    	else {
    		page = empRepo.findAll(pageable);
    	}
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "home_employee";

	}
	
}
