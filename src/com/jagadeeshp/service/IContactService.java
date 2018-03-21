package com.jagadeeshp.service;

import java.util.List;

import com.jagadeeshp.vo.ContactVO;
import com.jagadeeshp.vo.GenericMessageVO;

public interface IContactService {

	public GenericMessageVO saveContact(ContactVO contactVO);

	public ContactVO getContact(ContactVO contactVO);

	public GenericMessageVO deleteContact(ContactVO contactVO);

	public List<ContactVO> getAllContacts();

}