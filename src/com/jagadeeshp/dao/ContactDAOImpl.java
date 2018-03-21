package com.jagadeeshp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.jagadeeshp.vo.ContactVO;
import com.jagadeeshp.vo.GenericMessageVO;


@Repository("contactDAO1")
public class ContactDAOImpl implements IContactDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	
	
	
	@Override
	public GenericMessageVO insertContact(ContactVO contactVO){
		GenericMessageVO genericMessageVO = new GenericMessageVO();
		int affectedRows = 0; 
		
		
		/*********************Using JdbcTemplate*************************/
		
		String sqlQuery1 = "INSERT INTO CONTACTS (CONTACT_NAME, CONTACT_MOBILE, CONTACT_LANDLINE) " +
								" VALUES  (?, ?, ?) ";
		
		//affectedRows= jdbcTemplate.update(sqlQuery1, new Object[]{contactVO.getContactName(),contactVO.getContactMobile(),contactVO.getContactLandline()});

		
		/********************* /Using JdbcTemplate*************************/
		
		
		/**************Using NamedParameterJdbcTemplate*********************/
		
		
		String sqlQuery2 = "INSERT INTO CONTACTS (CONTACT_NAME, CONTACT_MOBILE, CONTACT_LANDLINE) " +
								" VALUES  (:cname, :cmobile, :cland) ";

		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
													.addValue("cname", contactVO.getContactName())
													.addValue("cmobile", contactVO.getContactMobile())
													.addValue("cland", contactVO.getContactLandline());
		
		affectedRows= namedParameterJdbcTemplate.update(sqlQuery2, sqlParameterSource);

		/************** /Using NamedParameterJdbcTemplate*********************/
		
		
		if(affectedRows>0){
			genericMessageVO.setStatus("SUCESS");
			genericMessageVO.setStatusDescription("SUCESS");
			genericMessageVO.setAffectedRows(affectedRows);
		}else{
			genericMessageVO.setStatus("FAILURE");
			genericMessageVO.setStatusDescription("FAILURE");
			genericMessageVO.setAffectedRows(affectedRows);
		}
		
		
		return genericMessageVO;
	}
	
	
	@Override
	public GenericMessageVO saveContact(ContactVO contactVO){
		GenericMessageVO genericMessageVO = new GenericMessageVO();
		int affectedRows = 0; 
		
		/**************Using NamedParameterJdbcTemplate*********************/
		
		String sqlQuery = "INSERT INTO CONTACTS (CONTACT_ID, CONTACT_NAME, CONTACT_MOBILE, CONTACT_LANDLINE) "+ 
				" VALUES  (:cid, :cname, :cmobile, :cland) "+
				" ON DUPLICATE KEY "+
						"UPDATE CONTACT_NAME=:cname, CONTACT_MOBILE=:cmobile, CONTACT_LANDLINE= :cland";
		
		SqlParameterSource sqlParameterSource = 
				new MapSqlParameterSource()
					.addValue("cid", contactVO.getContactId())
					.addValue("cname", contactVO.getContactName())
					.addValue("cmobile", contactVO.getContactMobile())
					.addValue("cland", contactVO.getContactLandline());
		
		affectedRows= namedParameterJdbcTemplate.update(sqlQuery, sqlParameterSource);
		
		/************** /Using NamedParameterJdbcTemplate*********************/
		
		
		if(affectedRows>0){
			genericMessageVO.setStatus("SUCESS");
			genericMessageVO.setStatusDescription("SUCESS");
			genericMessageVO.setAffectedRows(affectedRows);
		}else{
			genericMessageVO.setStatus("FAILURE");
			genericMessageVO.setStatusDescription("FAILURE");
			genericMessageVO.setAffectedRows(affectedRows);
		}
		
		
		return genericMessageVO;
	}
	
	@Override
	public ContactVO getContact(ContactVO contactVO){
		
		String sqlQuery = "SELECT CONTACT_ID, CONTACT_NAME, CONTACT_MOBILE, CONTACT_LANDLINE FROM CONTACTS WHERE CONTACT_ID=?";
		
		//queryForObject() must expect only only row from the ResutSet
		//if we got 0 or more than 1 row: throws 'EmptyResultDataAccessException'
		
		contactVO = jdbcTemplate.queryForObject(sqlQuery, new Object[] {contactVO.getContactId()}, new ContactRowMapper());
		
		
		return contactVO;
	}
	
	@Override
	public GenericMessageVO deleteContact(ContactVO contactVO){
		
		GenericMessageVO genericMessageVO = new GenericMessageVO();
		
		int affectedRows = 0; 
		
		String sqlQuery = "DELETE FROM CONTACTS WHERE CONTACT_ID=?";
		affectedRows = jdbcTemplate.update(sqlQuery,new Object[]{contactVO.getContactId()});
		

		if(affectedRows>0){
			genericMessageVO.setStatus("SUCESS");
			genericMessageVO.setStatusDescription("SUCESS");
			genericMessageVO.setAffectedRows(affectedRows);
		}else{
			genericMessageVO.setStatus("FAILURE");
			genericMessageVO.setStatusDescription("FAILURE");
			genericMessageVO.setAffectedRows(affectedRows);
		}
		
		
		return genericMessageVO;
	}

	@Override
	public List<ContactVO> getAllContacts() {
		
		List<ContactVO> contactVOs  = new ArrayList<ContactVO>();
		
		String sqlQuery = "SELECT CONTACT_ID, CONTACT_NAME, CONTACT_MOBILE, CONTACT_LANDLINE FROM CONTACTS";
		contactVOs =  jdbcTemplate.query(sqlQuery, new ContactRowMapper());
		
		return contactVOs;
	}
	
	
	private static final class ContactRowMapper implements RowMapper<ContactVO>{

		@Override
		public ContactVO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			
			ContactVO contactVO = new ContactVO();
			contactVO.setContactId(resultSet.getLong("CONTACT_ID"));
			contactVO.setContactName(resultSet.getString("CONTACT_NAME"));
			contactVO.setContactMobile(resultSet.getString("CONTACT_MOBILE"));
			contactVO.setContactLandline(resultSet.getString("CONTACT_LANDLINE"));
			
			return contactVO;
		}
		
	}
}
