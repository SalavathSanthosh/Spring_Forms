package com.nit.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nit.demo.constants.AppConstants;
import com.nit.demo.entity.UserEntity;
import com.nit.demo.pojo.User;
import com.nit.demo.properties.AppProperties;
import com.nit.demo.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private AppProperties AppProps;
	
	@Autowired
	private UserRepository repo;
	
	public UserController() {
		System.out.println("***UserController.UserController()***");
	}
	
	@RequestMapping(value=AppConstants.REQ_URL)
	public String loadForm(Model model)
	{
		//create form binding object
		User userObj=new User();
		
		List<String> countriesList=new ArrayList();
		
		//Add countries to the list
		countriesList.add("INDIA");
		countriesList.add("USA");
		countriesList.add("CHINA");
		countriesList.add("GERMANY");
		countriesList.add("CANADA");
		countriesList.add("JAPAN");
		
		//add objects to the model attribute
		model.addAttribute("countries", countriesList);
		model.addAttribute("user", userObj);
		
		//logical view name
		return AppConstants.REG_USER;
		
	}
	
	
	@RequestMapping(value=AppConstants.REQ_URL, method=RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, Model model)
	{
		System.out.println(user);
		
		UserEntity entity=new UserEntity();
		//copy user to the entity
		BeanUtils.copyProperties(user, entity);
		entity=repo.save(entity);
		System.out.println(entity);
		
		if(entity.getId()!=0)
		{
			model.addAttribute("successMsg", "Registration Successfull");
		}
		else
		{
			model.addAttribute("successMsg", "Registration failed");
		}
						
		//logical view name
		return AppConstants.REG_USER;
	}
	
	@RequestMapping(value="/displayUsers")
	public String displayUsers(@RequestParam("pn") Integer currentPage,  Model model)
	{
		int pageSize=4;
		PageRequest pageReq=PageRequest.of(currentPage-1, pageSize);
		System.out.println(pageReq);
		
		Page<UserEntity> pageData=repo.findAll(pageReq);
		
		List<UserEntity> userEntity=pageData.getContent();
		int totalPage=pageData.getTotalPages();
		
		/* Iterable<UserEntity> userEntity=repo.findAll(pageReq); */
		
		List<User> listUser=new ArrayList();
		for(UserEntity entity:userEntity)
		{
			User user=new User();
			
			//copy entity obj to the user
			BeanUtils.copyProperties(entity, user);
			listUser.add(user);
			System.out.println(user);	
		}
		
		model.addAttribute("tp", totalPage);
		model.addAttribute("cp", currentPage);
		model.addAttribute("UserList", listUser);
		
		//logical view name
		return "displayUsers";
		
	}	
	
	@RequestMapping(value="/deleteUser")
	public String deleteUser(@RequestParam("uid") Integer id)
	{
		repo.deleteById(id);
		
		return "redirect:displayUsers?pn=1";
	}
	
	@RequestMapping(value="/editUser")
	public String editeUser(@RequestParam("uid") Integer id, Model model)
	{
		User user=new User();
		
		Optional<UserEntity> userOpt=repo.findById(id);
		
		if(userOpt.isPresent()) {
			
			UserEntity entity=userOpt.get();
			
			//cpoy entity objects to the user
			BeanUtils.copyProperties(entity, user);			
		}
		
		loadForm(model);
		model.addAttribute("editUser", user);
				
		return "updateUser";
	}
	
	@RequestMapping(value="/updateUser")
	public String updateUser(@ModelAttribute("editUser") User user, Model model, @RequestParam("uid") Integer id)
	{
		UserEntity entity=new UserEntity();
		
		BeanUtils.copyProperties(user, entity);
		entity.setId(id);
		repo.save(entity);
		loadForm(model);
		model.addAttribute("updateMsg", "Updated Successfully ");
				
		return "updateUser";
	}
	
	
	/**
	 * get All Users Emails
	 * @return List<String>
	 */
	@RequestMapping(value="/getEmails")
	public @ResponseBody List<String> getAllEmails(){
		return repo.findAllEmails();
	}
	
	@RequestMapping(value="/getEmailById")
	public @ResponseBody String getUserEmailById(@RequestParam("uid") Integer id) {
		return repo.findUserEmailById(id);

}
}
