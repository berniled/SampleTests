package com.ssd.companyemloyee.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.companyemloyee.dao.PrivilegeRepository;
import com.ssd.companyemloyee.dao.RoleRepository;
import com.ssd.companyemloyee.dao.RoleRepositoryDAO;
import com.ssd.companyemloyee.dao.UserRepository;
import com.ssd.companyemloyee.entity.Privilege;
import com.ssd.companyemloyee.entity.Role;
import com.ssd.companyemloyee.entity.User;

@Component
public class InitDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;
	 
    @Autowired
    private UserRepository userRepository;
  
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    private PrivilegeRepository privilegeRepository;
  
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
  
        if (alreadySetup)
            return;
        Privilege readPrivilege
          = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
  
        List<Privilege> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege);        
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
 
        Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setUserName("test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole.get()));
        user.setEnabled(true);
        userRepository.save(user);
 
        alreadySetup = true;
    }
 
    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
  
        Optional<Privilege> privilege = privilegeRepository.findByName(name);
        Privilege priv = privilege.orElse(new Privilege(name));
        privilegeRepository.save(priv);
        return priv;
    }
 
    @Transactional
    private Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
  
        Optional<Role> role = roleRepository.findByName(name);
        Role roleN = role.orElse(new Role(name));
        
        roleRepository.save(roleN);
        return roleN;
    }

}
