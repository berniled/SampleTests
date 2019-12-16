package com.ssd.companyemloyee.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssd.companyemloyee.entity.Company;
import com.ssd.companyemloyee.service.CompanyService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ActiveProfiles("test")
public class CompanyControllerTest {
	
	private static final String REQUEST_MAPPING = "/api/company";
	@Autowired
    MockMvc mockMvc;

    @MockBean
    private CompanyService companyServiceMock;
    
    @Autowired
    private CompanyService companyService;
    
    
    
    @Test
    void getAllCompanies() throws Exception {
        List<Company> companyList = new ArrayList<Company>();
        companyList.add(new Company("Company 1","Company Addr 1",true, false));
        companyList.add(new Company("Company 2","Company Addr 2",true, false));
        when(companyServiceMock.findAll()).thenReturn(companyList);

        mockMvc.perform(MockMvcRequestBuilders.get(REQUEST_MAPPING + "/companies")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$", hasSize(2)))
        .andDo(print());
    }
    
    
    @Test
    void getCompany() throws Exception {
    	Company comp = new Company("companytest","company test addr", true,false);
    	
    	when(companyServiceMock.findCompanyByName("companytest")).thenReturn(comp);
    	
    	
    	mockMvc.perform( get(REQUEST_MAPPING+"/company/{id}",comp.getName())
	    		   .contentType(MediaType.APPLICATION_JSON))
	               .andDo(print())
	               .andExpect(status().isOk())
	               //.andExpect(jsonPath("$", hasSize(1)))
	               .andExpect(jsonPath("$.name", is(comp.getName())));
	   
	}
    
    @Test
    void updateCompany() throws Exception {
    	Company comp = new Company("Testnotupdate", "Test Address not Update", true, false);
    	
    	when(companyServiceMock.findCompanyByName("Testnotupdate")).thenReturn(comp);
    	comp.setId(1L);
    	comp.setName("Test update");
    	comp.setAddress("Test Address Update");
    	comp.setActive(false);
    	comp.setDeleted(true);
    	when(companyServiceMock.updateCompany(comp)).thenReturn(comp);
    	ObjectMapper mapper = new ObjectMapper();
    	//mapper.
    	
    	mockMvc.perform( put(REQUEST_MAPPING+"/updatecompany/{id}","Testnotupdate", comp)
    			   .contentType(MediaType.APPLICATION_JSON))
	               .andDo(print())
	               .andExpect(status().isOk())
	               //.andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
	               .andExpect((ResultMatcher) jsonPath("$.name", is(comp.getName())))
	               .andExpect((ResultMatcher) jsonPath("$.address", is(comp.getAddress())))
	               .andExpect((ResultMatcher) jsonPath("$.active", is(comp.isActive())))
	               .andExpect((ResultMatcher) jsonPath("$.deleted", is(comp.isDeleted())));

    }
    
    @Test
    void deactivateOrNotCompany() throws Exception {
    	Company comp = new Company("Test Company", "Test Address", true, false);
    	
    	when(companyServiceMock.findCompanyByName("Test Company")).thenReturn(comp);
    	
    	//deactivate
    	comp.setActive(false);
    	when(companyServiceMock.deactivateCompany(comp.getName())).thenReturn(comp);
    	
    	
    	mockMvc.perform( put(REQUEST_MAPPING+"/deactivatecompany/{id}",comp.getId())
	    		   .contentType(MediaType.APPLICATION_JSON))
	               .andDo(print())
	               .andExpect(status().isOk())
	               .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
	               .andExpect((ResultMatcher) jsonPath("$[0].active", is(comp.isActive())));

    	
    	//activate
    	comp.setActive(true);
    	when(companyServiceMock.activateCompany(comp.getName())).thenReturn(comp);
    	
    	
    	mockMvc.perform( put(REQUEST_MAPPING+"/activatecompany/{id}",comp.getId())
	    		   .contentType(MediaType.APPLICATION_JSON))
	               .andDo(print())
	               .andExpect(status().isOk())
	               .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
	               .andExpect((ResultMatcher) jsonPath("$[0].active", is(comp.isActive())));

    }
    
    @Test
    void deleteOrNotCompany() throws Exception {
    	Company comp = new Company("Test Company", "Test Address", true, false);
    	
    	when(companyServiceMock.findCompanyByName("Test Company")).thenReturn(comp);
    	
    	//soft delete
    	comp.setDeleted(true);
    	when(companyServiceMock.deleteCompany(comp)).thenReturn(comp);
    	
    	
    	mockMvc.perform( put(REQUEST_MAPPING+"/deletecompany/{id}",comp.getId())
	    		   .contentType(MediaType.APPLICATION_JSON))
	               .andDo(print())
	               .andExpect(status().isOk())
	               .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
	               .andExpect((ResultMatcher) jsonPath("$[0].deleted", is(comp.isDeleted())));

    	
    	//undelete
    	comp.setDeleted(false);
    	when(companyServiceMock.undeleteCompany(comp)).thenReturn(comp);
    	
    	
    	mockMvc.perform( put(REQUEST_MAPPING+"/deletecompany/{id}",comp.getId())
	    		   .contentType(MediaType.APPLICATION_JSON))
	               .andDo(print())
	               .andExpect(status().isOk())
	               .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
	               .andExpect((ResultMatcher) jsonPath("$[0].deleted", is(comp.isActive())));

    }
    
    @Test
    void insertCompany() throws Exception {
        Company comp = new Company("Test Company", "Test Address", true, false);
    	ObjectMapper mapper = new ObjectMapper();
    	Long original_comp_id = comp.getId()==null?0L:comp.getId();
    	String content = mapper.writeValueAsString(comp);
    	when(companyServiceMock.createCompany(comp)).thenReturn(comp);
    	
    	assertNotEquals(original_comp_id, comp.getId());
    	
    	mockMvc.perform( post(REQUEST_MAPPING+"/addcompany",comp).content(content)
	    		   .contentType(MediaType.APPLICATION_JSON))
    	           .andDo(print())
                   .andExpect(status().isOk());
	               //.andExpect(jsonPath("$", hasSize(1)))
	               //.andExpect(jsonPath("$.name", is(comp.getName())));

    }
   
}
