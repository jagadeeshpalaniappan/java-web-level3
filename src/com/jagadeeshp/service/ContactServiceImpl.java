package com.jagadeeshp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jagadeeshp.dao.IContactDAO;
import com.jagadeeshp.vo.ContactVO;
import com.jagadeeshp.vo.GenericMessageVO;


@Service("contactService1")
public class ContactServiceImpl implements IContactService {
	
	private IContactDAO contactDAO;
	
	@Autowired
	@Qualifier("contactDAO1")
	public void setContactDAO(IContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}
	
	@Override
	public ContactVO getContact(ContactVO contactVO){
		
		contactVO = contactDAO.getContact(contactVO);
		
		return contactVO;
	}
	
	
	@Override
	public List<ContactVO> getAllContacts() {
		
		List<ContactVO> contactVOs = null;
		
		contactVOs = contactDAO.getAllContacts();
		
		
		return contactVOs;
	}
	
	
	@Override
	public GenericMessageVO saveContact(ContactVO contactVO){
		
		return contactDAO.saveContact(contactVO);
	}
	

	
	
	@Override
	public GenericMessageVO deleteContact(ContactVO contactVO){
		
		
		return contactDAO.deleteContact(contactVO);
	}


}
