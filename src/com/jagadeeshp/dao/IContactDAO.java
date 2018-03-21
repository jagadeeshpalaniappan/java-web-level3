package com.jagadeeshp.dao;

import java.util.List;

import com.jagadeeshp.vo.ContactVO;
import com.jagadeeshp.vo.GenericMessageVO;

public interface IContactDAO {

	public GenericMessageVO saveContact(ContactVO contactVO);

	public ContactVO getContact(ContactVO contactVO);

	public GenericMessageVO deleteContact(ContactVO contactVO);

	public List<ContactVO> getAllContacts();

	public GenericMessageVO insertContact(ContactVO contactVO);

}