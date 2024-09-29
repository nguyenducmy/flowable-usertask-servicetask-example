package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

     // Run once tine only to input Group , User   
     @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

        return () -> {
	    Group validators = identityService.newGroup("validators");
	    validators.setName("validators");
	    validators.setType("security-role");
	    identityService.saveGroup(validators);

	    Group approvers = identityService.newGroup("approvers");
	    approvers.setName("approvers");
	    approvers.setType("security-role");
	    identityService.saveGroup(approvers);

	    Group payers = identityService.newGroup("payers");
	    payers.setName("payers");
	    payers.setType("security-role");
	    identityService.saveGroup(payers);

	    Group vendors = identityService.newGroup("vendors");
	    vendors.setName("vendors");
	    vendors.setType("security-role");
	    identityService.saveGroup(vendors);

	    User a = identityService.newUser("validator");
	    a.setPassword("pw");
	    identityService.saveUser(a);
	    identityService.createMembership(a.getId(), validators.getId());

	    User b = identityService.newUser("approver");
	    b.setPassword("pw");
	    identityService.saveUser(b);
	    identityService.createMembership(b.getId(), approvers.getId());

	    User c = identityService.newUser("payer");
	    c.setPassword("pw");
	    identityService.saveUser(c);
	    identityService.createMembership(c.getId(), payers.getId());

	    User v = identityService.newUser("vendor");
	    v.setPassword("pw");
	    identityService.saveUser(v);
	    identityService.createMembership(v.getId(), vendors.getId());

	    User admin = identityService.newUser("admin");
	    admin.setPassword("admin");
	    identityService.saveUser(admin);
	};
    }

    }

}
