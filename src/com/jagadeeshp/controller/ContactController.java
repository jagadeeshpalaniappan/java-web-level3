package com.jagadeeshp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jagadeeshp.service.IContactService;
import com.jagadeeshp.vo.ContactVO;
import com.jagadeeshp.vo.GenericMessageVO;

@Controller
@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	@Qualifier("contactService1")
	private IContactService contactService;
	
	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}
	
	
	//Contact Home Page URL  :
	//------------------------
	//Multiple Url's mapped in a single method

	@RequestMapping(value = {"", "/", "/all","index*", "welcome*"} , method = RequestMethod.GET)
	public String getAllContacts(ModelMap modelMap)throws Exception{
		
		List<ContactVO> contactVOs = null;
		
		contactVOs = contactService.getAllContacts(); 
		
		
		modelMap.addAttribute("contactVOs",contactVOs);
		
		return "/contacts/getAllContacts";
	}
	
	
	
	@RequestMapping(value = "/{contactId}", method = RequestMethod.GET)
	public String getContact(@PathVariable Long contactId, ModelMap modelMap)throws Exception{
		
		ContactVO contactVO = new ContactVO();
		contactVO.setContactId(contactId);
		
		contactVO = contactService.getContact(contactVO); 
		
		modelMap.addAttribute("contactVOKey",contactVO);
		
		return "/contacts/getContact";
	}
	
	
	
	
	@RequestMapping("/add")
	public String addContact(ModelMap modelMap)throws Exception{
		ContactVO contactVO = null;
		
		
		modelMap.addAttribute("contactVOKey",contactVO);
		return "/contacts/addContact";
	}
	
	
	@RequestMapping("/edit/{contactId}")
	public String editContact(@PathVariable Long contactId, ModelMap modelMap)throws Exception{

		ContactVO contactVO = new ContactVO();
		contactVO.setContactId(contactId);
		
		contactVO = contactService.getContact(contactVO); 
		
		modelMap.addAttribute("contactVOKey",contactVO);
		
		modelMap.addAttribute("contactVOKey",contactVO);
		return "/contacts/editContact";
	}
	
	
	@RequestMapping(value={"/save", "edit/save"}, method = RequestMethod.POST)
	public String saveContact(@ModelAttribute("contactVO") ContactVO contactVO, BindingResult bindingResult, ModelMap modelMap)throws Exception{
		GenericMessageVO genericMessageVO = null;
		
		if (bindingResult.hasErrors()) {
	        System.out.println("BindingResult errors: " + bindingResult.toString());
	    }

		
		genericMessageVO = contactService.saveContact(contactVO);
		
		modelMap.addAttribute("genericMessageVO",genericMessageVO);
		
		return "redirect:/contact/all";
	}
	
	
	
	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable Long contactId, @RequestParam("confirm") String confirm, ModelMap modelMap)throws Exception{
		GenericMessageVO genericMessageVO = null;
		
		ContactVO contactVO = new ContactVO();
		contactVO.setContactId(contactId);
		
		if("Yes".equalsIgnoreCase(confirm)){
			genericMessageVO = contactService.deleteContact(contactVO);
		}
		
		modelMap.addAttribute("genericMessageVO",genericMessageVO);
		
		return "redirect:/contact/all";
	}

}
